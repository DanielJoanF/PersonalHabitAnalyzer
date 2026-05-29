public abstract class Kebiasaan implements SiklusHarian{
    protected String namaKebiasaan;
    protected int rekorBeruntun;

    public Kebiasaan(String namaKebiasaan) {
        this.namaKebiasaan = namaKebiasaan;
        this.rekorBeruntun = 0;
    }

    public abstract void rekamProgresHarian();
    public abstract double hitungMatriks();

    public abstract void rekamProgresHarian(double nilai);
    public abstract double hitungMatriks();
}
