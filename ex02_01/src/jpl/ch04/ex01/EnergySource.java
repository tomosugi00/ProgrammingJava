package jpl.ch04.ex01;

public interface EnergySource {
	
	/** 燃料の値を見る */
	int getEnergy();
	
	/** 燃料をenergyだけ加算する
	 * energyは0以上であること
	 * 0未満だった場合、加算されない */
	void setEnergy(int Energy);
	
	/** 燃料の値が0であることをbooleanで返す 
	 * 0である場合はtrue,1以上である場合はfalse */
	boolean Empty();

}
