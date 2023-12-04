public class Date {
    private int jour;
    private int mois;
    private int annee;

    //constructeur par défaut
    public Date() {
        jour = 1;
        mois = 1;
        annee = 2020;
    }

    //constructeur par paramètre
    Date(int jour, int mois, int annee){
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    //get
    public int getJour(){
        return this.jour;
    }

    public int getMois() {
        return this.mois;
    }

    public int getAnnee() {
        return this.annee;
    }

    public void setJour(int jour){
        if(jour >= 1 && jour <= 31){
            this.jour = jour;
        }else {
            System.out.println("Le jour n'est pas valide.");
        }
    }

    public void setMois(int mois) {
        if (mois >= 1 && mois <= 12) {
            this.mois = mois;
        } else {
            System.out.println("Le mois n'est pas valide.");
        }
    }

    public void setAnnee(int annee) {
        if (annee >= 2020) {
            this.annee = annee;
        } else {
            System.out.println("L'année n'est pas valide.");
        }
    }

    //toString
    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", jour, mois, annee);
    }
}
