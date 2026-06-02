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
    public void rekamProgresHarian(double nilai) {
        // Untuk jenis ceklis, input angka juga dianggap sebagai konfirmasi
        this.statusKonfirmasi = true;
    }

    @Override
    public double hitungMatriks() {
        return this.statusKonfirmasi ? 100.0 : 0.0;
    }

    @Override
    public void resetStatusSiklusHari() {
        this.statusKonfirmasi = false;
    }
}
