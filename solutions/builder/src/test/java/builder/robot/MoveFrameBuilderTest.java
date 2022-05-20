package builder.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveFrameBuilderTest {

    @Test
    public void testBuildOnlyOneRotation() {
        //Given
        MoveFrameBuilder moveFrameBuilder = new MoveFrameBuilder();
        //When
        Frame frame = moveFrameBuilder.setRotationSpeed((byte) 100).build();
        //Then
        assertEquals("7D-00-00-64-00-E1", frame.toHexString());
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
        assertEquals("7D-01-02-03-04-87", frame.toHexString());
    }

    @Test
    public void testBuildDefaultValues() {
        //Given
        Frame frame = new MoveFrameBuilder().build();
        //Then
        assertEquals("7D-00-00-00-00-7D", frame.toHexString());
    }
}