package simplefactory.farm;

import java.util.ArrayList;
import java.util.List;

public class Farm {

    private final FarmAnimalFactory animalFactory;
    private final List<Animal> animals = new ArrayList<>();

    public Farm(FarmAnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

    public void newAnimalArrived(String animalType) {
        if (isEmpty(animalType)) {
            throw new IllegalArgumentException("Animal type must not be empty!");
        }
        Animal animal = animalFactory.create(animalType);
        animals.add(animal);
    }

    public List<String> getAnimalVoices() {
        List<String> animalVoices = new ArrayList<>();

        for (Animal animal : animals) {
            String voice = animal.speak();

            if (!animalVoices.contains(voice)) {
                animalVoices.add(voice);
            }
        }

        return animalVoices;
    }

    private boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
