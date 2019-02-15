package com.example.egehaneralp.challenge4;

public class Kullanici {   //firebase bağlantısı yapıp, online oynanabilirlik sağlamak için oluşturuldu.

    String ad;
    int secim;

    public Kullanici(){
        ad=null;
        secim=0;
    }
    public Kullanici(String s){
        ad=s;
        secim=0;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getSecim() {
        return secim;
    }

    public void setSecim(int secim) {
        this.secim = secim;
    }
}
