import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pole extends JPanel {
    int x,y,stan,wielkosc;

    public Pole(Plansza plansza) {
        super();
        stan = -1;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                stan = 0;
                plansza.wypelnijWektor(wielkosc);
                repaint();
                /*
                if(plansza.getGlobalny_stan()==0){
                    stan=0;
                    plansza.setGlobalny_stan(1);
                }
                else if(plansza.getGlobalny_stan()==1){
                    stan=1;
                    plansza.setGlobalny_stan(0);
                }*/
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                plansza.ruchGracza(plansza.getWektor(), 1, wielkosc);
                plansza.repaint();
                if (plansza.sprawdz_zwyciestwo(wielkosc) == true) {

                    JOptionPane.showMessageDialog(null, "Wygrywa gracz X", "Wygrana", JOptionPane.INFORMATION_MESSAGE);//System.out.println("Wygryw");
                }
                if (plansza.sprawdz_remis(plansza.getWektor()))
                    JOptionPane.showMessageDialog(null, "Remis", "Remis ", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D grafika2d = (Graphics2D) g;
        // Stroke opisuje ksztalt rysowanych pozniej figur, definiuje atrybuty renderowanie grafiki2D
        grafika2d.setStroke(new BasicStroke(3));
        if (stan == 0) {
            grafika2d.drawLine(0, 0, (800 + 60) / wielkosc, 800 / wielkosc);
            grafika2d.drawLine(840 / wielkosc, 0, 0, 780 / wielkosc);
        }
        if(stan == 1 )
            grafika2d.drawOval(0,0,840/wielkosc,780/wielkosc);
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public int getStan() {
        return stan;
    }

    public void set_X(int x) {
        this.x = x;
    }

    public int get_X() {
        return x;
    }

    public int get_Y() {
        return y;
    }

    public void set_Y(int y) {
        this.y = y;
    }

    public void setWielkosc(int _wielkosc) {
        this.wielkosc = _wielkosc;
    }

    public int getWielkosc(){
        return wielkosc;
    }

}
