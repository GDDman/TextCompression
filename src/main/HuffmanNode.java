package main;

public class HuffmanNode {

	private int value;
	private Character character;
	private HuffmanNode parent;
	private HuffmanNode leftchild;
	private HuffmanNode rightchild;
	private boolean isleaf;
	
	public HuffmanNode(int val, char c) {
		value = val;
		character = c;
		isleaf = true;
	}
	
	public HuffmanNode(int val) {
		value = val;
		character = null;
		isleaf = false;
	}
	
	public void setParent(HuffmanNode n) {
		parent = n;
	}
	
	public void setLeft(HuffmanNode n) {
		leftchild = n;
	}
	
	public void setRight(HuffmanNode n) {
		rightchild = n;
	}
	
	public HuffmanNode getParent() {
		return parent;
	}
	
	public HuffmanNode getleft() {
		return leftchild;
	}
	
	public HuffmanNode getright() {
		return rightchild;
	}
	
	public int getValue() {
		return value;
	}
	
	public void incvalue() {
		value++;
	}
	
	public char getCharacter() {
		return character;
	}
	
	public boolean isLeaf() {
		return isleaf;
	}
	
}
