package singleton.producerconsumer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ConsumerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void emptyStoreShouldThrowException() throws IllegalStateException {
        // Given
        StoreTestImpl store = new StoreTestImpl();
        exception.expect(IllegalStateException.class);
        exception.expectMessage("Store is empty");
        // When
        Consumer consumer = new Consumer(store);
        consumer.consume();
    }

    @Test
    public void testConsume() {
        //Given
        StoreTestImpl store = new StoreTestImpl();
        store.add(new Product("Apple"));
        store.add(new Product("Orange"));
        //When
        Consumer consumer = new Consumer(store);
        //Then
        assertThat(consumer.consume().getName(), is("Apple"));
        assertThat(consumer.consume().getName(), is("Orange"));
    }
}