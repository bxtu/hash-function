import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class veri{
    static int hash_tablo[]=new int[10];
    static list veri_liste[] =new list[10];
    static int sayac=0;
    static ArrayList<Integer> sayilar=new ArrayList<>();
    public static void ilkdeger(){
        for(int i=0;i<10;i++){
            hash_tablo[i]=-1;
        }
    }
    public static int hashcalc(int value){
        sayac=0;
        int temp=0;
        while(sayac<10){
            temp=(((value*3)+2)+(sayac*((value*7)+2)))%10;
            if (hash_tablo[temp] == -1) {
                return temp;
            } else {
                if(((sayac+1)*((value*7)+2))%10==0 && sayac!=0){
                    return temp;
                }else{
                    sayac++;
                }
            }
        }
        System.out.println(sayac);
        return  temp;
    }
    public static void degeratama() throws FileNotFoundException {
        File veri = new File("/Users/kad62/Desktop/veri.txt");
        Scanner scan = new Scanner(veri);
        int i = 0;
        while (i < 7) {
            int value;
            int valuetutucu;
            int indis;
            String s = scan.nextLine();
            String tutucu;
            String ad, soyad;
            int j = 0;
            while (j < s.length()) {
                if (s.charAt(j) == ' ' && Character.isDigit(s.charAt(j - 1))) {
                    tutucu = s.substring(0, j);
                    value = Integer.parseInt(tutucu);
                    sayilar.add(value);
                    valuetutucu = hashcalc(value);
                    indis = j + 1;
                    while (s.charAt(indis) != ' ') {
                        indis++;
                    }
                    ad = s.substring(j, indis);
                    soyad = s.substring(indis);

                    if ((sayac * ((value * 7) + 2)) % 10 == 0 && sayac != 0) {
                        veri_liste[valuetutucu].ekle(value, ad, soyad);
                    } else {
                        veri_liste[valuetutucu] = new list();
                        veri_liste[valuetutucu].ekle(value, ad, soyad);
                        hash_tablo[valuetutucu] = value;
                    }
                }
                j++;
            }
            i++;
        }
    }
    public static void kullanicidan(){
        Scanner in = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        int kosul=1;
        do {
            if (kosul != 1) {
                break;
            }else{
                System.out.println("Musteri no giriniz: ");
                int value=in.nextInt();
                sayilar.add(value);
                System.out.println("Musteri ad giriniz: ");
                String ad=input.nextLine();
                System.out.println("Musteri soyad giriniz: ");
                String soyad=input.nextLine();

                int valuetutucu = hashcalc(value);
                if ((sayac * ((value * 7) + 2)) % 10 == 0 && sayac != 0) {
                    veri_liste[valuetutucu].ekle(value, ad, soyad);
                } else {
                    veri_liste[valuetutucu] = new list();
                    veri_liste[valuetutucu].ekle(value, ad, soyad);
                    hash_tablo[valuetutucu] = value;
                }
            }
            System.out.println("Değer eklemek istiyor musunuz?(1/0)");
            kosul = in.nextInt();
        } while (kosul == 1);
    }
    public static int arama(int value){
        sayac=0;
        int temp;
        while(sayac<10){
            temp=(((value*3)+2)+(sayac*((value*7)+2)))%10;
            if(veri_liste[temp]==null){
                return -1;
            }
            if (hash_tablo[temp] == value) {
                return sayac+1;
            } else {
                if(((sayac+1)*((value*7)+2))%10==0 && sayac!=0){
                    sayac=veri_liste[temp].listeicindearama(value);
                    return sayac+1;
                }else{
                    sayac++;
                }
            }
        }
        return  sayac+1;
        }
        public static float ortalamaadim(){
        int i=0,toplam=0;
        float ortalama;
        while(i!=sayilar.size()){
            toplam=toplam+arama(sayilar.get(i));
            i++;
        }
        ortalama=(float) toplam/i;
        System.out.println(toplam+"/"+i+"="+ortalama);
        return ortalama;
        }
    public static void listeleme(){
        System.out.println();
        int i=0;
        while(i<10){
            if(veri_liste[i]!=null) {
                veri_liste[i].yazdir();
                System.out.println();
            }else{
                System.out.println("null");
            }
            i++;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        ilkdeger();
        degeratama();
        Scanner in = new Scanner(System.in);
        int value,tutucu;
        int kosul=0;
        while(kosul!=5){
            System.out.println("Yapmak istediğiniz işlemi seçiniz(1-ekleme,2-arama,3-listeleme,4-ortalama adım,5-çıkış)");
            kosul=in.nextInt();
        switch (kosul) {
            case (1):
                kullanicidan();
                break;
            case (2):
                System.out.println("Aramak istediğiniz değeri giriniz= ");
                value = in.nextInt();
                tutucu = value;
                value = arama(value);
                if(value==-1) System.out.println("Değer listede yoktur");
                else System.out.println(tutucu + " değeri " + value + " adımda bulundu.");
                break;
            case (3):
                listeleme();
                break;
            case (4):
                System.out.println("Bütün değerler ortalama " + ortalamaadim() + " adımda bulundu.");
            case (5):
                return;
            }
        }
    }
}