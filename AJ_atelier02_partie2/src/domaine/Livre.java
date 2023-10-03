package domaine;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.SortedSet;

public class Livre {
    private HashMap<Plat.Type, SortedSet<Plat>> livreDeRecettes;

    public Livre(){
        livreDeRecettes = new HashMap<>();
    }
    /**
     * Ajoute un plat dans le livre, s'il n'existe pas déjà dedans.
     * Il faut ajouter correctement le plat en fonction de son type.
     * @param plat le plat à ajouter
     * @return true si le plat a été ajouté, false sinon.
     */
    public boolean ajouterPlat(Plat plat) {

        SortedSet test = livreDeRecettes.get(plat);
        if (test.contains(plat)) return false;
        livreDeRecettes.get(plat).add(plat);
        livreDeRecettes.put(plat)
    }
}
