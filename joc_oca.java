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
                int daus[] = tirarDaus(posicio[jugador]);
                int suma = daus[0] + daus[1];

                System.out.println("Has tret un " + daus[0] + " i un " + daus[1] + ", sumant " + suma);

                posicio[jugador] += suma;

                if (posicio[jugador] > 63) {
                    posicio[jugador] = 63 - (posicio[jugador] - 63);
                    System.out.println("Has superat la casella 63! Retrocedeixes a la casella " + posicio[jugador]);

                }

                tornarATirar = gestionarCasella(jugador, posicio, penalitzacio);

                System.out.println("Estàs a la casella " + posicio[jugador]);

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

                    if (n >= 2 && n <= 4) {
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

        public int[] tirarDaus(int posicioActual) {
            int dau1 = rnd.nextInt(6) + 1;
            int dau2 = 0;

            if (posicioActual < 60) {
                dau2 = rnd.nextInt(6) + 1;
            }

            return new int[] {dau1, dau2};
        }

        public boolean gestionarCasella(int j, int[] posicio, int[] penalitzacio) {

            int casella = posicio[j];

            //Oques
            int[] oques = {5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59};
            for (int i = 0; i < oques.length - 1; i++) {
                if (casella == oques[i]) {
                    System.out.println("Oca. De oca en oca i tiro perquè hem toca");
                    posicio[j] = oques[i + 1];
                    System.out.println("Avances a la casella " + posicio[j]);
                    return true;    
                }
            }
            //Ponts
            if (casella == 6) {
                posicio[j] = 12;
                System.out.println("Has caigut al pont! Avances a la casella " + posicio[j]);
                return true;
            }
            else if (casella == 12) {
                posicio[j] = 6;
                System.out.println("Has caigut al pont! Retrocedeixes a la casella " + posicio[j]);
                return false;
            }

            //Fonda
            if (casella == 19) {
                penalitzacio[j] = 1;
                System.out.println("Has caigut a la fonda! Perds 1 torn.");
            }

            //Pou
            if (casella == 31) {
                penalitzacio[j] = 2;
                System.out.println("Has caigut al pou! Perds 2 torns.");
            }

            //Laberint
            if (casella == 42) {
                posicio[j] = 39;
                System.out.println("Has caigut al laberint! Retrocedeixes a la casella " + posicio[j]);
            }

            //Presó
            if (casella == 52) {
                penalitzacio[j] = 3;
                System.out.println("Has caigut a la presó! Perds 3 torns.");
            }

            //Mort
            if (casella == 58) {
                posicio[j] = 0;
                System.out.println("Has caigut a la mort! Tornes a la casella " + posicio[j]);
            }

            return false;

        }
    }
