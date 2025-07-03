package UIExample;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIExample {
    JFrame window = new JFrame("Example App");
    JPanel panel = new JPanel();
    JTextField emailField = new JTextField(" Enter your email address");
    JButton myButton = new JButton("Submit");
    void drawUi(){
        mypanel.add(emailField);
        mypanel.add(myButton);
        mypanel.setLayout(newGridLayout(rows: 2, cols ));
        window.add(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        UIExample ui = new UIExample();
        ui.drawUi();
    }
}

