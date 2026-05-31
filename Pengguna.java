import java.util.ArrayList;
import java.util.List;

public class Pengguna {
    private String nama;
    private List<Kebiasaan> daftarKebiasaan;

    public Pengguna(String nama) {
        this.nama = nama;
        this.daftarKebiasaan = new ArrayList<>();
    }

    public void definisiKebiasaanBaru(Kebiasaan k) {
        this.daftarKebiasaan.add(k);
    }

    public void analisisMatriks() {
        System.out.println("Analisis Matriks untuk: " + this.nama);
        for (Kebiasaan k : this.daftarKebiasaan) {
            System.out.printf("- %s: %.2f%% (Rekor beruntun: %d)%n",
                k.namaKebiasaan, k.hitungMatriks(), k.rekorBeruntun);
        }
    }
}
