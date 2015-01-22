package main;

public class Test {
	
	public static void main(String[] args) {
		
		Compressor comp = new Compressor();
		comp.read("src/res/test.txt");
		
		System.out.println("PROBABILITIES :");
		comp.printProbabilities();
		comp.huffman("src/res/encoded.txt");
		System.out.println("CODES :");
		comp.printCodes();
		comp.writeToFile("src/res/output.txt", comp.getOriginal());
		System.out.println("TEXT : \n" + comp.textToString());
				
	}
}
