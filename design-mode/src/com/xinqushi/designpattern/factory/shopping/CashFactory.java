package com.xinqushi.designpattern.factory.shopping;
/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午10:05:17
 * @Description: 现金收费工厂类
 */
public class CashFactory {
	
	/**
	 * 现金收取工厂
	 * @param type
	 * @return
	 */
	public static CashSuper createCashAccept(String type){
		CashSuper cs = null;
		
		switch (type) {
		case "正常收费":
			cs = new CashNormal();
			break;
		case "满300减100":
			cs = new CashReturn("300", "100");
			break;
		case "打8折":
			cs = new CashRebate("0.8");
			break;
		}
		
		return cs;
	}
}
