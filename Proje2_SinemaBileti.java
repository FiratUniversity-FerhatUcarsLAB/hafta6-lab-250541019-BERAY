Ad-Soyad: Beray Akar
Öğrenci No: 250541019
Proje Adı: Sinema Bileti Fiyatlandırma Sistemi
Teslim Tarihi: 26.11.2025




import java.util.Scanner;

public class SinemaBiletiFiyatlandırmaSistemi {

    // -------------------- METOTLAR --------------------

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        // 6 = Cumartesi, 7 = Pazar
        return gun == 6 || gun == 7;
    }

    // 2) Matine mi? (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;   // Hafta içi matine
        if (!weekend && !matinee) return 65;  // Hafta içi normal
        if (weekend && matinee) return 55;    // Hafta sonu matine
        return 85;                            // Hafta sonu normal
    }

    // 4) İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirim = 0;

        // Meslek seçimi (switch-case ZORUNLU)
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) indirim = 0.20; // Pzt–Perş
                else indirim = 0.15; // Cuma–Pazar
                break;

            case 2: // Öğretmen
                if (gun == 3) indirim = 0.35; // Sadece Çarşamba
                break;

            case 3: // Diğer
                indirim = 0;
                break;
        }

        // Yaş indirimleri
        if (yas >= 65) indirim = Math.max(indirim, 0.30);
        else if (yas < 12) indirim = Math.max(indirim, 0.25);

        return indirim;
    }

    // 5) Film türü ek ücreti
    public static double getFormatExtra(int tur) {
        // Film türü seçimi (switch-case ZORUNLU)
        switch (tur) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Final fiyat hesaplama
    public static double calculateFinalPrice(double base, double extra, double discount) {
        double toplam = base + extra;
        double indirimTutar = toplam * discount;
        return toplam - indirimTutar;
    }

    // 7) Bilet Bilgisi Yazdırma
    public static void generateTicketInfo(double base, double extra, double finalPrice) {
        System.out.println("\n--------- BİLET BİLGİSİ ---------");
        System.out.printf("Temel Fiyat: %.2f TL\n", base);
        System.out.printf("Format Ekstrası: %.2f TL\n", extra);
        System.out.printf("Ödenecek Tutar: %.2f TL\n", finalPrice);
        System.out.println("----------------------------------");
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Gün seçimi
        System.out.println("Gün seçin (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();

        // Saat seçimi
        System.out.print("Saat: ");
        int saat = sc.nextInt();

        // Yaş girişi
        System.out.print("Yaş: ");
        int yas = sc.nextInt();

        // Meslek seçimi (switch-case zorunlu)
        System.out.println("Meslek seçin: 1=Öğrenci  2=Öğretmen  3=Diğer");
        int meslek = sc.nextInt();

        // Film türü (switch-case zorunlu)
        System.out.println("Film türü seçin: 1=2D  2=3D  3=IMAX  4=4DX");
        int tur = sc.nextInt();

        double basePrice = calculateBasePrice(gun, saat);
        double extra = getFormatExtra(tur);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double finalPrice = calculateFinalPrice(basePrice, extra, discountRate);

        generateTicketInfo(basePrice, extra, finalPrice);

        sc.close();
    }
}


