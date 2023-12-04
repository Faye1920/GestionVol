import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GestionCompagnie {
    public static void main(String[] args) throws IOException {
        String nomCie = JOptionPane.showInputDialog(null, "Entrez le nom de la compagnie :", "Vérification de l'identité", JOptionPane.PLAIN_MESSAGE);
        int nbMaxVols = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre maximum de vols :", "Vérification de l'identité", JOptionPane.PLAIN_MESSAGE));
        String nomFichier = "src/Studium/" + nomCie + ".txt";
        File file = new File(nomFichier);

        if (file.exists()) {
            // accéder au menu principal
        } else {
            int response = JOptionPane.showConfirmDialog(null, "Le fichier n'a pas été trouvé\nCréez le fichier maintenant et allez au menu ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                try {
                    file.createNewFile();
                    // Ajouter de nouveau fichier au menu
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Compagnie compagnie = new Compagnie(nomCie, nbMaxVols);
        int choix = -1;
        while (choix != 0) {
            choix = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "\tGESTION DES VOLS\n\n1.Liste des vols\n2.Ajout d'un vol\n3.Retrait d'un vol\n4.Modification de " +
                            "la date de départ\n5" +
                            ".Réservation d'un vol\n0.Terminer\n\t\t\t\t\t\tFaites votre choix :\n", nomCie, JOptionPane.PLAIN_MESSAGE));
            switch (choix) {
                case 1:
                    compagnie.listeVols();
                    break;
                case 2:
                    compagnie.insererVol();
                    break;
                case 3:
                    compagnie.retirerVol();
                    break;
                case 4:
                    compagnie.modifierDate();
                    break;
                case 5:
                    compagnie.reserverVol();
                    break;
                case 0:
                    compagnie.ecrireFichier();
                    JOptionPane.showMessageDialog(null, "Nous vous remercions d'avoir utilisé notre système.", "Quitter", JOptionPane.PLAIN_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Le choix est invalide!");
            }
        }
    }
}