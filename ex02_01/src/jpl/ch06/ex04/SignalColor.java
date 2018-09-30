package jpl.ch06.ex04;

import java.awt.Color;

/* 定数固有のメソッドを作る必要はない
 * getColorに関しては単純に自身のColorを返すだけなので定数固有にすると
 * 無駄な記述が増えて可読性が下がる */

public enum SignalColor
{
	RED(Color.RED)
	{
		Color getColor() {
			return this.color;
		}
	},
	YELLOW(Color.YELLOW)
	{
		Color getColor() {
			return this.color;
		}
	},
	BLUE(Color.BLUE)
	{
		Color getColor() {
			return this.color;
		}
	};
	
	Color color;
	SignalColor(Color c){this.color = c;};
	abstract Color getColor();
}
