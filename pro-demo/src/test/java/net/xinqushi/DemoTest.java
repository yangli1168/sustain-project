package net.xinqushi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import net.xinqushi.common.constants.CacheConstants;
import net.xinqushi.common.enums.CommonStatus;
import net.xinqushi.util.DateUtils;
import net.xinqushi.util.JedisPoolUtil;
import net.xinqushi.vo.LineSetting;
import net.xinqushi.vo.LineTimePriceSetting;
import net.xinqushi.vo.PriceRule;
import net.xinqushi.vo.TimeSlot;
import net.xinqushi.vo.TimeSlotCast;
import redis.clients.jedis.Jedis;

public class DemoTest {

	public static void main(String[] args) {
//		getAvailableTimeSlots(33, "SHARE", 4);
		
		Jedis jedis = JedisPoolUtil.getJedis();
		jedis.set("test", "测试");
		System.out.println(jedis.get("test"));
		
		
//		System.out.println(CommonStatus.toString(CommonStatus.VALID));
//		System.out.println(CommonStatus.VALID.toString());
//		System.out.println(CommonStatus.VALID.name());

	}

	/**
	 * 获取可用预约时间列表
	 * 
	 * @param lineId
	 *            线路id
	 * @param tripMode
	 *            乘车方式：包、拼车
	 * @param seats
	 *            座位数
	 */
	public static void getAvailableTimeSlots(int lineId, String tripMode, int seats) {

		// 从缓存获取线路相关设置
		String str = JedisPoolUtil.getJedis().hget(CacheConstants.LINE_SETTINGS_KEY, lineId + "");
		System.out.println("str = " + str);
		LineSetting lineSetting = JSON.parseObject(str, LineSetting.class);

		// 从线路设置中取出拼车时间设置
		Long startTime = System.currentTimeMillis();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(startTime);
		int hour = cal2.get(Calendar.HOUR_OF_DAY);
		int min = cal2.get(Calendar.MINUTE);
		int idx = hour * 2;
		if (min >= 30) {
			idx += 1;
		}
		
		LineTimePriceSetting setting = null;
		List<LineTimePriceSetting> timePriceSettings = null;
		if (tripMode.equals("SHARE")) {
			timePriceSettings = lineSetting.getShareTimeSettings();
		} else {
			timePriceSettings = lineSetting.getChartorTimeSettings();
		}
		for (LineTimePriceSetting timePriceSetting : timePriceSettings) {
			if (timePriceSetting.getBeginTime() <= idx && idx <= timePriceSetting.getEndTime()) {
				setting = timePriceSetting;
				break;
			}
		}
		System.out.println(setting);
		// 拼接
		TreeMap<Long, TimeSlot> slots = new TreeMap<Long, TimeSlot>();
		for (LineTimePriceSetting pricSetting : timePriceSettings) {
			// 获取最大座位数
			int maxSeat = 0;
			Map<Integer, List<PriceRule>> carModelPriceRules = pricSetting.getCarModelPriceRules();
			Set<Integer> keySet = carModelPriceRules.keySet();
			for (Integer integer : keySet) {
				if (maxSeat < integer) {
					maxSeat = integer;
				}
			}

			int begin = pricSetting.getBeginTime();
			int end = pricSetting.getEndTime();
			int interval = pricSetting.getIntervalMin();
			int maxReservDay = pricSetting.getMaxReservDay();

			int beginHour = begin / 2;
			int beginMin = begin % 2 == 0 ? 0 : 30;

			int endHour = end / 2;
			int endMin = end % 2 == 0 ? 0 : 30;

			long step = interval * 60 * 1000;
			int count = 0;
			while (count < maxReservDay) {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, count);
				cal.set(Calendar.HOUR_OF_DAY, beginHour);
				cal.set(Calendar.MINUTE, beginMin);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);

				Calendar calEnd = Calendar.getInstance();
				calEnd.add(Calendar.DATE, count);
				calEnd.set(Calendar.HOUR_OF_DAY, endHour);
				calEnd.set(Calendar.MINUTE, endMin);

				long slotBegin = cal.getTimeInMillis();
				long slotEnd = slotBegin + step;
				long rangeEnd = calEnd.getTimeInMillis();

				while (slotEnd <= rangeEnd) {
					if (!slots.containsKey(slotBegin)) {
						TimeSlot slot = new TimeSlot(slotBegin, slotEnd);
						slot.setMaxSeats(maxSeat);
						slots.put(slotBegin, slot);
					}
					slotBegin = slotEnd;
					slotEnd += step;
				}
				count++;
			}
		}
		System.out.println("该线路的合法预约时段：" + JSON.toJSONString(slots));

		// 按当前时间过滤
		Long start = startTime;
		if (setting != null && setting.getMinReservMin() > 0) {
			start += setting.getMinReservMin() * 60000;
		}
		NavigableMap<Long, TimeSlot> submap = slots.tailMap(start, true);
		int ahead = null == setting ? 7 : setting.getMaxReservDay();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, ahead);
		long end = cal.getTimeInMillis();
		submap = submap.headMap(end, false);
		Entry<Long, TimeSlot> entry = slots.floorEntry(submap.firstKey() - 1);
		if (entry != null) {
			TimeSlot slot = entry.getValue();
			if (slot != null) {
				if (slot.getSlotBegin() <= start && start <= slot.getSlotEnd()) {
					TreeMap<Long, TimeSlot> result = new TreeMap<Long, TimeSlot>();
					result.put(entry.getKey(), slot);
					result.putAll(submap);

					submap = result;
				}
			}
		}
		System.out.println("按当前时间过滤后：" + JSON.toJSONString(submap));

		// 按座位数过滤
		List<Long> removeList = new ArrayList<Long>();

		for (TimeSlot slot : submap.values()) {
			if (slot.getMaxSeats() < seats) {
				removeList.add(slot.getSlotBegin());
			}
		}
		if (removeList.size() > 0) {
			for (Long time : removeList) {
				submap.remove(time);
			}
		}
		System.out.println("按当前时间+座位数过滤后：" + JSON.toJSONString(submap));

		// long timeSlot = System.currentTimeMillis();
		// System.out.println(DateUtils.cast(timeSlot));

		// timeSlot -> cast
		TreeMap<String, TimeSlotCast> castSlots = new TreeMap<String, TimeSlotCast>();
		for (TimeSlot timeSlot : submap.values()) {
			TimeSlotCast tsc = new TimeSlotCast();
			tsc.setMaxSeats(timeSlot.getMaxSeats());
			tsc.setSlotBegin(DateUtils.cast(timeSlot.getSlotBegin()));
			tsc.setSlotEnd(DateUtils.cast(timeSlot.getSlotEnd()));
			castSlots.put(tsc.getSlotBegin(), tsc);
		}
		System.out.println("转换后：" + JSON.toJSONString(castSlots));
	}
}
