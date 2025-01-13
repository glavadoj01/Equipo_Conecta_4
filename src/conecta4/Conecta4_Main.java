package conecta4;
/*
Desarrolla un programa que emule el juego 4 en raya o también llamado conecta 4.
Debe funcionar en consola, aparecer el tablero y las fichas de dos jugadores.

https://www.epasatiempos.es/juego-4-en-raya.php
*/

import java.util.Scanner;

public class Conecta4_Main {
    static Scanner escanear = new Scanner(System.in);
    static final String JUGADOR1 = "X";
    static final String JUGADOR2 = "O";
    static boolean victoria;
    static final int FILAS = 6;
    static final int COLUMNAS = 7;
    static String[][] tablero = new String[FILAS][COLUMNAS];
    static int contador;
    static int ultimoMovimiento;
    static int ultimaFila;
    static String jugadorActual;

    public static void main(String[] args) {
        boolean repetirPartida;
        do {
            victoria = false;
            contador = FILAS * COLUMNAS;
            rellenarTableroInicial();
            jugadorActual = JUGADOR1;
            cabecera();
            
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

            repetirPartida = volverAjugar();
        }while(repetirPartida);


        escanear.close();
    }

    public static void cabecera() {
        System.out.print("""
                ▐▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▌
                ▐ ░█░█░░░█▀▀░█▀█░░░█▀▄░█▀█░█░█░█▀█ ▌
                ▐ ░░▀█░░░█▀▀░█░█░░░█▀▄░█▀█░░█░░█▀█ ▌
                ▐ ░░░▀░░░▀▀▀░▀░▀░░░▀░▀░▀░▀░░▀░░▀░▀ ▌
                ▐▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▌
                """);
        System.out.println();
    }

    public static void rellenarTableroInicial() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = " ";
            }
        }
    }

    public static void pintarTablero() {
        //Números de cada columna
        System.out.print("   ");
        for (int j = 1; j <= COLUMNAS; j++) {
            System.out.print(j + "   ");
        }
        System.out.println();

        //Borde superior
        System.out.print("  ·");
        for (int j = 0; j < COLUMNAS; j++) {
            System.out.print("---·");
        }
        System.out.println();

        //Filas
        for (int i = 0; i < FILAS; i++) {
            System.out.print((i + 1) + " |");
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(" " + tablero[i][j] + " |");
            }
            System.out.println();

            //Lineas que separan cada fila
            System.out.print("  ·");
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print("---·");
            }
            System.out.println();
        }
        System.out.println();
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

    public static void comprobar4() {
        //Comprobación Horizontal a Izquierda
        int contarFichasH = 0;
        for (int j = ultimoMovimiento; j >= Math.max(0, (ultimoMovimiento - 3)); j--) {
            if (tablero[ultimaFila][j].equals(jugadorActual)) {
                contarFichasH++;
            } else {
                break;
            }
        }
        //Comprobación Horizontal a Derecha
        for (int j = ultimoMovimiento + 1; j <= Math.min(tablero[ultimaFila].length-1, (ultimoMovimiento + 3)); j++) {
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

    public static void cambiarJugador() {
        if (jugadorActual.equals(JUGADOR1)) {
            jugadorActual = JUGADOR2;
        } else {
            jugadorActual = JUGADOR1;
        }
    }

    public static void mensajeFinal() {
        if (victoria) {
            if (jugadorActual.equals(JUGADOR1)) {
                System.out.println("ENHORABUENA - HA GANADO EL JUGADOR 1 ( \"x\" )");
            }else{
                System.out.println("ENHORABUENA - HA GANADO EL JUGADOR 2 ( \"o\" )");
            }
            System.out.print("""
                    
                                                       .''.\s
                           .''.      .        *''*    :_\\/_:     .\s
                          :_\\/_:   _\\(/_  .:.*_\\/_*   : /\\ :  .'.:.'.\s
                      .''.: /\\ :   ./)\\   ':'* /\\ * :  '..'.  -=:o:=-\s
                     :_\\/_:'.:::.    ' *''*    * '.\\'/.' _\\(/_'.':'.'\s
                     : /\\ : :::::     *_\\/_*     -= o =-  /)\\    '  *\s
                      '..'  ':::'     * /\\ *     .'/.\\'.   '\s
                          *            *..*         :\s
                           *\s
                            *\s
                    """);
        } else {
            System.out.println("La partida ha finalizado en empate");
        }
    }


}
