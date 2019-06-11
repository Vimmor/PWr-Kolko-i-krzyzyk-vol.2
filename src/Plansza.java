import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Plansza extends JPanel {
    private Border czarna_linia;
    private Pole[][] tablica_pol;
    private GridLayout layout = new GridLayout();
    private int zwycieska_ilosc;
    private int stan_globalny =0;
    private Pole[] wektor;
    private Zwyciesca zwyciesca = Zwyciesca.AI;

    public Plansza() {
        czarna_linia = BorderFactory.createLineBorder(Color.black);
        setLayout(layout);
        setVisible(false);
    }

    public void setWielkosc(int _wielkosc) {
        layout.setColumns(_wielkosc);
        layout.setRows(_wielkosc);
        tablica_pol = new Pole[_wielkosc][_wielkosc];
        wypelnij_tablice(_wielkosc);
    }
    //Metoda wypelniajaca tablice obiektami typu Pole
    public void wypelnij_tablice(int _wielkosc) {
        for (int i = 0; i < _wielkosc; i++) {
            for (int j = 0; j < _wielkosc; j++) {
                Pole p = new Pole(this);
                p.setWielkosc(_wielkosc);
                p.set_X(j);
                p.set_Y(i);
                p.setBackground(Color.WHITE);
                p.setBorder(czarna_linia);
                tablica_pol[i][j] = p;
                add(p);
            }
        }
    }

    //Metoda sprawdzajaca czy na przekatnej, w rzedzie lub w kolumnie planszy wystepuje zwycieska liczba figury
    public boolean sprawdz_zwyciestwo(int _wielkosc) {
        int pom, a, b;
        int tmp;
        int h = _wielkosc - 1;
        for (int i = 0; i < _wielkosc; i++) {
            for (int j = 0; j < _wielkosc; j++) {
                if (tablica_pol[i][j].getStan() == 0 || tablica_pol[i][j].getStan() == 1) {
                    pom = tablica_pol[i][j].getStan();
                    tmp = 1;
                    a = i;
                    b = j;
                    while (b != 0 && a != h && tablica_pol[a][b].getStan() == pom) {
                        if (tablica_pol[++a][--b].getStan() == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                    tmp = 1;
                    a = i;
                    b = j;
                    while (a != h && b != h && tablica_pol[a][b].getStan() == pom) {
                        if (tablica_pol[++a][++b].getStan() == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                    tmp = 1;
                    a = i;
                    b = j;
                    while (a != h && tablica_pol[a][b].getStan() == pom) {
                        if (tablica_pol[++a][b].getStan() == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                    tmp = 1;
                    a = i;
                    b = j;
                    while (b != h && tablica_pol[a][b].getStan() == pom) {
                        if (tablica_pol[a][++b].getStan() == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                }
            }
        }
        return false;
    }

    //Wypelniamy wektor obiektami typu Pole
    public void wypelnijWektor(int _wielkosc){
        ArrayList<Pole> lista  = new ArrayList<>();
        for(int i=0;i<_wielkosc;i++){
            for(int j=0;j<_wielkosc;j++){
                lista.add(tablica_pol[i][j]);
            }
        }
        wektor = new Pole[lista.size()];
        for(int i=0;i<wektor.length;i++)
            wektor[i]=lista.get(i);
    }

    //Sprawdzamy czy w wektorze wystepuje stan -1, co oznacza brak figury
    public boolean sprawdz_remis(Pole[] wektor){
        for(int i=0;i<wektor.length;i++)
            if(wektor[i].getStan()==-1)
                return false;
            return true;
    }

    //algorytm sztucznej inteligencji minmax
    public int alg_minMax(Pole[] wektor,int gracz,int _wielkosc){
        int min, max;
        if(sprawdz_zwyciestwo(_wielkosc)) return (gracz==1)?1:-1;

        if(sprawdz_remis(wektor)) return 0;
        gracz = (gracz==1)?0:1;
        max = (gracz==0)?10:-10;

        for (int i = 0; i < wektor.length; i++)
            if (wektor[i].getStan() == -1) {
                wektor[i].setStan(gracz);
                min = alg_minMax(wektor, gracz, _wielkosc);
                wektor[i].setStan(-1);
                if (((gracz == 0) && (min < max)) || ((gracz == 1) && (min > max)))
                    max = min;
            }
        return max;
    }

    //Metoda wyznaczajaca ruch dla sztucznej inteligencji
    int ruchAI(Pole[] wektor,int _wielkosc){
        int ruch = 0,min,max;
        max = -10;

        for(int i=0;i<wektor.length;i++){
            if(wektor[i].getStan()==-1){
                wektor[i].setStan(1);
                min = alg_minMax(wektor,1,_wielkosc);
                wektor[i].setStan(-1);
                if(min>max){
                    max = min;
                    ruch = i;
                }
            }
        }
        return ruch;
    }

    //Metoda sprawdzajaca czy jest mozliwe wykonanie ruchu, a nastepnie wybierajac pole odpowiednio zmienia jego stan
    public void ruch(Pole[] wektor,int gracz,int _wielkosc){
        int ruch;
        ruch = ruchAI(wektor,_wielkosc);
        if((ruch>=0)&&(ruch<wektor.length)&&(wektor[ruch].getStan()==-1))
            wektor[ruch].setStan(gracz);
    }
    //Enum okreslajacy zwyciesce
    public enum Zwyciesca{
        AI,GRACZ
    }

    public int getZwycieska_ilosc() {
        return zwycieska_ilosc;
    }

    public void setZwycieska_ilosc(int zwycieska_ilosc) {
        this.zwycieska_ilosc = zwycieska_ilosc;
    }

    public int getGlobalny_stan() {
        return stan_globalny;
    }

    public void setGlobalny_stan(int globalny_stan) {
        this.stan_globalny = globalny_stan;
    }

    public void setTablica_pol(Pole[][] tablica_pol) {
        this.tablica_pol = tablica_pol;
    }

    public Pole[][] getTablica_pol() {
        return tablica_pol;
    }

    public Pole[] getWektor() {
        return wektor;
    }

    public void setWektor(Pole[] wektor) {
        this.wektor = wektor;
    }

    public void setZwyciesca(Zwyciesca zwyciesca) {
        this.zwyciesca = zwyciesca;
    }

    public Zwyciesca getZwyciesca() {
        return zwyciesca;
    }
}
