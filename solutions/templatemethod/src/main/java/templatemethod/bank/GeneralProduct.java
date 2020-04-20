package templatemethod.bank;

public abstract class GeneralProduct {

	private int price;

	protected abstract int round(double newPrice);

	public GeneralProduct(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public int increase(double percent) {

		price = round(price + price * (percent / 100));
		return price;
	}
}
