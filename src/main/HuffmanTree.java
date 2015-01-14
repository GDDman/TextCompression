package main;

import java.util.ArrayList;
import java.util.HashMap;

public class HuffmanTree {
	
	private HashMap<Character, Integer> codes;
	private ArrayList<HuffmanNode> tree = new ArrayList<HuffmanNode>();

	public HuffmanTree(ArrayList<HuffmanNode> probabilities) {
		tree = probabilities;
	}
	
	//returns the root node of the tree
	public HuffmanNode generateTree() {
		
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
		
		return tree.get(0);

	}
	
	public HashMap generateCodes() {
		return null;
	}
	
}
