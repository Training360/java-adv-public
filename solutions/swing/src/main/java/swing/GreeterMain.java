package swing;

public class GreeterMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GreeterFrame greeterFrame = new GreeterFrame();
                greeterFrame.setVisible(true);
            }
        });
    }
}
