package jpl.ch06.ex05;

import java.awt.Color;

public enum SignalColor
{
	RED(Color.RED),
	YELLOW(Color.YELLOW),
	BLUE(Color.BLUE);
	
	Color color;
	SignalColor(Color c){this.color = c;};
	public Color getColor() {return this.color;};
}
