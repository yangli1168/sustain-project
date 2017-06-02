package com.xinqushi.designpattern.builder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author yangli
 */
public class BuilderDemo {
	
	public static void main(String[] args) {
		
		JFrame jf = new JFrame("建造者模式测试");
		JPanel jp = new JPanel();
		JButton jb = new JButton("建造瘦子");
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = jp.getGraphics();
				g.setColor(Color.GREEN);
				g.setXORMode(Color.YELLOW);
				
				PersonThinBuilder ptb = new PersonThinBuilder(g);
				PersonDirector pdThin = new PersonDirector(ptb);
				pdThin.createPerson();
				
			}
		});
		JButton jb2 = new JButton("建造胖子");
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Graphics g = jp.getGraphics();
				g.setColor(Color.RED);
				g.setXORMode(Color.YELLOW);
				
				PersonFatBuilder ptb = new PersonFatBuilder(g);
				PersonDirector pdThin = new PersonDirector(ptb);
				pdThin.createPerson();
				
			}
		});
		
		jp.setVisible(true);
		jp.setBackground(Color.PINK);
		jp.add(jb);
		jp.add(jb2);
		
		jf.setContentPane(jp);
		jf.setSize(800, 600);
		jf.setResizable(false);
		jf.setLocation(300, 300); 
		jf.setVisible(true);
		
	}
}
