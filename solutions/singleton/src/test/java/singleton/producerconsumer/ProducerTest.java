package singleton.producerconsumer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProducerTest {

    @Test
    public void testProduce() {
        StoreTestImpl store = new StoreTestImpl();
        Producer producer = new Producer(store);

        producer.produce("Apple");

        assertThat(store.remove().getName(), is("Apple"));
    }
}