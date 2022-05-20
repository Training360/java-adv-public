package genericclass;


public class DataPair<T> {

    private T firstObject;
    private T secondObject;

    public DataPair() {
        this.firstObject = null;
        this.secondObject = null;
    }

    public DataPair(T firstObject, T secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public T getFirstObject() {
        return firstObject;
    }

    public T getSecondObject() {
        return secondObject;
    }
}
