package swing;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterFrameTest {
    @Test
    void pushButton() {
        GreeterFrame greeterFrame = new GreeterFrame();
        greeterFrame.getTextField().setText("John Doe");
        greeterFrame.getButton().doClick();

        assertEquals("Hello John Doe", greeterFrame.getLabel().getText());
    }
}
