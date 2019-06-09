import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Plansza extends JPanel {
    Border czarna_linia;
    Pole[][] tablica_pol;
    GridLayout layout = new GridLayout();

    public Plansza(){
        czarna_linia = BorderFactory.createLineBorder(Color.BLACK);
        setLayout(layout);
        setVisible(false);
    }

    public void setSize(int _wielkosc){
        layout.setColumns(_wielkosc);
        layout.setRows(_wielkosc);
        tablica_pol = new Pole[_wielkosc][_wielkosc];
        wypelnij_tablice(_wielkosc);
    }

    public void wypelnij_tablice(int _wielkosc){
        for(int i=0;i<_wielkosc;i++){
            for(int j=0;j<_wielkosc;j++){
                Pole p = new Pole(this);
                p.setX(j);
                p.setY(i);
                p.setBackground(Color.white);
                p.setBorder(czarna_linia);
                tablica_pol[i][j]=p;
                add(p);
            }
        }
    }

}
