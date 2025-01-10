package conecta4_Package;
/*
Desarrolla un programa que emule el juego 4 en raya o también llamado conecta 4.
Debe funcionar en consola, aparecer el tablero y las fichas de dos jugadores.

https://www.epasatiempos.es/juego-4-en-raya.php
*/

import java.util.Scanner;

public class Conecta4_Main {
    static Scanner sc = new Scanner(System.in);
    static char jugador1 = 'x';
    static char jugador2 = 'o';
    static boolean victoria = false;
    static String [][] tablero = new String [7][6];
    static int contador = tablero.length * tablero[0].length; // Para terminar si no quedan huecos libres
    static char jugadorActual;


    public static void main(String[] args) {
        reyenarTableroInicial();
        jugadorActual = jugador1;

        while (true) {
            pintarTablero();
            jugadorInserta();
            comprobar4();
            if (victoria || contador == 0) {
                break;
            }
            cambiarJugador();
        }
        mensajeFinal();
    }

    public static void jugadorInserta() {

    }

    public static void pintarTablero() {

    }

    public static void comprobar4() {

    }

    public static void reyenarTableroInicial() {

    }

    public static void cambiarJugador() {

    }

    public static void mensajeFinal() {

    }
}
