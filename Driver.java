import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Driver {
	public static void main(String [] args) throws Exception { 
		Polynomial p = new Polynomial(); 
 		System.out.println(p.evaluate(3)); 
  		double [] c11 = {6,5}; 
  		int [] c12 = {0,3};
  		Polynomial p1 = new Polynomial(c11, c12); 
  		double [] c21 = {-2,-9}; 
  		int [] c22 = {1,4};
  		Polynomial p2 = new Polynomial(c21, c22); 
  		Polynomial s = p1.add(p2); 
  		for (int i=0; i<s.coeffs.length; i++) {
  			System.out.println("co: "+ s.coeffs[i] + " exp: " + s.exp[i]);
  		}
  		System.out.println("s(0.1) = " + s.evaluate(0.1)); 
  		if(s.hasRoot(1)) 
   			System.out.println("1 is a root of s"); 
  		else 
   			System.out.println("1 is not a root of s");
  		double [] l11 = {7,2,4,7};
  		int [] l12 = {0,1,3,4};
  		double [] l21 = {2,3,9,1};
  		int [] l22 = {0,1,2,4};
  		Polynomial m1 = new Polynomial(l11,l12);
  		Polynomial m2 = new Polynomial(l21,l22);
  		Polynomial m = m1.multiply(m2);
  		for (int i=0; i<m.coeffs.length; i++) {
  			System.out.println("co: "+ m.coeffs[i] + " exp: " + m.exp[i]);
  		}
  		System.out.println("--------------------------------");
  		BufferedWriter writer = new BufferedWriter(new FileWriter("outp.txt"));
  		writer.write("3x2+5x+7-5x4-x7");
  		writer.close();
  		File fl = new File("outp.txt");
  		Polynomial f = new Polynomial(fl);
  		for (int i=0; i<f.coeffs.length; i++) {
  			System.out.println("co: "+ f.coeffs[i] + " exp: " + f.exp[i]);
  		}
  		f.saveToFile("w.txt");
	} 
}
