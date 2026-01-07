import java.util.Random;
import java.util.Scanner;

public class joc_oca {
    public static void main(String[] args) {
        joc_oca p = new joc_oca();
        p.principal();
    }
    public void principal() {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        int numJugadors = demanarNumJugadors();
        
        String[] noms = new String[numJugadors];
        int[] posicio = new int[numJugadors];
        int[] penalitzacio = new int [numJugadors];

        demanarNumJugadors();

        boolean guanyador = false;
        int torn = 0;

        while(!guanyador) {

            int jugador = torn % numJugadors;
            System.out.println("\nÉs el torn del jugador " + (jugador + 1) + ", " + noms[jugador]);

            if (penalitzacio[jugador] > 0) {
                penalitzacio[jugador]--;
                System.out.println("No pots tirar. Torns restants de penalització: " + penalitzacio[jugador]);
                torn++;
                continue;
            }

            System.out.println(">> tiro");
            sc.nextLine();

            boolean tornarATirar;

            do {
                tornarATirar = false;
                int daus[] = tirarDaus();
                int suma = daus[0] + daus[1];

                System.out.println("Has tret un " + daus[0] + " i un " + daus[1] + ", sumant " + suma);

                posicio[jugador] += suma;

                if (posicio[jugador] > 63) {
                    posicio[jugador] = 63 - (posicio[jugador] - 63);
                    
                }

                System.out.println("Has superat la casella 63! Retrocedeixes a la casella " + posicio[jugador]);

                tornarATirar = gestionarCasella();

                if(posicio[jugador] == 63) {
                    guanyador = true;
                    System.out.println("Enhorabona " + noms[jugador] + ", has guanyat la partida!");
                
                }

            } while (tornarATirar && !guanyador);
            torn++;
            }
        }

        public int demanarNumJugadors() {

        }

        public void demanarNomsJugadors() {

        }

        public int[] tirarDaus() {

        }

        public boolean gestionarCasella() {
            
        }
    }
