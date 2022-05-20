package templatemethod.bank;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankProductTest {

    @Test
    public void testIncreaseWhenLoanProduct() {
        //Given
        GeneralProduct product = new LoanProduct(100);
        //When
        product.increase(15);
        //Then
        assertEquals(115, product.getPrice());
    }

    @Test
    public void testIncreaseWhenYieldProduct() {
        //Given
        GeneralProduct product = new YieldProduct(100);
        //When
        product.increase(15);
        //Then
        assertEquals(115, product.getPrice());
    }
}