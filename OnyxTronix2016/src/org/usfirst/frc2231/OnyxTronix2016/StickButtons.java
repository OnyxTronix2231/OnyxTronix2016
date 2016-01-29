package org.usfirst.frc2231.OnyxTronix2016;

public class StickButtons {

	public enum Buttons
	
	{
		
		A(1),
		B(2),
		X(3),
		Y(3),
		LB(4),
		RB(5),
		BACK(6),
		START(6);
	    private final int value;
	    private Buttons(int value) {
	        this.value = value;
	    }
	    public int getValue() {
	        return value;
	    }
		
		
		
	}
}
