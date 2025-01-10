package conecta4_Package;
/*
Desarrolla un programa que emule el juego 4 en raya o también llamado conecta 4.
Debe funcionar en consola, aparecer el tablero y las fichas de dos jugadores.

https://www.epasatiempos.es/juego-4-en-raya.php
*/

import java.util.Scanner;

public class Conecta4_Main {
    static Scanner escanear = new Scanner(System.in);
    static String jugador1 = "x";
    static String jugador2 = "o";
    static boolean victoria;
    static String[][] tablero = new String[7][6];
    static int contador; // Para terminar si no quedan huecos libres
    static int ultimoMovimiento;
    static int ultimaFila;
    static String jugadorActual;


    public static void main(String[] args) {
        victoria = false;
        contador = tablero.length * tablero[0].length;
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
        pintarTablero();
        mensajeFinal();
    }

    public static void jugadorInserta() {
        int movActual;
        boolean movValido = false;

        while (!movValido) {

            while (true) {
                System.out.print("Ingrese un movimiento valido: ");
                movActual = escanear.nextInt();
                if (movActual >= 1 && movActual <= 6) {
                    movActual = movActual - 1;
                    break;
                }
                System.out.println("ERROR - Introducir una columna valida");
            }
            for (int i = tablero.length - 1; i >= 0; i--) {
                if (tablero[i][movActual].equals(" ")) {
                    tablero[i][movActual] = jugadorActual;
                    ultimoMovimiento = movActual;
                    ultimaFila = i;
                    contador--;
                    movValido = true;
                    break;
                }
            }
            if (!movValido) {
                System.out.println("ERROR - La columna seleccionada está llena");
            }
        }
    }

    public static void pintarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "\t");
            }
            System.out.print("\b\n");
        }
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
        for (int j = ultimoMovimiento + 1; j <= Math.min(tablero[ultimaFila].length-1, (ultimoMovimiento + 3)); j--) {
            if (tablero[ultimaFila][j].equals(jugadorActual)) {
                contarFichasH++;
            } else {
                break;
            }
        }
        if (contarFichasH >= 4) {
            victoria = true;
        }

        // Comprobación Vertical hacia abajo
        int contarFichasV = 0;
        for (int i = ultimaFila; i <= Math.min(tablero.length-1, ultimaFila + 3); i++) {
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
            if (tablero[i][ultimoMovimiento - k].equals(jugadorActual)) {
                contarFichasD1++;
            } else {
                break;
            }
            k++;
            if (ultimoMovimiento - k < 0) {
                break;
            }
        }
        // Comprobación D1 a derecha/abajo
        k = 1;
        for (int i = ultimaFila + 1; i <= Math.min(ultimaFila + 3, tablero.length-1); i++) {
            if (ultimoMovimiento + k >= tablero[ultimaFila].length) {
                break;
            }
            if (tablero[i][ultimoMovimiento + k].equals(jugadorActual)) {
                contarFichasD1++;
            } else {
                break;
            }
            k++;
        }
        if (contarFichasD1 >= 4) {
            victoria = true;
        }

        // Comprobación D2 a derecha/arriba
        int contarFichasD2 = 0;
        k = 0;
        for (int i = ultimaFila; i >= Math.max(0, ultimaFila - 3); i--) {
            if (tablero[i][ultimoMovimiento + k].equals(jugadorActual)) {
                contarFichasD2++;
            } else {
                break;
            }
            k++;
            if (ultimoMovimiento + k >= tablero[ultimaFila].length-1) {
                break;
            }
        }

        // Comprobación D2 a izquierda/abajo
        k = 1;
        for (int i = ultimaFila + 1; i <= Math.min(ultimaFila + 3, tablero.length-1); i++) {
            if (ultimoMovimiento - k < 0) {
                break;
            }
            if (tablero[i][ultimoMovimiento - k].equals(jugadorActual)) {
                contarFichasD2++;
            } else {
                break;
            }
            k++;
        }
        if (contarFichasD2 >= 4) {
            victoria = true;
        }
    }

    public static void reyenarTableroInicial() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = " ";
            }
        }
    }

    public static void cambiarJugador() {
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
    }

    public static void mensajeFinal() {
        if (victoria) {
            if (jugadorActual==jugador1) {
                System.out.println("ENHORABUENA - HA GANADO EL Jugador 1 ( \"x\" )");
            }else{
                System.out.println("ENHORABUENA - HA GANADO EL Jugador 2 ( \"o\" )");
            }
        } else {
            System.out.println("La partida ha finalizado en empate");
        }
    }
}
