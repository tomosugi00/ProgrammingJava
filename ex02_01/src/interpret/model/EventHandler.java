package interpret.model;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class EventHandler
{
	/* Consumerは引数を消費するインスタンスを定義するためのインターフェイス。
	 * 引数は取るが「返り値は存在しない」ので、型指定は引数側の1つだけ。  */
	
	/** Consumerのリスト */
	private Set<Consumer<EventArgs>> listeners = new HashSet<Consumer<EventArgs>>();

	/** 処理追加 */
    public void add(Consumer<EventArgs> listener)
    {
        listeners.add(listener);
    }
    
    /** 処理削除 */
    public void remove(Consumer<EventArgs> listener)
    {
        listeners.remove(listener);
    }

    /** 全てのConsumerのaccept発火  */
    public void broadcast(EventArgs args)
    {
        listeners.forEach(x -> x.accept(args));
    }
}
