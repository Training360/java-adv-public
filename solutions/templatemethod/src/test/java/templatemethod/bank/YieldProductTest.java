package templatemethod.bank;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YieldProductTest {

    @Test
    public void testRound() {
        //Given
        YieldProduct yieldProduct = new YieldProduct(123);
        //Then
        assertEquals(125, yieldProduct.round(125.5));
    }
}