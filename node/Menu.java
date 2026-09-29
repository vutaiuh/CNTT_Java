package node;

public class Menu {

	// Dựng cây mẫu (giống trong Main)
	public static Node buildTree() {
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

	// In danh sách chức năng
	public static void printMenu() {
		System.out.println();
		System.out.println("========== MENU CAY NHI PHAN ==========");
		System.out.println(" 1. Duyet cay LNR (trai - goc - phai)");
		System.out.println(" 2. Duyet cay NLR (goc - trai - phai)");
		System.out.println(" 3. Duyet cay LRN (trai - phai - goc)");
		System.out.println(" 4. Duyet cay RNL (phai - goc - trai)");
		System.out.println(" 5. Duyet cay theo muc");
		System.out.println(" 6. Dem tong so node");
		System.out.println(" 7. Dem so node la");
		System.out.println(" 8. Node trai nhat");
		System.out.println(" 9. Node phai nhat");
		System.out.println("10. Tong cac label");
		System.out.println("11. Gia tri trung binh cac label");
		System.out.println("12. Dem so node nho hon x");
		System.out.println("13. Dem so node lon hon x");
		System.out.println("14. Chieu cao cua cay");
		System.out.println(" 0. Thoat");
		System.out.println("=======================================");
		System.out.print("Chon chuc nang: ");
	}

	public static void main(String[] args) {
		// Dùng tên đầy đủ nên không cần câu lệnh import
		java.util.Scanner sc = new java.util.Scanner(System.in);
		Node root = buildTree();
		int choice = -1;

		do {
			printMenu();
			try {
				choice = Integer.parseInt(sc.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Vui long nhap so nguyen!");
				choice = -1;
				continue;
			}

			switch (choice) {
			case 1:
				System.out.println("LNR:");
				Main.readtree(root);
				System.out.println();
				break;
			case 2:
				System.out.println("NLR:");
				Main.preorder(root);
				System.out.println();
				break;
			case 3:
				System.out.println("LRN:");
				Main.postorder(root);
				System.out.println();
				break;
			case 4:
				System.out.println("RNL:");
				Main.reverseInorder(root);
				System.out.println();
				break;
			case 5:
				System.out.println("Theo muc:");
				Main.levelOrder(root);
				System.out.println();
				break;
			case 6:
				System.out.println("So node cua cay: " + Main.countNode(root));
				break;
			case 7:
				System.out.println("So node la: " + Main.countLeaf(root));
				break;
			case 8:
				Node left = Main.theleftNode(root);
				System.out.println(left == null ? "Cay rong!" : "Node trai nhat co label: " + left.label);
				break;
			case 9:
				Node right = Main.therightNode(root);
				System.out.println(right == null ? "Cay rong!" : "Node phai nhat co label: " + right.label);
				break;
			case 10:
				System.out.println("Tong cac label: " + Main.sumLabel(root));
				break;
			case 11:
				int total = Main.countNode(root);
				if (total == 0) {
					System.out.println("Cay rong!");
				} else {
					System.out.println("Gia tri trung binh: " + (double) Main.sumLabel(root) / total);
				}
				break;
			case 12:
				try {
					System.out.print("Nhap x: ");
					double x = Double.parseDouble(sc.nextLine().trim());
					System.out.println("So node nho hon " + x + ": " + Main.countLess(root, x));
				} catch (NumberFormatException e) {
					System.out.println("Gia tri x khong hop le!");
				}
				break;
			case 13:
				try {
					System.out.print("Nhap x: ");
					double y = Double.parseDouble(sc.nextLine().trim());
					System.out.println("So node lon hon " + y + ": " + Main.countGreater(root, y));
				} catch (NumberFormatException e) {
					System.out.println("Gia tri x khong hop le!");
				}
				break;
			case 14:
				System.out.println("Chieu cao cua cay: " + Main.height(root));
				break;
			case 0:
				System.out.println("Tam biet!");
				break;
			default:
				System.out.println("Lua chon khong hop le, moi nhap lai!");
			}
		} while (choice != 0);

		sc.close();
	}
}