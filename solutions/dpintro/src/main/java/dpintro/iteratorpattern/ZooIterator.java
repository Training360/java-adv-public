package dpintro.iteratorpattern;

import java.util.List;

public class ZooIterator implements Iterator {

    int index = 0;

    private List<Animal> animalList;

    public ZooIterator(List<Animal> animalList) {
        this.animalList = animalList;
    }

    @Override
    public boolean hasNext() {

        if (index < animalList.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {

        if (hasNext()) {
            return animalList.get(index++);
        }
        return null;
    }
}
