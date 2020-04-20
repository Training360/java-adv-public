package builder.robot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SetMovementCapabilityFrameBuilderTest {

    @Test
    public void testBuildWDefaultValues() {
        //Given
        Frame frame = new SetMovementCapabilityFrameBuilder().build();
        //Then
        assertThat(frame.toHexString(), is("04-60-0A-62-08-D8"));
    }

    @Test
    public void testBuildUsingOnlyAcceleration() {
        //Given
        Frame frame = new SetMovementCapabilityFrameBuilder().setMaximumAcceleration((byte) 100).build();
        //Then
        assertThat(frame.toHexString(), is("04-60-0A-64-08-DA"));
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
        assertThat(frame.toHexString(), is("04-00-00-00-00-04"));
    }
}