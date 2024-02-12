package collectionslist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FoodStoreTest {

    FoodStore foodStore = new FoodStore();

    @BeforeEach
    void init() {
        foodStore.addFood(new Food("kenyér", LocalDate.now().plusDays(1)));
        foodStore.addFood(new Food("tej", LocalDate.now().plusDays(1)));
        foodStore.addFood(new Food("tejföl", LocalDate.now().plusDays(1)));
    }

    @Test
    void testAddFood() {
        foodStore.addFood(new Food("felvágott", LocalDate.now().plusDays(1)));

        assertEquals(4, foodStore.getFoods().size());
        assertEquals("felvágott", foodStore.getFoods().get(3).getName());
    }

    @Test
    void testAddFoodHasExpiredToday() {
        foodStore.addFood(new Food("felvágott", LocalDate.now()));

        assertEquals(4, foodStore.getFoods().size());
        assertEquals("felvágott", foodStore.getFoods().get(0).getName());
    }
}