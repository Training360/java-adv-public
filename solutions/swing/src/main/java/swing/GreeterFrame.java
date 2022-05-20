package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreeterFrame extends JFrame {

    private JTextField textField = new JTextField();

    private JLabel label = new JLabel();

    private JButton button = new JButton("Say hello!");

    private JLabel htmlLabel = new JLabel();

    public GreeterFrame() {
        super("HelloWorldSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField.setColumns(20);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(new Greeter().sayHello(textField.getText()));
                htmlLabel.setText(new Greeter().sayHelloHtml(textField.getText()));
            }
        });
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(textField);
        getContentPane().add(button);
        getContentPane().add(label);
        getContentPane().add(htmlLabel);

        setSize(600, 200);
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getButton() {
        return button;
    }

    public JTextField getTextField() {
        return textField;
    }
}
