package swing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloFrameTest {

    @Test
    public void pushButton() {
        HelloFrame helloFrame = new HelloFrame();
        helloFrame.getButton().doClick();

        assertEquals("Hello World!", helloFrame.getLabel().getText());
    }
}
