package net.xinqushi.sms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.xinqushi.common.constants.SmsConstants;
import net.xinqushi.util.HttpUtil;
import net.xinqushi.util.StringUtils;
import net.xinqushi.util.XMLUtil;

@Service("smsService")
public class SmsService {

	private static Logger logger = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	private XMLUtil xmlUtil;

	private static String URL = "";// 默认指定模板请求
	private static String USERNAME = "";// 无参模板通过只能匹配请求
	private static String PASSWORD = "";

	private static final String CHANNEL_818 = "1012818";
	private static final String CHANNEL_888 = "1012888";

	@PostConstruct
	public void init() {
		URL = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
		USERNAME = "dlyoutu0";
		PASSWORD = "29xuydnQ";
	}

	public boolean sendMsg(String phone, String tplId, String[] args) {
		try {
			String result = "";
			String msgTemplate = matchTplValue(tplId);
			if ("TPL_NOT_FOUND".equals(msgTemplate)) {
				logger.info("fail to match tplId : TPL_NOT_FOUND");
				return false;
			}
			
			String[] paramValues = null;
			if (args == null || args.length == 0) {
				paramValues = new String[] { "" };
			} else {
				paramValues = new String[args.length];
				for (int i = 0; i < args.length; i++) {
					String[] info = args[i].split("=");
					if (info.length > 1) {
						paramValues[i] = info[1];
					} else {
						paramValues[i] = info[0];
					}
				}
			}
			String msg = StringUtils.msgFormat(msgTemplate, paramValues);
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("sname", USERNAME);
			paramMap.put("spwd", PASSWORD);
			paramMap.put("scorpid", "");
			if (tplId.equals(SmsConstants.TEMPLATE_VERCODE)) {
				paramMap.put("sprdid", CHANNEL_888);
			} else {
				paramMap.put("sprdid", CHANNEL_818);
			}
			paramMap.put("sdst", phone);
			paramMap.put("smsg", msg);
			logger.info("Send SMS request: " + paramMap);
			result = HttpUtil.post(URL, paramMap);
			logger.info("Send SMS request result: " + result);
			Map<String, String> resultMap = xmlUtil.XMLToMap((result));
			if (Integer.valueOf(resultMap.get("State")) == 0) {
				return true;
			} else {
				logger.warn("Send SMS failed");
			}
		} catch (Exception e) {
			logger.error("sendSms Exception occured", e);
		}
		return false;
	}

	public static void main(String[] args) {
		String result = "";
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sname", "dlyoutu0");
		paramMap.put("spwd", "29xuydnQ");
		paramMap.put("scorpid", "");
		// if(tplId.equals(SmsConstants.TEMPLATE_VERCODE)){
		paramMap.put("sprdid", CHANNEL_888);
		// }else {
		// paramMap.put("sprdid", CHANNEL_818);
		// }
		paramMap.put("sdst", "13540268360");
//		paramMap.put("smsg", "【UTo城际出行】您的验证码是1234,请于1分钟内正确输入。");
		
		String[] msg = { "陛下", "欢迎使用悠途服务，祝您生活愉快！"};
		String msgFormat = StringUtils.msgFormat(matchTplValue("10000"), msg);
		paramMap.put("smsg", msgFormat);
		
		logger.info("Send SMS request: " + paramMap);
		result = HttpUtil.post("http://cf.51welink.com/submitdata/Service.asmx/g_Submit", paramMap);
		logger.info("Send SMS request result: " + result);

	}

	private static String matchTplValue(String tplId) {

		String template = "";

		switch (tplId) {
		case SmsConstants.TEMPLATE_VERCODE:
			template = "【UTo城际出行】亲爱的{1},{2}!";
			break;
		default:
			template = "TPL_NOT_FOUND";
			break;
		}

		return template;
	}

}
