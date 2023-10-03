import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements Iterable<LigneDeCommande>{
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> lignesCommande;

    public Commande(Client client){
        if (client.getCommandeEnCours() != null) throw new IllegalArgumentException("impossible de créer une commande pour un client ayant encore une commande en cours");
        this.client = client;
        client.enregistrer(this);
        this.numero=numeroSuivant;
        numeroSuivant++;
        this.date=LocalDateTime.now();
        lignesCommande = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public boolean ajouter(Pizza pizza, int quantite){
        if (this.client == null || this != client.getCommandeEnCours()) return false;
        boolean verif = false;
        for (LigneDeCommande ligne: lignesCommande) {
            if (ligne.getPizza().equals(pizza)) {
                verif = true;
                ligne.setQuantite(ligne.getQuantite() + quantite);
            }
        }
        if (!verif)
            lignesCommande.add(new LigneDeCommande(pizza, quantite));
        return true;
    }
    public boolean ajouter(Pizza pizza){
        return ajouter(pizza,1);
    }

    public boolean retirer(Pizza pizza, int quantite){
        if (this.client == null || this != client.getCommandeEnCours()) return false;
        boolean verif = false;
        for (LigneDeCommande ligne : lignesCommande) {
            if (ligne.getPizza().equals(pizza)) {
                verif = true;
                if (quantite < ligne.getQuantite()) {
                    ligne.setQuantite(ligne.getQuantite() - quantite);
                }
            }
        }
    }
    

    public double calculerMontantTotal(){
        double somme = 0;
        for (LigneDeCommande ligne: lignesCommande) {
            somme += ligne.calculerPrixTotal();
        }
        return somme;
    }

    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        String encours = "";
        if (client.getCommandeEnCours() == this)
            encours = " (en cours)";
        return "Commande n° " + numero + encours + " du " + client + "\ndate : " + formater.format(date);
    }

    public String detailler(){
        String aRenvoyer = "";
        for (LigneDeCommande ligne : lignesCommande) {
            aRenvoyer += ligne.toString()+"\n";
        }
        return aRenvoyer;
    }

    public Iterator<LigneDeCommande> iterator(){
        return lignesCommande.iterator();
    }
}
