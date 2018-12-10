package jpl.ch14.ex02;

public class SingleLinkQueue<E>
{
	protected Cell<E> head;
	protected Cell<E> tail;
	public int size = 0;		// めっちゃ危険
	
	public void add(E item)
	{
		Cell<E> cell = new Cell<E>(item);
		if(tail==null)
		{
			head = tail = cell;
		}
		else
		{
			tail.setNext(cell);
			tail=cell;
		}
		size++;
	}
	
	public E remove()
	{
		if(head==null)
		{
			return null;
		}
		Cell<E> cell = head;
		head = head.getNext();
		if(head==null)
		{
			tail=null;	//空のキュー
		}
		size--;
		return cell.getElement();
	}
}
