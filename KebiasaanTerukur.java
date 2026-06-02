public class KebiasaanTerukur extends Kebiasaan {
    private double targetHarian;
    private double inputNilaiAngka;

    public KebiasaanTerukur(String namaKebiasaan, double targetHarian) {
        super(namaKebiasaan);
        this.targetHarian = targetHarian;
        this.inputNilaiAngka = 0.0;
    }

    @Override
    public void rekamProgresHarian() {
        this.inputNilaiAngka += 1.0;
    }

    @Override
    public void rekamProgresHarian(double nilaiAngka) {
        this.inputNilaiAngka += nilaiAngka;
    }

    @Override
    public double hitungMatriks() {
        if (this.targetHarian <= 0) {
            return 0.0;
        }
        return (this.inputNilaiAngka / this.targetHarian) * 100.0;
    }

    @Override
    public void resetStatusSiklusHari() {
        this.inputNilaiAngka = 0.0;
    }
}
