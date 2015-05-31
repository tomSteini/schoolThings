/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab14;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 20120336
 */
public class Lab14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        List<Number> l = FirstRAF.getList("Lab_11_1.dat");
//        System.out.println(l);
//        Map<String,Integer> m = FirstRAF.analyze(l);
//        System.out.println(m + "\n\n\n");
        
        
        List<Hotel> l = HotelReadWrite.getHotelList("db-1x2o.db");
                for(Hotel h : l){
            System.out.println(""+h);
        }
        System.out.println("Sortiert: \n");
        //HotelReadWrite.writeHotelList("test1.db", l);
//        l.sort(new Comparator<Hotel>() {
//
//            @Override
//            public int compare(Hotel o1, Hotel o2) {
//                int h = o1.getLoc().compareTo(o2.getLoc());
//                if(h == 0)
//                    return o1.getName().compareTo(o2.getName());
//                return h;
//            }
//        });
        for(Hotel h : l){
            System.out.println(""+h);
        }
        HotelReadWrite.writeHotelList("db-1x2.db", l);
    }
    
}
