package list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class QuanLySoArrayList {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Integer> ds = new ArrayList<>();

    public static void main(String[] args) {
        int chon;
        do {
            menu();
            chon = docSo("Chon chuc nang: ");
            switch (chon) {
                case 1: hienThi(); break;
                case 2: them(); break;
                case 3: themTaiViTri(); break;
                case 4: sua(); break;
                case 5: xoaTheoViTri(); break;
                case 6: xoaTheoGiaTri(); break;
                case 7: timKiem(); break;
                case 8: sapXep(); break;
                case 9: demSoTrung(); break;
                case 0: System.out.println("Thoat chuong trinh."); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    static void menu() {
        System.out.println("\n===== QUAN LY DANH SACH (ArrayList) =====");
        System.out.println("1. Hien thi danh sach");
        System.out.println("2. Them phan tu (cuoi danh sach)");
        System.out.println("3. Them phan tu tai vi tri");
        System.out.println("4. Sua phan tu (theo vi tri)");
        System.out.println("5. Xoa phan tu theo vi tri");
        System.out.println("6. Xoa phan tu theo gia tri");
        System.out.println("7. Tim kiem");
        System.out.println("8. Sap xep tang dan");
        System.out.println("9. Dem so trung (hien thi da sap xep)");
        System.out.println("0. Thoat");
    }

    static int docSo(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen!");
            }
        }
    }

    static void hienThi() {
        if (ds.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("Danh sach: " + ds);
    }

    static void them() {
        ds.add(docSo("Nhap so can them: "));
        System.out.println("Da them.");
    }

    static void themTaiViTri() {
        int viTri = docSo("Nhap vi tri (0.." + ds.size() + "): ");
        if (viTri < 0 || viTri > ds.size()) {
            System.out.println("Vi tri khong hop le!");
            return;
        }
        ds.add(viTri, docSo("Nhap so can them: "));
        System.out.println("Da them.");
    }

    static void sua() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        int viTri = docSo("Nhap vi tri can sua (0.." + (ds.size() - 1) + "): ");
        if (viTri < 0 || viTri >= ds.size()) {
            System.out.println("Vi tri khong hop le!");
            return;
        }
        ds.set(viTri, docSo("Nhap gia tri moi: "));
        System.out.println("Da sua.");
    }

    static void xoaTheoViTri() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        int viTri = docSo("Nhap vi tri can xoa (0.." + (ds.size() - 1) + "): ");
        if (viTri < 0 || viTri >= ds.size()) {
            System.out.println("Vi tri khong hop le!");
            return;
        }
        ds.remove(viTri);
        System.out.println("Da xoa.");
    }

    static void xoaTheoGiaTri() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        int giaTri = docSo("Nhap gia tri can xoa: ");
        // removeAll xoa moi phan tu co gia tri nay
        boolean daXoa = ds.removeAll(Collections.singleton(giaTri));
        System.out.println(daXoa ? "Da xoa tat ca gia tri " + giaTri : "Khong tim thay.");
    }

    static void timKiem() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        int giaTri = docSo("Nhap gia tri can tim: ");
        boolean thay = false;
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i) == giaTri) {
                System.out.println("Tim thay " + giaTri + " tai vi tri " + i);
                thay = true;
            }
        }
        if (!thay) System.out.println("Khong tim thay " + giaTri);
    }

    static void sapXep() {
        Collections.sort(ds);
        System.out.println("Da sap xep tang dan: " + ds);
    }

    static void demSoTrung() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        Collections.sort(ds); // sap xep de cac so trung nam canh nhau
        StringBuilder sb = new StringBuilder();
        for (int x : ds) sb.append(x).append(' ');
        System.out.println("Danh sach sau khi sap xep: " + sb.toString().trim());

        Map<Integer, Integer> dem = new TreeMap<>();
        for (int x : ds) dem.merge(x, 1, Integer::sum);

        boolean coTrung = false;
        for (Map.Entry<Integer, Integer> e : dem.entrySet()) {
            if (e.getValue() > 1) {
                System.out.println("So " + e.getKey() + " xuat hien " + e.getValue() + " lan");
                coTrung = true;
            }
        }
        if (!coTrung) System.out.println("Khong co so nao trung.");
    }
}