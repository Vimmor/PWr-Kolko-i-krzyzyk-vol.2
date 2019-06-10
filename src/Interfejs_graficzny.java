import javax.swing.*;
import java.awt.*;

public class Interfejs_graficzny extends JFrame {
     JButton start_gry, wybor_wielkosci, wybor_wartosci;
     JPanel panel;
     Wielkosc_planszy wielkosc;
     Zwycieska_ilosc ilosc;
     Plansza plansza;

     public Interfejs_graficzny(){
         super("Kolko i krzyzyk");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocation(250, 150);
         setSize(850, 850);
         setResizable(false);
         setLayout(new BorderLayout());

         panel = new JPanel();
         add(panel,BorderLayout.PAGE_START);

         start_gry = new JButton("Start gry");
         wielkosc = new Wielkosc_planszy();
         plansza = new Plansza();
         ilosc = new Zwycieska_ilosc();

         add(plansza, BorderLayout.CENTER);

         start_gry.addActionListener(e -> {
             plansza.removeAll();
             plansza.setWielkosc(wielkosc.getRozmiar_planszy());
             plansza.revalidate();
             plansza.repaint();
             plansza.setZwycieska_ilosc(ilosc.getZwyciestwo());
             plansza.setVisible(true);
         });
         panel.add(start_gry);

         wybor_wielkosci = new JButton("Rozmiar planszy");
         wybor_wielkosci.addActionListener(e -> {
             wielkosc.setVisible(true);
         });
         panel.add(wybor_wielkosci);

         wybor_wartosci = new JButton("Wygrywająca wartość");
         wybor_wartosci.addActionListener(e -> {
             ilosc.setVisible(true);
             //  gameBoard.show_board(sizeForm.boardSize);
         });
         panel.add(wybor_wartosci);

         setVisible(true);
     }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                 new GUI();
                JFrame.setDefaultLookAndFeelDecorated(true);
                Interfejs_graficzny interfejs = new Interfejs_graficzny();
            }
        });
    }
}
