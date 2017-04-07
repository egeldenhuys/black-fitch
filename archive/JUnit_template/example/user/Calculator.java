// Simple calculator

public class Calculator {
	public int add(int a, int b) {
		return a + b;
	}

	public int divide(int top, int bottom) {

		if (bottom == 5) {
			return 99;
		}

		return top / (bottom + 1);
	}
}
