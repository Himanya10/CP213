package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Himanya Verma
 * @author David Brown
 * @version 2024-10-15
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for valid. May force node rotation if the retrieval count of
     * the located node data is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The data to search for. Count is updated to count in matching
     *             node data if key is found.
     * @return The updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedData<T> key) {

	if (node == null) {
	    node = new TreeNode<T>(key);
	}

	else {

	    // Compare the node data & retrieve data.
	    final int result = node.getData().compareTo(key);
	    this.comparisons++;

	    if (result > 0) {
		// General case - check the left subtree.
		node.setLeft(this.retrieveAux(node.getLeft(), key));

		if (node.getLeft() != null && node.getData().getCount() < node.getLeft().getData().getCount()) {
		    node = this.rotateRight(node);
		}
	    }

	    else if (result < 0) {
		// General case - check the right subtree.
		node.setRight(this.retrieveAux(node.getRight(), key));

		if (node.getRight() != null && node.getData().getCount() < node.getRight().getData().getCount()) {
		    node = this.rotateLeft(node);
		}
	    }

	    else {
		// Base case - data is in the Popularity Tree.
		node.getData().incrementCount();
		key.setCount(node.getData().getCount());
	    }
	}
	return node;

    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

	TreeNode<T> newRoot = parent.getRight();
	parent.setRight(newRoot.getLeft());
	newRoot.setLeft(parent);
	parent.updateHeight();
	newRoot.updateHeight();

	return newRoot;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

	TreeNode<T> newRoot = parent.getLeft();
	parent.setLeft(newRoot.getRight());
	newRoot.setRight(parent);
	parent.updateHeight();
	newRoot.updateHeight();

	return newRoot;
    }

    /**
     * Replaces BST insertAux - does not increment count on repeated insertion.
     * Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedData<T> data) {

	if (node == null) {
	    node = new TreeNode<>(data);
	    this.size++;
	} else {
	    int comparision = data.compareTo(node.getData());
	    if (comparision < 0) {
		node.setLeft(insertAux(node.getLeft(), data));
	    } else if (comparision > 0) {
		node.setRight(insertAux(node.getRight(), data));
	    }
	}

	node.updateHeight();
	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An Popularity Tree must meet the BST validation conditions, and
     * additionally the counts of any node data must be greater than or equal to the
     * counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	if (node == null) {
	    return true;
	}
	if ((minNode != null && node.getData().compareTo(minNode.getData()) <= 0)
		|| (maxNode != null && node.getData().compareTo(maxNode.getData()) >= 0)) {
	    return true;
	}

	if ((node.getLeft() != null && node.getData().getCount() < node.getLeft().getData().getCount())
		|| (node.getRight() != null && node.getData().getCount() < node.getRight().getData().getCount())) {
	    return false;

	}
	return isValidAux(node.getLeft(), minNode, node);
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, data, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

    /**
     * Very similar to the BST retrieve, but increments the data count here instead
     * of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedData<T> retrieve(CountedData<T> key) {

	this.root = retrieveAux(this.root, key);
	if (this.root != null && this.root.getData().compareTo(key) == 0) {
	    return this.root.getData();
	}

	return null;
    }

}
