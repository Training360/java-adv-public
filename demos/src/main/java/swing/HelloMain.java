package swing;

public class HelloMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloFrame helloFrame = new HelloFrame();
                helloFrame.setVisible(true);
            }
        });
    }
}
