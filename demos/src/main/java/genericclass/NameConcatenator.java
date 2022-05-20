package genericclass;

import java.util.List;

public class NameConcatenator {

    public String concat(List<? extends HasName> list) {
        StringBuilder sb = new StringBuilder();
        for (Object o: list) {
            sb.append(((HasName) o).getName()).append(", ");
        }
        return sb.toString();
    }

    public void add(List<? super Trainer> list) {
        list.add(new Trainer("Jack"));

    }
}
