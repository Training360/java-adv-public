package dpintro.reptile;

public class Crocodile extends LandDweller implements CanSwim {

    public static final int WALK_ENERGY_LOSS = 20;
    public static final int SWIM_ENERGY_LOSS = 5;

    public Crocodile(int energy) {
        super(energy);
    }

    @Override
    public void swim() {
        decreaseEnergy(SWIM_ENERGY_LOSS);
    }

    @Override
    public void walk() {
        decreaseEnergy(WALK_ENERGY_LOSS);
    }
}
