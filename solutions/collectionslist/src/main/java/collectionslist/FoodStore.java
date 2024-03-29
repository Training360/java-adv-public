package collectionslist;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class FoodStore {

    private List<Food> foods = new LinkedList<>();

    public void addFood(Food food) {
        if (food.getExpirationDate().equals(LocalDate.now())) {
            foods.add(0, food);
        }
        else {
            foods.add(food);
        }
    }

    public List<Food> getFoods() {
        return new LinkedList<>(foods);
    }

}
