package com.xinqushi.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体公司类[树枝节点]
 * @author yangli
 */
public class ConcreteCompany extends Company{

	public ConcreteCompany(String name) {
		super(name);
	}
	
	private List<Company> children = new ArrayList<Company>();
	
	@Override
	public void add(Company company) {
		children.add(company);
	}

	@Override
	public void del(Company company) {
		children.remove(company);
	}

	@Override
	public void display(int depth) {
		for(int i=1; i<=depth ; i++){
			System.out.print("-");
		}
		System.out.print(name);
		System.out.println();
		for (Company company : children) {
			company.display(depth + 2);
		}
	}

	@Override
	public void lineOfDuty() {
		for (Company company : children) {
			company.lineOfDuty();
		}
	}
	
}
