import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}

public class AddressBookGUI extends JFrame {

    private AddressBook addressBook;

    private JTextArea contactsArea;

    public AddressBookGUI() {
        addressBook = new AddressBook();

        setTitle("Address Book System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        contactsArea = new JTextArea(10, 30);
        contactsArea.setEditable(false);

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddContactDialog();
            }
        });

        JPanel panel = new JPanel();
        panel.add(addButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JScrollPane(contactsArea), BorderLayout.CENTER);
        contentPane.add(panel, BorderLayout.SOUTH);
    }

    private void showAddContactDialog() {
        String name = JOptionPane.showInputDialog("Enter Name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter Phone Number:");
        String email = JOptionPane.showInputDialog("Enter Email:");

        Contact newContact = new Contact(name, phoneNumber, email);
        addressBook.addContact(newContact);

        displayContacts();
    }

    private void displayContacts() {
        StringBuilder contactsText = new StringBuilder();
        contactsText.append("Contacts:\n");
        for (Contact contact : addressBook.getContacts()) {
            contactsText.append("Name: ").append(contact.getName()).append(", ")
                    .append("Phone: ").append(contact.getPhoneNumber()).append(", ")
                    .append("Email: ").append(contact.getEmail()).append("\n");
        }
        contactsArea.setText(contactsText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddressBookGUI().setVisible(true);
            }
        });
    }
}
