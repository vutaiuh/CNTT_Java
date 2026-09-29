package list;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class QuanLySoLinkedList {
    private static final Scanner sc = new Scanner(System.in);
    private static final LinkedList<Integer> ds = new LinkedList<>();

    public static void main(String[] args) {
        int chon;
        do {
            menu();
            chon = docSo("Chon chuc nang: ");
            switch (chon) {
                case 1: hienThi(); break;
                case 2: themCuoi(); break;
                case 3: themDau(); break;
                case 4: themTaiViTri(); break;
                case 5: sua(); break;
                case 6: xoaTheoViTri(); break;
                case 7: xoaTheoGiaTri(); break;
                case 8: timKiem(); break;
                case 9: sapXep(); break;
                case 10: demSoTrung(); break;
                case 0: System.out.println("Thoat chuong trinh."); break;
                default: System.out.println("Lua chon khong hop le!");
            }
        } while (chon != 0);
    }

    static void menu() {
        System.out.println("\n===== QUAN LY DANH SACH (LinkedList) =====");
        System.out.println("1.  Hien thi danh sach");
        System.out.println("2.  Them vao cuoi (addLast)");
        System.out.println("3.  Them vao dau (addFirst)");
        System.out.println("4.  Them tai vi tri");
        System.out.println("5.  Sua phan tu (theo vi tri)");
        System.out.println("6.  Xoa phan tu theo vi tri");
        System.out.println("7.  Xoa phan tu theo gia tri");
        System.out.println("8.  Tim kiem");
        System.out.println("9.  Sap xep tang dan");
        System.out.println("10. Dem so trung (hien thi da sap xep)");
        System.out.println("0.  Thoat");
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

    static void themCuoi() {
        ds.addLast(docSo("Nhap so can them: "));
        System.out.println("Da them vao cuoi.");
    }

    static void themDau() {
        ds.addFirst(docSo("Nhap so can them: "));
        System.out.println("Da them vao dau.");
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
        boolean daXoa = false;
        // Dung Iterator de xoa an toan khi duyet LinkedList
        Iterator<Integer> it = ds.iterator();
        while (it.hasNext()) {
            if (it.next() == giaTri) {
                it.remove();
                daXoa = true;
            }
        }
        System.out.println(daXoa ? "Da xoa tat ca gia tri " + giaTri : "Khong tim thay.");
    }

    static void timKiem() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        int giaTri = docSo("Nhap gia tri can tim: ");
        int viTri = 0;
        boolean thay = false;
        // Duyet bang for-each (nhanh hon get(i) tren LinkedList)
        for (int x : ds) {
            if (x == giaTri) {
                System.out.println("Tim thay " + giaTri + " tai vi tri " + viTri);
                thay = true;
            }
            viTri++;
        }
        if (!thay) System.out.println("Khong tim thay " + giaTri);
    }

    static void sapXep() {
        Collections.sort(ds);
        System.out.println("Da sap xep tang dan: " + ds);
    }

    static void demSoTrung() {
        if (ds.isEmpty()) { System.out.println("Danh sach rong."); return; }
        Collections.sort(ds);
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