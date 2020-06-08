package cn.possible2dream.menjin_at.test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Test {

    public static void main(String [] args){
//        System.out.println("sdfsdfs");
        Set ts = new TreeSet();
        ts.add("a");
        ts.add("c");
        ts.add("b");
        ts.add("z");
        ts.add("a");
        Iterator it = ts.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
