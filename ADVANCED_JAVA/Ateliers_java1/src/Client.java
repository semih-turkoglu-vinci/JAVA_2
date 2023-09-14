import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Client implements Iterable<Commande>{

    private static int numeroSuivant = 1;
    private int numero;
    private String nom, prenom, telephone;
    private Commande commandeEnCours;
    private ArrayList<Commande> commandesPassees = new ArrayList<>();

    public Client(String nom, String prenom, String telephone){
        this.nom=nom;
        this.prenom=prenom;
        this.telephone=telephone;
        this.numero=numeroSuivant;
        numeroSuivant++;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Commande getCommandeEnCours() {
        return commandeEnCours;
    }

    public boolean enregistrer(Commande commande){
        if (commandeEnCours != null || commande.getClient()!=this)
            return false;
        if (commandesPassees.contains(commande))
            return false;
        this.commandeEnCours=commande;
        return true;
    }

    public boolean cloturerCommandeEnCours(){
        if (commandeEnCours==null)
            return false;
        commandesPassees.add(commandeEnCours);
        this.commandeEnCours=null;
        return true;
    }

    public Iterator<Commande> iterator(){
        return commandesPassees.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return numero == client.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
    @Override
    public String toString() {
        return "client n° " + numero + " (" + prenom  + " " + nom + ", telephone : " + telephone +")";
    }
}
