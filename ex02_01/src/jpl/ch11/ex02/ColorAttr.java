package jpl.ch11.ex02;

class ColorAttr<E> extends Attr<E>
{
	private ScreenColor myColor;
	
	public ColorAttr(String name,E value)
	{
		super(name,value);
		decodeColor();
	}
	
	public E setValue(E newValue)
	{
		E retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}
	
	public ScreenColor getColor()
	{
		return myColor;
	}
	
	protected void decodeColor()
	{
		if(getValue()==null)
		{
			myColor = null;
		}
		else
		{
			myColor = new ScreenColor(getValue());
		}
	}
	
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ColorAttr))
		{
			return false;
		}
		ColorAttr<E> cAttr = (ColorAttr<E>)obj;
		
		return this.myColor == cAttr.myColor;
	}
	
	public int hashCode()
	{
		return myColor.hashCode();
	}
}

class ScreenColor
{
	ScreenColor(Object obj)
	{
		
	}
}
