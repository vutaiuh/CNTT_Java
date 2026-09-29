package basic;

import java.util.Scanner;

public class Bai01_TimMaxMin3So {

	public static void main(String[] args) {
		Scanner nhap = new Scanner(System.in);
		System.out.print("Nhap a: ");
		int a = nhap.nextInt();
		System.out.print("Nhap b: ");
		int b = nhap.nextInt();
		System.out.print("Nhap c: ");
		int c = nhap.nextInt();

		int max = a;
		int min = a;
		if (b > max) max = b;
		if (c > max) max = c;
		if (b < min) min = b;
		if (c < min) min = c;

		System.out.println("Gia tri lon nhat: " + max);
		System.out.println("Gia tri nho nhat: " + min);
	}
}
