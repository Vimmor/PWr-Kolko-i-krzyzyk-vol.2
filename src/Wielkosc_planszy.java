import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wielkosc_planszy extends JFrame implements ActionListener {
    JTextField wielkosc;
    JLabel w;
    JButton zmien_wielkosc;

    private int rozmiar_planszy = 3;

    public Wielkosc_planszy(){
        super("Rozmiar planszy");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(400, 200);
        this.setSize(600, 90);
        this.setLayout(null);

        w = new JLabel("Podaj rozmiar planszy:");
        //Mozemy okreslic dokladnie polozenie i rozmiar komponentu, po uprzednim ustawieniu layoutu na null
        w.setBounds(20, 20, 200, 20);
        add(w);

        wielkosc = new JTextField();
        wielkosc.setBounds(220, 20, 150, 20);
        add(wielkosc);

        zmien_wielkosc = new JButton("Zapisz wielkość");
        zmien_wielkosc.setBounds(370, 20, 200, 20);
        add(zmien_wielkosc);
        zmien_wielkosc.addActionListener(this);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        rozmiar_planszy = Integer.valueOf(wielkosc.getText());
        setVisible(false);
    }

    public void setRozmiar_planszy(int rozmiar_planszy) {
        this.rozmiar_planszy = rozmiar_planszy;
    }

    public int getRozmiar_planszy() {
        return rozmiar_planszy;
    }
}
