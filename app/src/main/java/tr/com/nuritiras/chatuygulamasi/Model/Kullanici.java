package tr.com.nuritiras.chatuygulamasi.Model;

public class Kullanici {
    private  String KullaniciIsmi,kullaniciEmail,kullaniciId,kullaniciProfil;

    public Kullanici(String kullaniciIsmi, String kullaniciEmail,String kullaniciId,String kullaniciProfil) {
        KullaniciIsmi = kullaniciIsmi;
        this.kullaniciEmail = kullaniciEmail;
        this.kullaniciId=kullaniciId;
        this.kullaniciProfil=kullaniciProfil;
    }

    public Kullanici() {
    }

    public String getKullaniciIsmi() {
        return KullaniciIsmi;
    }

    public void setKullaniciIsmi(String kullaniciIsmi) {
        KullaniciIsmi = kullaniciIsmi;
    }

    public String getKullaniciEmail() {
        return kullaniciEmail;
    }

    public void setKullaniciEmail(String kullaniciEmail) {
        this.kullaniciEmail = kullaniciEmail;
    }

    public String getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(String kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getKullaniciProfil() {
        return kullaniciProfil;
    }

    public void setKullaniciProfil(String kullaniciProfil) {
        this.kullaniciProfil = kullaniciProfil;
    }
}
