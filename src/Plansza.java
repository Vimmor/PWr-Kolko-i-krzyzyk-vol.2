import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Plansza extends JPanel {
    Border czarna_linia;
    Pole[][] tablica_pol;
    GridLayout layout = new GridLayout();
    int zwycieska_ilosc;

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

    public void wypelnij_tablice(int _wielkosc) {
        for (int i = 0; i < _wielkosc; i++) {
            for (int j = 0; j < _wielkosc; j++) {
                Pole p = new Pole(this);
                p.setWielkosc(_wielkosc);
                p.setX(j);
                p.setY(i);
                p.setBackground(Color.white);
                p.setBorder(czarna_linia);
                tablica_pol[i][j] = p;
                add(p);
            }
        }
    }

    public boolean sprawdz_zwyciestwo(int _wielkosc) {
        int pom, a, b;
        int tmp;
        int h = _wielkosc - 1;
        for (int i = 0; i < _wielkosc; i++) {
            for (int j = 0; j < _wielkosc; j++) {
                if (tablica_pol[i][j].stan == 0 || tablica_pol[i][j].stan == 1) {
                    pom = tablica_pol[i][j].stan;
                    tmp = 1;
                    a = i;
                    b = j;
                    while (b != 0 && a != h && tablica_pol[a][b].stan == pom) {
                        if (tablica_pol[++a][--b].stan == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                    tmp = 1;
                    a = i;
                    b = j;
                    while (a != h && b != h && tablica_pol[a][b].stan == pom) {
                        if (tablica_pol[++a][++b].stan == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                    tmp = 1;
                    a = i;
                    b = j;
                    while (a != h && tablica_pol[a][b].stan == pom) {
                        if (tablica_pol[++a][b].stan == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                    tmp = 1;
                    a = i;
                    b = j;
                    while (b != h && tablica_pol[a][b].stan == pom) {
                        if (tablica_pol[a][++b].stan == pom) tmp++;
                        else tmp = 0;
                        if (tmp == zwycieska_ilosc) return true;
                    }
                }
            }
        }
        return false;
    }

    public int getZwycieska_ilosc() {
        return zwycieska_ilosc;
    }

    public void setZwycieska_ilosc(int zwycieska_ilosc) {
        this.zwycieska_ilosc = zwycieska_ilosc;
    }
}
