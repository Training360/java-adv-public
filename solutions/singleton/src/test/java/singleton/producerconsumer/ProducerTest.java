package singleton.producerconsumer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProducerTest {

    @Test
    void testProduce() {
        Store store = LocalStore.getInstance();
        Producer producer = new Producer(store);
        producer.produce("Apple");
        assertEquals("Apple", store.remove().getName());
    }
}