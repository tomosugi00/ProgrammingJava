package jpl.ch03.ex07;

class ColorAttr extends Attr
{
	private ScreenColor myColor;	// 変色された色
	
	public ColorAttr(String name,Object value)
	{
		super(name,value);
		decodeColor();
	}
	
	public ColorAttr(String name)
	{
		this(name, "transparent");
	}
	
	public ColorAttr(String name,ScreenColor value)
	{
		super(name,value.toString());
		myColor = value;
	}
	
	public Object setValue(Object newValue)
	{
		// スーパークラスのsetValueを最初に行う
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}
	
	/** 値を記述ではなくScreenColorに設定する */
	public ScreenColor setValue(ScreenColor newValue)
	{
		//スーパークラスのsetValueを最初に行う
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}
	
	/** 変換されたScreenObjectオブジェクトを返す */
	public ScreenColor getColor()
	{
		return myColor;
	}
	
	/** getValue()で得られる記述からScreenColorを設定する */
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
	
	/** equalsオーバーライド */
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ColorAttr))
		{
			return false;
		}
		ColorAttr cAttr = (ColorAttr)obj;
		
		return this.myColor == cAttr.myColor;
	}
	/** hashCodeオーバーライド */
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
