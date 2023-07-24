import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private JLabel messageLabel;
    private JTextField inputField;
    private JButton guessButton;
    private JTextArea resultArea;

    private Random random;
    private int generatedNumber;
    private int attempts;
    private int maxAttempts;
    private int score;

    public NumberGuessingGameGUI() {
        initGame();
        initComponents();
    }

    private void initGame() {
        random = new Random();
        generatedNumber = 0;
        attempts = 0;
        maxAttempts = 5;
        score = 0;
    }

    private void initComponents() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        messageLabel = new JLabel("Welcome to the Number Guessing Game!");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.PLAIN, 18));
        guessButton.setBackground(Color.BLUE);
        guessButton.setForeground(Color.WHITE);
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 16));

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onGuessButtonClicked();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(messageLabel);
        panel.add(inputField);
        panel.add(guessButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Set background color for the content pane
        contentPane.setBackground(Color.WHITE);

        // Set background color for the panel
        panel.setBackground(Color.WHITE);
    }

    private void onGuessButtonClicked() {
        if (generatedNumber == 0) {
            generatedNumber = getRandomNumber(1, 100);
            attempts = 0;
            score = 0;
            resultArea.setText("");
            printMessage("A random number between 1 and 100 has been generated.");
            printMessage("You have " + maxAttempts + " attempts to guess the number.");
        }

        attempts++;
        int userGuess = Integer.parseInt(inputField.getText());
        if (userGuess == generatedNumber) {
            printMessage("Congratulations! You guessed the correct number.");
            score++;
            generatedNumber = 0;
        } else if (userGuess < generatedNumber) {
            printMessage("Too low! Try again.");
        } else {
            printMessage("Too high! Try again.");
        }

        if (attempts == maxAttempts) {
            printMessage("You have used all your attempts. The correct number was: " + generatedNumber);
            generatedNumber = 0;
        }

        printMessage("Do you want to play again? (yes/no): ");
    }

    private void printMessage(String message) {
        resultArea.append(message + "\n");
    }

    private int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGameGUI gameGUI = new NumberGuessingGameGUI();
                gameGUI.setVisible(true);
                gameGUI.getContentPane().setBackground(Color.LIGHT_GRAY);
            }
        });
    }
}
