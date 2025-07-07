void drawGrid() {
    gridPanel.removeAll();
    gridPanel.setBackground(Color.WHITE);
    playerOne.clear();
    playerTwo.clear();
    flag = 0;

    turnLabel.setText("Turn: " + player1Name);
    turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
    turnLabel.setForeground(Color.DARK_GRAY);

    scoreLabel.setText("Score: " + player1Score + " - " + player2Score);
    scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
    scoreLabel.setForeground(new Color(0, 128, 128));

    for (int i = 0; i < 9; i++) {
        JButton btn = new JButton();
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(255, 250, 240));
        int position = i + 1;
        btn.addActionListener(e -> {
            playClickSound();
            if (flag == 0) {
                btn.setText("X");
                btn.setForeground(player1Color.darker());
                playerOne.add(position);
                turnLabel.setText("Turn: " + player2Name);
                frame.getContentPane().setBackground(player2Color);
            } else {
                btn.setText("O");
                btn.setForeground(player2Color.darker());
                playerTwo.add(position);
                turnLabel.setText("Turn: " + player1Name);
                frame.getContentPane().setBackground(player1Color);
            }
            btn.setEnabled(false);
            flag = 1 - flag;
            checkWinner();
        });
        buttons[i] = btn;
        gridPanel.add(btn);
    }

    JPanel centerPanel = new JPanel(new BorderLayout());
    centerPanel.setPreferredSize(new Dimension(300, 350));
    centerPanel.setOpaque(false);
    centerPanel.add(gridPanel, BorderLayout.CENTER);

    JPanel topPanel = new JPanel(new GridLayout(2, 1));
    topPanel.setOpaque(false);
    topPanel.add(turnLabel);
    topPanel.add(scoreLabel);

    JPanel container = new JPanel(new BorderLayout());
    container.add(topPanel, BorderLayout.NORTH);
    container.add(centerPanel, BorderLayout.CENTER);
    container.setOpaque(false);

    frame.getContentPane().removeAll();
    frame.setLayout(new GridBagLayout());
    frame.getContentPane().add(container);
    frame.getContentPane().setBackground(player1Color);
    frame.revalidate();
    frame.repaint();
    frame.setSize(500, 500);
}