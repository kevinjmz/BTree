/****************************************************
 * 
 * Kevin Jimenez
 * 
 * 
 * The University of Texas at El Paso
 * 
 * CS2302 Data Structures
 * 
 * Lab 5
 * 
 * Professor Olac Fuentes
 **************************************************/
// package kevinGarciaJimenez;
public class BTreeNode {
	public int[] key;
	public BTreeNode[] child;
	boolean isLeaf;
	public int numberOfKeys;
	private int T; // Each node has at least T-1 and at most 2T-1 keys

	public BTreeNode(int t) {// Build nodes inside tree
		T = t;
		isLeaf = true;
		key = new int[2 * T - 1];
		child = new BTreeNode[2 * T];
		numberOfKeys = 0;
	}

	public boolean isFull() {// Check if the nodes are full
		return numberOfKeys == (2 * T - 1);
	}

	public void print() {
		// Prints all keys in the tree in ascending order
		if (isLeaf) {
			for (int i = 0; i < numberOfKeys; i++)
				System.out.print(key[i] + " ");
			System.out.println();
		} else {
			for (int i = 0; i < numberOfKeys; i++) {
				child[i].print();
				System.out.print(key[i] + " ");
			}
			child[numberOfKeys].print();
		}
	}

	public boolean search(int mValueB) {
		// Search a key in the tree, in the most optimal way
		boolean result = false;

		if (isLeaf) {
			for (int i = 0; i < numberOfKeys; i++)// last option, check one by
													// one
			{
				System.out.println(" Comparing : " + key[i]);// print which key
																// is being
																// compared
				if (mValueB == key[i]) {
					System.out.println(" FOUND ");
					result = true;
					return result;// in case it is FOUND in LEAF
				}
			}
			System.out.println();
		} else {// in case it is NOT FOUND in LEAF and has to traverse tree
			for (int i = 0; i < numberOfKeys; i++) {
				System.out.println(" Comparing : " + key[i]);
				if (mValueB == key[i]) {
					System.out.println("FOUND in Tree " + key[i]);
					result = true;
					return true; // FOUND in TREE
				} else // Check which child to check... Left or Right ?
				if (key[i] > mValueB) {
					System.out.println("Going Left " + key[i]);
					result = child[i].search(mValueB); // Search on LEFT Branch
					break;
					// if (result) break If not Found Can't be on the right.
					// Check if Next Number is Greater

				} else if (i + 1 == numberOfKeys) {
					System.out.println("Going Right End of Node" + key[i]);
					result = child[i + 1].search(mValueB); // Search on Right
															// Branch
					break;
				} else if (mValueB == key[i + 1]) {
					System.out.println("FOUND in Tree +1 " + key[i + 1]);
					result = true;
					return true; // FOUND in TREE
				} else if (mValueB < key[i + 1]) {
					System.out.println("Going Right " + key[i] + " Left of " + key[i + 1]);
					result = child[i + 1].search(mValueB); // Search on Right
															// Branch
					break;
				}
			}
		}
		return result;
	}

	public void insert(int newKey) {
		// Insert new key to current node
		// We make sure that the current node is not full by checking and
		// splitting if necessary before descending to node

		System.out.println("inserting " + newKey); // Debugging code
		int i = numberOfKeys - 1;
		if (isLeaf) {
			while ((i >= 0) && (newKey < key[i])) { // Sorting Data
				key[i + 1] = key[i];
				i--;
			}

			numberOfKeys++;
			key[i + 1] = newKey;
		} else {
			while ((i >= 0) && (newKey < key[i])) {
				i--;
			}
			int insertChild = i + 1; // Subtree where new key must be inserted

			if (child[insertChild].isFull()) {// used to split node
				// The root of the subtree where new key will be inserted has to
				// be split
				// We promote the mediand of that root to the current node and
				// update keys and references accordingly
				BTree.totalNumberOfNodes++;
				System.out.println("This is the full node we're going to break ");// Debugging
																					// code
				// child[insertChild].printNodes();
				System.out.println("going to promote " + child[insertChild].key[T - 1]);
				numberOfKeys++;
				child[numberOfKeys] = child[numberOfKeys - 1];
				for (int j = numberOfKeys - 1; j > insertChild; j--) {
					child[j] = child[j - 1];
					key[j] = key[j - 1];
				}
				key[insertChild] = child[insertChild].key[T - 1];
				child[insertChild].numberOfKeys = T - 1;

				BTreeNode newNode = new BTreeNode(T);
				for (int k = 0; k < T - 1; k++) {
					newNode.child[k] = child[insertChild].child[k + T];
					newNode.key[k] = child[insertChild].key[k + T];
				}

				newNode.child[T - 1] = child[insertChild].child[2 * T - 1];
				newNode.numberOfKeys = T - 1;
				newNode.isLeaf = child[insertChild].isLeaf;
				child[insertChild + 1] = newNode;

				if (newKey < key[insertChild]) {
					child[insertChild].insert(newKey);
				} else {
					child[insertChild + 1].insert(newKey);
				}
			} // end of split code

			else
				child[insertChild].insert(newKey);// insert new Key recursively
		}
	}

	public void printNodes() {
		// Prints all keys in the tree, node by node, using preorder
		// It also prints the indicator of whether a node is a leaf
		// Used mostly for debugging purposes
		for (int i = 0; i < numberOfKeys; i++)
			System.out.print(key[i] + " ");
		if (!isLeaf) {
			for (int i = 0; i <= numberOfKeys; i++) {
				child[i].printNodes();
			}
		}
	}

	public void printDescending() {

		if (!isLeaf) {
			for (int i = numberOfKeys; i > 0; i--) {// start at the end of the
													// node
				child[i].printDescending();// check on the right most child node
				System.out.print(key[i - 1] + " ");
				child[i - 1].printDescending();// keep going until it is leaf
			}

		} else {// in case it is a leaf
			for (int i = numberOfKeys; i > 0; i--)// find the most right key and
													// print it
				System.out.print(key[i - 1] + " ");
		}
	}

	public boolean isInTree(int k) {
		return search(k);// use search method to search a key in a node
	}

	public int findMaximum() {
		if (isLeaf) {
			return key[numberOfKeys - 1];
		}
		return child[numberOfKeys].findMaximum();
	}

	public int findMinimum() {
		if (isLeaf) {
			return key[0];
		}
		return child[0].findMinimum();
	}

	public int findTotal() {
		int sum = 0;// declare counter
		for (int i = 0; i < numberOfKeys; i++) {
			sum = sum + key[i];// add keys one by one of the root
		}
		if (isLeaf) {// if the root has no children, then just return it
			return sum;
		}
		for (int i = 0; i <= numberOfKeys; i++) {// call children and repeat
													// method to add all numbers
			sum = sum + child[i].findTotal();
		}
		return sum;// return the total amount
	}

	public int numberOfLeaves() {
		if (isLeaf) {// if node leaf return 1.
			return 1;
		}
		int sum = 0;// if not equal count 0
		for (int i = 0; i <= numberOfKeys; i++) {
			sum = sum + child[i].numberOfLeaves();// check recursively all
													// leaves to be added
		}
		return sum;// return counter
	}

	public int fullNodes() {
		if (isLeaf) {
			if (numberOfKeys == key.length) {// if full and leaf return 1 to
												// count that node
				return 1;
			}
			return 0;// if not full and leaf return 0;
		}
		int count = 0;// if not leaf count = 0
		for (int i = 0; i <= numberOfKeys; i++) {
			count = count + child[i].fullNodes();// traverse children
													// recursively and
													// accumulate
		}
		if (numberOfKeys == key.length) {// if parent is full add 1 to count.
			return 1 + count;
		}
		return count;
	}

	public int minimumNodes() {
		int sum = 0;// declare counter
		if (numberOfKeys == key.length / 2) {// add one to counter if they match
												// the length
			sum++;
		}
		if (isLeaf) {
			return sum;// if no other nodes return
		}
		for (int i = 0; i <= numberOfKeys; i++) {
			sum = sum + child[i].minimumNodes();// accumulate min nodes of
												// children
		}
		return sum;
	}

	public int nodesWithN(int n) {
		int count = 0;// start counter
		if (numberOfKeys == n) {// check if the number of keys match input then
								// add one if true
			count++;
		}
		if (isLeaf) {// return if it is the last one
			return count;
		}
		for (int i = 0; i <= numberOfKeys; i++) {// traverse children
			count = count + child[i].nodesWithN(n);// recursively checks nodes
													// with n keys.
		}
		return count;
	}

	public int nodesAtDepth (int d) {

		if (d <= 0) {
			return 1;// it is not possible that there is some negative depth
		}

		int count = 0;

		if (!isLeaf) {// check if children are at desired depth
			for (int i = 0; i <= numberOfKeys; i++) {// traverse children
				count = count + child[i].nodesAtDepth(d - 1);// accumulate
																// recursively
																// results
			}
		}
		return count;// return count.
	}
}
