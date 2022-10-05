package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class test {
    public static void main(String args[]){
        Map <String, String> m = new HashMap<>();
        Set<String> keys = m.keySet();


        m.put("one", "luqman");
        m.put("two", "suliman");
        m.put("three", "said");
        m.put("four", "alhasn");
        String[] ls = keys.toArray(new String[keys.size()]);
       // keys = keys.toArray()
        for (int i = 0; i<m.size(); i++)
            System.out.println(ls[i]);
        
    }
}
