package domaine;

import java.util.Set;

public class StageStub implements Stage {

    private int bonNumeroDeSemaine;
    private Sport sportStub;
    private Moniteur moniteurStub;

    public StageStub(int bonNumeroDeSemaine, Sport sportStub, Moniteur moniteurStub) {
        this.bonNumeroDeSemaine = bonNumeroDeSemaine;
        this.sportStub = sportStub;
        this.moniteurStub = moniteurStub;
    }

    @Override
    public String getIntitule() {
        return null;
    }

    @Override
    public String getLieu() {
        return null;
    }

    @Override
    public int getNumeroDeSemaine() {
        return bonNumeroDeSemaine;
    }

    @Override
    public Sport getSport() {
        return sportStub;
    }

    @Override
    public boolean enregistrerMoniteur(Moniteur moniteur) {
        return false;
    }

    @Override
    public boolean supprimerMoniteur() {
        return false;
    }

    @Override
    public Moniteur getMoniteur() {
        return moniteurStub;
    }

    @Override
    public boolean ajouterEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean supprimerEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public boolean contientEnfant(Enfant enfant) {
        return false;
    }

    @Override
    public int nombreDEnfants() {
        return 0;
    }

    @Override
    public Set<Enfant> enfants() {
        return null;
    }
}
