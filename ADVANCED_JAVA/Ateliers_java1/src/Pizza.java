import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Pizza implements Iterable<Ingredient>{
    public static final double PRIX_BASE = 5.0;
    private String titre;
    private String description;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public Pizza(String titre,String description){
        this.titre=titre;
        this.description=description;
    }
    public Pizza(String titre, String description, ArrayList<Ingredient> ingredients) {
        this(titre, description);
        for (Ingredient i : ingredients) {
            if (this.ingredients.contains(i))
                throw new IllegalArgumentException("Il ne peut pas y avoir deux fois le même ingrédient dans une pizza.");
            this.ingredients.add(i);
        }
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(titre, pizza.titre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre);
    }

    public boolean ajouter(Ingredient ingredient){
        if (!ingredients.contains(ingredient))
            return ingredients.add(ingredient);
        return false;
    }
    public boolean supprimer(Ingredient ingredient){
        if (ingredients.contains(ingredient))
            return ingredients.remove(ingredient);
        return false;
    }
    public double calculerPrix(){
        double somme = PRIX_BASE;
        for (Ingredient ingredient: ingredients) {
            somme += ingredient.getPrix();
        }
        return somme;
    }
    public Iterator<Ingredient> iterator(){
        return ingredients.iterator();
    }
    @Override
    public String toString() {
        String infos = titre + "\n" + description + "\nIngrédients : ";
        for (Ingredient ingredient : ingredients){
            infos +="\n" + ingredient.getNom();
        }
        infos +="\nprix : " + calculerPrix() + " euros";
        return infos;
    }
}
