package builder.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SetMovementCapabilityFrameBuilderTest {

    @Test
    public void testBuildWDefaultValues() {
        //Given
        Frame frame = new SetMovementCapabilityFrameBuilder().build();
        //Then
        assertEquals("04-60-0A-62-08-D8", frame.toHexString());
    }

    @Test
    public void testBuildUsingOnlyAcceleration() {
        //Given
        Frame frame = new SetMovementCapabilityFrameBuilder().setMaximumAcceleration((byte) 100).build();
        //Then
        assertEquals("04-60-0A-64-08-DA", frame.toHexString());
    }

    @Test
    public void testBuildSetAll() {
        //Given
        Frame frame = new SetMovementCapabilityFrameBuilder()
                .setMaximumAcceleration((byte) 0)
                .setMaximumRotationalAcceleration((byte) 0)
                .setMaximumSpeed((byte) 0)
                .setMaximumRotationSpeed((byte) 0)
                .build();
        //Then
        assertEquals("04-00-00-00-00-04", frame.toHexString());
    }
}