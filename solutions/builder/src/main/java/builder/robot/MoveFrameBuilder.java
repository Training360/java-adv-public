package builder.robot;

public class MoveFrameBuilder {

    private byte bytes[] = new byte[6];

    public MoveFrameBuilder() {
        bytes[0] = 125;
    }

    public MoveFrameBuilder setXSpeed(byte speed) {
        bytes[1] = speed;

        return this;
    }

    public MoveFrameBuilder setYSpeed(byte speed) {
        bytes[2] = speed;

        return this;
    }

    public MoveFrameBuilder setRotationSpeed(byte speed) {
        bytes[3] = speed;

        return this;
    }

    public MoveFrameBuilder setMaximumSpeed(byte speed) {
        bytes[4] = speed;

        return this;
    }

    public Frame build() {
        bytes[5] = checksum();

        Frame frame = new Frame(bytes);
        return frame;
    }

    private byte checksum() {
        byte checksum = 0;
        for (int i = 0; i < bytes.length; i++) {
            checksum += bytes[i];
        }
        return checksum;
    }
}
