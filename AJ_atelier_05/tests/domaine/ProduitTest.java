package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Prix prixAucune, prixPub, prixSolde;
    private Produit produitParametre, produitParametre2;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub = new Prix(TypePromo.PUB,6);
        prixPub.definirPrix(3,15);
        prixSolde = new Prix(TypePromo.SOLDE,8);
        produitParametre = new Produit("Hey","Porsche","skuu");
        produitParametre2 = new Produit("henry","Mercedes","skur");
        produitParametre2.ajouterPrix(LocalDate.now(),prixAucune);
        produitParametre2.ajouterPrix(LocalDate.ofYearDay(2023,100),prixPub);
        produitParametre2.ajouterPrix(LocalDate.ofYearDay(2023,280),prixSolde);
    }

    @Test
    void getMarque() {
    }

    @Test
    void getNom() {
    }

    @Test
    void getRayon() {
    }

    @Test
    void ajouterPrix() {
        assertThrows(IllegalArgumentException.class,() -> produitParametre.ajouterPrix(null,prixAucune));
    }
    @Test
    void ajouterPrix2(){
        assertThrows(DateDejaPresenteException.class,() -> produitParametre2.ajouterPrix(LocalDate.ofYearDay(2023,100),new Prix()));
    }
    @Test
    @DisplayName("Test si prix s'ajoute bien pour une date donnée")
    void ajouterPrix3(){
        Prix prixtest = new Prix(TypePromo.PUB,50);
        produitParametre2.ajouterPrix(LocalDate.ofYearDay(2023,50),prixtest);
        assertEquals(prixtest,produitParametre2.getPrix(LocalDate.ofYearDay(2023,50)));
    }
    @Test
    @DisplayName("Vérifiez que lorsqu’on demande un prix pour une date antérieure à la définition d’un prix l’exception PrixNonDisponibleException est lancée")
    void getprix1(){
        assertThrows(PrixNonDisponibleException.class,() -> produitParametre2.getPrix(LocalDate.ofYearDay(2023,20)));
    }


    @Test
    @DisplayName("Vérifiez que lorsqu’on demande un prix pour un produit qui n’en n’a pas, l’exception PrixNonDisponibleException est lancée.")
    void getPrix2() {
        assertThrows(PrixNonDisponibleException.class,() -> produitParametre.getPrix(LocalDate.now()));
    }
    @Test
    @DisplayName("Vérifiez que lorsqu’on demande un prix pour une date comprise entre deux dates pour lesquelles le prix a été défini, c’est bien celui de la date antérieure qui a été renvoyé")
    void getPrix3() {
        assertEquals(prixPub,produitParametre2.getPrix(LocalDate.ofYearDay(2023,120)));
    }

    @Test
    @DisplayName("Vérifiez que la méthode equals fonctionne pour 2 instances de Produit différentes mais qui ont les même marque, nom et rayon")
    void testEquals1() {
        Produit produitTest = new Produit("Hey","Porsche","skuu");
        assertEquals(produitParametre,produitTest);
    }
    @Test
    @DisplayName("Vérifiez que la méthode equals renvoie faux pour deux produits ayant la même marque et le même rayon mais ayant des noms différents")
    void testEquals2() {
        assertNotEquals(produitParametre, new Produit("hey", "Porsche", "skuu"));
    }
    @Test
    @DisplayName("Vérifiez que la méthode equals renvoie faux pour deux produits ayant le même nom et le même rayon mais ayant des marques différentes")
    void testEquals3(){
        assertNotEquals(produitParametre,new Produit("Hey","merco","skuu"));
    }
    @Test
    @DisplayName("Vérifiez que la méthode equals renvoie faux pour deux produits ayant le même nom et la même marque mais ayant des rayons différentes")
    void testEquals4(){
        assertNotEquals(produitParametre,new Produit("Hey","Porsche","skuru"));
    }

    @Test
    @DisplayName("Vérifiez que la méthode hashCode renvoie bien la même valeur pour 2 instances de Produit différentes mais qui ont les même marque, nom et rayon")
    void testHashCode() {
        assertEquals(produitParametre.hashCode(),new Produit("Hey","Porsche","skuu").hashCode());
    }
    @DisplayName("Test du constructeur avec paramètre invalide")
    @Test
    void testConstructeur1a() {
        assertThrows(IllegalArgumentException.class,() -> new Produit(null,null,null));
    }
    @DisplayName("Test du constructeur avec paramètre invalide")
    @Test
    void testConstructeur1b() {
        assertThrows(IllegalArgumentException.class,() -> new Produit("","",""));
    }
    @DisplayName("Test du constructeur avec des valeurs valides")
    @Test
    void testConstructeur2a() {
        assertEquals("Hey",produitParametre.getNom());
    }
    @DisplayName("Test du constructeur avec des valeurs valides")
    @Test
    void testConstructeur2b() {
        assertEquals("Porsche",produitParametre.getMarque());
    }
    @DisplayName("Test du constructeur avec des valeurs valides")
    @Test
    void testConstructeur2c() {
        assertEquals("skuu",produitParametre.getRayon());
    }

}