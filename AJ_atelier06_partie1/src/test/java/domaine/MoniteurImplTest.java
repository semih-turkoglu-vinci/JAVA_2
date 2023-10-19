package domaine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MoniteurImplTest {
    private Moniteur moniteur;
    private SportStub mma;
    private StageStub stageEte;
    @BeforeEach
    void setUp() {
        moniteur = new MoniteurImpl("hadje");
        mma = new SportStub(true);
        stageEte = new StageStub(8,mma,moniteur);
    }
    private boolean amenerALEtat(int etat, Moniteur moniteur) {
        for (int i = 1; i <= etat; i++) {
            moniteur.ajouterStage(new StageStub(i, mma, null));
        }
        return true;
    }


    @Test
    void ajouterStage1() {

        assertTrue(moniteur.ajouterStage(stageEte));
    }
    @Test
    void ajouterStage2(){

        amenerALEtat(1,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertEquals(2,moniteur.nombreDeStages()))
        ;
    }
    @Test
    void ajouterStage3(){

        amenerALEtat(2,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertEquals(3,moniteur.nombreDeStages())
        );
    }

    @Test
    void ajouterStage4(){

        amenerALEtat(3,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertEquals(4,moniteur.nombreDeStages())
        );
    }
    @Test
    void ajouterStage5() {
        amenerALEtat(3,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertFalse(moniteur.ajouterStage(stageEte))
        );
    }
    @Test
    void ajouterStage6() {
        amenerALEtat(4,moniteur);

        assertFalse(moniteur.ajouterStage(new StageStub(4,mma,null)));
    }
    @Test
    void ajouterStage7(){
        amenerALEtat(4,moniteur);
        assertFalse(moniteur.ajouterStage(new StageStub(5,mma,new MoniteurImpl("sdf"))));
    }
    @Test
    void ajouterStage8(){
        amenerALEtat(4,moniteur);
        assertTrue(moniteur.ajouterStage(new StageStub(5,mma,moniteur)));
    }
    @Test
    void ajouterStage9(){
        amenerALEtat(4,moniteur);
        assertFalse(moniteur.ajouterStage(new StageStub(5,new SportStub(false),moniteur)));
    }

    @Test
    void supprimerStage1() {

        amenerALEtat(3,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertTrue(moniteur.supprimerStage(stageEte)),
                () -> assertEquals(3,moniteur.nombreDeStages())
        );
    }
    @Test
    void supprimerStage2() {
        amenerALEtat(2,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertTrue(moniteur.supprimerStage(stageEte)),
                () -> assertEquals(2,moniteur.nombreDeStages())
        );
    }
    @Test
    void supprimerStage3() {
        amenerALEtat(1,moniteur);

        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertTrue(moniteur.supprimerStage(stageEte)),
                () -> assertEquals(1,moniteur.nombreDeStages())
        );
    }
    @Test
    void supprimerStage4() {
        assertAll(
                () -> assertTrue(moniteur.ajouterStage(stageEte)),
                () -> assertTrue(moniteur.supprimerStage(stageEte)),
                () -> assertEquals(0,moniteur.nombreDeStages())
        );
    }
    @Test
    void supprimerStage5(){
        amenerALEtat(4,moniteur);
        assertFalse(moniteur.supprimerStage(stageEte));
    }
}