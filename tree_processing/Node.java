package tree_processing;

public class Node {
	int label;
	Node left;
	Node right;

	public Node(int label) {
		this.label = label;
		this.left = null;
		this.right = null;
	}
}