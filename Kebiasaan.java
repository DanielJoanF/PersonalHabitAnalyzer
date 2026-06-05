public abstract class Kebiasaan implements SiklusHarian {
    protected String namaKebiasaan;
    protected int rekorBeruntun;

    public Kebiasaan(String namaKebiasaan) {
        this.namaKebiasaan = namaKebiasaan;
        this.rekorBeruntun = 0;
    }

    public abstract void rekamProgresHarian();
    public abstract void rekamProgresHarian(double nilai);
    public abstract double hitungMatriks();

    public void cetakRecap(){
        double skor = hitungMatriks();
        System.out.println("--- Recap Kebiasaan: " + namaKebiasaan + " ---");

        if(skor >= 80){
            System.out.println("Status: Target Tercapai! Skor: " + skor + "%");
        } else {
            System.out.println("Status: Target Belum Tercapai. Skor: " + skor + "%");
        }

        System.out.println("Rekor beruntun saat ini: " + rekorBeruntun);

        if(rekorBeruntun == 7){
            System.out.println("Selamat! Anda telah mencapai rekor beruntun 7 hari!");
        }
    }
}
