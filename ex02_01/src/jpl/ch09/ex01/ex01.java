package jpl.ch09.ex01;

public class ex01 {

	public static void main(String[] args)
	{
		System.out.println("(正+正)："+(Double.POSITIVE_INFINITY+Double.POSITIVE_INFINITY));
		System.out.println("(正+負)："+(Double.POSITIVE_INFINITY+Double.NEGATIVE_INFINITY));
		System.out.println("(負+負)："+(Double.NEGATIVE_INFINITY+Double.NEGATIVE_INFINITY));
		System.out.println("---");
		System.out.println("(正-正)："+(Double.POSITIVE_INFINITY-Double.POSITIVE_INFINITY));
		System.out.println("(正-負)："+(Double.POSITIVE_INFINITY-Double.NEGATIVE_INFINITY));
		System.out.println("(負-負)："+(Double.NEGATIVE_INFINITY-Double.NEGATIVE_INFINITY));
		System.out.println("---");
		System.out.println("(正*正)："+(Double.POSITIVE_INFINITY*Double.POSITIVE_INFINITY));
		System.out.println("(正*負)："+(Double.POSITIVE_INFINITY*Double.NEGATIVE_INFINITY));
		System.out.println("(負*負)："+(Double.NEGATIVE_INFINITY*Double.NEGATIVE_INFINITY));
		System.out.println("---");
		System.out.println("(正/正)："+(Double.POSITIVE_INFINITY/Double.POSITIVE_INFINITY));
		System.out.println("(正/負)："+(Double.POSITIVE_INFINITY/Double.NEGATIVE_INFINITY));
		System.out.println("(負/正)："+(Double.NEGATIVE_INFINITY/Double.POSITIVE_INFINITY));
		System.out.println("(負/負)："+(Double.NEGATIVE_INFINITY/Double.NEGATIVE_INFINITY));
	}
}
