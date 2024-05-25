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
import tr.com.nuritiras.chatuygulamasi.R;
import tr.com.nuritiras.chatuygulamasi.databinding.ActivityGirisYapBinding;
import tr.com.nuritiras.chatuygulamasi.databinding.ActivityKayitOlBinding;

public class GirisYapActivity extends AppCompatActivity {
    ProgressDialog mProgress;
    ActivityGirisYapBinding binding;
    FirebaseFirestore mFireStore;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Kullanici mKullanici;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGirisYapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mFireStore=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        if(mUser !=null) {
            finish();
            startActivity(new Intent(GirisYapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
        
    }

    public void btnGirisYap(View view) {
        String txtEmail=binding.girisYapEditEmail.getText().toString();
        String txtSifre=binding.girisYapEditSifre.getText().toString();

        if(!txtEmail.isEmpty()){
            if(!txtSifre.isEmpty()){

                mProgress=new ProgressDialog(this);
                mProgress.setTitle("Giriş Yapılıyor");
                mProgress.show();;

                mAuth.signInWithEmailAndPassword(txtEmail,txtSifre)
                        .addOnCompleteListener(GirisYapActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    progressAyar();
                                    Toast.makeText(GirisYapActivity.this, "Başarı ile giriş yaptınız.", Toast.LENGTH_SHORT).show();
                                    finish();
                                    startActivity(new Intent(GirisYapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                } else
                                    {
                                        progressAyar();
                                        Snackbar.make(binding.main, task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                            }
                        });
            } else binding.girisYapInputSifre.setError("Lütfen geçerli bir şifre belirleyiniz.");
        } else binding.girisYapInputEmail.setError("Lütfen geçerli bir E-Posta adresi giriniz.");
    }

    public void btnGitKayitOl(View view) {
        startActivity(new Intent(GirisYapActivity.this, KayitOlActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    private void progressAyar(){
        if(mProgress.isShowing()){
            mProgress.dismiss();
        }
    }

}