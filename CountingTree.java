/* File: SqaureRoot.java
 * Author: Nathan Levy (nathanml@bu.edu)
 * Date:  December 2, 2017
 * Purpose: This is the solution for problem 1.
 */

package hw7;

public class CountingTree {

	private class TreeNode {
		String key;
		int count; // how many times key appears in the tree
		TreeNode left, right;
		TreeNode(String key) {
			this.key = key; left = right = null; count = 1;
		}
	}

	private TreeNode root = null;
	private int totalEntries = 0; // total of all counts
	private int distinctEntries = 0; // number of nodes

	/**
	 * @return number of total entries (i.e., total of all counts)
	 */
	public int getTotalEntries() {return totalEntries;}

	/**
	 * @return number of distinct entries (i.e., number of nodes)
	 */
	public int getDistinctEntries() {return distinctEntries;}

	/**
	 * Inserts key into the tree.  If key is already in,
	 * just increments the corresponding counter.  If not,
	 * creates a new node.
	 * @param key the value to be inserted
	 */
	public void insert (String key) {
		totalEntries ++;
		if(root != null)
		{
			TreeNode current_node = root;
			TreeNode par = current_node;
			int comparison = 0;
			while(current_node != null)
			{
				par = current_node;
				comparison = key.compareTo(current_node.key);
				if (comparison== 0)
				{
					current_node.count++;
					return;
				}
				else if(comparison < 0)
				{
					current_node = current_node.left;
				}
				else if(comparison > 0)
				{
					current_node = current_node.right;
				}
			}
			distinctEntries++;
			if(comparison<0)
			{
				par.left = new TreeNode(key);
			}
			else
			{
				par.right = new TreeNode(key);
			}
				
		}
		else
		{
			root = new TreeNode(key);
			distinctEntries ++;
		} 
	
	}
	/**
	 * @param key
	 * @return number of times key is in the tree, i.e., the count of key (0 if key not in the tree)
	 */
	public int search (String key) {
		return _search(key, this.root);
	}
	
	private int _search(String key, TreeNode root)
	{
		if(root == null)
		{
			return 0;
		}
		int comparison = key.compareTo(root.key);
		if(comparison < 0)
		{
			return _search(key, root.left);
		}
		if(comparison > 0)
		{
			return _search(key, root.right);
		}
		else {
			return root.count;
		}
	}





	/**
	 * Creates and returns a new tree, which allows lookup
	 * of entries in this tree by their counts.  Thus, in the new
	 * tree keys are integers, and data is String.  The new tree has as
	 * many nodes as the current tree.  The new tree must allow
	 * insertion of nodes with duplicate keys, since counts may repeat.
	 * @return the new tree
	 */
	public BSTWithDuplicates frequencyTree () {
		BSTWithDuplicates t = new BSTWithDuplicates ();
		frequencyTree(root, t);
		return t;
	}

	/**
	 * Private recursive helper for frequencyTree 
	 * @param root
	 * @param t
	 */
	private void frequencyTree (TreeNode root, BSTWithDuplicates t) {
		if (root != null) {
			// just do a traversal -- any order is fine
			t.insert(root.count, root.key);
			frequencyTree(root.left, t);
			frequencyTree(root.right, t);
		}
	}

}
