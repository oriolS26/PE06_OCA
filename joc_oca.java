import java.util.Random;
import java.util.Scanner;

public class joc_oca {

    Scanner sc = new Scanner(System.in);
    Random rnd = new Random();

    public static void main(String[] args) {
        joc_oca p = new joc_oca();
        p.principal();
    }
    public void principal() {

        int numJugadors = demanarNumJugadors();
        
        String[] noms = new String[numJugadors];
        int[] posicio = new int[numJugadors];
        int[] penalitzacio = new int [numJugadors];

        demanarNomsJugadors(noms, posicio, penalitzacio);

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
            int n = 0;
            boolean correcte = false;

            do {
                try {
                    System.out.println("Introdueix el nombre de jugadors (2-4): ");
                    n = Integer.parseInt(sc.nextLine());

                    if (n < 2 || n >4) {
                        correcte = true;
                    } else {
                        System.out.println("Error: El nombre de jugadors ha d'estar entre 2 i 4.");
                    }
                }

                catch (NumberFormatException e) {
                    System.out.println("Error: Si us plau, introdueix un nombre vàlid.");
                } 

                catch (Exception e) {
                    System.out.println("Error inesperat: " + e.getMessage());
                }
            }while(!correcte);

            return n;

        }

        public void demanarNomsJugadors(String[] noms, int[] posicio, int[] penalitzacio) {
            for (int i = 0; i < noms.length; i++) {
                System.out.println("Introdueix el nom del jugador " + (i + 1) + ": ");
                noms[i] = sc.nextLine();
                posicio[i] = 0;
                penalitzacio[i] = 0;
            }

        }

        public int[] tirarDaus() {

        }

        public boolean gestionarCasella() {
            
        }
    }
