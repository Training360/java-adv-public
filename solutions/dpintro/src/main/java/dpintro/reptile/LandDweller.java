package dpintro.reptile;

public abstract class LandDweller {

    private int energy;

    public abstract void walk();

    public LandDweller(int energy) {
        this.energy = energy;
    }

    protected void decreaseEnergy(int value) {
        if (energy - value < 0) {
            throw new IllegalArgumentException("Not enough energy to move!");
        }
        energy -= value;
    }

    public int getEnergy() {
        return energy;
    }
}
