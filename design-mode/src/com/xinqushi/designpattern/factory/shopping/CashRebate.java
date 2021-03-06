package com.xinqushi.designpattern.factory.shopping;

/**
 * 
 * @author yangli
 * @date 2017年5月31日 - 下午9:55:20
 * @Description: 打折收费实现类
 */
public class CashRebate extends CashSuper {

    /** 折扣率 */
    private double moneyRebate = 1d;

    public CashRebate(String moneyRebate) {
        this.moneyRebate = Double.parseDouble(moneyRebate);
    }

    public CashRebate() {
        this.moneyRebate = Double.parseDouble("0.3");
    }

    /** 打折收费时按折扣率返回 */
    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }

}
