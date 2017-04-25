package net.xinqushi.jms;

import java.util.TreeSet;

import org.springframework.stereotype.Component;

import net.xinqushi.common.enums.ManagementEventType;
import net.xinqushi.util.JedisPoolUtil;

/**
 * 生成事件标志类：用于在redis缓存中生成标志
 * @author yangli
 * 2017年4月25日-上午10:22:38
 */
@Component
public class ManagementEventMarker {
	
	/**
	 * 生成标志
	 * @param signKind 标志的种类
	 * @param eventType 事件的类型
	 * @param data
	 * 2017年4月25日-上午10:27:43
	 */
	public void markManagmentEvent(String signKind, ManagementEventType eventType, String data) {
		JedisPoolUtil.getJedis().hset(signKind, eventType.name(), data);
	}
	
	/**
	 * 添加标志
	 * @param signKind
	 * @param eventType
	 * @param data
	 * 2017年4月25日-上午10:29:03
	 */
	public void appendManagmentEvent(String signKind, ManagementEventType eventType, String data){
		String str = JedisPoolUtil.getJedis().hget(signKind, eventType.name());
		if (null == str) {
			this.markManagmentEvent(signKind, eventType, data);
		} else {
			String[] array = str.split(",");
			TreeSet<String> set = new TreeSet<String>();
			for (String ele : array) {
				set.add(ele);
			}

			if (set.add(data)) {
				StringBuilder sb = new StringBuilder();
				for (String ele : set) {
					if (sb.length() == 0) {
						sb.append(ele);
					} else {
						sb.append(",").append(ele);
					}
				}
				this.markManagmentEvent(signKind, eventType, sb.toString());
			}
		}
		
	}
}
