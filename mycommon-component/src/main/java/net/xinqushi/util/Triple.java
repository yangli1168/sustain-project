package net.xinqushi.util;

import java.io.Serializable;

public class Triple<K, V, S> implements Serializable {

    private static final long serialVersionUID = 1424511789309517880L;

	private K val1;
    
    private V val2;
    
    private S val3;

    public Triple() {
    }

	public Triple(K val1, V val2, S val3) {
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
    }

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

	@Override
    public String toString() {
	    return "Triple [val1=" + val1 + ", val2=" + val2 + ", val3=" + val3 + "]";
    }
    
}
