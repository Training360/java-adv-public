package singleton.producerconsumer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class LocalStoreTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetInstance() {
        LocalStore store = LocalStore.getInstance();
        store.reset();
        LocalStore localStore = LocalStore.getInstance();

        assertThat(store, sameInstance(localStore));
    }

    @Test
    public void testAdd() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertThat(store.getProductCount(), is(0));
        //When
        store.add(new Product("Apple"));
        //Then
        assertThat(store.getProductCount(), is(1));
    }

    @Test
    public void testAddTooMuch() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertThat(store.getProductCount(), is(0));
        //When
        for (int i = 0; i < LocalStore.CAPACITY + 2; i++) {
            store.add(new Product("Apple"));
        }

        assertThat(store.getProductCount(), is(LocalStore.CAPACITY));
    }

    @Test
    public void removeFromEmptyStoreShouldThrowException() throws IllegalStateException {
        // Given
        LocalStore store = LocalStore.getInstance();
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Store is empty");
        // When
        store.reset();
        store.remove();
    }

    @Test
    public void testAddRemove() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertThat(store.getProductCount(), is(0));
        //When
        store.add(new Product("Apple"));
        store.remove();
        //Then
        assertThat(store.getProductCount(), is(0));
    }
}