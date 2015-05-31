/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab14;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab14.Hotel.DatenFeld;

/**
 *
 * @author 20120336
 */
public class Hotel {
    private boolean istGeloescht;
    private String name;
    private String loc;
    private int size;
    private String smoking;
    private double rate;
    private Date date;
    private String owner;
    
    
    public enum DatenFeld {
    
        NAME(64, "Name"),        
        LOCATION(64, "Location"),
        SIZE(4, "Size"),
        SMOKING(1,"Smoking"),
        RATE(8,"Rate"),
        DATE(10,"Date"),
        OWNER(8,"Owner");        
        
        
        private int len; // länge in Byte
        private String bezeichnung;

        private DatenFeld(int len, String bezeichnung) {
            this.len = len;
            this.bezeichnung = bezeichnung;
        }

        public int getLen() {
            return len;
        }

        public String getBezeichnung() {
            return bezeichnung;
        }
        

      
        
        
    }

    public Hotel(boolean istGeloescht, String name, String loc, int size, String smoking, double rate, Date date, String owner) {
        this.istGeloescht = istGeloescht;
        this.name = name;
        this.loc = loc;
        this.size = size;
        this.smoking = smoking;
        this.rate = rate;
        this.date = date;
        this.owner = owner;
    }
    
    
    
    public Hotel(byte []data) {
        String h = new String(Arrays.copyOfRange(data, 0, 2));
        String flag = h;
        String namen = new String(Arrays.copyOfRange(data, 2, 66));
        String location = new String(Arrays.copyOfRange(data, 66, 130));
        String groesse = new String(Arrays.copyOfRange(data, 130, 134));
        String smoke = new String(Arrays.copyOfRange(data, 134, 135));
        String preis = new String(Arrays.copyOfRange(data, 135, 143));
        String datum = new String(Arrays.copyOfRange(data, 143, 153));
        String besitzer = new String(Arrays.copyOfRange(data, 153, 161));
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd");
            this.date = sdf.parse(datum);
            preis = preis.replace('$', ' ');
            this.rate = Double.parseDouble(preis.trim());
            this.size = Integer.parseInt(groesse.trim());
            this.loc = location.trim();
            this.name = namen.trim();
            this.smoking = smoke.trim();
            this.owner = besitzer.trim();
            if(flag.trim().isEmpty())
                this.istGeloescht = false;
            else
                this.istGeloescht = true;
        }
        catch(NumberFormatException | ParseException ex){
            System.out.println(""+ex.toString());
        }
    }
    public byte[] getBytes() { 
  
        byte[] b = new byte[161];
        byte a = 32;
        short f;
        Arrays.fill(b, a);
        ByteBuffer bf = ByteBuffer.wrap(b);
        if(isIstGeloescht())
            f=(short) 0x8000;
        else
            f=0x0000;
        //System.arraycopy((f+"").getBytes(), 0, b, 0, (f+"").length());
        bf.putShort(f);
        System.arraycopy(name.getBytes(), 0, b,2 , name.length());
        System.arraycopy(loc.getBytes(), 0, b,66 , loc.length());
        System.arraycopy((size+"").getBytes(), 0, b, 130, (size+"").length());
        System.arraycopy(smoking.getBytes(), 0, b, 134, smoking.length());
        System.arraycopy(String.format(Locale.US,"$%.2f", rate).getBytes(), 0, b, 135, String.format(Locale.UK,"$%.2f",rate).length());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        System.arraycopy(sdf.format(date).getBytes(), 0, b, 143, sdf.format(date).length());
        System.arraycopy(owner.getBytes(), 0, b, 153, owner.length());
        //System.arraycopy(this.size., 0, f,0 , 64);
        return b;
    }

    @Override
    public String toString() {
        return " " + name + "," + loc + ", " + size + ", " + smoking + ", " + rate + ", " + date + ", " + owner;
    }

    public boolean isIstGeloescht() {
        return istGeloescht;
    }

    public String getName() {
        return name;
    }

    public String getLoc() {
        return loc;
    }

    public int getSize() {
        return size;
    }

    public String getSmoking() {
        return smoking;
    }

    public double getRate() {
        return rate;
    }

    public Date getDate() {
        return date;
    }

    public String getOwner() {
        return owner;
    }

    public void setIstGeloescht(boolean istGeloescht) {
        this.istGeloescht = istGeloescht;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    
    
    
    
}
