import java.util.Scanner;

public class AplikasiUtama {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan nama/username pengguna: ");
        String nama = sc.nextLine().trim();
        Pengguna pengguna = new Pengguna(nama);

        int hari = 1;
        while (true) {
            System.out.println("\n=== Hari " + hari + " ===");
            System.out.println("=== Menu ===");
            System.out.println("1. Definisi kebiasaan baru");
            System.out.println("2. Pencatatan progress (daily log)");
            System.out.println("3. Tampilkan recap & analisis");
            System.out.println("4. Tampilkan daftar kebiasaan");
            System.out.println("5. Ganti hari (lanjut ke hari berikutnya)");
            System.out.println("6. Keluar");
            System.out.print("Pilih (1-6): ");

            String pilih = sc.nextLine().trim();
            if (pilih.equals("1")) {
                System.out.print("Nama kebiasaan: ");
                String namaK = sc.nextLine().trim();
                System.out.print("Jenis kebiasaan (1=Ya/Tidak, 2=Terukur): ");
                String jenis = sc.nextLine().trim();
                if (jenis.equals("1")) {
                    KebiasaanCeklis k = new KebiasaanCeklis(namaK);
                    pengguna.definisiKebiasaanBaru(k);
                    System.out.println("Kebiasaan (Ya/Tidak) ditambahkan.");
                } else if (jenis.equals("2")) {
                    System.out.print("Target Harian (angka): ");
                    double target = 0.0;
                    try {
                        target = Double.parseDouble(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid, target diset 0.");
                    }
                    KebiasaanTerukur k = new KebiasaanTerukur(namaK, target);
                    pengguna.definisiKebiasaanBaru(k);
                    System.out.println("Kebiasaan terukur ditambahkan.");
                } else {
                    System.out.println("Jenis tidak dikenali.");
                }

            } else if (pilih.equals("2")) {
                if (pengguna.getJumlahKebiasaan() == 0) {
                    System.out.println("Belum ada kebiasaan. Tambahkan terlebih dahulu.");
                    continue;
                }
                pengguna.tampilkanDaftarKebiasaan();
                System.out.print("Pilih nomor kebiasaan untuk dicatat: ");
                int idx = -1;
                try {
                    idx = Integer.parseInt(sc.nextLine().trim()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid.");
                    continue;
                }
                if (idx < 0 || idx >= pengguna.getJumlahKebiasaan()) {
                    System.out.println("Nomor tidak ada.");
                    continue;
                }
                Kebiasaan pilihK = pengguna.getKebiasaan(idx);
                if (pilihK instanceof KebiasaanCeklis) {
                    System.out.print("Konfirmasi status (y/n) atau masukkan angka: ");
                    String v = sc.nextLine().trim();
                    if (v.equalsIgnoreCase("y") || v.equalsIgnoreCase("yes")) {
                        pilihK.rekamProgresHarian();
                        System.out.println("Status dicatat: selesai.");
                    } else {
                        try {
                            double val = Double.parseDouble(v);
                            pilihK.rekamProgresHarian(val);
                            System.out.println("Status dicatat lewat angka.");
                        } catch (NumberFormatException e) {
                            System.out.println("Tidak dicatat.");
                        }
                    }
                } else if (pilihK instanceof KebiasaanTerukur) {
                    System.out.print("Masukkan nilai angka untuk hari ini: ");
                    try {
                        double val = Double.parseDouble(sc.nextLine().trim());
                        pilihK.rekamProgresHarian(val);
                        System.out.println("Nilai dicatat: " + val);
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid.");
                    }
                }

            } else if (pilih.equals("3")) {
                pengguna.jalankanRecap();
                pengguna.analisisMatriks();

            } else if (pilih.equals("4")) {
                if (pengguna.getJumlahKebiasaan() == 0) {
                    System.out.println("Belum ada kebiasaan.");
                } else {
                    pengguna.tampilkanDaftarKebiasaan();
                }
            } else if (pilih.equals("5")) {
                if (pengguna.getJumlahKebiasaan() == 0) {
                    System.out.println("Belum ada kebiasaan untuk diproses.");
                } else {
                    // End of day processing: recap, analysis, then reset each habit
                    System.out.println("Menjalankan recap dan analisis sebelum ganti hari...");
                    pengguna.jalankanRecap();
                    pengguna.analisisMatriks();
                    for (int i = 0; i < pengguna.getJumlahKebiasaan(); i++) {
                        pengguna.getKebiasaan(i).resetStatusSiklusHari();
                    }
                    hari++;
                    System.out.println("Berpindah ke hari " + hari + ".");
                }

            } else if (pilih.equals("6")) {
                System.out.println("Keluar. Sampai jumpa.");
                break;
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }

        sc.close();
    }
}