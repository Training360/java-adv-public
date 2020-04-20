package templatemethod.bank;

public class LoanProduct extends GeneralProduct {

	public LoanProduct(int price) {
		super(price);
	}

	@Override
	protected int round(double newPrice) {
		return (int)(newPrice + 0.5);
	}
}
