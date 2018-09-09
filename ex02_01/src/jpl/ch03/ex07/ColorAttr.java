package jpl.ch03.ex07;

class ColorAttr extends Attr
{
	private ScreenColor myColor;	// �ϐF���ꂽ�F
	
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
		// �X�[�p�[�N���X��setValue���ŏ��ɍs��
		Object retval = super.setValue(newValue);
		decodeColor();
		return retval;
	}
	
	/** �l���L�q�ł͂Ȃ�ScreenColor�ɐݒ肷�� */
	public ScreenColor setValue(ScreenColor newValue)
	{
		//�X�[�p�[�N���X��setValue���ŏ��ɍs��
		super.setValue(newValue.toString());
		ScreenColor oldValue = myColor;
		myColor = newValue;
		return oldValue;
	}
	
	/** �ϊ����ꂽScreenObject�I�u�W�F�N�g��Ԃ� */
	public ScreenColor getColor()
	{
		return myColor;
	}
	
	/** getValue()�œ�����L�q����ScreenColor��ݒ肷�� */
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
	
	/** equals�I�[�o�[���C�h */
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ColorAttr))
		{
			return false;
		}
		ColorAttr cAttr = (ColorAttr)obj;
		
		return this.myColor == cAttr.myColor;
	}
	/** hashCode�I�[�o�[���C�h */
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
