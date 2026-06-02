import java.util.ArrayList;
import java.util.List;

public class Pengguna {
    private String nama;
    private List<Kebiasaan> daftarKebiasaan;
    private int jumlahKebiasaan;
    private final int MAX_KEBIASAAN = 100;

    public Pengguna(String nama) {
        this.nama = nama;
        this.daftarKebiasaan = new Kebiasaan[MAX_KEBIASAAN];
        this.jumlahKebiasaan = 0;
    }

    public void definisiKebiasaanBaru(Kebiasaan k) {
         if (jumlahKebiasaan < MAX_KEBIASAAN) {
        this.daftarKebiasaan.add(k);
        jumlahKebiasaan++;
    } else {
        System.out.println("Daftar kebiasaan sudah penuh!");
           }
    }

    public void analisisMatriks() {
        System.out.println("Analisis Matriks untuk: " + this.nama);
        for (int i = 0; i < jumlahKebiasaan; i++) {
            Kebiasaan k = daftarKebiasaan[i];  
           System.out.printf("- %s: %.2f%% (Rekor beruntun: %d)%n",
                k.namaKebiasaan, k.hitungMatriks(), k.rekorBeruntun);
        }
    }

    public void jalankanRecap(){
        for (int i = 0; i < jumlahKebiasaan; i++) {
            daftarKebiasaan[i].cetakRecap();
        }
    }
}
