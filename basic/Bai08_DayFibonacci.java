package basic;

import java.util.Scanner;

public class Bai08_DayFibonacci {

	public static void main(String[] args) {
		int n;
		Scanner nhap = new Scanner(System.in);
		System.out.print("Nhap vao so phan tu: ");
		n = nhap.nextInt();

		long f[] = new long[n + 2];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i < n; i++) f[i] = f[i - 1] + f[i - 2];

		System.out.println("Day Fibonacci gom " + n + " so:");
		for (int i = 0; i < n; i++) System.out.print(f[i] + " ");
		System.out.println();
	}
}
