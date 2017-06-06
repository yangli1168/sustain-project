package com.xinqushi.designpattern.composite;
/**
 * 组合模式demo
 * <p>定义：将对象组合成树形结构以表示部分与整体的层次结构，
 * 使得用户对单个对象和组合对象的使用具有一致性</p>
 * <p>使用：需求中体现部分与整体层次的结构，或希望用户可以忽略组合对象与
 * 单个对象的不同，统一的使用组合结构中的所有对象时</p>
 * <p>优点：包含基本对象和组合对象，基本对象可被组合成更复杂的组合对象；
 * 可以一致的使用组合结构和单个对象</p>
 * @author yangli
 */
public class CompositeDemo {
	
	public static void main(String[] args) {
		
		ConcreteCompany root = new ConcreteCompany("成都总公司");
		root.add(new HRDepartment("总公司人力资源部"));
		root.add(new FianceDepartment("总公司财务部"));
		
		ConcreteCompany comp1 = new ConcreteCompany("上海华东分公司");
		comp1.add(new HRDepartment("华东分公司人力资源部"));
		comp1.add(new FianceDepartment("华东分公司财务部"));
		root.add(comp1);
		
		ConcreteCompany comp2 = new ConcreteCompany("杭州办事处");
		comp2.add(new HRDepartment("杭州办事处人力资源部"));
		comp2.add(new FianceDepartment("杭州办事处财务部"));
		root.add(comp2);
		
		ConcreteCompany comp3= new ConcreteCompany("南京办事处");
		comp3.add(new HRDepartment("南京办事处人力资源部"));
		comp3.add(new FianceDepartment("南京办事处财务部"));
		root.add(comp3);
		
		System.out.println("结构图：");
		root.display(1);
		
		System.out.println("职责：");
		root.lineOfDuty();
	}
}	
