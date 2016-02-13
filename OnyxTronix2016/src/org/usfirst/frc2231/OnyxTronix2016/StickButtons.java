package org.usfirst.frc2231.OnyxTronix2016;

public class StickButtons {

	public enum Buttons
	
	{
		
		A(1),
		B(2),
		X(3),
		Y(4),
		LB(5),
		RB(6),
		BACK(7),
		START(8);
	    private final int value;
	    private Buttons(int value) {
	        this.value = value;
	    }
	    public int getValue() {
	        return value;
	    }
		
		
		
	}
}
