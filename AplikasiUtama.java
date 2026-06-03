public class AplikasiUtama {
    public static void main(String[] args) {
        Pengguna pengguna = new Pengguna("Michelle"); 
        KebiasaanCeklis minumAir = new KebiasaanCeklis("Minum Air 8 gelas");
        pengguna.definisiKebiasaanBaru(minumAir);

        for (int hari = 1; hari <= 7; hari++) {
            System.out.println("=== Hari " + hari + " ===");
            
            // Simulasi Michelle selalu 100% tiap hari
            minumAir.rekamProgresHarian(); 
            
            // Menjalankan recap untuk memproses logika minimal 2 hari tadi
            pengguna.jalankanRecap();
            
            // Cetak status untuk melihat pergerakan rekor
            pengguna.analisisMatriks();

            // Reset status harian untuk hari berikutnya
            minumAir.resetStatusSiklusHari();
            System.out.println();
        }
    }
}