package basic;

import java.util.Scanner;

public class Bai07_SapXepTangGiamMang {

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

		// Sao chep ra mang khac de giu nguyen day so ban dau
		int tang[] = new int[n];
		for (int i = 0; i < n; i++) tang[i] = dayso[i];

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (tang[i] > tang[j]) {
					int tam = tang[i];
					tang[i] = tang[j];
					tang[j] = tam;
				}
			}
		}

		System.out.println("Day so vua nhap vao la:");
		for (int i = 0; i < n; i++) System.out.print(dayso[i] + " ");

		System.out.println();
		System.out.println("Day so theo thu tu tang dan:");
		for (int i = 0; i < n; i++) System.out.print(tang[i] + " ");

		System.out.println();
		System.out.println("Day so theo thu tu giam dan:");
		for (int i = n - 1; i >= 0; i--) System.out.print(tang[i] + " ");
		System.out.println();
	}
}
