import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            JOptionPane.showMessageDialog(null, "Deposit successful. New balance: " + balance);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount for deposit. Please try again.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: " + balance);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Insufficient balance or invalid amount for withdrawal. Please try again.");
        }
    }
}

public class ATMGUI extends JFrame {

    private BankAccount bankAccount;

    private JTextField amountField;
    private JTextArea balanceArea;

    public ATMGUI(double initialBalance) {
        bankAccount = new BankAccount(initialBalance);

        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        amountField = new JTextField(10);
        balanceArea = new JTextArea(5, 10);
        balanceArea.setEditable(false);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                bankAccount.deposit(amount);
                updateBalance();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                bankAccount.withdraw(amount);
                updateBalance();
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter Amount: "));
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);

        updateBalance();

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(balanceArea), BorderLayout.CENTER);
    }

    private void updateBalance() {
        balanceArea.setText("Current Balance: " + bankAccount.getBalance());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                double initialBalance = 1000.0; // Set the initial balance
                new ATMGUI(initialBalance).setVisible(true);
            }
        });
    }
}
