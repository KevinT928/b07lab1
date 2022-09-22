public class Polynomial {
	double [] coeffs;
	public Polynomial(){
		coeffs = new double[1];
		coeffs[0]=0;
	}
	public Polynomial(double [] input){
		coeffs = input;
	}
	public Polynomial add(Polynomial input){
		double [] result = new double [Math.max(input.coeffs.length, coeffs.length)];
		for (int i=0; i<coeffs.length; i++){
			result[i]+=coeffs[i];
		}
		for (int i=0; i<input.coeffs.length; i++){
			result[i]+=input.coeffs[i];
		}
		Polynomial added = new Polynomial(result);
		return added;
	}
	public double evaluate(double x) {
		double result = 0;
		for (int i=0; i<coeffs.length; i++){
			result += ((coeffs[i])*Math.pow(x,i));
		}
		return result;
	}
	public boolean hasRoot(double x) {
		return evaluate(x)==0;
	}
}
