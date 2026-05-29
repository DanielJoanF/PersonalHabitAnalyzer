public class KebiasaanCeklis extends Kebiasaan {
    private boolean statusKonfirmasi;

    public KebiasaanCeklis(String namaKebiasaan) {
        super(namaKebiasaan);
        this.statusKonfirmasi = false;
    }

    @Override
    public void rekamProgresHarian() {
        this.statusKonfirmasi = true;
    }

    @Override
    public double hitungMatriks() {
        return this.statusKonfirmasi ? 100.0 : 0.0;
    }

    @Override
    public void resetStatusSiklusHari() {
        if (this.statusKonfirmasi) {
            this.rekorBeruntun++;
        } else {
            this.rekorBeruntun = 0;
        }
        this.statusKonfirmasi = false;
    }
}
