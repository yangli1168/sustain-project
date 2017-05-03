package net.xinqushi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

import net.xinqushi.vo.TimeSlotsVO;

public class Aggregation {
	
	public void makeTimeslot(){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		TreeMap<String, TimeSlotsVO> result = new TreeMap<String, TimeSlotsVO>();

		String todayStr = null, tomoStr = null, afterTomoStr = null;
		LocalDateTime ldt = LocalDateTime.now();
		todayStr = dateTimeFormatter.format(ldt);
		ldt = ldt.plusDays(1);
		tomoStr = dateTimeFormatter.format(ldt);
		ldt = ldt.plusDays(1);
		afterTomoStr = dateTimeFormatter.format(ldt);
		
		
	}
}
