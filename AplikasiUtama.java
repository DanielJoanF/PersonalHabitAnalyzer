import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplikasiUtama {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        PrintStream originalOut = System.out;
        PrintStream fileOut = null;
        try {
            fileOut = new PrintStream(new FileOutputStream("progress_log.txt", true));
        } catch (IOException e) {
            originalOut.println("Gagal membuka file log progress: " + e.getMessage());
        }
        
        final PrintStream fOut = fileOut;
        if (fOut != null) {
            PrintStream dualOut = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    originalOut.write(b);
                    fOut.write(b);
                }
                @Override
                public void write(byte[] b, int off, int len) throws IOException {
                    originalOut.write(b, off, len);
                    fOut.write(b, off, len);
                }
                @Override
                public void flush() throws IOException {
                    originalOut.flush();
                    fOut.flush();
                }
                @Override
                public void close() throws IOException {
                    originalOut.close();
                    fOut.close();
                }
            }, true);
            System.setOut(dualOut);
        }
        
        // Print Session Separator with Date & Time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println();
        System.out.println("   SESI PENCATATAN BARU: " + dtf.format(now));
        System.out.println();
        
        System.out.println("===  PERSONAL HABIT ANALYZER  ===");
        
        // 1. Data Pengguna
        System.out.print("Masukkan Nama atau Username Pengguna: ");
        String namaPengguna = scanner.nextLine().trim();
        if (fOut != null) {
            fOut.println(namaPengguna);
        }
        while (namaPengguna.isEmpty()) {
            System.out.print("Nama tidak boleh kosong. Masukkan nama: ");
            namaPengguna = scanner.nextLine().trim();
            if (fOut != null) {
                fOut.println(namaPengguna);
            }
        }
        
        Pengguna pengguna = new Pengguna(namaPengguna);
        List<Kebiasaan> listKebiasaan = new ArrayList<>();
        
        // 2. Definisi Kebiasaan Baru
        int totalKebiasaan = 0;
        while (true) {
            System.out.print("Berapa banyak kebiasaan baru yang ingin didefinisikan?: ");
            try {
                String input = scanner.nextLine().trim();
                if (fOut != null) {
                    fOut.println(input);
                }
                totalKebiasaan = Integer.parseInt(input);
                if (totalKebiasaan > 0) {
                    break;
                }
                System.out.println("Jumlah kebiasaan harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
        
        for (int i = 1; i <= totalKebiasaan; i++) {
            System.out.println("\n--- Mendefinisikan Kebiasaan ke-" + i + " ---");
            System.out.print("- Nama Kebiasaan: ");
            String namaKebiasaan = scanner.nextLine().trim();
            if (fOut != null) {
                fOut.println(namaKebiasaan);
            }
            while (namaKebiasaan.isEmpty()) {
                System.out.print("Nama kebiasaan tidak boleh kosong. Masukkan nama: ");
                namaKebiasaan = scanner.nextLine().trim();
                if (fOut != null) {
                    fOut.println(namaKebiasaan);
                }
            }
            
            int jenis = 0;
            while (true) {
                System.out.println("- Jenis Kebiasaan:");
                System.out.println("  1. Ceklis (Ya/Tidak)");
                System.out.println("  2. Terukur (Kuantitas)");
                System.out.print("  Pilihan (1/2): ");
                try {
                    String input = scanner.nextLine().trim();
                    if (fOut != null) {
                        fOut.println(input);
                    }
                    jenis = Integer.parseInt(input);
                    if (jenis == 1 || jenis == 2) {
                        break;
                    }
                    System.out.println("Pilihan tidak valid! Pilih 1 atau 2.");
                } catch (NumberFormatException e) {
                    System.out.println("Pilihan harus berupa angka (1 atau 2)!");
                }
            }
            
            Kebiasaan kebiasaanBaru;
            if (jenis == 1) {
                kebiasaanBaru = new KebiasaanCeklis(namaKebiasaan);
            } else {
                double targetHarian = 0;
                while (true) {
                    System.out.print("- Target Harian (Kuantitas): ");
                    try {
                        String input = scanner.nextLine().trim();
                        if (fOut != null) {
                            fOut.println(input);
                        }
                        targetHarian = Double.parseDouble(input);
                        if (targetHarian > 0) {
                            break;
                        }
                        System.out.println("Target harian harus lebih besar dari 0!");
                    } catch (NumberFormatException e) {
                        System.out.println("Input harus berupa angka!");
                    }
                }
                kebiasaanBaru = new KebiasaanTerukur(namaKebiasaan, targetHarian);
            }
            
            pengguna.definisiKebiasaanBaru(kebiasaanBaru);
            listKebiasaan.add(kebiasaanBaru);
            System.out.println("-> Kebiasaan \"" + namaKebiasaan + "\" berhasil ditambahkan!");
        }
        
        // 3. Pencatatan Progress (Daily Log)
        int totalHari = 0;
        while (true) {
            System.out.print("\nBerapa hari pencatatan progress (daily log) yang ingin disimulasikan?: ");
            try {
                String input = scanner.nextLine().trim();
                if (fOut != null) {
                    fOut.println(input);
                }
                totalHari = Integer.parseInt(input);
                if (totalHari > 0) {
                    break;
                }
                System.out.println("Jumlah hari harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
        
        for (int hari = 1; hari <= totalHari; hari++) {
            PencatatProgress.catatProgresHarian(scanner, listKebiasaan, fOut, hari);    
            System.out.println("=== REKAP & ANALISIS HARI " + hari + " ===");
            pengguna.jalankanRecap();
            System.out.println();
            pengguna.analisisMatriks();
            
            // Reset status harian untuk hari berikutnya
            for (Kebiasaan k : listKebiasaan) {
                k.resetStatusSiklusHari();
            }
        }
        
        System.out.println("\n===  SIMULASI SELESAI, TERIMA KASIH!  ===");
        scanner.close();
        if (fOut != null) {
            fOut.close();
        }
    }
}