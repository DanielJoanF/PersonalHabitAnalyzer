public abstract class Kebiasaan implements SiklusHarian {
    protected String namaKebiasaan;
    protected int rekorBeruntun;

    //counter mengecek apakah sudah memenuhi syarat 2 hari 100%
    protected int hitungMinimalStreak;

    public Kebiasaan(String namaKebiasaan) {
        this.namaKebiasaan = namaKebiasaan;
        this.rekorBeruntun = 0;
        this.hitungMinimalStreak = 0;
    }

    public abstract void rekamProgresHarian();
    public abstract void rekamProgresHarian(double nilai);
    public abstract double hitungMatriks();

    public void cetakRecap(){
        double skor = hitungMatriks();
        System.out.println("--- Recap Kebiasaan: " + namaKebiasaan + " ---");

        //cetak target minimal 80%
        if(skor >= 80){
            System.out.println("Status: Target Tercapai! Skor:" + skor + "%");
        } else {
            System.out.println("Status: Target Belum Tercapai. Skor: " + skor + "%");
        }

        //logika streak (harus 100%)
        if (skor == 100) {
            hitungMinimalStreak++;
            //menghitung berapa hari berturut-turut mencapai 100%
            if (hitungMinimalStreak >= 2) {
                //jika hari ke-2 sukses 100%, maka rekor beruntun bertambah
                if (hitungMinimalStreak == 2) {
                    rekorBeruntun = 2;
                } else {
                    rekorBeruntun++;
                }
            }
        } else {
            rekorBeruntun = 0; //reset jika tidak mencapai 100%
            hitungMinimalStreak = 0; //reset jika tidak mencapai 100%
        }
        if (rekorBeruntun == 7) {
            System.out.println("Selamat! Kamu telah mencapai rekor konsisten 1 minggu!");
        }
    }
}
