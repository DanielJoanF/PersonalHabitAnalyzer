public class AplikasiUtama {
    public static void main(String[] args) {
        Pengguna pengguna = new Pengguna("Andi");

        KebiasaanCeklis minumAir = new KebiasaanCeklis("Minum Air 8 gelas");
        KebiasaanTerukur pushups = new KebiasaanTerukur("Pushups", 30);

        pengguna.definisiKebiasaanBaru(minumAir);
        pengguna.definisiKebiasaanBaru(pushups);

        // Simulasi beberapa hari
        for (int hari = 1; hari <= 3; hari++) {
            System.out.println("=== Hari " + hari + " ===");

            // Tandai kebiasaan ceklis setiap hari ganjil
            if (hari % 2 == 1) {
                minumAir.rekamProgresHarian();
                System.out.println("- Minum air: tercatat");
            } else {
                System.out.println("- Minum air: tidak tercatat");
            }

            // Tambah jumlah pushups bertahap
            double jumlahPushups = 10 * hari; // 10, 20, 30
            pushups.rekamProgresHarian(jumlahPushups);
            System.out.println("- Pushups: ditambahkan " + jumlahPushups + " kali");

            // Tampilkan analisis saat ini
            System.out.println();
            pengguna.analisisMatriks();

            // Reset siklus harian (menyimpan rekor beruntun bila terpenuhi)
            minumAir.resetStatusSiklusHari();
            pushups.resetStatusSiklusHari();

            System.out.println();
        }
    }
}
