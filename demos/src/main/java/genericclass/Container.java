package genericclass;

public class Container<T extends HasName> {

    private T value;

    public Container(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getName() {
        return value.getName();
    }
}
