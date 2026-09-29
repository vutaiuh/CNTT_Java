package basic;

import java.util.Scanner;

public class Bai06_MaxMinTrungBinhMang {

	public static void main(String[] args) {
		int n;
		Scanner nhap = new Scanner(System.in);
		System.out.print("Nhap vao so phan tu: ");
		n = nhap.nextInt();
		int dayso[] = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.print("Nhap phan tu thu " + (i + 1) + ": ");
			dayso[i] = nhap.nextInt();
		}
		System.out.println("Day so vua nhap vao la:");
		for (int i = 0; i < n; i++) System.out.print(dayso[i] + " ");

		int max = dayso[0];
		int min = dayso[0];
		int sum = dayso[0];
		for (int i = 1; i < n; i++) {
			if (dayso[i] > max) max = dayso[i];
			if (dayso[i] < min) min = dayso[i];
			sum += dayso[i];
		}
		double tb = (double) sum / n;

		int demLonHon = 0;
		int demNhoHon = 0;
		for (int i = 0; i < n; i++) {
			if (dayso[i] > tb) demLonHon++;
			if (dayso[i] < tb) demNhoHon++;
		}

		System.out.println();
		System.out.println("Gia tri lon nhat: " + max);
		System.out.println("Gia tri nho nhat: " + min);
		System.out.println("Gia tri trung binh: " + tb);
		System.out.println("So luong lon hon trung binh: " + demLonHon);
		System.out.println("So luong nho hon trung binh: " + demNhoHon);
	}
}
