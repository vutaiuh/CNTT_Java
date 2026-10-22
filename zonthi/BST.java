package zonthi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Cay nhi phan tim kiem BST - nhan la customer_ID
 */
public class BST {

    private Customer root;

    public BST() {
        this.root = null;
    }

    public Customer getRoot() { return root; }
    public void setRoot(Customer root) { this.root = root; }

    // ===================== 1) THEM NODE =====================
    public void insert(Customer e) {
        root = insertRec(root, e);
    }

    private Customer insertRec(Customer node, Customer e) {
        if (node == null)
            return new Customer(e.getCustomer_ID(), e.getCustomer_Name(),
                                e.getCustomer_Gender(), e.getCustomer_Age(),
                                e.getCustomer_Blood());
        if (e.getCustomer_ID() < node.getCustomer_ID())
            node.left  = insertRec(node.left,  e);
        else if (e.getCustomer_ID() > node.getCustomer_ID())
            node.right = insertRec(node.right, e);
        else
            System.out.println("  [!] ID " + e.getCustomer_ID() + " da ton tai, bo qua.");
        return node;
    }

    // ===================== 2) DUYET LNR -> ArrayList =====================
    public ArrayList<Customer> toList() {
        ArrayList<Customer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(Customer node, ArrayList<Customer> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node);
            inorder(node.right, list);
        }
    }

    // ===================== IN BANG =====================
    public static void printTable(ArrayList<Customer> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("  (Danh sach trong)");
            return;
        }
        System.out.println("+---------+---------------------------+------+-----+-------+");
        System.out.printf ("| %-7s | %-25s | %-4s | %-3s | %-5s |%n",
                "ID", "Ho Ten", "GT", "Tuoi", "Mau");
        System.out.println("+---------+---------------------------+------+-----+-------+");
        for (Customer c : list) {
            System.out.printf("| %-7d | %-25s | %-4s | %-3d | %-5s |%n",
                    c.getCustomer_ID(), c.getCustomer_Name(),
                    c.getCustomer_Gender(), c.getCustomer_Age(),
                    c.getCustomer_Blood());
        }
        System.out.println("+---------+---------------------------+------+-----+-------+");
        System.out.println("  Tong: " + list.size() + " khach hang.");
    }

    // ===================== 3) TINH TUOI =====================
    public double avgAge() {
        ArrayList<Customer> list = toList();
        if (list.isEmpty()) return 0;
        int sum = 0;
        for (Customer c : list) sum += c.getCustomer_Age();
        return (double) sum / list.size();
    }

    public Customer maxAge() {
        ArrayList<Customer> list = toList();
        if (list.isEmpty()) return null;
        Customer max = list.get(0);
        for (Customer c : list)
            if (c.getCustomer_Age() > max.getCustomer_Age()) max = c;
        return max;
    }

    public Customer minAge() {
        ArrayList<Customer> list = toList();
        if (list.isEmpty()) return null;
        Customer min = list.get(0);
        for (Customer c : list)
            if (c.getCustomer_Age() < min.getCustomer_Age()) min = c;
        return min;
    }

    // ===================== 4) LỌC GIOI TINH / NHOM MAU =====================
    public ArrayList<Customer> filterGender(String gender) {
        ArrayList<Customer> res = new ArrayList<>();
        filterGenderRec(root, gender, res);
        return res;
    }

    private void filterGenderRec(Customer node, String gender,
                                  ArrayList<Customer> res) {
        if (node == null) return;
        filterGenderRec(node.left, gender, res);
        if (node.getCustomer_Gender().equalsIgnoreCase(gender)) res.add(node);
        filterGenderRec(node.right, gender, res);
    }

    public ArrayList<Customer> filterBlood(String blood) {
        ArrayList<Customer> res = new ArrayList<>();
        filterBloodRec(root, blood, res);
        return res;
    }

    private void filterBloodRec(Customer node, String blood,
                                  ArrayList<Customer> res) {
        if (node == null) return;
        filterBloodRec(node.left, blood, res);
        if (node.getCustomer_Blood().equalsIgnoreCase(blood)) res.add(node);
        filterBloodRec(node.right, blood, res);
    }

    // ===================== 5) SAP XEP THEO TEN =====================
    public ArrayList<Customer> sortByName(String gender) {
        ArrayList<Customer> list = filterGender(gender);
        Collections.sort(list, Comparator.comparing(
            c -> normalize(c.getCustomer_Name())));
        return list;
    }

    // Lay ten cuoi de sap xep (ho va ten tieng Viet: "Nguyen Van An" -> "An")
    private String normalize(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) return "";
        String[] parts = fullName.trim().split("\\s+");
        return parts[parts.length - 1].toLowerCase();
    }

    // ===================== 6) TIM NAM MAX / MIN TUOI =====================
    public Customer maxAgeByGender(String gender) {
        ArrayList<Customer> list = filterGender(gender);
        if (list.isEmpty()) return null;
        Customer max = list.get(0);
        for (Customer c : list)
            if (c.getCustomer_Age() > max.getCustomer_Age()) max = c;
        return max;
    }

    public Customer minAgeByGender(String gender) {
        ArrayList<Customer> list = filterGender(gender);
        if (list.isEmpty()) return null;
        Customer min = list.get(0);
        for (Customer c : list)
            if (c.getCustomer_Age() < min.getCustomer_Age()) min = c;
        return min;
    }

    // ===================== 7) TIM KIEM THEO ID =====================
    public Customer search(int id) {
        return searchRec(root, id);
    }

    private Customer searchRec(Customer node, int id) {
        if (node == null) return null;
        if (id == node.getCustomer_ID()) return node;
        if (id < node.getCustomer_ID()) return searchRec(node.left,  id);
        return searchRec(node.right, id);
    }

    // ===================== 8) XOA THEO ID =====================
    public boolean delete(int id) {
        if (search(id) == null) return false;
        root = deleteRec(root, id);
        return true;
    }

    private Customer deleteRec(Customer node, int id) {
        if (node == null) return null;
        if (id < node.getCustomer_ID()) {
            node.left  = deleteRec(node.left,  id);
        } else if (id > node.getCustomer_ID()) {
            node.right = deleteRec(node.right, id);
        } else {
            // Xoa node nay
            if (node.left == null)  return node.right;
            if (node.right == null) return node.left;
            // Co 2 con: lay node nho nhat ben phai (inorder successor)
            Customer successor = findMin(node.right);
            successor.right = deleteRec(node.right, successor.getCustomer_ID());
            successor.left  = node.left;
            return successor;
        }
        return node;
    }

    private Customer findMin(Customer node) {
        if (node == null || node.left == null) return node;
        return findMin(node.left);
    }

    // ===================== KIEM TRA TRONG =====================
    public boolean isEmpty() { return root == null; }

     // ===================== IN CAY DANG HINH CAY THAT (tren xuong) chi de tham khao =====================
    public void printTreeShape() {
        System.out.println("\n--- HINH DANG CAY BST (goc o tren, trai/phai nhu thuc te) ---");
        if (root == null) {
            System.out.println("  (Cay rong)");
            return;
        }
        int cell = maxIdLen(root) + 2;           // do rong moi o = so chu so lon nhat + 2
        int rows = getHeight() * 2 - 1;          // moi tang: 1 dong ten + 1 dong noi nhanh
        int cols = countNode() * cell;           // moi node chiem 1 o theo thu tu LNR
        char[][] g = new char[rows][cols];
        for (char[] r : g) java.util.Arrays.fill(r, ' ');

        fillGrid(root, 0, g, new int[]{0}, cell);

        for (char[] r : g)
            System.out.println("  " + new String(r).replaceAll("\\s+$", ""));
        System.out.println();
    }
    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(Customer node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
    }
    public int countNode() {
        return countNodeRec(root);
    }

    private int countNodeRec(Customer node) {
        if (node == null) return 0;
        return 1 + countNodeRec(node.left) + countNodeRec(node.right);
    }
    private int maxIdLen(Customer node) {
        if (node == null) return 0;
        return Math.max(String.valueOf(node.getId()).length(),
                Math.max(maxIdLen(node.left), maxIdLen(node.right)));
    }
     private int fillGrid(Customer node, int depth, char[][] g, int[] counter, int cell) {
        int lc = -1, rc = -1;
        if (node.left != null) lc = fillGrid(node.left, depth + 1, g, counter, cell);

        int col = counter[0] * cell + cell / 2;
        counter[0]++;

        if (node.right != null) rc = fillGrid(node.right, depth + 1, g, counter, cell);

        // dong ten node
        String s = String.valueOf(node.getId());
        int start = col - s.length() / 2;
        for (int i = 0; i < s.length(); i++) g[depth * 2][start + i] = s.charAt(i);

        // dong noi nhanh xuong con
        int r = depth * 2 + 1;
        if (lc != -1 && rc != -1) {
            g[r][lc] = '┌';
            for (int x = lc + 1; x < rc; x++) g[r][x] = '─';
            g[r][rc] = '┐';
            g[r][col] = '┴';
        } else if (lc != -1) {
            g[r][lc] = '┌';
            for (int x = lc + 1; x < col; x++) g[r][x] = '─';
            g[r][col] = '┘';
        } else if (rc != -1) {
            g[r][col] = '└';
            for (int x = col + 1; x < rc; x++) g[r][x] = '─';
            g[r][rc] = '┐';
        }
        return col;
    }

}
