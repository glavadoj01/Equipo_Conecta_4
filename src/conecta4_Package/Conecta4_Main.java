package conecta4_Package;
/*
Desarrolla un programa que emule el juego 4 en raya o también llamado conecta 4.
Debe funcionar en consola, aparecer el tablero y las fichas de dos jugadores.

https://www.epasatiempos.es/juego-4-en-raya.php
*/

import java.util.Scanner;

public class Conecta4_Main {
    static Scanner escanear = new Scanner(System.in);
    static char jugador1 = 'x';
    static char jugador2 = 'o';
    static boolean victoria;
    static String[][] tablero = new String[7][6];
    static int contador; // Para terminar si no quedan huecos libres
    static int ultimoMovimiento;
    static int ultimaFila;
    static char jugadorActual;


    public static void main(String[] args) {
        victoria = false;
        contador = tablero.length * tablero[0].length;
        int ultimoMovimiento = 0;
        int ultimaFila = 0;
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
        int contarFichasH = 0;
        //Comprobación Horizontal a Izquierda
        for (int j = ultimoMovimiento; j >= Math.max(0, (ultimoMovimiento - 3)); j--) {
            if (tablero[ultimaFila][j].equals(jugadorActual)) {
                contarFichasH++;
            } else {
                break;
            }
        }
        //Comprobación Horizontal a Derecha
        for (int j = ultimoMovimiento + 1; j <= Math.min(tablero[ultimaFila].length, (ultimoMovimiento + 3)); j--) {
            if (tablero[ultimaFila][j].equals(jugadorActual)) {
                contarFichasH++;
            } else {
                break;
            }
        }
        if (contarFichasH >= 4) {
            victoria = true;
        }

        // Comprobación Vertical
        int contarFichasV = 0;
        for (int i = ultimaFila; i >= Math.max(0, ultimaFila - 3); i--) {
            if (tablero[i][ultimoMovimiento].equals(jugadorActual)) {
                contarFichasV++;
            } else {
                break;
            }
        }
        if (contarFichasV == 4) {
            victoria = true;
        }

        // Comprobación D1 a izquierda/arriba
        int contarFichasD1 = 0;
        int k = 0;
        for (int i = ultimaFila; i >= Math.max(0, ultimaFila - 3); i--) {
            if (tablero[i][ultimoMovimiento-k].equals(jugadorActual)) {
                contarFichasD1++;
            } else {
                break;
            }
            k++;
            if (ultimoMovimiento-k < 0){
                break;
            }
        }
        // Comprobación D1 a derecha/abajo
        k=1;
        for (int i = ultimaFila+1; i >= Math.min(ultimaFila+3,tablero.length); i++) {
            if (tablero[i][ultimoMovimiento+k].equals(jugadorActual)) {
                contarFichasD1++;
            } else {
                break;
            }
            k++;
            if (ultimoMovimiento+k >= tablero[ultimaFila].length){
                break;
            }
        }
        if (contarFichasD1 >= 4) {
            victoria = true;
        }

        // Comprobación D2 a derecha/arriba
        int contarFichasD2 = 0;
        k=0;
        for (int i = ultimaFila; i >= Math.max(0, ultimaFila - 3); i--) {
            if (tablero[i][ultimoMovimiento+k].equals(jugadorActual)) {
                contarFichasD1++;
            } else {
                break;
            }
            k++;
            if (ultimoMovimiento+k >= tablero[ultimaFila].length){
                break;
            }
        }

        // Comprobación D2 a izquierda/abajo
        k=1;
        for (int i = ultimaFila+1; i <= Math.min(ultimaFila+3,tablero.length); i++) {
            if (tablero[i][ultimoMovimiento-k].equals(jugadorActual)) {
                contarFichasD2++;
            } else {
                break;
            }
            k++;
            if (ultimoMovimiento-k < 0){
                break;
            }
        }
        if (contarFichasD2 >= 4) {
            victoria = true;
        }
    }

    public static void reyenarTableroInicial() {

    }

    public static void cambiarJugador() {

    }

    public static void mensajeFinal() {

    }
}
