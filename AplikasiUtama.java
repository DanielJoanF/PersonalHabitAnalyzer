import java.util.Scanner;

public class AplikasiUtama {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // 1. DEPENDENCY: AplikasiUtama menciptakan (instantiates) kelas Pengguna
        Pengguna user1 = new Pengguna("Daniel");

        // 2. PEWARISAN & POLIMORFISME: Membuat objek dari kelas anak
        // Asumsi konstruktor: (Nama Kebiasaan) untuk Ceklis, dan (Nama, Target Harian) untuk Terukur
        KebiasaanCeklis habit1 = new KebiasaanCeklis("Merapikan Kasur");
        KebiasaanTerukur habit2 = new KebiasaanTerukur("Minum Air Putih", 2000.0);

        // 3. AGREGASI: Memasukkan kebiasaan ke dalam Array of Object di kelas Pengguna
        user1.definisiKebiasaanBaru(habit1);
        user1.definisiKebiasaanBaru(habit2);

        System.out.println("=== SELAMAT DATANG DI HABITPULSE ===");
        
        // Looping Menu Interaktif (Terminal/CLI)
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nMenu HabitPulse:");
            System.out.println("1. Tampilkan Laporan (Analisis Matriks)");
            System.out.println("2. Simulasi Catat Progres");
            System.out.println("3. Simulasi Pergantian Hari (Reset 00:00)");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();

            if (pilihan == 1) {
                System.out.println("\n--- LAPORAN HARI INI ---");
                // Memanggil method yang memproses Array of Object secara otomatis
                user1.analisisMatriks(); 
            } 
            else if (pilihan == 2) {
                // OVERRIDING & OVERLOADING BEKERJA DI SINI
                System.out.println("\n[Sistem] Mencentang 'Merapikan Kasur'...");
                habit1.rekamProgresHarian(); // Tanpa parameter (Kebiasaan Ceklis)

                System.out.println("[Sistem] Memasukkan progres 500ml Air...");
                habit2.rekamProgresHarian(500.0); // Dengan parameter angka (Kebiasaan Terukur)
            } 
            else if (pilihan == 3) {
                // IMPLEMENTASI INTERFACE BEKERJA DI SINI
                System.out.println("\n[Sistem] Jam menunjukkan 00:00. Mengeksekusi SiklusHarian...");
                habit1.resetStatusSiklusHari();
                habit2.resetStatusSiklusHari();
                System.out.println("[Sistem] Pergantian hari berhasil. Progres di-reset ke 0.");
            } 
            else if (pilihan == 4) {
                isRunning = false;
                System.out.println("Terima kasih telah menggunakan HabitPulse!");
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
        
        input.close();
    }
}