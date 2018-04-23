import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    Fenetre(){
        this.setSize(400, 500);
        setTitle("Treasure HUNT");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        Container espace_jeu = getContentPane();
        espace_jeu.setLayout(new BorderLayout());

        /*JPanel grid = new JPanel();
        grid.setBackground(Color.BLUE);
        espace_jeu.add(grid, "Center");*/

        /*JPanel sac_pierre = new JPanel();
        sac_pierre.setBackground(Color.CYAN);
        espace_jeu.add(sac_pierre, BorderLayout.WEST);
        sac_pierre.setLocation(100, 100);
        sac_pierre.setSize(50, 50);
        sac_pierre.setLayout(null);*/

       /* JPanel bot = new JPanel();
        bot.setBackground(Color.green);
        espace_jeu.add(bot, BorderLayout.SOUTH);
*/
        JButton quitter = new JButton("Abandonner");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//        bot.add(quitter);

        /*JButton sac = new JButton("S");
        sac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        sac_pierre.add(sac);
        sac.setPreferredSize(new Dimension(25,25));
        sac.setLocation(sac_pierre.getWidth()/2, sac_pierre.getHeight()/2);*/



    }
}

