package car.manager;

import car.manager.views.Home;
import car.manager.views.IPanel;

import javax.swing.*;
import java.awt.*;

public class App {

    static JFrame frame = new JFrame();

    public static  void main(String[] args){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.pack();
        frame.setVisible(true);

        Navigate(new Home());
    }

    public static  void Navigate(IPanel panel){
        frame.setContentPane(panel.getPanel());
        frame.revalidate();
    }
}
