package net.xinqushi.util;

import java.io.Serializable;

public class Quadra <K, V, S,T> implements Serializable {

	/**
	 * 
	 */
    private static final long serialVersionUID = 4337853747545306411L;

	private K val1;
    
    private V val2;
    
    private S val3;
    
    private T val4;

	public K getVal1() {
		return val1;
	}

	public void setVal1(K val1) {
		this.val1 = val1;
	}

	public V getVal2() {
		return val2;
	}

	public void setVal2(V val2) {
		this.val2 = val2;
	}

	public S getVal3() {
		return val3;
	}

	public void setVal3(S val3) {
		this.val3 = val3;
	}

	public T getVal4() {
		return val4;
	}

	public void setVal4(T val4) {
		this.val4 = val4;
	}
    
	@Override
    public String toString() {
	    return "Triple [val1=" + val1 + ", val2=" + val2 + ", val3=" + val3 +",val4=" + val4 + "]";
    }
}
