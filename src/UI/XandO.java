package UI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class XandO {

    ArrayList<Integer> playerOne = new ArrayList<>();
    ArrayList<Integer> playerTwo = new ArrayList<>();
    JFrame XandO = new JFrame();
    JButton btn1 = new JButton();
    JButton btn2 = new JButton();
    JButton btn3= new JButton();
    JButton btn4 = new JButton();
    JButton btn5 = new JButton();
    JButton btn6 = new JButton();
    JButton btn7 = new JButton();
    btn1.addActionListener(new ActionListener(){
        @Override
        public  void actionPerformer
    }

    JButton btn8 = new JButton();
    JButton btn9 = new JButton();
    JPanel myPanel = new JPanel();

    void drawGrid(){
        myPanel.add(btn1);
        myPanel.add(btn2);
        myPanel.add(btn3);
        myPanel.add(btn4);
        myPanel.add(btn5);
        myPanel.add(btn6);
        myPanel.add(btn7);
        myPanel.add(btn8);
        myPanel.add(btn9);
        XandO.add(myPanel);
        XandO.setSize();
        XandO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        XandO.setVisible(true);

        playerOne.add(1);
        playerTwo.add(2);

    }
}
public XandO() {
    setTitle("x and o game");
    setSize(400, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new CardLayout());

    createWelcomePanel();
    createGamePanel();
    createResultPanel();

    add(welcomePanel, "Welcome");
    add(gamePanel, "Game");
    add(resultPanel, "Result");

    showPanel("Welcome");
    setVisible(true);
}

public static void main(String[] args){
    XandO xando = new xando();
    xando.drawGrid();

}
void checkWin(){
    f(playerOne.contains(1)) && playerOne.contains(2)
            && playerOne.contains(3){
        JOptionPane.showMessageDialog(null,
                "Player 1 wins!");
        f(playerOne.contains(2) && playerOne.contains(5)
                && playerOne.contains(8)){

        }
    }
}

