import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Lab07A_Invoice extends JFrame {
    private JTextField itemField, priceField;
    private JTextArea invoiceDisplay;
    private JButton enterButton, clearButton, quitButton;
    private double totalAmount;

    public Lab07A_Invoice() {
        setTitle("Invoice");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        totalAmount = 0;

        JPanel inputPanel = new JPanel(new GridLayout(2,2,10,10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Item and Price"));
        inputPanel.add(new JLabel("Item: "));
        itemField = new JTextField();
        inputPanel.add(itemField);
        inputPanel.add(new JLabel("Price: "));
        priceField = new JTextField();
        inputPanel.add(priceField);

        JPanel displayPanel = new JPanel();
        invoiceDisplay = new JTextArea(10,40);
        invoiceDisplay.setEditable(false);
        displayPanel.add(new JScrollPane(invoiceDisplay));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        enterButton = new JButton("Enter");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        buttonPanel.add(enterButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(quitButton);

        add(inputPanel, BorderLayout.NORTH);
        add(displayPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        enterButton.addActionListener(new EnterButtonListener());
        clearButton.addActionListener(e -> clearForm());
        quitButton.addActionListener(e -> System.exit(0));
    }

    private class EnterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String item = itemField.getText().trim();
            String price = priceField.getText().trim();

            if(item.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(Lab07A_Invoice.this, "All fields must be filled.","Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            } try {
                double priceValue = Double.parseDouble(price);
                totalAmount += priceValue;

                DecimalFormat df = new DecimalFormat("0.00");
                String invoiceText = invoiceDisplay.getText() + item + " - $" + df.format(priceValue) + "\n";
                invoiceDisplay.setText(invoiceText);
                invoiceDisplay.append("\nTotal: $" + df.format(totalAmount));

                itemField.setText("");
                priceField.setText("");
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(Lab07A_Invoice.this,"Invalid amount format.","Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void clearForm() {
        itemField.setText("");
        priceField.setText("");
        invoiceDisplay.setText("");
        totalAmount = 0;
    }
}