package builder.robot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MoveFrameBuilderTest {

    @Test
    public void testBuildOnlyOneRotation() {
        //Given
        MoveFrameBuilder moveFrameBuilder = new MoveFrameBuilder();
        //When
        Frame frame = moveFrameBuilder.setRotationSpeed((byte) 100).build();
        //Then
        assertThat(frame.toHexString(), is("7D-00-00-64-00-E1"));
    }

    @Test
    public void testBuildSettingAllValue() {
        //Given
        MoveFrameBuilder moveFrameBuilder = new MoveFrameBuilder();
        //When
        Frame frame = moveFrameBuilder
                .setXSpeed((byte) 1)
                .setYSpeed((byte) 2)
                .setRotationSpeed((byte) 3)
                .setMaximumSpeed((byte) 4)
                .build();
        //Then
        assertThat(frame.toHexString(), is("7D-01-02-03-04-87"));
    }

    @Test
    public void testBuildDefaultValues() {
        //Given
        Frame frame = new MoveFrameBuilder().build();
        //Then
        assertThat(frame.toHexString(), is("7D-00-00-00-00-7D"));
    }
}