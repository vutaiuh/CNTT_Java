package zonthi;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static BST bst = new BST();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // === 9) Doc file khi khoi dong ===
        Customer savedRoot = FileManager.loadTree();
        if (savedRoot != null) {
            bst.setRoot(savedRoot);
        }

        // Neu cay rong, them du lieu mau
        if (bst.isEmpty()) {
            System.out.println("  [Info] Khong co du lieu cu, them du lieu mau...");
            themDuLieuMau();
            FileManager.saveTree(bst.getRoot());
        }

        int luaChon;
        do {
            inMenu();
            System.out.print("  Nhap lua chon: ");
            luaChon = docSoNguyen();
            System.out.println();
            switch (luaChon) {
                case 1  -> chuc1_NhapKhachHang();
                case 2  -> chuc2_XuatDanhSach();
                case 3  -> chuc3_TinhTuoi();
                case 4  -> chuc4_LocGioiTinhNhomMau();
                case 5  -> chuc5_SapXepTheoTen();
                case 6  -> chuc6_TimNamMaxMinTuoi();
                case 7  -> chuc7_TimTheoID();
                case 8  -> chuc8_XoaTheoID();
                case 9  -> chuc9_LuuFile();
                case 0  -> System.out.println("  Tam biet!");
                default -> System.out.println("  Lua chon khong hop le, vui long chon lai.");
            }
        } while (luaChon != 0);

        sc.close();
    }

    // ==================== MENU ====================
    static void inMenu() {
        System.out.println();
        System.out.println("=========================================");
        System.out.println("   QUAN LY KHACH HANG - CHUONG TRINH ON THI");
        System.out.println("=========================================");
        System.out.println("  1. Nhap khach hang vao cay BST");
        System.out.println("  2. Xuat danh sach khach hang (bang)");
        System.out.println("  3. Tinh tuoi TB, max, min");
        System.out.println("  4. Loc theo gioi tinh & nhom mau");
        System.out.println("  5. Sap xep theo ten - xuat danh sach Nam");
        System.out.println("  6. Tim Nam lon tuoi nhat / nho tuoi nhat");
        System.out.println("  7. Tim kiem khach hang theo ID");
        System.out.println("  8. Xoa khach hang theo ID");
        System.out.println("  9. Luu danh sach ra file");
        System.out.println("  0. Thoat");
        System.out.println("=========================================");
    }

    // ==================== CHUC NANG 1 ====================
    static void chuc1_NhapKhachHang() {
        System.out.println("--- NHAP KHACH HANG VAO CAY BST ---");
        System.out.print("Nhap so luong khach hang can them: ");
        int n = docSoNguyen();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n  Khach hang thu " + i + ":");

            System.out.print("  ID        : ");
            int id = docSoNguyen();

            sc.nextLine(); // xoa newline thua

            System.out.print("  Ho ten    : ");
            String ten = sc.nextLine().trim();

            System.out.print("  Gioi tinh (Nam/Nu): ");
            String gt = sc.nextLine().trim();

            System.out.print("  Tuoi      : ");
            int tuoi = docSoNguyen();

            sc.nextLine();
            System.out.print("  Nhom mau (A/B/O/AB): ");
            String mau = sc.nextLine().trim().toUpperCase();

            Customer kh = new Customer(id, ten, gt, tuoi, mau);
            bst.insert(kh);
        }
        System.out.println("  [OK] Da them " + n + " khach hang vao cay BST.");
        FileManager.saveTree(bst.getRoot());
    }

    // ==================== CHUC NANG 2 ====================
    static void chuc2_XuatDanhSach() {
        System.out.println("--- DANH SACH KHACH HANG (theo ID tang dan) ---");
        BST.printTable(bst.toList());
    }

    // ==================== CHUC NANG 3 ====================
    static void chuc3_TinhTuoi() {
        System.out.println("--- THONG KE TUOI ---");
        ArrayList<Customer> list = bst.toList();
        if (list.isEmpty()) {
            System.out.println("  Cay rong, khong co du lieu."); return;
        }
        System.out.printf("  Tuoi trung binh : %.2f%n", bst.avgAge());
        Customer max = bst.maxAge();
        Customer min = bst.minAge();
        System.out.println("  Lon tuoi nhat   : " + max);
        System.out.println("  Nho tuoi nhat   : " + min);
    }

    // ==================== CHUC NANG 4 ====================
    static void chuc4_LocGioiTinhNhomMau() {
        System.out.println("--- DANH SACH KHACH HANG NAM ---");
        BST.printTable(bst.filterGender("Nam"));

        System.out.println("\n--- DANH SACH KHACH HANG NU ---");
        BST.printTable(bst.filterGender("Nu"));

        System.out.println("\n--- NHOM MAU A ---");
        BST.printTable(bst.filterBlood("A"));

        System.out.println("\n--- NHOM MAU B ---");
        BST.printTable(bst.filterBlood("B"));

        System.out.println("\n--- NHOM MAU O ---");
        BST.printTable(bst.filterBlood("O"));

        System.out.println("\n--- NHOM MAU AB ---");
        BST.printTable(bst.filterBlood("AB"));
    }

    // ==================== CHUC NANG 5 ====================
    static void chuc5_SapXepTheoTen() {
        System.out.println("--- DANH SACH KHACH HANG NAM SAP XEP THEO TEN ---");
        ArrayList<Customer> sortedMale = bst.sortByName("Nam");
        BST.printTable(sortedMale);
    }

    // ==================== CHUC NANG 6 ====================
    static void chuc6_TimNamMaxMinTuoi() {
        System.out.println("--- KHACH HANG NAM THEO TUOI ---");
        Customer maxNam = bst.maxAgeByGender("Nam");
        Customer minNam = bst.minAgeByGender("Nam");

        if (maxNam == null) {
            System.out.println("  Khong co khach hang Nam trong he thong.");
        } else {
            System.out.println("  Nam lon tuoi nhat : " + maxNam);
            System.out.println("  Nam nho tuoi nhat : " + minNam);
        }
    }

    // ==================== CHUC NANG 7 ====================
    static void chuc7_TimTheoID() {
        System.out.println("--- TIM KIEM THEO ID ---");
        System.out.print("  Nhap ID can tim: ");
        int id = docSoNguyen();

        Customer found = bst.search(id);
        if (found != null) {
            System.out.println("\n  Tim thay:");
            ArrayList<Customer> temp = new ArrayList<>();
            temp.add(found);
            BST.printTable(temp);
        } else {
            System.out.println("  Khong tim thay khach hang co ID = " + id);
        }
    }

    // ==================== CHUC NANG 8 ====================
    static void chuc8_XoaTheoID() {
        System.out.println("--- XOA KHACH HANG THEO ID ---");
        System.out.print("  Nhap ID can xoa: ");
        int id = docSoNguyen();

        Customer found = bst.search(id);
        if (found == null) {
            System.out.println("  Khong tim thay khach hang co ID = " + id);
            return;
        }

        System.out.println("  Tim thay: " + found);
        System.out.print("  Ban co chac muon xoa? (y/n): ");
        sc.nextLine();
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            bst.delete(id);
            System.out.println("  [OK] Da xoa khach hang ID = " + id);
            FileManager.saveTree(bst.getRoot());
            System.out.println("\n  Danh sach sau khi xoa:");
            BST.printTable(bst.toList());
        } else {
            System.out.println("  Huy thao tac xoa.");
        }
    }

    // ==================== CHUC NANG 9 ====================
    static void chuc9_LuuFile() {
        System.out.println("--- LUU DU LIEU RA FILE ---");
        FileManager.saveTree(bst.getRoot());
    }

    // ==================== DU LIEU MAU ====================
    static void themDuLieuMau() {
        bst.insert(new Customer(107, "Nguyen Van An",    "Nam", 30, "A"));
        bst.insert(new Customer(101, "Le Thi Hoa",       "Nu",  20, "O"));
        bst.insert(new Customer(105, "Tran Van Khanh",   "Nam", 35, "B"));
        bst.insert(new Customer(103, "Pham Ngoc Mai",    "Nu",  28, "AB"));
        bst.insert(new Customer(109, "Vo Thanh Huy",     "Nam", 40, "A"));
        bst.insert(new Customer(102, "Bui Van Binh",     "Nam", 25, "O"));
        bst.insert(new Customer(104, "Dang Thi Thuan",   "Nu",  37, "B"));
        bst.insert(new Customer(106, "Nguyen Thi Lan",   "Nu",  22, "A"));
        bst.insert(new Customer(108, "Hoang Minh Duc",   "Nam", 45, "O"));
        bst.insert(new Customer(110, "Ly Thi Thu",       "Nu",  19, "B"));
    }

    // ==================== TIEN ICH ====================
    static int docSoNguyen() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Vui long nhap so nguyen: ");
            }
        }
    }
}
