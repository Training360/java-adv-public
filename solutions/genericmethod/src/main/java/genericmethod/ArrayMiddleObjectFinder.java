package genericmethod;


public class ArrayMiddleObjectFinder {

    public <T> T findMiddleObject(T... values) {
        if (values == null) {
            throw new NullPointerException("Null array");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("Empty array");
        }
        if (values.length % 2 == 0) {
            throw new IllegalArgumentException("Even number of elements, no middle object can be found!");
        }
        return values[values.length / 2];
    }
}
