package tr.com.nuritiras.chatuygulamasi.Controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import tr.com.nuritiras.chatuygulamasi.Fragment.KullanicilarFragment;
import tr.com.nuritiras.chatuygulamasi.Fragment.MesajlarFragment;
import tr.com.nuritiras.chatuygulamasi.Fragment.ProfilFragment;
import tr.com.nuritiras.chatuygulamasi.R;
import tr.com.nuritiras.chatuygulamasi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    KullanicilarFragment kullanicilarFragment;
    MesajlarFragment mesajlarFragment;
    ProfilFragment profilFragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        kullanicilarFragment=new KullanicilarFragment();
        mesajlarFragment=new MesajlarFragment();
        profilFragment=new ProfilFragment();

        fragmentiAyarla(kullanicilarFragment);

        binding.mainActivityBottomView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_nav_ic_people) {
                    fragmentiAyarla(kullanicilarFragment);
                    return true;
                }
                else if (item.getItemId() == R.id.bottom_nav_ic_message) {
                    fragmentiAyarla(mesajlarFragment);
                    return true;
                } else  if (item.getItemId() == R.id.bottom_nav_ic_profile) {
                    fragmentiAyarla(profilFragment);
                    return true;
                } else return false;
            }
        });

    }

    private void fragmentiAyarla(Fragment fragment){
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity_frameLayout,fragment);
        transaction.commit();
    }
}