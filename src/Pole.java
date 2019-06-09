import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pole extends JPanel {
    private int x,y,stan,wielkosc;

    public Pole(Plansza plansza){
        super();
        stan = -1;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                stan = 0;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D grafika2D = (Graphics2D) g;
        grafika2D.setStroke(new BasicStroke(3));
        if (stan == 0) {
            grafika2D.drawLine(0, 0, (800 + 60) / wielkosc, 800 / wielkosc);
            grafika2D.drawLine(840 / wielkosc, 0, 0, 780 / wielkosc);
        }
        if(stan == 1 )
        {
            grafika2D.drawOval(0,0,840/wielkosc,780/wielkosc);
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getStan() {
        return stan;
    }

    public int getWielksoc() {
        return wielkosc;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setStan(int stan) {
        this.stan = stan;
    }

    public void setWielksoc(int wielksoc) {
        this.wielkosc = wielksoc;
    }
}
