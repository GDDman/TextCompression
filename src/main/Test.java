package main;

public class Test {
	
	public static void main(String[] args) {
		
		Compressor comp = new Compressor();
		comp.read("src/res/test.txt");
		
		comp.printProbabilities();
		comp.huffman();
		comp.printCodes();
		comp.writeToFile("src/res/output.txt", comp.getOriginal());
		
	}
}
