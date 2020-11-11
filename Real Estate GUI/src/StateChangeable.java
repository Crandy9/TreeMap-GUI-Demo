/*
 * File: StateChangeable.java
 * Purpose: manage real estate database using GUI
 */
public interface StateChangeable <T extends Enum<T>> {
	
	public Enum<T> changeState (T t);
}
