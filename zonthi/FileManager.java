package zonthi;

import java.io.*;

/**
 * Luu / doc cay BST ra file nhi phan (Serializable)
 * File: customers.dat (luu trong thu muc chay chuong trinh)
 */
public class FileManager {

    private static final String FILE_PATH = "customers.dat";

    // Luu BST ra file
    public static boolean saveTree(Customer root) {
        try (ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(root);
            System.out.println("  [OK] Da luu du lieu vao file: " + FILE_PATH);
            return true;
        } catch (IOException e) {
            System.out.println("  [LOI] Ghi file: " + e.getMessage());
            return false;
        }
    }

    // Doc BST tu file (tra ve null neu chua co file)
    public static Customer loadTree() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            System.out.println("  [Info] Chua co file du lieu, bat dau voi cay rong.");
            return null;
        }
        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Customer root = (Customer) ois.readObject();
            System.out.println("  [OK] Da doc du lieu tu file: " + FILE_PATH);
            return root;
        } catch (Exception e) {
            System.out.println("  [LOI] Doc file: " + e.getMessage());
            return null;
        }
    }
}
