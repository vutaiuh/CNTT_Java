package basic;

import java.util.Scanner;

public class Bai11_TimThemXoaMangDaSapXep {

	// Tim kiem nhi phan, tra ve vi tri hoac -1 neu khong co
	static int timKiem(int dayso[], int n, int x) {
		int dau = 0, cuoi = n - 1;
		while (dau <= cuoi) {
			int giua = (dau + cuoi) / 2;
			if (dayso[giua] == x) return giua;
			if (dayso[giua] < x) dau = giua + 1;
			else cuoi = giua - 1;
		}
		return -1;
	}

	static void xuat(int dayso[], int n) {
		for (int i = 0; i < n; i++) System.out.print(dayso[i] + " ");
		System.out.println();
	}

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

		// Sap xep tang dan de co mang da sap xep
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (dayso[i] > dayso[j]) {
					int tam = dayso[i];
					dayso[i] = dayso[j];
					dayso[j] = tam;
				}
			}
		}
		System.out.println("Mang da sap xep:");
		xuat(dayso, n);

		int chon;
		do {
			System.out.println("----- MENU -----");
			System.out.println("1. Tim kiem phan tu");
			System.out.println("2. Them phan tu");
			System.out.println("3. Xoa phan tu");
			System.out.println("0. Thoat");
			System.out.print("Chon chuc nang: ");
			chon = nhap.nextInt();

			if (chon == 1) {
				System.out.print("Nhap gia tri can tim: ");
				int x = nhap.nextInt();
				int vt = timKiem(dayso, n, x);
				if (vt == -1) System.out.println("Khong tim thay " + x);
				else System.out.println("Tim thay " + x + " tai vi tri " + vt);
			} else if (chon == 2) {
				System.out.print("Nhap gia tri can them: ");
				int x = nhap.nextInt();
				int vt = 0;
				while (vt < n && dayso[vt] < x) vt++;
				int moi[] = new int[n + 1];
				for (int i = 0; i < vt; i++) moi[i] = dayso[i];
				moi[vt] = x;
				for (int i = vt; i < n; i++) moi[i + 1] = dayso[i];
				dayso = moi;
				n++;
				System.out.println("Mang sau khi them:");
				xuat(dayso, n);
			} else if (chon == 3) {
				System.out.print("Nhap gia tri can xoa: ");
				int x = nhap.nextInt();
				int vt = timKiem(dayso, n, x);
				if (vt == -1) {
					System.out.println("Khong tim thay " + x + " de xoa");
				} else {
					int moi[] = new int[n - 1];
					for (int i = 0; i < vt; i++) moi[i] = dayso[i];
					for (int i = vt + 1; i < n; i++) moi[i - 1] = dayso[i];
					dayso = moi;
					n--;
					System.out.println("Mang sau khi xoa:");
					xuat(dayso, n);
				}
			}
		} while (chon != 0);
	}
}
