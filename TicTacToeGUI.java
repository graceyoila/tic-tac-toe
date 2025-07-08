import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private String currentPlayer = "X";
    private JLabel statusLabel;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Player X's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        Font btnFont = new Font("Arial", Font.BOLD, 40);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(btnFont);
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]);
            }
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            return;
        }

        clicked.setText(currentPlayer);
        clicked.setForeground(currentPlayer.equals("X") ? Color.RED : Color.GREEN);

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "🎉 Player " + currentPlayer + " wins!");
            resetBoard();
            return;
        }

        if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a tie!");
            resetBoard();
            return;
        }

        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        statusLabel.setText("Player " + currentPlayer + "'s Turn");
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if (checkEqual(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (checkEqual(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }

        // Check diagonals
        if (checkEqual(buttons[0][0], buttons[1][1], buttons[2][2])) return true;
        if (checkEqual(buttons[0][2], buttons[1][1], buttons[2][0])) return true;

        return false;
    }

    private boolean checkEqual(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") &&
                b1.getText().equals(b2.getText()) &&
                b2.getText().equals(b3.getText());
    }

    private boolean isBoardFull() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                if (b.getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        currentPlayer = "X";
        statusLabel.setText("Player X's Turn");
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                b.setText("");
                b.setForeground(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
