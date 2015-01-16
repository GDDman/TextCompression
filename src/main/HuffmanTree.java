package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HuffmanTree {
	
	private HashMap<Character, Integer> codes;
	private HuffmanNode root;
	private List<HuffmanNode> leaves;

	public HuffmanTree() {
		root = null;
		codes = null;
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
				else {
					if (node.getValue() <= smallest.getValue()) {
						secondsmallest = smallest;
						smallest = node;	
					}
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
	
	public HashMap generateCodes() {
		return null;
	}
	
	public HuffmanNode getRoot() {
		return root;
	}
	
}
