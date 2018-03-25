/* File: SqaureRoot.java
 * Author: Nathan Levy (nathanml@bu.edu)
 * Date:  December 2, 2017
 * Purpose: This is the solution for problem 2.
 */

package hw7;
import java.util.LinkedList;
 
public class DirectoryTree {

	public class Node<String>{
		String data;
		LinkedList<Node<String>> children;
		Node<String> parent;
		public Node(String value)
		{
			data = value;
			children = null;
			parent = null;
		}
	}
	
	Node<String> root;
	Node<String> currentDir;

	
	public boolean mkdir (String name) {
		if(root == null)
		{
			Node<String> newDirectory = new Node<>("");
			root = newDirectory;
			root.children = new LinkedList<>();
			Node<String> newChild = new Node<>(name);
			newChild.parent = root;
			root.children.add(newChild);
			currentDir = root;
			return true;
		}
		if(currentDir.children == null)
		{
			Node<String> newDirectory = new Node<>(name);
			currentDir.children = new LinkedList<>();
			currentDir.children.add(newDirectory);
			return true;
		}
		for(Node<String> child: currentDir.children)
			{
				if(child.data.equals(name))
					return false;
			}
		Node<String> newDirectory = new Node<>(name);
		currentDir.children.add(newDirectory);
		return true;
		}
	
	public boolean cd (String name) {
		if(currentDir == null || currentDir.children == null)
			return false;
		for(Node<String> child: currentDir.children)
		{
			if(child.data.equals(name))
			{
				Node<String> temp = currentDir;
				currentDir = child;
				child.parent = temp;
				return true;
			}
		}
		return false;
	}
	
	public boolean cdUp() {
		if(currentDir == null)
			return false;
		else if(currentDir.parent == null)
			return false;
		else {
			currentDir = currentDir.parent;
			return true;
		}
	}
	
	public boolean rmdir (String name) {
		if(currentDir == null)
			return false;
		for(Node<String> child: currentDir.children)
		{
			if(child.data.equals(name))
				currentDir.children.remove(child);
				return true;
		}
		return false;
		}
	

	public String ls () {
		String ret =  "";
		if(currentDir == null || currentDir.children == null)
			return ret;
		for(Node<String> child: currentDir.children)
			ret += child.data + "\n";
		return ret;
	}
	
	public String printSubTree() {
		return _printSubTree(currentDir, 0);
	}
	
	private String _printSubTree(Node<String> n, int depth)
	{
		String ret = "";
		int i = 0;
		while(i < depth)
		{
			ret += " ";
			i++;
		}
		if(n == null)
		{
			return ret;
		}
		if(n.children == null)
		{
			ret += n.data;
			return ret;
		}
		ret = ret + n.data + "\n";
		for(Node<String>child:n.children)
		{
			ret = ret + _printSubTree(child, depth + 1) + "\n";
		}
		return ret;
	}
	
	public String pwd() {
		Node<String> temp = currentDir;
		if(temp == null)
		{
			return "/";
		}
		else {
		String ret = "/" + temp.data;
		while(temp.parent != null)
			{
				temp = temp.parent;
				ret = temp.data + ret;
				if(temp.parent != null)
				{
					ret = "/" + ret;
				}
			}
		return ret;
		}
	}
	
	public int numNodes() {
		return _numNodes(currentDir);
	}
	
	private int _numNodes(Node<String> n)
	{
		int ret = 1;
		if(n == null)
		{
			return ret;
		}
		if(n.children == null)
		{
			return ret;
		}
		for(Node<String> child: n.children)	
		{
			ret += _numNodes(child);
		}
		return ret;
	}
}
