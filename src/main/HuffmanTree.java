package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanTree {
	
	private HashMap<Character, String> codes = new HashMap<Character, String>();
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
				else if (node.getValue() <= smallest.getValue()) {
					smallest = node;	
				}
			}
			
			for (HuffmanNode node: tree) {
				if (node != smallest) {
					if (secondsmallest == null) {
						secondsmallest = node;
					}
					else if (node.getValue() <= secondsmallest.getValue()) {
						secondsmallest = node;	
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
	
	public HashMap<Character, String> generateCodes() {

		HuffmanNode tempnode;
		HuffmanNode parent;
		String code;
		
		for (HuffmanNode n: leaves) {
			code = "";
			tempnode = n;
			while((parent = tempnode.getParent()) != null) {
				if (tempnode == parent.getright()) {
					code += "1";
				}
				if (tempnode == parent.getleft()){
					code += "0";
				}
				tempnode = parent;
			}
			code = new StringBuilder(code).reverse().toString();
			codes.put(n.getCharacter(), code);
		}
		return codes;
	}
	
	//GETTERS 
	
	public HuffmanNode getRoot() {
		return root;
	}
	
	public HashMap<Character, String> getCodes() {
		return codes;
	}
	
}
