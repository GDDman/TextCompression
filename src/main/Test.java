package main;

public class Test {
	
	public static void main(String[] args) {
		
		Compressor comp = new Compressor();
		comp.read("src/res/test.txt");
		
		/*
		for (char c: comp.getOriginal()) {
			System.out.print(c);
		}
		*/
		
		for (HuffmanNode n: comp.getProbabilities()) {
			System.out.println(n.getCharacter() + ": " + n.getValue() + ", ");
		}
		
		comp.writeToFile("src/res/output.txt", comp.getOriginal());
		
	}
}
