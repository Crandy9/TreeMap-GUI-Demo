/*
 * File: StateChangeable.java
 * Date: October 14, 2020
 * Professor: Teresa Fernandez
 * CMIS 242
 * Purpose: manage real estate database using GUI
 */
public interface StateChangeable <T extends Enum<T>> {
	
	public Enum<T> changeState (T t);
}
