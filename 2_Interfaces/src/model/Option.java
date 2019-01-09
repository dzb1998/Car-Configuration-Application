package model;

import java.io.Serializable;

/**
 * Used to be Inner class Option in Lab 1, no longer
 * @author dengzhubo
 */
public class Option implements Serializable {
	
	private String opName;
	private float opPrice;
	
	// 4 constructors
	protected Option() {
		
	}
	
	protected Option(String n) {
		opName = n;
	}
	
	protected Option(float p) {
		opPrice = p;
	}

	protected Option(String n, float p) {
		opName = n;
		opPrice = p;
	}
	
	// 2 getters; get name and get price
	protected String getOpName() {
		return opName;
	}
	
	protected float getOpPrice() {
		return opPrice;
	}
	
	// 2 setters; set name and set price
	protected void setOpName(String n) {
		opName = n;
	}
	
	protected void setOpPrice(float p) {
		opPrice = p;
	}
	
	/**
	 * Override toString; used for printing
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t" + "Option: ");      // indent twice auto->opset->op
		sb.append(opName + "; ");
		sb.append("Price: ");
		sb.append("$").append(opPrice + "\n");
		return sb.toString();			
	}
}
