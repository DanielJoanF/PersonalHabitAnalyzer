import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class PencatatProgress {
    public static void catatProgresHarian(Scanner scanner, List<Kebiasaan> listKebiasaan, PrintStream fOut, int hari) {
        System.out.println("==== PENCATATAN PROGRESS HARI " + hari + " ====");
        
        for (Kebiasaan k : listKebiasaan) {
            System.out.println("\nKebiasaan: " + k.namaKebiasaan);
            if (k instanceof KebiasaanCeklis) {
                while (true) {
                    System.out.print("- Apakah terlaksana? (ya/tidak): ");
                    String konfirmasi = scanner.nextLine().trim().toLowerCase();
                    if (fOut != null) {
                        fOut.println(konfirmasi);
                    }
                    if (konfirmasi.equals("ya") || konfirmasi.equals("y")) {
                        k.rekamProgresHarian();
                        break;
                    } else if (konfirmasi.equals("tidak") || konfirmasi.equals("t")) {
                        // Tidak terlaksana, statusKonfirmasi tetap false
                        break;
                    } else {
                        System.out.println("Input tidak valid! Masukkan 'ya' atau 'tidak'.");
                    }
                }
            } else if (k instanceof KebiasaanTerukur) {
                while (true) {
                    System.out.print("- Masukkan nilai angka pencapaian: ");
                    try {
                        String input = scanner.nextLine().trim();
                        if (fOut != null) {
                            fOut.println(input);
                        }
                        double nilai = Double.parseDouble(input);
                        if (nilai >= 0) {
                            k.rekamProgresHarian(nilai);
                            break;
                        }
                        System.out.println("Nilai pencapaian tidak boleh negatif!");
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka!");
                    }
                }
            }
        }
    }
}
