package templatemethod.bank;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoanProductTest {

    @Test
    public void testRround() {
        //Given
        LoanProduct loanProduct = new LoanProduct(123);
        //Then
        assertEquals(129, loanProduct.round(128.5));
    }
}