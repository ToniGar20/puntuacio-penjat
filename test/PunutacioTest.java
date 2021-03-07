package test;

import com.jaume.penjat.Puntuacio;
import com.jaume.penjat.Tauler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class PuntuacióTest {

    private Puntuacio puntuacio;
    private Tauler tauler;

    @BeforeEach
    void reiniciar() {
        this.puntuacio = new Puntuacio();
        this.tauler = new Tauler();
    }

    @Test
    void inicitalitzacioTemps()throws InterruptedException {
        this.puntuacio.getParaulaSecretaDificultat(1);
        TimeUnit.SECONDS.sleep(1);
        this.puntuacio.calcularPuntuacio(new String[1],0);
        assertNotEquals(0, this.puntuacio.getTemps());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,27})
    void comprobacioVides(int difNumber){
        puntuacio.getParaulaSecretaDificultat(difNumber);
        int vides = 0;
            switch (difNumber) {
                case 1:
                    difNumber = 1;
                    vides = 5;
                    break;
                case 2:
                    difNumber = 2;
                    vides = 4;
                    break;
                case 3:
                    difNumber = 3;
                    vides = 3;
                    break;
                case 27:
                    System.out.println("Valor introduit incorrecte");
                    return;

        }
        Assertions.assertEquals(vides, puntuacio.getIntents());
        System.out.println("Tens " + vides +" vides i es test es correcte per la dificultat "+difNumber );
    }

    @Test
    void comprobarPuntuacio(){
        this.puntuacio.getParaulaSecretaDificultat(1);
        Assertions.assertEquals(40, this.puntuacio.calcularPuntuacio(new String[]{"h", "o", "l","a"}, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void comprobacioPunts(int difNumber) {
        float puntsTest=puntuacio.calcularPuntuacio(new String[]{"x", "o", "r", "i", "g", "u","e","r"},puntuacio.getIntents());
        float puntsFinals= 1000f;
        float puntsPercentatge = 0f;
        float puntsTemps=100f;
        switch (difNumber){
            case 1:
                puntsPercentatge = 0.1f;
                puntsTemps*=puntuacio.getDificultat();
                if(Arrays.stream(puntuacio.getLetresBonus()).anyMatch(puntuacio.getParaula()::contains)){
                    puntsPercentatge += 0.01f;
                } else if (puntsPercentatge > 0) {
                    puntsFinals *= puntsPercentatge;

                } else if (puntsFinals > 0 || puntsTemps > 0) {
                    puntsFinals += puntsTemps;
                    assertEquals(puntsFinals, puntsTest);
                }
                System.out.println(puntsFinals);
                break;
            case 2:
                puntsPercentatge = 0.2f;
                puntsTemps*=puntuacio.getDificultat();
                if(Arrays.stream(puntuacio.getLetresBonus()).anyMatch(puntuacio.getParaula()::contains)) {
                    puntsPercentatge += 0.01f;
                } else if (puntsPercentatge > 0) {
                    puntsFinals *= puntsPercentatge;

                } else if (puntsFinals > 0 || puntsTemps > 0) {
                    puntsFinals += puntsTemps;
                    assertEquals(puntsFinals, puntsTest);
                }
                System.out.println(puntsFinals);
                break;

            case 3:
                puntsPercentatge = 0.3f;
                puntsTemps*=puntuacio.getDificultat();
                if(Arrays.stream(puntuacio.getLetresBonus()).anyMatch(puntuacio.getParaula()::contains)){
                    puntsPercentatge += 0.01f;
                } else if (puntsPercentatge > 0) {
                    puntsFinals *= puntsPercentatge;

                } else if (puntsFinals > 0 || puntsTemps > 0) {
                    puntsFinals += puntsTemps;
                    assertEquals(puntsFinals, puntsTest);
                }
                System.out.println(puntsFinals);
                break;
            default:
                break;
        }

    }


    }