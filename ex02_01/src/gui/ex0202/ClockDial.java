package gui.ex0202;

import java.time.LocalDateTime;

public class ClockDial
{
	/** 現在の時刻を取得して文字列として返す
	 *  時刻はHH:MM:ssの形式で返す*/
	public static String getClockTime()
	{
		LocalDateTime dateTime = LocalDateTime.now();
		int hours = dateTime.getHour();
		int minutes = dateTime.getMinute();
		int seconds = dateTime.getSecond();
		String dial = hours+":"+minutes+":"+seconds;
		return dial;
	}
}
