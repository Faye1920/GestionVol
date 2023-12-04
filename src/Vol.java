public class Vol {
    private String numeroVol;
    private String destination;
    private Date dateDepart;
    private int nbReservations;

    //constructeur par défaut
    Vol() {
    }

    //constructeur par paramètre
    Vol(String numeroVol, String destination, Date dateDepart, int nbReservations){
        this.numeroVol = numeroVol;
        this.destination = destination;
        this.dateDepart = dateDepart;
        this.nbReservations = nbReservations;
    }

    //get
    public String getNumeroVol() {
        return numeroVol;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public int getNbReservations() {
        return nbReservations;
    }

    //set
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public void setNbReservations(int nbReservations) {
        this.nbReservations = nbReservations;
    }

    //toString
    @Override
    public String toString() {
        return this.numeroVol+"\t"+this.destination+"\t"+this.dateDepart+"\t"+this.nbReservations;
    }

    //la méthode compareTo()
    public int compareTo(Vol vol) {
        return Integer.parseInt(this.getNumeroVol()) - Integer.parseInt(vol.getNumeroVol());
    }
}