package tree_processing;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Xu ly cay nhi phan bang LinkedList:
 * - Cac phep duyet cay luu ket qua vao LinkedList<Integer>
 * - Duyet theo muc dung LinkedList lam HANG DOI (Queue: offer / poll)
 * - Duyet NLR khong de quy dung LinkedList lam NGAN XEP (Stack: push / pop)
 * - RNL lay bang cach duyet nguoc LNR qua descendingIterator()
 * - Cac nghiep vu tong / trung binh / dem nho hon / lon hon duyet bang for-each (Iterator)
 */
public class XuLyCayLinkedList {

	// ===== DUYET CAY: luu ket qua vao LinkedList =====

	// LNR (trai - goc - phai)
	public static void lnr(Node root, LinkedList<Integer> ds) {
		if (root != null) {
			lnr(root.left, ds);
			ds.addLast(root.label);
			lnr(root.right, ds);
		}
	}

	// NLR (goc - trai - phai): KHONG de quy, dung LinkedList lam stack
	public static LinkedList<Integer> nlr(Node root) {
		LinkedList<Integer> ketQua = new LinkedList<>();
		LinkedList<Node> nganXep = new LinkedList<>();
		if (root != null) nganXep.push(root);
		while (!nganXep.isEmpty()) {
			Node n = nganXep.pop();
			ketQua.addLast(n.label);
			// day phai truoc de trai duoc lay ra truoc
			if (n.right != null) nganXep.push(n.right);
			if (n.left != null) nganXep.push(n.left);
		}
		return ketQua;
	}

	// LRN (trai - phai - goc): hau thu tu
	public static void lrn(Node root, LinkedList<Integer> ds) {
		if (root != null) {
			lrn(root.left, ds);
			lrn(root.right, ds);
			ds.addLast(root.label);
		}
	}

	// RNL (phai - goc - trai): them vao DAU danh sach khi duyet LNR
	// -> ket qua tu dong bi dao nguoc, khong can duyet lai cay
	public static LinkedList<Integer> rnl(Node root) {
		LinkedList<Integer> ketQua = new LinkedList<>();
		rnlHelper(root, ketQua);
		return ketQua;
	}

	private static void rnlHelper(Node root, LinkedList<Integer> ds) {
		if (root != null) {
			rnlHelper(root.left, ds);
			ds.addFirst(root.label); // addFirst: O(1) tren LinkedList
			rnlHelper(root.right, ds);
		}
	}

	// Duyet theo muc (BFS): LinkedList dong vai tro hang doi
	public static LinkedList<Integer> duyetTheoMuc(Node root) {
		LinkedList<Integer> ketQua = new LinkedList<>();
		LinkedList<Node> hangDoi = new LinkedList<>();
		if (root != null) hangDoi.offer(root);
		while (!hangDoi.isEmpty()) {
			Node n = hangDoi.poll(); // lay phan tu o dau hang doi
			ketQua.addLast(n.label);
			if (n.left != null) hangDoi.offer(n.left);
			if (n.right != null) hangDoi.offer(n.right);
		}
		return ketQua;
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

	// ===== NGHIEP VU TINH TREN LINKEDLIST (duyet bang for-each / Iterator) =====

	public static int sumLabel(LinkedList<Integer> ds) {
		int tong = 0;
		for (int x : ds) tong += x; // khong dung get(i) vi la O(n) tren LinkedList
		return tong;
	}

	public static int countLess(LinkedList<Integer> ds, double x) {
		int dem = 0;
		for (int v : ds) {
			if (v < x) dem++;
		}
		return dem;
	}

	public static int countGreater(LinkedList<Integer> ds, double x) {
		int dem = 0;
		Iterator<Integer> it = ds.iterator();
		while (it.hasNext()) {
			if (it.next() > x) dem++;
		}
		return dem;
	}

	// ===== TIEN ICH =====

	public static void in(String tieuDe, LinkedList<Integer> ds) {
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

		LinkedList<Integer> dsLNR = new LinkedList<>();
		lnr(root, dsLNR);
		in("Duyet cay theo thu tu LNR:", dsLNR);

		in("Duyet cay theo thu tu NLR (stack):", nlr(root));

		LinkedList<Integer> dsLRN = new LinkedList<>();
		lrn(root, dsLRN);
		in("Duyet cay theo thu tu LRN:", dsLRN);

		in("Duyet cay theo thu tu RNL (addFirst):", rnl(root));

		in("Duyet cay theo muc (queue):", duyetTheoMuc(root));

		int total = countNode(root);
		System.out.println("So Node cua cay root la: " + total);
		System.out.println("So leaf cay root la: " + countLeaf(root));
		System.out.println("Node trai nhat co label: " + theleftNode(root).label);
		System.out.println("Node phai nhat co label: " + therightNode(root).label);

		// Tong, trung binh, dem nho/lon hon: tinh tren LinkedList (dsLNR)
		double avg = (double) sumLabel(dsLNR) / dsLNR.size();
		System.out.println("Gia tri trung binh cac label: " + avg);
		System.out.println("So node nho hon trung binh: " + countLess(dsLNR, avg));
		System.out.println("So node lon hon trung binh: " + countGreater(dsLNR, avg));

		System.out.println("Chieu cao cua cay: " + height(root));
	}
}