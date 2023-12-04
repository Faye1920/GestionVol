import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class Compagnie extends JFrame {
    public static final int MAX_PLACES = 340;
    private String nomCompagnie;
    private int nbMaxVols;
    private int nbVolsActifs = 0;
    private Vol[] tabVols;

    //constructeur par défaut
    Compagnie() {
    }

    //constructeur par paramètre
    Compagnie(String nomCompagnie, int nbMaxVols) throws IOException{
        this.nomCompagnie = nomCompagnie;
        this.nbMaxVols = nbMaxVols;
        this.tabVols = new Vol[this.nbMaxVols];
        chargerVols();
    }

    //la méthode privée rechercherVol()
    private int rechercherVol(int numVol) {
        for (int i = 0; i < nbVolsActifs; i++) {
            if (tabVols[i].getNumeroVol().equals(Integer.toString(numVol))) {
                return i;
            }
        }
        return -1;
    }

    //la méthode insererVol()
    public void insererVol() {
        if (nbVolsActifs == nbMaxVols) {
            JOptionPane.showMessageDialog(null, "Il n'y a plus de place pour ajouter un vol.", "Ajout d'un vol", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        String numVol;
        do {
            numVol = JOptionPane.showInputDialog(null, "Entrez le numéro du nouveau vol : ", "Ajout d'un vol", JOptionPane.PLAIN_MESSAGE);
            if (rechercherVol(Integer.parseInt(numVol)) != -1) {
                JOptionPane.showMessageDialog(null, "Le numéro du vol existe déjà.", "Ajout d'un vol", JOptionPane.PLAIN_MESSAGE);
            }
        } while (rechercherVol(Integer.parseInt(numVol)) != -1);
        String destination = JOptionPane.showInputDialog(null, "Entrez la destination : ", "Ajout d'un vol", JOptionPane.PLAIN_MESSAGE);
        String dateDepartStr = JOptionPane.showInputDialog(null, "Entrez la date de départ sous la forme jj/mm/aaaa : ", "Ajout d'un vol", JOptionPane.PLAIN_MESSAGE);
        String[] dateDepartChamps = dateDepartStr.split("/");
        int jourDepart = Integer.parseInt(dateDepartChamps[0]);
        int moisDepart = Integer.parseInt(dateDepartChamps[1]);
        int anDepart = Integer.parseInt(dateDepartChamps[2]);
        Date dateDepart = new Date(jourDepart, moisDepart, anDepart);
        int nbReservations = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du nombre total de réservations : ", "Ajout d'un vol", JOptionPane.PLAIN_MESSAGE));
        tabVols[nbVolsActifs] = new Vol(numVol, destination, dateDepart, nbReservations);
        nbVolsActifs++;
        JOptionPane.showMessageDialog(null, "Le vol a été ajouté avec succès.", "Ajout réussi", JOptionPane.PLAIN_MESSAGE);
    }

    //la méthode retirerVol()
    public void retirerVol() {
        int numVol = Integer.parseInt(JOptionPane.showInputDialog("Entrez le numéro du vol à retirer : "));
        int indiceVol = rechercherVol(numVol);
        if (indiceVol == -1) {
            JOptionPane.showMessageDialog(null, "Le numéro du vol n'existe pas.", "Retrait d'un vol", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        String message = "Destination : " + tabVols[indiceVol].getDestination() + "\n";
        message += "Date de départ : " + tabVols[indiceVol].getDateDepart().toString() + "\n";
        message += "Total des réservations : " + tabVols[indiceVol].getNbReservations() + "\n";
        message += "Désirez-vous vraiment retirer ce vol ?";
        int reponse = JOptionPane.showConfirmDialog(null, message);
        if (reponse == JOptionPane.YES_OPTION) {
            for (int i = indiceVol; i < nbVolsActifs - 1; i++) {
                tabVols[i] = tabVols[i + 1];
            }
            nbVolsActifs--;
        }
        JOptionPane.showMessageDialog(null, "Le vol a été retiré avec succès.", "Retrait réussi", JOptionPane.PLAIN_MESSAGE);
    }

    //la méthode modifierDate()
    public void modifierDate() {
        int numVol = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du vol dont vous voulez modifier la date : ", "Modification de la date de départ", JOptionPane.PLAIN_MESSAGE));
        int indiceVol = rechercherVol(numVol);
        if (indiceVol == -1) {
            JOptionPane.showMessageDialog(null, "Le numéro du vol n'existe pas.");
            return;
        }
        String message = "Destination : " + tabVols[indiceVol].getDestination() + "\n";
        message += "Date de départ actuelle : " + tabVols[indiceVol].getDateDepart().toString() + "\n";
        String dateDepartStr = JOptionPane.showInputDialog(null, message + "Entrez la nouvelle date de départ sous la forme jj/mm/aaaa : ", "Modification de la date de départ", JOptionPane.PLAIN_MESSAGE);
        String[] dateDepartChamps = dateDepartStr.split("/");
        int jourDepart = Integer.parseInt(dateDepartChamps[0]);
        int moisDepart = Integer.parseInt(dateDepartChamps[1]);
        int anDepart = Integer.parseInt(dateDepartChamps[2]);
        Date dateDepart = new Date(jourDepart, moisDepart, anDepart);
        tabVols[indiceVol].setDateDepart(dateDepart);
        JOptionPane.showMessageDialog(null, "La date de vol a été modifié avec succès.", "Modification réussie",
                JOptionPane.PLAIN_MESSAGE);
    }

    //la méthode reserverVol()
    public void reserverVol() {
        int numVol = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le numéro du vol que vous désirez réserver : ", "Réservation d'un vol", JOptionPane.PLAIN_MESSAGE));
        int indiceVol = rechercherVol(numVol);
        if (indiceVol == -1) {
            JOptionPane.showMessageDialog(null, "Le numéro du vol n'existe pas.");
            return;
        }
        Vol vol = tabVols[indiceVol];
        int nbPlacesRestantes = MAX_PLACES - vol.getNbReservations();
        if (nbPlacesRestantes == 0) {
            JOptionPane.showMessageDialog(null, "Il n'y a plus de place disponible pour ce vol.");
            return;
        }
        String message = "Destination : " + vol.getDestination() + "\n";
        message += "Date de départ : " + vol.getDateDepart().toString() + "\n";
        message += "Nombre de places disponibles : " + nbPlacesRestantes + "\n";
        int nbPlacesReservees = Integer.parseInt(JOptionPane.showInputDialog(null, message + "Entrez le nombre de places que vous désirez réserver : ", "Réservation d'un vol", JOptionPane.PLAIN_MESSAGE));
        if (nbPlacesReservees > nbPlacesRestantes) {
            JOptionPane.showMessageDialog(null, "Il n'y a pas suffisamment de places disponibles pour ce vol.");
            return;
        }
        vol.setNbReservations(vol.getNbReservations() + nbPlacesReservees);
        JOptionPane.showMessageDialog(null, "Le vol a été réservé avec succès.", "Réservation réussie",
                JOptionPane.PLAIN_MESSAGE);
    }

    //la méthode listeVols()
    public void listeVols() {
        String message = String.format("%-10s %-20s %-15s %-10s%n", "Numéro", "Destination", "Date Départ", "Réservations");
        for (int i = 0; i < nbVolsActifs; i++) {
            message += String.format("%-10s %-20s %-15s %-10s%n", tabVols[i].getNumeroVol(), tabVols[i].getDestination(),
                    tabVols[i].getDateDepart().toString(), tabVols[i].getNbReservations());
        }
        JTextArea textArea = new JTextArea(message);
        textArea.setFont(new Font("Courier", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(null, textArea, "Liste des vols pour la compagnie" + this.nomCompagnie, JOptionPane.PLAIN_MESSAGE);
    }

    //la méthode ecrireFichier()
    public void ecrireFichier() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("src/Studium/" + this.nomCompagnie + ".txt"));
            for (int i = 0; i < nbVolsActifs; i++) {
                Vol vol = tabVols[i];
                String ligne = vol.getNumeroVol() + ";" + vol.getDestination() + ";" + vol.getDateDepart().getJour() +
                        ";" + vol.getDateDepart().getMois() + ";" + vol.getDateDepart().getAnnee() + ";" + vol.getNbReservations();
                writer.println(ligne);
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'écriture du fichier.", "Quitter", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //la méthode insere()
    private void insere(Vol vol) {
        int i = nbVolsActifs - 1;
        while (i >= 0 && vol.compareTo(tabVols[i]) < 0) {
            tabVols[i + 1] = tabVols[i];
            i--;
        }
        tabVols[i + 1] = vol;
        nbVolsActifs++;
    }

    public void chargerVols() throws IOException{
        String champs[] = new String[6], ligne;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Studium/" + this.nomCompagnie + ".txt"));
        // 14567;Toronto;12;04;2002;167
        while((ligne = bufferedReader.readLine()) != null && this.nbVolsActifs < this.nbMaxVols){
            champs = ligne.split(";");
            tabVols[this.nbVolsActifs] = new Vol(champs[0], champs[1],
                    new Date(Integer.parseInt(champs[2]), Integer.parseInt(champs[3]), Integer.parseInt(champs[4])), Integer.parseInt(champs[5]));
            this.nbVolsActifs++;
        }
        bufferedReader.close();
    }

}