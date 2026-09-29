package tree_processing;

import java.util.ArrayList;

/**
 * Xu ly cay nhi phan bang ArrayList:
 * - Cac phep duyet cay luu ket qua vao ArrayList<Integer>
 * - Duyet theo muc dung de quy tung tang (khong dung hang doi)
 * - Cac nghiep vu tong / trung binh / dem nho hon / lon hon tinh tren ArrayList
 */
public class XuLyCayArrayList {

	// ===== DUYET CAY: luu ket qua vao ArrayList =====

	// LNR (trai - goc - phai)
	public static void lnr(Node root, ArrayList<Integer> ds) {
		if (root != null) {
			lnr(root.left, ds);
			ds.add(root.label);
			lnr(root.right, ds);
		}
	}

	// NLR (goc - trai - phai): tien thu tu
	public static void nlr(Node root, ArrayList<Integer> ds) {
		if (root != null) {
			ds.add(root.label);
			nlr(root.left, ds);
			nlr(root.right, ds);
		}
	}

	// LRN (trai - phai - goc): hau thu tu
	public static void lrn(Node root, ArrayList<Integer> ds) {
		if (root != null) {
			lrn(root.left, ds);
			lrn(root.right, ds);
			ds.add(root.label);
		}
	}

	// RNL (phai - goc - trai): LNR dao nguoc
	public static void rnl(Node root, ArrayList<Integer> ds) {
		if (root != null) {
			rnl(root.right, ds);
			ds.add(root.label);
			rnl(root.left, ds);
		}
	}

	// Lay cac node o dung tang "level" (tang 1 = goc)
	public static void layTheoTang(Node root, int level, ArrayList<Integer> ds) {
		if (root == null) return;
		if (level == 1) {
			ds.add(root.label);
		} else {
			layTheoTang(root.left, level - 1, ds);
			layTheoTang(root.right, level - 1, ds);
		}
	}

	// Duyet theo muc (tung tang, trai -> phai), khong dung hang doi
	public static ArrayList<Integer> duyetTheoMuc(Node root) {
		ArrayList<Integer> ds = new ArrayList<>();
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			layTheoTang(root, i, ds);
		}
		return ds;
	}

	// ===== NGHIEP VU TREN CAY (de quy) =====

	public static int countNode(Node root) {
		if (root == null) return 0;
		return countNode(root.left) + 1 + countNode(root.right);
	}

	public static int countLeaf(Node root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;
		return countLeaf(root.left) + countLeaf(root.right);
	}

	public static int height(Node root) {
		if (root == null) return 0;
		return Math.max(height(root.left), height(root.right)) + 1;
	}

	public static Node theleftNode(Node root) {
		if (root == null) return null;
		if (root.left == null) return root;
		return theleftNode(root.left);
	}

	public static Node therightNode(Node root) {
		if (root == null) return null;
		if (root.right == null) return root;
		return therightNode(root.right);
	}

	// ===== NGHIEP VU TINH TREN ARRAYLIST =====

	public static int sumLabel(ArrayList<Integer> ds) {
		int tong = 0;
		for (int i = 0; i < ds.size(); i++) {
			tong += ds.get(i); // get(i) cua ArrayList la O(1)
		}
		return tong;
	}

	public static int countLess(ArrayList<Integer> ds, double x) {
		int dem = 0;
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i) < x) dem++;
		}
		return dem;
	}

	public static int countGreater(ArrayList<Integer> ds, double x) {
		int dem = 0;
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i) > x) dem++;
		}
		return dem;
	}

	// ===== TIEN ICH =====

	public static void in(String tieuDe, ArrayList<Integer> ds) {
		System.out.println(tieuDe);
		for (int x : ds) System.out.print(x + " ");
		System.out.println();
	}

	public static Node taoCayMau() {
		Node n7 = new Node(7);
		Node n5 = new Node(5);
		Node n12 = new Node(12);
		Node n3 = new Node(3);
		Node n6 = new Node(6);
		Node n9 = new Node(9);
		Node n15 = new Node(15);
		Node n1 = new Node(1);
		Node n4 = new Node(4);
		Node n8 = new Node(8);
		Node n10 = new Node(10);
		Node n13 = new Node(13);
		Node n17 = new Node(17);
		n3.left = n1; n3.right = n4;
		n9.left = n8; n9.right = n10;
		n15.left = n13; n15.right = n17;
		n5.left = n3; n5.right = n6;
		n12.left = n9; n12.right = n15;
		n7.left = n5; n7.right = n12;
		return n7;
	}

	public static void main(String[] args) {
		Node root = taoCayMau();

		ArrayList<Integer> dsLNR = new ArrayList<>();
		lnr(root, dsLNR);
		in("Duyet cay theo thu tu LNR:", dsLNR);

		ArrayList<Integer> dsNLR = new ArrayList<>();
		nlr(root, dsNLR);
		in("Duyet cay theo thu tu NLR:", dsNLR);

		ArrayList<Integer> dsLRN = new ArrayList<>();
		lrn(root, dsLRN);
		in("Duyet cay theo thu tu LRN:", dsLRN);

		ArrayList<Integer> dsRNL = new ArrayList<>();
		rnl(root, dsRNL);
		in("Duyet cay theo thu tu RNL:", dsRNL);

		in("Duyet cay theo muc:", duyetTheoMuc(root));

		int total = countNode(root);
		System.out.println("So Node cua cay root la: " + total);
		System.out.println("So leaf cay root la: " + countLeaf(root));
		System.out.println("Node trai nhat co label: " + theleftNode(root).label);
		System.out.println("Node phai nhat co label: " + therightNode(root).label);

		// Tong, trung binh, dem nho/lon hon: tinh tren ArrayList (dsLNR)
		double avg = (double) sumLabel(dsLNR) / dsLNR.size();
		System.out.println("Gia tri trung binh cac label: " + avg);
		System.out.println("So node nho hon trung binh: " + countLess(dsLNR, avg));
		System.out.println("So node lon hon trung binh: " + countGreater(dsLNR, avg));

		System.out.println("Chieu cao cua cay: " + height(root));
	}
}