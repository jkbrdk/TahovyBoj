
package cz.itnetwork;

public class Bojovnik {
    /**
     * Jméno bojovníka
     */
    protected String jmeno;
    /**
     * Život v HP
     */
    protected int zivot;
    /**
     * Maximální zdraví
     */
    protected int maximalniZivot;
    /**
     * Útok v HP
     */
    protected int utok;
    /**
     * Obrana v HP
     */
    protected int obrana;
    /**
     * Instance hrací kostky
     */
    protected Kostka kostka;
    /**
     * Poslední zpráva
     */
    protected String zprava;

    /**
     * Vytvoří novou instanci bojovníka
     *
     * @param jmeno  Jméno bojovníka
     * @param zivot  Život v HP
     * @param utok   Útok v HP
     * @param obrana Obrana v HP
     * @param kostka Instance hrací kostky
     */
    public Bojovnik(String jmeno, int zivot, int utok, int obrana, Kostka kostka) {
        this.jmeno = jmeno;
        this.zivot = zivot;
        this.maximalniZivot = zivot;
        this.utok = utok;
        this.obrana = obrana;
        this.kostka = kostka;
    }

    /**
     * Provede útok na soupeře
     *
     * @param souper Soupeř bojovník
     */
    public void utoc(Bojovnik souper) {
        int uder = utok + kostka.hod();
        nastavZpravu(String.format("%s útočí s úderem za %s hp", jmeno, uder));
        souper.branSe(uder);
    }

    /**
     * Brání se proti úderu soupeře
     *
     * @param uder Úder soupeře v HP
     */
    public void branSe(int uder) {
        int zraneni = uder - (obrana + kostka.hod());
        if (zraneni > 0) {
            zivot -= zraneni;
            zprava = String.format("%s utrpěl poškození %s hp", jmeno, zraneni);
            if (zivot <= 0) {
                zivot = 0;
                zprava += " a zemřel";
            }
        } else {
            zprava = String.format("%s odrazil útok", jmeno);
        }
        nastavZpravu(zprava);
    }

    /**
     * Zjistí, zda je bojovník naživu
     *
     * @return True, pokud je naživu, jinak false
     */
    public boolean jeZivy() {
        return (zivot > 0);
    }

    /**
     * Nastaví zprávu o útoku nebo obraně
     *
     * @param zprava Zpráva o útoku nebo obraně
     */
    protected void nastavZpravu(String zprava) {
        this.zprava = zprava;
    }

    /**
     * Vrátí poslední zprávu o útoku nebo obraně
     *
     * @return Poslední zpráva o útoku nebo obraně
     */
    public String vratPosledniZpravu() {
        return zprava;
    }

    /**
     * Vrátí grafický ukazatel na základě aktuálního a maximálního zdraví
     * 
     * @param aktualni  Aktuální zdraví bojovníka
     * @param maximalni Maximální zdraví bojovníka
     * @return Řetězec s grafickou reprezentací života
     */
    protected String grafickyUkazatel(int aktualni, int maximalni) {
        String grafickyZivot = "";
        int celkem = 20;
        double pocetDilku = Math.round(((double) aktualni / maximalni) * celkem);
        if ((pocetDilku == 0) && (jeZivy())) {
            pocetDilku = 1;
        }
        for (int i = 0; i < pocetDilku; i++) {
            grafickyZivot += "█";
        }
        for (int i = 0; i < celkem - pocetDilku; i++) {
            grafickyZivot += " ";
        }
        return grafickyZivot;
    }

    /**
     * Vrátí grafickou reprezentaci života bojovníka
     *
     * @return Řetězec s grafickou reprezentací života
     */
    public String grafickyZivot() {
        return grafickyUkazatel(zivot, maximalniZivot);
    }

    /**
     * Vrací textovou reprezentaci bojovníka
     *
     * @return Textová reprezentace bojovníka
     */
    @Override
    public String toString() {
        return jmeno;
    }
}