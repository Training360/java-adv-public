package templatemethod.bank;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BankProductTest {

	@Test
	public void testIncreaseWhenLoanProduct() {
		//Given
		GeneralProduct product = new LoanProduct(100);
		//When
		product.increase(15);
		//Then
		assertThat(product.getPrice(), is(115));
	}

	@Test
	public void testIncreaseWhenYieldProduct() {
		//Given
		GeneralProduct product = new YieldProduct(100);
		//When
		product.increase(15);
		//Then
		assertThat(product.getPrice(), is(115));
	}
}