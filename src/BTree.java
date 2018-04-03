 /****************************************************

 * Kevin Jimenez 

 * The University of Texas at El Paso 

 * CS2302 Data Structures 

 * Lab 5

 * Professor Olac Fuentes
 * **************************************************/
//package kevinGarciaJimenez;
public class BTree{
	private BTreeNode root;
	private int T; //2T is the maximum number of children a node can have
	private int height;
	int totalNumberOfKeys;
	public static int totalNumberOfNodes;

	public BTree(int t){//create tree
		root = new BTreeNode(t);
		T = t;
		height = 0;
		totalNumberOfKeys=0;
		totalNumberOfNodes=1;
	}

	public void printHeight(){
		System.out.println("Tree height is "+height);//get the value of the tree
	}

	public void insert(int newKey){
		if (root.isFull()){//check if Split root is necessary;
			split();
			height++;
		}
		root.insert(newKey);
		totalNumberOfKeys++;//increase number of keys by one because adding one more
		System.out.println("Total number of Keys: "+totalNumberOfKeys);
		System.out.println("Total number of Nodes: "+totalNumberOfNodes);
	}

	public void print(){
		// Wrapper for node print method
		root.print();
	}


	public boolean search(int mValueB){
		// Wrapper for node search method
		return root.search(mValueB);
	}

	public void printNodes(){
		// Wrapper for node printNodes method
		root.printNodes();
	}

	public void printDescending(){
		// Wrapper for node printDescending method
		root.printDescending();
	}

	public boolean isInTree(int k){
		// Wrapper for node isInTree method
		return root.isInTree(k);
	}

	public int findMaximum(){
		// Wrapper for node findMaximum method
		return root.findMaximum();
	}
	public int findMinimum(){
		// Wrapper for node findMinimum method
		return root.findMinimum();	
	}
	public int numberOfLeaves(){
		// Wrapper for node numberOfLeaves method
		return root.numberOfLeaves();	
	}
	public int fullNodes(){
		// Wrapper for fullNodes method
		return root.fullNodes();
	}
	public int minimumNodes(){
		// Wrapper for minNodes method
		return root.minimumNodes();
	}
	public int nodesWithN(int n){
		// Wrapper for NodeswithN method
		return root.nodesWithN(n);
	}
	public int findTotal(){
		// Wrapper for findTotal method
		return root.findTotal();
	}
	public int nodesAtDepth(int d){
		// Wrapper for nodesAtDepth method
		return root.nodesAtDepth(d);
	}
	public void split(){
		// Splits the root into three nodes.
		// The median element becomes the only element in the root
		// The left subtree contains the elements that are less than the median
		// The right subtree contains the elements that are larger than the median
		// The height of the tree is increased by one

		System.out.println("Before splitting root");
		BTreeNode leftChild = new BTreeNode(T);
		totalNumberOfNodes++;
		BTreeNode rightChild = new BTreeNode(T);
		totalNumberOfNodes++;
		leftChild.isLeaf = root.isLeaf;
		rightChild.isLeaf = root.isLeaf;
		leftChild.numberOfKeys = T-1;
		rightChild.numberOfKeys = T-1;
		int median = T-1;
		for (int i = 0;i<T-1;i++){
			leftChild.child[i] = root.child[i];
			leftChild.key[i] = root.key[i];
		}
		leftChild.child[median]= root.child[median];
		for (int i = median+1;i<root.numberOfKeys;i++){
			rightChild.child[i-median-1] = root.child[i];
			rightChild.key[i-median-1] = root.key[i];
		}
		rightChild.child[median]=root.child[root.numberOfKeys];
		root.key[0]=root.key[median];
		root.numberOfKeys = 1;
		root.child[0]=leftChild;
		root.child[1]=rightChild;
		root.isLeaf = false;

	}

}