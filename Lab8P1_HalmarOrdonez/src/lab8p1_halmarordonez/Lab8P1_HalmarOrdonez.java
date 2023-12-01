package lab8p1_halmarordonez;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Lab8P1_HalmarOrdonez {

    static Scanner read = new Scanner(System.in);
    static Random rng = new Random();
    static ArrayList<String> lista = new ArrayList<>();

    public static void main(String[] args) {
        char syn = 's';
        while (syn == 's' || syn == 'S') {
            System.out.println("-----Menu-----");
            System.out.println("1. Game of life");
            System.out.println("2. Salir del programa");
            System.out.print("Ingresar opcion: ");
            int opc = read.nextInt();

            switch (opc) {
                case 1 -> {
                    System.out.println("-----Game of life-----");
                    System.out.print("Cuantas rondas desea jugar?: ");
                    int round = read.nextInt();

                    jugar(round);

                }
                case 2 -> {
                    System.out.println("Saliendo del programa...");
                    syn = 'n';
                }
                default -> {
                    System.out.println("Opcion invalida");
                }
            }
            if (opc != 2) {
                System.out.print("Desea regresar al menu inicial(s/n)?: ");
                syn = read.next().charAt(0);
            }
        }
    }

    public static void imprimir(int[][] matriz) {
        int[][]temp=new int[10][10];
        for(int i=0;i<lista.size();i++){
            String cord=lista.get(i);
            char x=cord.charAt(0);
            char y=cord.charAt(2);
            int numx = x - '0';
            int numy= y-'0';
            temp[numx][numy]=1;
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                System.out.print(temp[i][j]+ " ");
            }
            System.out.println();
        }
    }//FIN IMPRIMIR

    public static int[][] rngfill() {
        int[][] temp = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != 0 && i != 9 && j != 0 && j != 9) {
                    int rand = rng.nextInt((1 - 0) + 1) + 0;
                    temp[i][j] = rand;
                    if (rand == 1) {
                        lista.add(i + ":" + j);
                    }

                } else {
                    temp[i][j] = 0;
                }
            }
        }
        return temp;
    }

    public static void jugar(int round) {
        int[][] actual = rngfill();
        System.out.println("Tablero inicial: ");
        imprimir(actual);
        for (int i = 0; i < round; i++) {
            int[][] siguiente = nextGen(actual);
            System.out.println("Coordenadas de celdas vivas: " + lista);
            System.out.println("------Ronda" + (i + 1) + "------");
            imprimir(siguiente);
           

            actual = siguiente;

        }
        int[][] siguiente = nextGen(actual);
        System.out.println("Coordenadas de celdas vivas: " + lista);
    }

    public static int[][] nextGen(int[][] actual) {
        lista.clear();
        int[][] siguiente = new int[10][10];
        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual.length; j++) {
                if (actual[i][j] == 1) {
                    lista.add(i + ":" + j);
                    int acum = actual[i - 1][j];
                    acum += actual[i + 1][j];
                    acum += actual[i - 1][j];
                    acum += actual[i][j - 1];
                    acum += actual[i][j + 1];
                    acum += actual[i - 1][j + 1];
                    acum += actual[i + 1][j - 1];
                    acum += actual[i - 1][j - 1];
                    acum += actual[i + 1][j + 1];

                    if (acum == 2) {
                        siguiente[i][j] = 1;
                    } else {
                        siguiente[i][j] = 0;
                    }
                } else if (actual[i][j] == 0 && (i != 0 && i != 9 && j != 0 && j != 9)) {
                    int acum = actual[i - 1][j];
                    acum += actual[i + 1][j];
                    acum += actual[i - 1][j];
                    acum += actual[i][j - 1];
                    acum += actual[i][j + 1];
                    acum += actual[i - 1][j + 1];
                    acum += actual[i + 1][j - 1];
                    acum += actual[i - 1][j - 1];
                    acum += actual[i + 1][j + 1];

                    if (acum == 3) {
                        siguiente[i][j] = 1;
                    } else {
                        siguiente[i][j] = 0; //Else es innecessario pero me ayuda a mantener orden
                    }
                }

            }
        }
        return siguiente;
    }
}
