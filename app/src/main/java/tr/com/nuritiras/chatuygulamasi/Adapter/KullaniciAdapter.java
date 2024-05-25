package tr.com.nuritiras.chatuygulamasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tr.com.nuritiras.chatuygulamasi.Model.Kullanici;
import tr.com.nuritiras.chatuygulamasi.R;

public class KullaniciAdapter extends RecyclerView.Adapter<KullaniciAdapter.KullaniciHolder> {
    private ArrayList<Kullanici> mKullaniciList;
    private Context mContext;
    View v;
    private Kullanici mKullanici;

    public KullaniciAdapter(ArrayList<Kullanici> mKullaniciList, Context mContext) {
        this.mKullaniciList = mKullaniciList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public KullaniciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v= LayoutInflater.from(mContext).inflate(R.layout.kullanici_item,parent,false);
        return new KullaniciHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull KullaniciHolder holder, int position) {
        mKullanici=mKullaniciList.get(position);
        holder.kullaniciIsmi.setText(mKullanici.getKullaniciIsmi());
        holder.kullaniciProfil.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return mKullaniciList.size();
    }

    class KullaniciHolder extends RecyclerView.ViewHolder{
        TextView kullaniciIsmi;
        ImageView kullaniciProfil;

        public KullaniciHolder(@NonNull View itemView) {
            super(itemView);

            kullaniciIsmi=itemView.findViewById(R.id.kullanici_item_txtKullaniciIsim);
            kullaniciProfil=itemView.findViewById(R.id.kullanici_item_imgKullaniciProfili);
        }
    }
}
