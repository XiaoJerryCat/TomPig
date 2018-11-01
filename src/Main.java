import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new JFrame("XiaoJerryCat");
                jFrame.setLocationRelativeTo(null);
                jFrame.setVisible(false);
                jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                jFrame.setContentPane(new Interface());
                jFrame.setPreferredSize(new Dimension(Interface.height,Interface.weight));
                jFrame.setResizable(false);
                jFrame.pack();
                jFrame.setVisible(true);
            }
        });
    }
}
