import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Polynomial {
	double[] coeffs;
	int[] exp;

	public Polynomial() {
		coeffs = new double[1];
		exp = new int[1];
		coeffs[0] = 0;
		exp[0] = 0;
	}
	public Polynomial(double[] co, int[] ex) {
		coeffs = co;
		exp = ex;
	}

	public Polynomial (double[] input) {
		int leng = 0;
		for (int i=0; i<input.length;i++) {
			if (input[i]!=0)
				leng++;
		}
		coeffs = new double[leng];
		exp = new int [leng];
		int co_index = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] != 0) {
				coeffs[co_index] = input[i];
				exp[co_index] = i;
				co_index++;
			}
		}
	}
	public Polynomial(File input) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(input));
		String line = br.readLine();
		String[] sl = line.split("\\+|(?=-)");
		int maxim = 0;
		for (int i = 0; i<sl.length;i++) {
			String sll[] = sl[i].split("(?<=x)|(?=x)");
			if (sll.length>1) {
				if ((sll[0].equals("x"))|(sll.length==3)){
					if (Integer.parseInt(sll[sll.length-1]) >= maxim) {
						maxim=Integer.parseInt(sll[sll.length-1]);
					}
				}
			}
		}
		double[] temp = new double[maxim+1];
		for (int i = 0; i<sl.length;i++) {
			String sll[] = sl[i].split("(?<=x)|(?=x)");
			if (sll.length==3) {
				if (sll[0].equals("-"))
					temp[Integer.parseInt(sll[2])]=-1;
				else
					temp[Integer.parseInt(sll[2])]=Double.parseDouble(sll[0]);
			}
			if (sll.length==2){
				if (sll[0].equals("x")) {
					temp[Integer.parseInt(sll[1])]=1;
				}
				if (sll[1].equals("x")) {
					if (sll[0].equals("-"))
						temp[1]=-1;
					temp[1]=Double.parseDouble(sll[0]);
				}
			}
			if (sll.length==1){
				if (sll[0].equals("-"))
					temp[0]=-1;
				temp[0]=Double.parseDouble(sll[0]);
			}
		}
		int leng = 0;
		for (int i=0; i<temp.length;i++) {
			if (temp[i]!=0)
				leng++;
		}
		coeffs = new double[leng];
		exp = new int [leng];
		int co_index = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != 0) {
				coeffs[co_index] = temp[i];
				exp[co_index] = i;
				co_index++;
			}
		}
	}
	public void saveToFile(String f_nam) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter(f_nam)); 
		String ret = "";
		for (int i=0; i<coeffs.length;i++) {
			if (i>0) {
				if (coeffs[i]>0)
					ret = ret + "+";
			}
			ret = ret + String.valueOf(coeffs[i]);
			if (exp[i]==1)
				ret = ret + "x";
			if (exp[i]>1)
				ret = ret + "x" + String.valueOf(exp[i]);
		}
		bw.write(ret);
		bw.close();
	}

	public Polynomial add(Polynomial input) {
		double[] temp = new double[Math.max(input.exp[input.exp.length - 1], exp[exp.length - 1]) + 1];
		for (int i = 0; i < coeffs.length; i++) {
			temp[exp[i]] += coeffs[i];
		}
		for (int i = 0; i < input.coeffs.length; i++) {
			temp[input.exp[i]] += input.coeffs[i];
		}
		Polynomial added = new Polynomial(temp);
		return added;
	}

	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coeffs.length; i++) {
			result += ((coeffs[i]) * Math.pow(x, exp[i]));
		}
		return result;
	}

	public Polynomial multiply(Polynomial input) {
		Polynomial temp;
		double[] tem = new double[input.exp[input.exp.length - 1] + exp[exp.length - 1]+1];
		for (int i = 0; i < exp.length; i++) {
			for (int j = 0; j < input.exp.length; j++) {
				tem[exp[i] + input.exp[j]] += coeffs[i] * input.coeffs[j];
			}
		}
		temp = new Polynomial(tem);
		return temp;
	}

	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
}
