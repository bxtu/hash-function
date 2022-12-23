public class list {
    Musteri bas,temp,son;
    public list(){
        bas=null;
        temp=null;
        son=null;
    }
    public void ekle(int value,String ad,String soyad){
        Musteri musteri=new Musteri(value,ad,soyad);
        if(dolumu()){
            son.next=musteri;
            son=musteri;
        }else{
            bas=musteri;
            son=musteri;
        }
    }
    public boolean dolumu(){
        if(bas!=null){
            return  true;
        }else{
            return false;
        }
    }
    public int listeicindearama(int value){
        Musteri temp=bas;
        int sayac=0;
        while(temp.musteri_no!=value){
            if(temp.next==null){
                return -2;
            }
            temp=temp.next;
            sayac++;
        }
        return sayac;
    }
    public void yazdir(){
        Musteri temp=bas;
        while(temp!=null) {
            System.out.print(temp.musteri_no+" "+temp.ad+' '+temp.soyad+"-->");
            temp=temp.next;
        }
    }
}
