package jpl.ch07.ex02;

public class ex02 {

	//整数
	private byte b;		// -128～127
	private short s;		// -32768～32767
	private int i;			// -2147483648～2147483647
	private long L;		// -9223372036854775808～9223372036854775807
	//浮動小数点
	private float f;		// ±3.40282347E+38 ～ ±1.40239846E-45
	private double d;	// ±1.79769313486231570E+308 ～ ±4.94065645841246544E-324
	
	public void calcurate()
	{
		b = 127;
		//b = 128;	//intとして捉えられる
		//b = 1.1;	//doubleとして捉えられる
		
		s = 128;
		s = 32767;
		//s = 32768;	//int
		
		i = 2147483647;
		//i = 2147483648;	//範囲外と言われる。longとは考えない。
		//i = 1.1;	//キャストなどは行われない
		
		L = 2147483647;
		
		f = 10;
		f = 10.0f; //大丈夫
		//f = 10.0;	//double型として捉えられる
		
		d = 10.0;
		
	}

}
