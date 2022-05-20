package builder.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameTest {

    @Test
    public void testCreationAndGetter() {
        //Given
        byte[] bytes = {-1, 0, 1};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertEquals(bytes[0], frame.getBytes()[0]);
        assertEquals(bytes[1], frame.getBytes()[1]);
        assertEquals(bytes[2], frame.getBytes()[2]);
    }

    @Test
    public void toHex() {
        //Given
        byte[] bytes = {-1, 0, 1};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertEquals("FF-00-01", frame.toHexString());
    }

    @Test
    public void toHexWithEmpty() {
        //Given
        byte[] bytes = {};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertEquals("", frame.toHexString());
    }

    @Test
    public void toHexWithOneByte() {
        //Given
        byte[] bytes = {1};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertEquals("01", frame.toHexString());
    }
}