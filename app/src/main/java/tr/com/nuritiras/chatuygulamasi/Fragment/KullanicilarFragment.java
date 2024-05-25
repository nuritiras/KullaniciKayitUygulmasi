package tr.com.nuritiras.chatuygulamasi.Fragment;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import tr.com.nuritiras.chatuygulamasi.Adapter.KullaniciAdapter;
import tr.com.nuritiras.chatuygulamasi.Model.Kullanici;
import tr.com.nuritiras.chatuygulamasi.R;

public class KullanicilarFragment extends Fragment {

    RecyclerView mRecyclerView;
    View v;
    KullaniciAdapter mAdepter;
    ArrayList<Kullanici> mKullaniciList;
    Kullanici mKullanici;

    FirebaseUser mUser;
    FirebaseFirestore mFireStore;
    Query mQery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.fragment_kullanicilar, container, false);
        mUser= FirebaseAuth.getInstance().getCurrentUser();
        mFireStore=FirebaseFirestore.getInstance();

        mKullaniciList=new ArrayList<>();

        mRecyclerView=v.findViewById(R.id.kullaniciler_fragment_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext(),LinearLayoutManager.VERTICAL,false));

        mQery=mFireStore.collection("Kullanicilar");
        mQery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(v.getContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(value !=null){
                    mKullaniciList.clear();

                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        mKullanici=snapshot.toObject(Kullanici.class);

                        assert mKullanici !=null;
                        mKullaniciList.add(mKullanici);
                    }

                    mRecyclerView.addItemDecoration(new LinearDecoration(20,mKullaniciList.size()));
                    mAdepter = new KullaniciAdapter(mKullaniciList,v.getContext());
                    mRecyclerView.setAdapter(mAdepter);
                }
            }
        });

        return v;
    }

    class LinearDecoration extends RecyclerView.ItemDecoration{
        int boslukMikatari;
        int veriSayisi;

        public LinearDecoration(int boslukMikatari, int veriSayisi) {
            this.boslukMikatari = boslukMikatari;
            this.veriSayisi = veriSayisi;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int pos=parent.getChildAdapterPosition(view);
            if(pos != (veriSayisi-1))
                outRect.bottom=boslukMikatari;
        }
    }
}