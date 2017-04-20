package com.xinqushi.designmodel.prototype;

import java.io.*;

/** 
 * <h3>原型模式</h3>
 * 将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象。<br/>
 * 浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。<br/>
 * 深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。<br/>
 * 简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。<br/>
 * 要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
 * @author Administrator
 *
 */
public class Prototype implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	private String string;

	private SerializableObject obj;

	/* 浅复制 */
	public Object clone() throws CloneNotSupportedException {
		Prototype proto = (Prototype) super.clone();
		return proto;
	}

	/* 深复制 */
	public Object deepClone() throws IOException, ClassNotFoundException {

		/* 写入当前对象的二进制流 */
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);

		/* 读出二进制流产生的新对象 */
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public SerializableObject getObj() {
		return obj;
	}

	public void setObj(SerializableObject obj) {
		this.obj = obj;
	}

}

class SerializableObject implements Serializable {
	private static final long serialVersionUID = 1L;
}