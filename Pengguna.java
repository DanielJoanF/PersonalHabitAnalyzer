public class Pengguna {
    private String namaPengguna;
    private int jumlahKebiasaan;

    public Pengguna(String namaPengguna, int jumlahKebiasaan) {
        this.namaPengguna = namaPengguna;
        this.jumlahKebiasaan = jumlahKebiasaan;
    }

    public void definisikanKebiasaanBaru(kebiasaan k){
        if (this.jumlahKebiasaan >= this.daftarKebiasaan.length * 2){
            System.arraycopy(this.daftarKebiasaan, 0, temp, 0, this.daftarKebiasaan.length);
            this.daftarKebiasaan = temp;
        }
        this.daftarKebiasaan[this.jumlahKebiasaan] = k;
        this.jumlahKebiasaan++;
    }

    public void analisisMatriks() {
        System.out.println("Analisis Matriks untuk Pengguna: " + this.namaAtauUsername);
        for (int i = 0; i < this.jumlahKebiasaan; i++) {
            Kebiasaan k = this.daftarKebiasaan[i];
            double progres = k.hitungMatriks();
            System.out.printf("- %s | Rekor Beruntun: %d hari | Progres Hari Ini: %.1f%%\n",
                    k.namaKebiasaan, k.rekorBeruntun, progres);
        }
    }
}
