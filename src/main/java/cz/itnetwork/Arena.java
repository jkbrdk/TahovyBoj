
package cz.itnetwork;

class Arena {

    /**
     * První bojovník v aréně
     */
    private Bojovnik bojovnikA;
    /**
     * Druhý bojovník v aréně
     */
    private Bojovnik bojovnikB;
    /**
     * Hrací kostka
     */
    private Kostka kostka;

    /**
     * Vytvoří novou instanci arény
     *
     * @param bojovnikA První bojovník v aréně
     * @param bojovnikB Druhý bojovník v aréně
     * @param kostka    Hrací kostka
     */
    public Arena(Bojovnik bojovnikA, Bojovnik bojovnikB, Kostka kostka) {
        this.bojovnikA = bojovnikA;
        this.bojovnikB = bojovnikB;
        this.kostka = kostka;
    }

    /**
     * Simuluje zápas bojovníků
     */
    public void zapas() {
        // původní pořadí
        Bojovnik bojovnikA = this.bojovnikA;
        Bojovnik bojovnikB = this.bojovnikB;
        System.out.println("Vítejte v aréně!");
        System.out.printf("Dnes se utkají %s s %s! %n%n", bojovnikA, bojovnikB);
        // prohození bojovníků
        boolean zacinaBojovnikB = (kostka.hod() <= kostka.vratPocetSten() / 2);
        if (zacinaBojovnikB) {
            bojovnikA = this.bojovnikB;
            bojovnikB = this.bojovnikA;
        }
        System.out.printf("Začínat bude bojovník %s! %nZápas může začít...%n", bojovnikA);

        // cyklus s bojem
        while (bojovnikA.jeZivy() && bojovnikB.jeZivy()) {
            bojovnikA.utoc(bojovnikB);
            vykresli();
            vypisZpravu(bojovnikA.vratPosledniZpravu()); // zpráva o útoku
            vypisZpravu(bojovnikB.vratPosledniZpravu()); // zpráva o obraně
            if (bojovnikB.jeZivy()) {
                bojovnikB.utoc(bojovnikA);
                vykresli();
                vypisZpravu(bojovnikB.vratPosledniZpravu()); // zpráva o útoku
                vypisZpravu(bojovnikA.vratPosledniZpravu()); // zpráva o obraně
            }
            System.out.println();
        }
    }

    /**
     * Vypíše bojovníka
     * 
     * @param bojovnik Bojovník k vypsání
     */
    private void vypisBojovnika(Bojovnik bojovnik) {
        System.out.println(bojovnik);
        System.out.print("Život: ");
        System.out.println(bojovnik.grafickyZivot());

        if (bojovnik instanceof Mag) {
            System.out.print("Mana: ");
            System.out.println(((Mag) bojovnik).grafickaMana());
        }
    }

    /**
     * Vykreslí informační obrazovku
     */
    private void vykresli() {
        System.out.println(""
                + "   __    ____  ____  _  _    __   \n"
                + "  /__\\  (  _ \\( ___)( \\( )  /__\\  \n"
                + " /(__)\\  )   / )__)  )  (  /(__)\\ \n"
                + "(__)(__)(_)\\_)(____)(_)\\_)(__)(__)\n");
        System.out.println("Bojovníci: \n");
        vypisBojovnika(bojovnikA);
        System.out.println();
        vypisBojovnika(bojovnikB);
        System.out.println();
    }

    /**
     * Vypíše zprávu do konzole s dramatickou pauzou
     *
     * @param zprava Zpráva
     */
    private void vypisZpravu(String zprava) {
        System.out.println(zprava);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println("Chyba, nepodařilo se uspat vlákno!");
        }
    }
}