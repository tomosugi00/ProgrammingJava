package jpl.ch14.ex02;

public class Cell<E>
{
	private Cell<E> next;
	private E element;
	public Cell(E element)
	{
		this.element = element;
	}
	public Cell(E element, Cell<E> next)
	{
		this.element = element;
		this.next = next;
	}
	public E getElement()
	{
		return this.element;
	}
	public void setElement(E element)
	{
		this.element = element;
	}
	public Cell<E> getNext()
	{
		return this.next;
	}
	public void setNext(Cell<E> next)
	{
		this.next = next;
	}
}
