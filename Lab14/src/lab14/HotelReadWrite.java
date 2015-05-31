/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab14;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20120336
 */
public class HotelReadWrite {
    
    public static List<Hotel> getHotelList(String path){
        int identifier,offset;
        short anzDatenFelder;
        List<Hotel> l = new ArrayList<>();
        try(RandomAccessFile raf = new RandomAccessFile(path, "r")){
            identifier = raf.readInt();
            offset = raf.readInt();
            anzDatenFelder = raf.readShort();
            System.out.println(identifier+" , " + offset+ ",  " + anzDatenFelder);
            for(int i = 0; i<anzDatenFelder;i++){
                short lenName = raf.readShort();
                byte []f = new byte[lenName];
                raf.read(f);
                String s = new String(f);
                short len = raf.readShort();
                System.out.println("len Name:" +lenName+", Name: " +s+", len: "+len);
            }
            while(raf.getFilePointer()< raf.length()){
                byte []f = new byte[161];
                raf.read(f);
                Hotel h = new Hotel(f);
                l.add(h);
                
            }
        }catch(IOException e){
            System.out.println(""+e.toString());
        }
        return l;
    }
    
    
    public static void changeData(String pfad,int zeile,Hotel newValue){
        int offset;
        System.out.println(""+pfad);
        try(RandomAccessFile raf = new RandomAccessFile(pfad, "rw")){
            raf.seek(4L);
            offset= raf.readInt();
          //  System.out.println("Zeile: " + zeile);
            //System.out.println("offset: " + offset);
            offset+= 161 * zeile;
            //offset= offset+h;
            //System.out.println("161*zeile: "+161*zeile+" Offset "+(offset)+ ", 161*zeile-1: "+161*(zeile));
            raf.seek(offset);
            raf.write(newValue.getBytes());
        }  
        catch (IOException ex) {
            System.out.println(""+ex.toString()+",   "+ex.getMessage());
        }
    }
    
    public static void writeHotelList(String pfad, List<Hotel> l){
        try(RandomAccessFile raf = new RandomAccessFile(pfad,"rw")){
            raf.writeInt(28);
            raf.writeInt(74);
            raf.writeShort(7);
            Hotel.DatenFeld []d = Hotel.DatenFeld.values();
            for(int i = 0; i<7; i++){
                raf.writeShort(4);
                raf.write(d[i].getBezeichnung().getBytes());
                raf.writeShort(d[i].getLen());
            }
            for(Hotel h : l){
                raf.write(h.getBytes());
            }
        } catch (IOException ex) {
            Logger.getLogger(HotelReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addNewHotel(String pfad, byte[] h){
        try(RandomAccessFile raf = new RandomAccessFile(pfad,"rw")){
            raf.seek(raf.length());
            raf.write(h);
        } catch (IOException ex) {
                System.out.println("Fehler addHotel, HotelReadWrite:  "+ex);
        }
    }
    
}
