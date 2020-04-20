package builder.robot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FrameTest {

    @Test
    public void testCreationAndGetter() {
        //Given
        byte[] bytes = {-1, 0, 1};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertThat(frame.getBytes()[0], is(bytes[0]));
        assertThat(frame.getBytes()[1], is(bytes[1]));
        assertThat(frame.getBytes()[2], is(bytes[2]));
    }

    @Test
    public void toHex() {
        //Given
        byte[] bytes = {-1, 0, 1};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertThat(frame.toHexString(), is("FF-00-01"));
    }

    @Test
    public void toHexWithEmpty() {
        //Given
        byte[] bytes = {};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertThat(frame.toHexString(), is(""));
    }

    @Test
    public void toHexWithOneByte() {
        //Given
        byte[] bytes = {1};
        //When
        Frame frame = new Frame(bytes);
        //Then
        assertThat(frame.toHexString(), is("01"));
    }
}