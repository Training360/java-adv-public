package builder.robot;

public class Frame {

    private byte bytes[];

    public Frame(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String toHexString() {

        String result = new String("");

        for (byte b : bytes){
            if (result.length() != 0){
                result += "-";
            }
            String str = Integer.toHexString(0xFF & b);
            if (str.length() == 1) {
                str = "0" + str;
            }
            result += str;
        }
        return result.toUpperCase();
    }
}
