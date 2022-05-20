package singleton.producerconsumer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProducerTest {

    @Test
    public void testProduce() {
        StoreTestImpl store = new StoreTestImpl();
        Producer producer = new Producer(store);

        producer.produce("Apple");

        assertEquals("Apple", store.remove().getName());
    }
}