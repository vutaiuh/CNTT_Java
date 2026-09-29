package basic;

import java.util.Scanner;

public class Bai10_LietKePhanTuXuatHien {

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

		System.out.println();
		System.out.println("Cac phan tu xuat hien trong mang (moi phan tu liet ke 1 lan):");
		for (int i = 0; i < n; i++) {
			boolean daXuatHien = false;
			for (int j = 0; j < i; j++) {
				if (dayso[j] == dayso[i]) {
					daXuatHien = true;
					break;
				}
			}
			if (!daXuatHien) {
				int dem = 0;
				for (int j = 0; j < n; j++)
					if (dayso[j] == dayso[i]) dem++;
				System.out.println(dayso[i] + " xuat hien " + dem + " lan");
			}
		}
	}
}
