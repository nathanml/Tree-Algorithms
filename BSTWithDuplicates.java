/* File: SqaureRoot.java
 * Author: Nathan Levy (nathanml@bu.edu)
 * Date:  December 2, 2017
 * Purpose: This is the solution for problem 1.
 */

package hw7;

import java.util.LinkedList;
import java.util.Random;

public class BSTWithDuplicates {
	private class TreeNode {
		int key;
		String data;
		TreeNode left, right;
		TreeNode(int k, String d) {
			key = k; data = d; left = right = null; 
		}
	}

	private TreeNode root = null;
	Random random = new Random();


	public void insert (int key, String data) {
		root = insert (key, data, root);
	}


private TreeNode insert (int key, String data, TreeNode subtree) {	
		if(subtree == null)
		{
			return new TreeNode(key, data);
		}
		boolean direction = false;
		if(key == subtree.key)
			direction = random.nextBoolean();
		if(key < subtree.key || (key == subtree.key) && (direction == false)) 	//direction is false go to the left
			subtree.left = insert(key, data, subtree.left);
		else {
				subtree.right = insert(key, data, subtree.right);
			}
		return subtree;
	}


	public LinkedList<String> rangeSearch (int low, int high) {
		LinkedList<String> ret = new LinkedList<String>();
		rangeSearch (root, low, high, ret);
		return ret;
	}

	private void rangeSearch (TreeNode subtree, int low, int high, LinkedList<String> ret) {
			if(subtree == null)
				return;
			if(subtree.key >= low)
			{
				rangeSearch(subtree.left, low, high, ret);
			}
			if(subtree.key <= high)
			{
				rangeSearch(subtree.right, low, high, ret);
			}
			if(subtree.key >= low && subtree.key <= high)
			{
				ret.add(subtree.data);
			}
	}

	public int maxKey() {
		return _maxKey(this.root);
	}
	
	private int _maxKey(TreeNode root)
	{
		if(root == null)
		{
			return 0;
		}
		if(root.right != null)
		{
			return _maxKey(root.right);
		}
		else {
			return root.key;
		}
	}

}
