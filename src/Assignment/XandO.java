package Assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;
import javax.swing.text.BadLocationException;

public class XandO extends JFrame {

    ArrayList<Integer> playerOne = new ArrayList<>();
    ArrayList<Integer> playerTwo = new ArrayList<>();
    boolean isPlayerOneTurn = true;
    String playerOneName = "Player 1";
    String playerTwoName = "Player 2";
    int playerOneScore = 0;
    int playerTwoScore = 0;

    JButton[] buttons = new JButton[9];
    JPanel myPanel = new JPanel();
    JLabel turnLabel = new JLabel();
    JLabel scoreLabel = new JLabel();
    JButton restartButton = new JButton("Restart Game");
    Clip clickSound;

    public XandO() {
        setTitle("🎮 X and O Game");
        setSize(450, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        loadSound();
        createWelcomeScreen();
    }

    private void loadSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("click.wav"));
            clickSound = AudioSystem.getClip();
            clickSound.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Sound file not found or cannot be played.");
        }
    }

    private void playClickSound() {
        if (clickSound != null) {
            if (clickSound.isRunning()) clickSound.stop();
            clickSound.setFramePosition(0);
            clickSound.start();
        }
    }




















    private void createWelcomeScreen() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridLayout(7, 1, 10, 10));
        welcomePanel.setBackground(new Color(240, 248, 255));
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel title = new JLabel("🎉 Welcome to X and O Game 🎯", SwingConstants.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        title.setForeground(new Color(199, 21, 133));

        JTextField playerOneField = new JTextField();
        playerOneField.setDocument(new JTextFieldLimit(16));
        playerOneField.setFont(new Font("Arial", Font.PLAIN, 14));
        playerOneField.setToolTipText("Enter Player 1 Name");

        JTextField playerTwoField = new JTextField();
        playerTwoField.setDocument(new JTextFieldLimit(16));
        playerTwoField.setFont(new Font("Arial", Font.PLAIN, 14));
        playerTwoField.setToolTipText("Enter Player 2 Name");

        JLabel tipLabel = new JLabel("👥 Enter your names to begin!", SwingConstants.CENTER);
        tipLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        tipLabel.setForeground(Color.DARK_GRAY);

        JButton startButton = new JButton("Continue ▶");
        startButton.setBackground(new Color(218, 112, 214));
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setFocusPainted(false);

        // Add components
        welcomePanel.add(title);
        welcomePanel.add(new JLabel("Player 1 Name:"));
        welcomePanel.add(playerOneField);
        welcomePanel.add(new JLabel("Player 2 Name:"));
        welcomePanel.add(playerTwoField);
        welcomePanel.add(tipLabel);
        welcomePanel.add(startButton);

        add(welcomePanel, BorderLayout.CENTER);
        setVisible(true);

        startButton.addActionListener(e -> {
            String name1 = playerOneField.getText().trim();
            String name2 = playerTwoField.getText().trim();

            if (name1.isEmpty() || name2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Both players must enter their names!", "Missing Name", JOptionPane.WARNING_MESSAGE);
            } else {
                playerOneName = name1;
                playerTwoName = name2;
                remove(welcomePanel);
                drawGrid();
                revalidate();
                repaint();
            }
        });
    }




    void drawGrid() {
        myPanel.setLayout(new GridLayout(3, 3));
        myPanel.setBackground(new Color(255, 239, 213));

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
            button.setBackground(new Color(255, 182, 193));
            final int index = i + 1;
            button.addActionListener(e -> {
                playClickSound();
                handleMove(button, index);
            });
            buttons[i] = button;
            myPanel.add(button);
        }

        turnLabel.setText(playerOneName + "'s Turn");
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        turnLabel.setForeground(new Color(0, 102, 204));

        scoreLabel.setText(getScoreText());
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        scoreLabel.setForeground(new Color(25, 25, 112));

        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.setBackground(new Color(255, 105, 180));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setPreferredSize(new Dimension(200, 40));

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(255, 240, 245));
        topPanel.add(turnLabel);
        topPanel.add(scoreLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(199, 21, 133));
        bottomPanel.add(restartButton);

        restartButton.addActionListener(e -> restartGame());

        add(topPanel, BorderLayout.NORTH);
        add(myPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    String getScoreText() {
        return "📊 Score → " + playerOneName + ": " + playerOneScore + " | " + playerTwoName + ": " + playerTwoScore;
    }

    void handleMove(JButton button, int position) {
        if (!button.getText().equals("")) return;

        if (isPlayerOneTurn) {
            button.setText("X");
            button.setForeground(Color.BLUE);
            playerOne.add(position);
            turnLabel.setText(playerTwoName + "'s Turn");
            turnLabel.setForeground(Color.RED);
        } else {
            button.setText("O");
            button.setForeground(Color.RED);
            playerTwo.add(position);
            turnLabel.setText(playerOneName + "'s Turn");
            turnLabel.setForeground(new Color(0, 102, 204));
        }

        checkWin();
        isPlayerOneTurn = !isPlayerOneTurn;
    }

    void checkWin() {
        int[][] winCombos = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9},
                {1, 4, 7}, {2, 5, 8}, {3, 6, 9},
                {1, 5, 9}, {3, 5, 7}
        };

        for (int[] combo : winCombos) {
            if (playerOne.contains(combo[0]) && playerOne.contains(combo[1]) && playerOne.contains(combo[2])) {
                playerOneScore++;
                JOptionPane.showMessageDialog(this, "🎉 " + playerOneName + " Wins!");
                updateScore();
                disableButtons();
                return;
            } else if (playerTwo.contains(combo[0]) && playerTwo.contains(combo[1]) && playerTwo.contains(combo[2])) {
                playerTwoScore++;
                JOptionPane.showMessageDialog(this, "🎉 " + playerTwoName + " Wins!");
                updateScore();
                disableButtons();
                return;
            }
        }

        if (playerOne.size() + playerTwo.size() == 9) {
            JOptionPane.showMessageDialog(this, "🤝 It's a Draw!");
        }
    }

    void updateScore() {
        scoreLabel.setText(getScoreText());
    }

    void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    void restartGame() {
        playerOne.clear();
        playerTwo.clear();
        isPlayerOneTurn = true;

        turnLabel.setText(playerOneName + "'s Turn");
        turnLabel.setForeground(new Color(0, 102, 204));

        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
            button.setForeground(Color.BLACK); // Reset text color (optional)
            button.setBackground(new Color(255, 182, 193)); // Reset background
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new XandO());
    }
}

// Text field limit helper
class JTextFieldLimit extends javax.swing.text.PlainDocument {
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException {
        if (str == null) return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
