/****************************************************

 * Kevin Jimenez 

 * The University of Texas at El Paso 

 * CS2302 Data Structures 

 * Lab 5

 * Professor Olac Fuentes
 * **************************************************/
//package kevinGarciaJimenez;
import java.util.*;

public class BTreeTest {

	public static void main(String[] args) {
		int[] S = { 2, 25, 5 }; 
		int nextInt;
		int mValue;
		Scanner input = new Scanner(System.in);
		Scanner s= new Scanner (System.in);
		Random generator = new Random();

		BTree T = new BTree(3);//declare a new tree T=3   Array Size 5

		for (int i = 0; i < S.length; i++) {//insert values of array S

			T.insert(S[i]);

			System.out.println();

		}
		T.print();//Print array
		
		while (true) {//display menu
			System.out.println("\nType the number of the operation that you would like to perform.\n"
					+ "1) Insert new element\n"
					+ "2) Search an element\n"
					+ "3) Search the lowest element in the tree.\n"
					+ "4) Search the highest element in the tree.\n"
					+ "5) Print the number of keys in the tree.\n"
					+ "6) Print the number of nodes in the tree.\n"
					+ "7) Print the addition of the keys of the tree.\n"
					+ "8) Print the keys in descending order.\n"
					+ "9) Print the number of leaves in a tree.\n"
					+ "10) Print the number of nodes that are full.\n"
					+ "11) Print the nodes in the tree that have minimum number of keys.\n"
					+ "12) Print the nodes that have certain number of keys.\n"
					+ "13) Print nodes in certain depth\n");
			int option=s.nextInt();
			if (option==1){
				System.out.println("Please type the desired Integer Value to insert ");
				mValue = input.nextInt();
				System.out.println("Searching  " + mValue);
				if (T.search(mValue)) // if found
				{
					System.out.println("Found Value Not Able to Insert "+ mValue);
				} 
				else {// if not found
					System.out.println("NOT Found Inserting " + mValue);
					try{T.insert(mValue);
					T.print();}
					catch (InputMismatchException e) {
						System.out.println("Please Try Again a valid Number ");
						System.out.println("Program End");
						System.exit(1);
						// Catch block

					}
				}}
			if (option==2){
				System.out.println("What number are you searching?");
				int n=s.nextInt();
				T.isInTree(n);

			}
			if (option==3){
				System.out.println(T.findMinimum() + " is the minimum number.");
			}
			if (option==4){
				System.out.println(T.findMaximum() + " is the maximum number.");
			}
			if (option==5){
				System.out.println("Total number of Keys: "+T.totalNumberOfKeys);
			}
			if (option==6){
				System.out.println("Total number of Nodes: "+BTree.totalNumberOfNodes);
			}
			if (option==7){
				System.out.println(T.findTotal()+ " is the total sum of the keys.");
			}
			if (option==8){
				System.out.print("Descending:  ");
				T.printDescending();
			}
			if (option==9){
				System.out.println(T.numberOfLeaves()+ " is the number of leaves in the tree.");
			}
			if (option==10){
				System.out.println(T.fullNodes()+ " is the number of nodes that are full in the tree.");
			}
			if (option==11){
				System.out.println(T.minimumNodes()+ " is the number of nodes that have minimum keys in a tree.");
			}
			if (option==12){
				System.out.println("How many keys you want on your nodes to be analized?");
				int n=s.nextInt();
				T.nodesWithN(n);
				System.out.println(T.nodesAtDepth(n)+ " is the number of nodes with those keys.");
			}
			if (option==13){
				System.out.println("How much do you want depth to be?");
				int n=s.nextInt();
				System.out.println(T.nodesAtDepth(n)+ " is the number of nodes at that specific level.");
			}
		}
	}

}

