package domaine;

import util.Util;

import java.time.Duration;
import java.util.*;

public class Plat {
    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> recette = new ArrayList<>();
    private Set<IngredientQuantifie> ingredients = new HashSet<>();
    private Type type;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout, Type type){
        this.nom=nom;
        this.nbPersonnes=nbPersonnes;
        this.niveauDeDifficulte=niveauDeDifficulte;
        this.cout=cout;
        this.type=type;
        this.dureeEnMinutes=Duration.ZERO;

    }

    public Type getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        if (position>=recette.size()) throw new IllegalArgumentException();
        recette.add(position - 1, instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
    }
    public void ajouterInstruction(Instruction instruction){
        recette.add(instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());

    }
    public Instruction remplacerInstruction(int position, Instruction instruction){
        Util.checkStrictlyPositive(position);
        Instruction retour = recette.remove(position - 1);
        dureeEnMinutes = dureeEnMinutes.minus(retour.getDureeEnMinutes());
        recette.add(position - 1,instruction);
        dureeEnMinutes = dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        return retour;
    }
    public Instruction supprimerInstruction (int position){
        dureeEnMinutes = dureeEnMinutes.minus(recette.get(position - 1).getDureeEnMinutes());
        Instruction retour = recette.remove(position - 1);
        return retour;
    }
    public Iterator<Instruction> instructions(){
        return Collections.unmodifiableList(recette).iterator();
    }
    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        IngredientQuantifie ingredientQuantifie = new IngredientQuantifie(ingredient,quantite,unite);
        if (ingredients.contains(ingredientQuantifie)) return false;
        ingredients.add(ingredientQuantifie);
        return true;
    }
    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        IngredientQuantifie ingredient1 = new IngredientQuantifie(ingredient,quantite,Unite.NEANT);
        if (ingredients.contains(ingredient1)) return false;
        ingredients.add(ingredient1);
        return true;
    }
    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){

        for (IngredientQuantifie ingredientquanti: ingredients) {
            if (ingredientquanti.getIngredient().equals(ingredient)) {
                ingredientquanti.setQuantite(quantite);
                ingredientquanti.setUnite(unite);
                return true;
            }
        }
        return false;
    }
    public boolean supprimerIngredient(Ingredient ingredient){
        for (IngredientQuantifie ingrdientquanti : ingredients) {
            if (ingrdientquanti.getIngredient().equals(ingredient)) {
                ingredients.remove(ingrdientquanti);
                return true;
            }
        }
        return false;
    }
    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        IngredientQuantifie retour;
        for (IngredientQuantifie ingredientquanti : ingredients) {
            if (ingredientquanti.getIngredient().equals(ingredient)){
                retour = ingredientquanti;
                return retour;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }
        return res;
    }

    public enum Difficulte{
        X,XX,XXX,XXXX,XXXXX;
        public String toString(){
            return super.toString().replace("X","*");
        }
    }
    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;
        public String toString(){
            return super.toString().replace("\\$","€");
        }
    }
    public enum Type{
        ENTREE,PLAT,DESSERT;
    }
}
