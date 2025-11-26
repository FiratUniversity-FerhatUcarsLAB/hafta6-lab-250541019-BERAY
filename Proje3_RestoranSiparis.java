Ad-Soyad: Beray Akar
Öğrenci No: 250541019
Proje Adı: Akıllı Restoran Sipariş Sistemi
Teslim Tarihi: 26.11.2025



  import java.util.Scanner;

public class AkıllıRestoranSiparisSistemi {

    // -------------------- METOTLAR --------------------

    // 1) Ana Yemek Fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç Fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;  // Çorba
            case 2: return 45;  // Humus
            case 3: return 55;  // Sigara Böreği
            default: return 0;
        }
    }

    // 3) İçecek Fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;  // Kola
            case 2: return 12;  // Ayran
            case 3: return 35;  // Meyve Suyu
            case 4: return 25;  // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı Fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;  // Künefe
            case 2: return 55;  // Baklava
            case 3: return 35;  // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo Menüsü mü?
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy Hour kontrolü (14:00 - 17:00)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim Hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double indirim = 0;

        // Combo %15
        if (combo) indirim += tutar * 0.15;

        // 200 TL üstü %10
        if (tutar >= 200) indirim += tutar * 0.10;

        // Happy Hour → içeceklerde %20 (main içinde içecek fiyatına yansıtırız)

        // Öğrenci (hafta içi %10) → Gün bilgisi olmadığı için varsayılan uygulanabilir
        if (ogrenci) indirim += tutar * 0.10;

        return indirim;
    }

    // 8) Bahşiş (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Ana Yemek ---
        System.out.println("Ana yemek seçin: 1=Izgara Tavuk 2=Adana 3=Levrek 4=Mantı 0=İstemiyorum");
        int ana = sc.nextInt();
        double anaFiyat = getMainDishPrice(ana);
        boolean anaVar = anaFiyat > 0;

        // --- Başlangıç ---
        System.out.println("Başlangıç seçin: 1=Çorba 2=Humus 3=Sigara Böreği 0=Yok");
        int basla = sc.nextInt();
        double baslaFiyat = getAppetizerPrice(basla);

        // --- İçecek ---
        System.out.println("İçecek seçin: 1=Kola 2=Ayran 3=Meyve Suyu 4=Limonata 0=Yok");
        int icecek = sc.nextInt();
        double icecekFiyat = getDrinkPrice(icecek);
        boolean icecekVar = icecekFiyat > 0;

        // Happy Hour indirimi
        System.out.print("Saat: ");
        int saat = sc.nextInt();

        if (isHappyHour(saat) && icecekVar) {
            icecekFiyat = icecekFiyat * 0.80; // %20 indirim içeceklere
        }

        // --- Tatlı ---
        System.out.println("Tatlı seçin: 1=Künefe 2=Baklava 3=Sütlaç 0=Yok");
        int tatli = sc.nextInt();
        double tatliFiyat = getDessertPrice(tatli);
        boolean tatliVar = tatliFiyat > 0;

        // --- Öğrenci misiniz? ---
        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        int ogr = sc.nextInt();
        boolean ogrenci = (ogr == 1);

        // --- Tutar Hesaplama ---
        double araToplam = anaFiyat + baslaFiyat + icecekFiyat + tatliFiyat;

        // Combo kontrol
        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        // İndirim hesaplama
        double indirimMiktari = calculateDiscount(araToplam, combo, ogrenci, saat);

        // Net tutar
        double odenecek = araToplam - indirimMiktari;

        // Bahşiş
        double bahsis = calculateServiceTip(odenecek);

        // --- Fiş Yazdırma ---
        System.out.println("\n---------- SİPARİŞ ÖZETİ ----------");
        System.out.printf("Ara Toplam: %.2f TL\n", araToplam);
        System.out.printf("Toplam İndirim: -%.2f TL\n", indirimMiktari);
        System.out.printf("Ödenecek Tutar: %.2f TL\n", odenecek);
        System.out.printf("Önerilen Bahşiş (%%10): %.2f TL\n", bahsis);
        System.out.println("------------------------------------");

        sc.close();
    }
}



