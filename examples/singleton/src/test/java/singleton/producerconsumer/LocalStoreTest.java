package singleton.producerconsumer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalStoreTest {

    @Test
    public void testGetInstance() {
        LocalStore store = LocalStore.getInstance();
        store.reset();
        LocalStore localStore = LocalStore.getInstance();

        assertEquals(localStore, store);
    }

    @Test
    public void testAdd() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertEquals(0, store.getProductCount());
        //When
        store.add(new Product("Apple"));
        //Then
        assertEquals(1, store.getProductCount());
    }

    @Test
    public void testAddTooMuch() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertEquals(0, store.getProductCount());
        //When
        for (int i = 0; i < LocalStore.CAPACITY + 2; i++) {
            store.add(new Product("Apple"));
        }

        assertEquals(LocalStore.CAPACITY, store.getProductCount());
    }

    @Test
    public void removeFromEmptyStoreShouldThrowException() throws IllegalStateException {
        LocalStore store = LocalStore.getInstance();

        store.reset();

        Exception ex = assertThrows(IllegalStateException.class, () -> store.remove());
        assertEquals("Store is empty, no product is available!", ex.getMessage());
    }

    @Test
    public void testAddRemove() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertEquals(0, store.getProductCount());
        //When
        store.add(new Product("Apple"));
        store.remove();
        //Then
        assertEquals(0, store.getProductCount());
    }
}