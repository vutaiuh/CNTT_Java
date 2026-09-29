package basic;

public class Bai03_TamGiacSaoNguoc {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= i - 1; j++)
				System.out.print("  ");
			for (int j = 1; j <= 11 - i; j++)
				System.out.print("* ");
			System.out.println();
		}
	}
}
