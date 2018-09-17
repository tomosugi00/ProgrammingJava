package jpl.ch04.ex04;

public interface originalCollection
{
	/** 要素objをコレクションに追加する
	 *  要素を追加できたらtrue,追加できなかったらfalseを返す */
	boolean add(Object obj);
	
	/** キーstrに対応する要素をコレクションから削除する
	 *  要素を削除できたらtrue,削除できなかったらfalseを返す*/
	boolean remove(String str);
	
	/** キーstrに対応する要素が存在すればそれを返す
	 *  存在しなければnullを返す */
	Object find(String str);
	
}
