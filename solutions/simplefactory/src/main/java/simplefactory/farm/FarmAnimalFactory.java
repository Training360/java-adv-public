package simplefactory.farm;

public class FarmAnimalFactory {


    public Animal create(String animalType) {

        if (isEmpty(animalType)) {
            throw new IllegalArgumentException("Animal type must not be empty!");
        }
        switch (animalType) {
            case "cock":
                return new Cock();
            case "horse":
                return new Horse();
            case "frog":
                return new Frog();
            default:
                throw new IllegalArgumentException("Unrecognizable animal type!");
        }
    }

    private boolean isEmpty(String str) {

        return str == null || "".equals(str.trim());
    }
}
