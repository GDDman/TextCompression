package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HuffmanTree {
	
	private HashMap<Character, ArrayList<Integer>> codes = new HashMap<Character, ArrayList<Integer>>();
	private HuffmanNode root;
	private List<HuffmanNode> leaves;

	public HuffmanTree() {
		root = null;
	}
	
	//returns the root node of the tree
	public void generateTree(ArrayList<HuffmanNode> tree) {
		
		leaves = new ArrayList<HuffmanNode>(tree);
		
		while (tree.size() > 1) {
		
			HuffmanNode smallest = null;
			HuffmanNode secondsmallest = null;
				
			for (HuffmanNode node: tree) {
				if (smallest == null) {
					smallest = node;
				}
				if (node.getValue() <= smallest.getValue()) {
					secondsmallest = smallest;
					smallest = node;	
				}
			}
			
			HuffmanNode parent = new HuffmanNode(smallest.getValue() + secondsmallest.getValue());
			smallest.setParent(parent);
			secondsmallest.setParent(parent);
			parent.setLeft(smallest);
			parent.setRight(secondsmallest);
			
			tree.remove(smallest);
			tree.remove(secondsmallest);
			tree.add(parent);
		}
		
		root = tree.get(0);

	}
	
	public HashMap<Character, ArrayList<Integer>> generateCodes() {

		HuffmanNode tempnode;
		HuffmanNode parent;
		ArrayList<Integer> code;
		
		for (HuffmanNode n: leaves) {
			code = new ArrayList<Integer>();
			tempnode = n;
			while((parent = tempnode.getParent()) != null) {
				if (tempnode == parent.getright()) {
					code.add(1);
				}
				else {
					code.add(0);
				}
				tempnode = parent;
			}
			Collections.reverse(code);
			codes.put(n.getCharacter(), code);
		}
		return codes;
	}
	
	public HuffmanNode getRoot() {
		return root;
	}
	
	public HashMap<Character, ArrayList<Integer>> getCodes() {
		return codes;
	}
	
}
