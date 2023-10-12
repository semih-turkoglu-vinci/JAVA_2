package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    private Prix prixAucune, prixPub, prixSolde;

    @BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixAucune.definirPrix(1,20);
        prixAucune.definirPrix(10,10);
        prixPub = new Prix(TypePromo.PUB,6);
        prixPub.definirPrix(3,15);
        prixSolde = new Prix(TypePromo.SOLDE,8);

    }

    @Test
    @DisplayName("Test pour que le type de la promo est null lors de l’instanciation d’un prix au moyen du constructeur sans paramètre")
    void getTypePromo() {
        assertNull(new Prix().getTypePromo());
    }
    @DisplayName("Test pour vérifier que le type de la promo correspond bien à celle passée en paramètre du constructeur")
    @Test
    void testTypePromo2(){
        assertEquals(TypePromo.PUB,prixPub.getTypePromo());
    }

    @Test
    @DisplayName("Test pour que la valeur de le promo est initialisée à 0 lors de l'instanciation")
    void getValeurPromo() {
        assertEquals(0,prixAucune.getValeurPromo());
    }
    @DisplayName("Test pour que la valeur de la promo correspond bien à celle passée en paramètre du constructeur")
    @Test
    void TestValeurPromo2(){
        assertEquals(6,prixPub.getValeurPromo());
    }

    @Test
    @DisplayName("Vérifier que la méthode definirPrix lance une IllegalArgumentException si le paramètre quantité est 0 ou négatif")
    void definirPrix() {
        assertThrows(IllegalArgumentException.class, () -> prixPub.getPrix(0));
    }
    @DisplayName("Vérifier que la méthode definirPrix lance une IllegalArgumentException si le paramètre quantité est 0 ou négatif")
    @Test
    void definirPrix2(){
        assertThrows(IllegalArgumentException.class,() -> prixPub.definirPrix(1,0));
    }
    @DisplayName("Test changement de valeur de prix")
    @Test
    void definirPrix3(){
        prixAucune.definirPrix(10,6);
        assertEquals(6,prixAucune.getPrix(10));
    }

    @Test
    @DisplayName("Vérifiez que la méthode lance une IllegalArgumentException si le paramètre quantité est négatif ou nu")
    void getPrix() {
        assertThrows(IllegalArgumentException.class,() -> prixAucune.getPrix(0));
    }
    @DisplayName("Testez les prix renvoyés par la méthode getPrix pour l’attribut prixAucune : faites le test pour 1 unité, 5 unités, 9 unités, 10 unités, 15 unités, 20 unités et 25 unités")
    @ParameterizedTest
    @ValueSource(ints = {1,5,9,10,15,20,25})
    void getPrix2(int value){
        if(value<10)
            assertEquals(20,prixAucune.getPrix(value));
        if (value>=10)
            assertEquals(10,prixAucune.getPrix(value));
    }
    @DisplayName("Testez qu’une QuantiteNonAutoriseeException est lancée si vous demandez le prix pour 2 unités pour l’attribut prixPub")
    @Test
    void getPrix3(){
        assertThrows(QuantiteNonAutoriseeException.class,()->prixPub.getPrix(2));
    }
    @DisplayName("Testez qu’une QuantiteNonAutoriseeException est lancée si vous demandez le prix pour 1 unité pour l’attribut prixSolde")
    @Test
    void getPrix4(){
        assertThrows(QuantiteNonAutoriseeException.class,() -> prixSolde.getPrix(1));
    }

    @DisplayName("Test du constructeur avec un type de promo null")
    @Test
    void testConstructeur(){
        assertThrows(IllegalArgumentException.class, () -> new Prix(null,6));
    }
    @DisplayName("Test du constructeur avec une valaue promo négative")
    @ParameterizedTest
    @ValueSource(doubles = {-4634,-100,-2})
    void testConstructeur2(double valeur){
        assertThrows(IllegalArgumentException.class, () -> new Prix(TypePromo.PUB,valeur));
    }


}