package com.xinqushi.special;

import java.math.BigInteger;

/**
 * 类型安全枚举类模式demo
 * <p>该模式提供了编译时的类型安全性</p>
 * @author yangli
 */
public class TypeSafeEnum {
	
	private final String name;
	
	private TypeSafeEnum(String name) {
		this.name = name;
	}
	
	public static final TypeSafeEnum CLUBS = new TypeSafeEnum("clubs");
	public static final TypeSafeEnum DIAMONDS = new TypeSafeEnum("diamonds");
	public static final TypeSafeEnum HEARTS = new TypeSafeEnum("hearts");
	public static final TypeSafeEnum SPADES = new TypeSafeEnum("spades");
	
	@Override
	public String toString() {
		return name;
	}
	
	public static void main(String[] args) {
		
		System.out.println(TypeSafeEnum.CLUBS);
		
		mod(new BigInteger("-100"));
	}
	
	/**
	 * 
	 * @param m
	 * @return this mod m
	 * @throws ArithmeticException if m <= 0
	 */
	public static BigInteger mod(BigInteger m){
		try {
			if (m.signum() <= 0) {
				throw new ArithmeticException("Modulus not positive");
			}
		} catch (Exception e) {
			System.err.println("发生异常：" + e.getMessage());
		}
		return m;
	}
}
