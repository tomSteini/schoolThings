/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab14;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20120336
 */
public class FirstRAF {
    
    public static List<Number> getList(String pfad){
        List<Number> list = new ArrayList<>();
        byte b;
        Number num;
        try(RandomAccessFile raf = new RandomAccessFile(pfad, "r")){
            raf.seek(0L);
            while(raf.getFilePointer() < raf.length()){
            b = raf.readByte();
            if(b == 0)
                num = raf.readInt();
            else
                num = raf.readDouble();
            list.add(num);
            }
        } catch (IOException ex) {
            System.out.println("IOException!!!!!");
        }
        return list;
    }
    
    public static Map<String,Integer> analyze(List<? extends Number> l){
        int integer = 0;
        int d = 0;
        Map<String,Integer> m = new HashMap<>();
        for(Number n : l){
            if(n.getClass() == Integer.class)
                integer++;
            if(n.getClass() == Double.class)
                d++;
        }
        m.put("int", integer);
        m.put("double", d);
        return m;
    }
    
}
