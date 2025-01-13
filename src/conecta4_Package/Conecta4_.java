package conecta4;

import java.util.Scanner;

public class Conecta4_ {
    static Scanner escanear = new Scanner(System.in);
    static final String JUGADOR1 = "X";
    static final String JUGADOR2 = "O";
    static boolean victoria;
    static final int FILAS = 6; //
    static final int COLUMNAS = 7; //
    static String[][] tablero = new String[FILAS][COLUMNAS];


    static int contador = FILAS * COLUMNAS;
    static int ultimoMovimiento;
    static int ultimaFila;
    static String jugadorActual;

    public static void main(String[] args) {
        boolean repetirPartida;
        do {
            victoria = false;
            jugadorActual = JUGADOR1;
            rellenarTableroInicial();

            while (true) {
                cabecera();
                pintarTablero();
                jugadorInserta();
                comprobar4();
                if (victoria || !hayHuecosDisponibles()) {
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
        while (true) {
            movActual = solicitarColumna();
            if (!columnaLlena(movActual)) {
                for (int i = FILAS - 1; i >= 0; i--) {
                    if (tablero[i][movActual].equals(" ")) {
                        tablero[i][movActual] = jugadorActual;
                        ultimoMovimiento = movActual;
                        ultimaFila = i;
                        contador--;
                        return;
                    }
                }
            } else {
                System.out.println("ERROR - La columna seleccionada está llena. Intente nuevamente.");
                System.out.println();
            }
        }
    }

    public static int solicitarColumna() {
        String entrada;
        int columna = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print("Jugador \"" + jugadorActual + "\", ingrese una columna válida (1-" + COLUMNAS + "): ");
            entrada = escanear.nextLine();
            if (esNumero(entrada)) {
                columna = Integer.parseInt(entrada) - 1; // Convertir a índice
                if (columna >= 0 && columna < COLUMNAS) {


                    valido = true;
                } else {

                    System.out.println("ERROR -" +
                            " Columna fuera de rango. Debe estar entre 1 y " + COLUMNAS + ".");
                    System.out.println();
                }
            } else {
                System.out.println("ERROR - Debe ingresar un número entero.");
                System.out.println();
            }
        }
        return columna;
    }

    public static boolean esNumero(String entrada) {
        for (int i = 0; i < entrada.length(); i++) {
            if (!Character.isDigit(entrada.charAt(i))) {
                return false;
            }
        }
        return !entrada.isEmpty();
    }

    public static boolean columnaLlena(int columna) {
        for (int i = 0; i < FILAS; i++) {
            if (tablero[i][columna].equals(" ")) {
                return false;
            }
        }
        return true;
    }

    public static void comprobar4() {
        // Comprobar todas las direcciones
        if (contarFichas(ultimaFila, ultimoMovimiento, 0, 1) + contarFichas(ultimaFila, ultimoMovimiento, 0, -1) - 1 >= 4) {
            victoria = true; // Horizontal
        } else if (contarFichas(ultimaFila, ultimoMovimiento, 1, 0) >= 4) {
            victoria = true; // Vertical
        } else if (contarFichas(ultimaFila, ultimoMovimiento, 1, 1) + contarFichas(ultimaFila, ultimoMovimiento, -1, -1) - 1 >= 4) {
            victoria = true; // Diagonal principal (\)
        } else if (contarFichas(ultimaFila, ultimoMovimiento, 1, -1) + contarFichas(ultimaFila, ultimoMovimiento, -1, 1) - 1 >= 4) {
            victoria = true; // Diagonal secundaria (/)
        }
    }

    public static int contarFichas(int fila, int columna, int deltaFila, int deltaColumna) {
        int contar = 0;
        while (fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS) {
            if (tablero[fila][columna].equals(jugadorActual)) {
                contar++;
            } else {
                break;
            }
            fila += deltaFila;
            columna += deltaColumna;
        }
        return contar;
    }

    public static boolean hayHuecosDisponibles() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (tablero[i][j].equals(" ")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void cambiarJugador() {
        jugadorActual = jugadorActual.equals(JUGADOR1) ? JUGADOR2 : JUGADOR1;
    }

    //Función que pregunta si se desea repetir la partida
    public static boolean volverAjugar() {
        String respuesta;
        while (true) {
            System.out.print("¿Otra partida? (S/N): ");
            respuesta = escanear.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
                return true;
            } else if (respuesta.equals("N")) {
                return false;
            } else {
                System.out.println("Elige S para seguir o N para salir del juego");
            }
        }
    }

    public static void mensajeFinal() {
        if (victoria) {
            System.out.println("¡ENHORABUENA! Ha ganado el jugador \"" + jugadorActual + "\".");
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
            System.out.println("La partida ha terminado en empate.");
        }
    }
}

