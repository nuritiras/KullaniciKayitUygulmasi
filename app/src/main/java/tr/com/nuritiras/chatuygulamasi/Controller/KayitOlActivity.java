package tr.com.nuritiras.chatuygulamasi.Controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import tr.com.nuritiras.chatuygulamasi.Model.Kullanici;
import tr.com.nuritiras.chatuygulamasi.databinding.ActivityKayitOlBinding;

public class KayitOlActivity extends AppCompatActivity {
    ProgressDialog mProgress;
    ActivityKayitOlBinding binding;
    FirebaseFirestore mFireStore;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Kullanici mKullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityKayitOlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mFireStore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
    }

    public void init(){

    }
    public void btnKayitOl(View view) {
        String txtIsim=binding.kayitOlEditIsim.getText().toString();
        String txtEmail=binding.kayitOlEditEmail.getText().toString();
        String txtSifre=binding.kayitOlEditSifre.getText().toString();
        String txtSifreTekrar=binding.kayitOlEditSifreTekrar.getText().toString();

        if(!txtIsim.isEmpty()){
            if(!txtEmail.isEmpty()){
                if(!txtSifre.isEmpty()){
                    if(!txtSifreTekrar.isEmpty()){
                        if(txtSifre.equals(txtSifreTekrar)){
                            mProgress=new ProgressDialog(this);
                            mProgress.setTitle("Kayıt Olunuyor");
                            mProgress.show();;

                            mAuth.createUserWithEmailAndPassword(txtEmail,txtSifre)
                                    .addOnCompleteListener(KayitOlActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                mUser=mAuth.getCurrentUser();
                                                if(mUser !=null){
                                                    mKullanici= new Kullanici(txtIsim,txtEmail,mUser.getUid(),"default");
                                                    mFireStore.collection("Kullanicilar").document(mUser.getUid())
                                                            .set(mKullanici)
                                                            .addOnCompleteListener(KayitOlActivity.this, new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if(task.isSuccessful()){
                                                                        progressAyar();
                                                                        Toast.makeText(KayitOlActivity.this, "Başarı ile kayıt oldunuz.", Toast.LENGTH_SHORT).show();
                                                                        finish();
                                                                        startActivity(new Intent(KayitOlActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                                    } else {
                                                                        progressAyar();
                                                                        Snackbar.make(binding.main,task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                }
                                            }   else {
                                                        progressAyar();
                                                        Snackbar.make(binding.main,task.getException().getMessage(),Snackbar.LENGTH_SHORT).show();
                                                    }
                                        }
                                    });
                        } else Snackbar.make(binding.main,"Şifreler uyuşmuyor",Snackbar.LENGTH_SHORT).show();
                    } else binding.kayitOlInputSifreTekrar.setError("Lütfen geçerli bir şifre belirleyiniz.");
                } else binding.kayitOlInputSifre.setError("Lütfen geçerli bir şifre belirleyiniz.");
            } else binding.kayitOlInputEmail.setError("Lütfen geçerli bir E-Posta adresi giriniz.");
        } else binding.kayitOlInputIsim.setError("Lütfen geçerli bir isim bilgisi giriniz.");
    }

    private void progressAyar(){
        if(mProgress.isShowing()){
            mProgress.dismiss();
        }
    }

}