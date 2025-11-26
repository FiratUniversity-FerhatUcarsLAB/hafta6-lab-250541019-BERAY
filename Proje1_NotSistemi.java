Ad-Soyad: Beray Akar
Öğrenci No: 250541019
Proje Adı: Not Sistemi
Teslim Tarihi: 26.11.2025





import java.util.Scanner;

public class Proje1 {

    // -------------------- METOTLAR --------------------

    // 1) Ortalama Hesaplama
    public static double calculateAverage(double vize, double fin, double odev) {
        // Formül: Vize %30 + Final %40 + Ödev %30
        return (vize * 0.30) + (fin * 0.40) + (odev * 0.30);
    }

    // 2) Geçip geçmediğini kontrol etme
    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    // 3) Harf notu hesaplama
    public static char getLetterGrade(double ort) {
        if (ort >= 90) return 'A';
        else if (ort >= 80) return 'B';
        else if (ort >= 70) return 'C';
        else if (ort >= 60) return 'D';
        else if (ort >= 50) return 'E';
        else return 'F';
    }

    // 4) Onur listesi kontrolü
    public static boolean isHonorList(double ort, double v, double f, double o) {
        return ort >= 85 && v >= 70 && f >= 70 && o >= 70;
    }

    // 5) Bütünleme hakkı kontrolü
    public static boolean hasRetakeRight(double ort) {
        return ort >= 40 && ort < 50;
    }

    // -------------------- MAIN --------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Vize notu: ");
        double vize = sc.nextDouble();

        System.out.print("Final notu: ");
        double fin = sc.nextDouble();

        System.out.print("Ödev notu: ");
        double odev = sc.nextDouble();

        double ort = calculateAverage(vize, fin, odev);
        char harf = getLetterGrade(ort);

        System.out.printf("\nOrtalama: %.2f\n", ort);
        System.out.println("Harf Notu: " + harf);

        if (isPassingGrade(ort))
            System.out.println("Durum: GEÇTİ");
        else
            System.out.println("Durum: KALDI");

        if (isHonorList(ort, vize, fin, odev))
            System.out.println("Onur Listesi: EVET");
        else
            System.out.println("Onur Listesi: HAYIR");

        if (hasRetakeRight(ort))
            System.out.println("Bütünleme Hakkı: VAR");
        else
            System.out.println("Bütünleme Hakkı: YOK");

        sc.close();
    }
}


