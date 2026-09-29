package node;

public class Main {

	// Duyệt cây theo thứ tự LNR (trái - gốc - phải)
	public static void readtree(Node root) {
		if (root != null) {
			readtree(root.left);
			System.out.print(root.label + " ");
			readtree(root.right);
		}
	}

	// Duyệt cây theo thứ tự NLR (gốc - trái - phải) : tiền thứ tự
	public static void preorder(Node root) {
		if (root != null) {
			System.out.print(root.label + " ");
			preorder(root.left);
			preorder(root.right);
		}
	}

	// Duyệt cây theo thứ tự LRN (trái - phải - gốc) : hậu thứ tự
	public static void postorder(Node root) {
		if (root != null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.label + " ");
		}
	}

	// Duyệt cây theo thứ tự RNL (phải - gốc - trái) : LNR đảo ngược
	public static void reverseInorder(Node root) {
		if (root != null) {
			reverseInorder(root.right);
			System.out.print(root.label + " ");
			reverseInorder(root.left);
		}
	}

	// In các node ở đúng tầng "level" (tầng 1 = gốc)
	public static void printLevel(Node root, int level) {
		if (root == null) return;
		if (level == 1) {
			System.out.print(root.label + " ");
		} else {
			printLevel(root.left, level - 1);
			printLevel(root.right, level - 1);
		}
	}

	// Duyệt cây theo mức (từng tầng, từ trái sang phải), không dùng hàng đợi
	public static void levelOrder(Node root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printLevel(root, i);
		}
	}

	// Đếm tổng số node
	public static int countNode(Node root) {
		if (root == null) return 0;
		else return countNode(root.left) + 1 + countNode(root.right);
	}

	// Đếm số node lá
	public static int countLeaf(Node root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;
		else return countLeaf(root.left) + countLeaf(root.right);
	}

	// Node trái nhất
	public static Node theleftNode(Node root) {
		if (root == null) return null;
		if (root.left == null) return root;
		else return theleftNode(root.left);
	}

	// Node phải nhất
	public static Node therightNode(Node root) {
		if (root == null) return null;
		if (root.right == null) return root;
		else return therightNode(root.right);
	}

	// Tổng các label
	public static int sumLabel(Node root) {
		if (root == null) return 0;
		else return sumLabel(root.left) + root.label + sumLabel(root.right);
	}

	// Đếm số node có label nhỏ hơn giá trị x
	public static int countLess(Node root, double x) {
		if (root == null) return 0;
		int c = (root.label < x) ? 1 : 0;
		return countLess(root.left, x) + c + countLess(root.right, x);
	}

	// Đếm số node có label lớn hơn giá trị x
	public static int countGreater(Node root, double x) {
		if (root == null) return 0;
		int c = (root.label > x) ? 1 : 0;
		return countGreater(root.left, x) + c + countGreater(root.right, x);
	}

	// Chiều cao của cây (cây rỗng = 0, cây 1 node = 1)
	public static int height(Node root) {
		if (root == null) return 0;
		else return Math.max(height(root.left), height(root.right)) + 1;
	}

	public static void main(String[] args) {
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
		Node root = n7;

		System.out.println("Duyet cay theo thu tu LNR:");
		readtree(root);
		System.out.println();

		System.out.println("Duyet cay theo thu tu NLR:");
		preorder(root);
		System.out.println();

		System.out.println("Duyet cay theo thu tu LRN:");
		postorder(root);
		System.out.println();

		System.out.println("Duyet cay theo thu tu RNL:");
		reverseInorder(root);
		System.out.println();

		System.out.println("Duyet cay theo muc:");
		levelOrder(root);
		System.out.println();

		int total = countNode(root);
		System.out.println("So Node cua cay root la: " + total);
		System.out.println("So leaf cay root la: " + countLeaf(root));
		System.out.println("Node trai nhat co label: " + theleftNode(root).label);
		System.out.println("Node phai nhat co label: " + therightNode(root).label);

		double avg = (double) sumLabel(root) / total;
		System.out.println("Gia tri trung binh cac label: " + avg);
		System.out.println("So node nho hon trung binh: " + countLess(root, avg));
		System.out.println("So node lon hon trung binh: " + countGreater(root, avg));

		System.out.println("Chieu cao cua cay: " + height(root));
	}
}