package com.xinqushi.designpattern.composite;

/**
 * 财务部类[树叶节点]
 * @author yangli
 */
public class FianceDepartment extends Company{

	public FianceDepartment(String name) {
		super(name);
	}
	
	@Override
	public void add(Company company) {
	}

	@Override
	public void del(Company company) {
	}

	@Override
	public void display(int depth) {
		for(int i=1; i<=depth ; i++){
			System.out.print("-");
		}
		System.out.print(name);
		System.out.println();
	}

	@Override
	public void lineOfDuty() {
		System.out.println(name + " -> 公司财务收支管理");
	}
	
}
