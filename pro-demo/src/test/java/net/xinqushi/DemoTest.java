package net.xinqushi;

import net.xinqushi.util.DateUtils;
import net.xinqushi.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;

public class DemoTest {

	public static void main(String[] args) {
//		getAvailableTimeSlots(33, "SHARE", 4);
		
		Jedis jedis = JedisPoolUtil.getJedis();
//		jedis.set("sequence:consumption", "1");
//		System.out.println(jedis.get("sequence:consumption"));
		
		String cast = DateUtils.getFormatDateTime(System.currentTimeMillis(), DateUtils.yyyyMMddFormat_NOSEPARATOR);
//		System.out.println(cast);
		
		
		StringBuilder orderNumber = new StringBuilder("TD");
		if (null == jedis.get("sequence:consumption:" + cast)) {
			jedis.set("sequence:consumption:" + cast, 1 + "");
			orderNumber.append(cast).append(String.format("%04d", 1));
		} else {
			Long incr = jedis.incr("sequence:consumption:" + cast);
			orderNumber.append(cast).append(String.format("%04d", incr));
		}
		System.out.println("orderNumber = " + orderNumber);
		
//		jedis.set("test", "测试");
//		System.out.println(jedis.get("test"));
		
//		System.out.println(CommonStatus.toString(CommonStatus.VALID));
//		System.out.println(CommonStatus.VALID.toString());
//		System.out.println(CommonStatus.VALID.name());

	}

}
