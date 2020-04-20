package templatemethod.bank;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoanProductTest {

	@Test
	public void testRround(){
		//Given
		LoanProduct loanProduct = new LoanProduct(123);
		//Then
		assertThat(loanProduct.round(128.5), is(129));
	}
}