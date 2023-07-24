import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WordCounterGUI extends JFrame {

    private JTextArea textArea;
    private JLabel resultLabel;

    public WordCounterGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Word Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton countButton = new JButton("Count Words");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        resultLabel = new JLabel("Total word count: 0");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(countButton, BorderLayout.SOUTH);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(resultLabel, BorderLayout.SOUTH);
    }

    private void countWords() {
        String text = textArea.getText();

        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;

        String[] stopWords = { "a", "an", "the", "and", "or", "in", "on", "of", "to", "for" };
        for (String stopWord : stopWords) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].equalsIgnoreCase(stopWord)) {
                    words[i] = "";
                    wordCount--;
                }
            }
        }

        resultLabel.setText("Total word count (after removing common words): " + wordCount);

        // Calculate the frequency of each word
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            if (!word.isBlank()) {
                wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        StringBuilder frequencyOutput = new StringBuilder();
        frequencyOutput.append("<html>Word frequency:<br>");

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            frequencyOutput.append(entry.getKey()).append(": ").append(entry.getValue()).append("<br>");
        }
        frequencyOutput.append("</html>");

        JOptionPane.showMessageDialog(this, frequencyOutput.toString(), "Word Frequency",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WordCounterGUI().setVisible(true);
            }
        });
    }
}
