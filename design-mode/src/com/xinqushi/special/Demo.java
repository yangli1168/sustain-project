package com.xinqushi.special;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangli on 2017/5/26 - 19:47.
 */
public class Demo{

    public static void main(String[] args){
        List list = new ArrayList<Integer>();
        list.add(1);
        list.add(9);
        list.add(6);
      for(int i=0; i<list.size(); i++)
          System.out.print(list.get(i) + " -> ");
    }


}
