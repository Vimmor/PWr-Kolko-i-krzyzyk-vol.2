import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zwycieska_ilosc extends JFrame implements ActionListener {
    JTextField wartosc;
    JLabel tekst;
    JButton zmien_wartosc;
    int zwyciestwo = 3;

    public Zwycieska_ilosc(){
        super("Zwycieska wartosc");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400, 200);
        this.setSize(660, 90);
        this.setLayout(null);

        tekst = new JLabel("Ile kółek/krzyżyków w rzędzie ma wygrywać? Podaj wartość:");
        tekst.setBounds(20, 20, 350, 20);
        add(tekst);

        wartosc = new JTextField();
        wartosc.setBounds(370, 20, 100, 20);
        add(wartosc);

        zmien_wartosc = new JButton("Zapisz wartość");
        zmien_wartosc.setBounds(480, 20, 150, 20);
        add(zmien_wartosc);
        zmien_wartosc.addActionListener(this);

        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(wartosc.equals(""))
            JOptionPane.showMessageDialog(null, "Nie udalo sie wybrac zwycieskiej ilosc danej figury.");
        else
             zwyciestwo = Integer.valueOf(wartosc.getText());
        setVisible(false);
    }
}
