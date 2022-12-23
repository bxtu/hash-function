public class Musteri {
    int musteri_no;
    String ad;
    String soyad;
    Musteri next;
    public Musteri(int no,String ad,String soyad){
        musteri_no=no;
        this.ad=ad;
        this.soyad=soyad;
        this.next=null;
    }
}
