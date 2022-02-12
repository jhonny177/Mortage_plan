package mortageplan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Mortage {
public Map<String, ArrayList<Double>> readFile() {
		
		String tmpKey;
		String[] keyAndValue;
		
		Map<String,ArrayList<Double>> Map = new HashMap<String, ArrayList<Double>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("prospects.txt"));
			String line = null;
			for(int i = 0; i<2;i++) {
			line = br.readLine();
			}
			while(!line.isBlank()) {
				ArrayList<Double> values = new ArrayList<Double>();
				line = line.replace("\"", "");
				keyAndValue = line.split(",");
				tmpKey = keyAndValue[0];
				for(int i = 1;i<keyAndValue.length;i++) {
					if(isNumeric(keyAndValue[i])) {
						values.add(Double.parseDouble(keyAndValue[i]));	
					}
					else {
						tmpKey += " " + keyAndValue[i];
					}
				}
				Map.put(tmpKey, values);
		        line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return Map;
	}
	
	public void showMortage() {
		Map<String,ArrayList<Double>> Map = new HashMap<String, ArrayList<Double>>();
		Map = readFile(); 
		for (Entry<String, ArrayList<Double>> entry : Map.entrySet()){
			String name = entry.getKey();
			Double answ = calculate(entry.getValue());
			System.out.println(name+" "+answ);
		}
	}
	
	public double calculate(ArrayList<Double> list) {
		double fixedPayment;
		double totalLoan = list.get(0);
		double intrestMonthly = (list.get(1)/12)/100;
		double numPayments = list.get(2)*12;
		
		double numerator = intrestMonthly*(powerOf(1+intrestMonthly,numPayments));
		//System.out.println(base1);
		double denominator = (powerOf(1+intrestMonthly,numPayments))-1;
		//System.out.println(base2);
		fixedPayment = totalLoan*(numerator/denominator);
		return fixedPayment;
	}
	
	public double powerOf(double base,double exponent) {
		    double result = 1;

		    while (exponent != 0) {
		      result *= base;
		      --exponent;
		    }
		
		return result;
	}
	
	public boolean isNumeric(String string) {
	    double doubleValue;
			
	    if(string == null || string.equals("")) {
	        return false;
	    }
	    
	    try {
	        doubleValue = Double.parseDouble(string);
	        return true;
	    } catch (NumberFormatException e) {

	    }
	    return false;
	}
	

	public static void main(String[] args) {
		Mortage m = new Mortage();
		m.showMortage();
	}

}