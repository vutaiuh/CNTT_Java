package basic;

import java.util.Scanner;

public class Bai09_ChuoiNhiPhanNBit {

	public static void main(String[] args) {
		int n;
		Scanner nhap = new Scanner(System.in);
		System.out.print("Nhap vao so bit n: ");
		n = nhap.nextInt();

		int tong = 1 << n; // 2^n chuoi nhi phan
		System.out.println("Cac chuoi nhi phan " + n + " bit:");
		for (int i = 0; i < tong; i++) {
			for (int j = n - 1; j >= 0; j--)
				System.out.print((i >> j) & 1);
			System.out.println();
		}
	}
}
