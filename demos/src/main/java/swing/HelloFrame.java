package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloFrame extends JFrame {

    private JLabel label = new JLabel();

    private JButton button = new JButton("Say hello!");

    public HelloFrame() {
        super("HelloWorldSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Hello World!");
            }
        });
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(button);
        getContentPane().add(label);

        setSize(600, 200);
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getButton() {
        return button;
    }
}
