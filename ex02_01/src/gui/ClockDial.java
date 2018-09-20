package gui;

import java.time.LocalDateTime;

/** デジタル時計の文字盤担当 */
public class ClockDial
{
	private LocalDateTime dateTime;
	private int hours;
	private int minutes;
	private int seconds;
	private String dial;
	
	/*  */
	public ClockDial()
	{
		this.dateTime = LocalDateTime.now();
        this.hours = dateTime.getHour();
        this.minutes = dateTime.getMinute();
        this.seconds = dateTime.getSecond();
        this.dial = hours+":"+minutes+":"+seconds;
	}
	
	/** 現在の時刻を取得して文字列として返す
	 *  時刻はHH:MM:ssの形式で返す*/
	public String getClockTime()
	{
		this.dateTime = LocalDateTime.now();
        this.hours = dateTime.getHour();
        this.minutes = dateTime.getMinute();
        this.seconds = dateTime.getSecond();
        this.dial = hours+":"+minutes+":"+seconds;
        if(this.dial==null)
        {
        	return "";
        }
        return this.dial;
	}
}
