/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab14;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Thomas
 */
public class HotelTableModel extends AbstractTableModel implements TableModel{

    private List<Hotel> data = new ArrayList<>();
    private List<Hotel> allHotels = new ArrayList<>();

    public HotelTableModel() {
        allHotels = HotelReadWrite.getHotelList("db-1x2o.db");
        List<Hotel> l = new ArrayList<>();
        for(Hotel h : allHotels){
            if(!h.isIstGeloescht())
                l.add(h);
        }
        data = l;
    }
    
    
        public HotelTableModel(String pfad) {
        allHotels = HotelReadWrite.getHotelList(pfad);
        List<Hotel> l = new ArrayList<>();
        for(Hotel h : allHotels){
            if(!h.isIstGeloescht())
                l.add(h);
        }
        data = l;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return data.get(rowIndex).getName();
            case 1: return data.get(rowIndex).getLoc();
            case 2: return data.get(rowIndex).getSize();
            case 3: return data.get(rowIndex).getSmoking();
            case 4: return data.get(rowIndex).getRate();
            case 5: return data.get(rowIndex).getDate();
            case 6: return data.get(rowIndex).getOwner();
            default: return null;
        }
    }
    
    @Override
     public String getColumnName(int columnIndex){
         return Hotel.DatenFeld.values()[columnIndex].name();
     }
     
    @Override
     public boolean isCellEditable(int rowIndex, int columnIndex){
         return columnIndex >=5;
     }
     
    @Override
     public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        switch(columnIndex){
            case 0: data.get(rowIndex).setName((String)aValue);
                HotelReadWrite.changeData("db-1x2.db", allHotels.indexOf(data.get(rowIndex)),data.get(rowIndex));
                break;
            case 1: data.get(rowIndex).setLoc((String) aValue);
                break;
            case 2:  data.get(rowIndex).setSize((int) aValue);
                break;
            case 3:  data.get(rowIndex).setSmoking((String)aValue);
                break;
            case 4:  data.get(rowIndex).setRate((double)aValue);
                break;
            case 5:  data.get(rowIndex).setDate((Date)aValue);
                HotelReadWrite.changeData("db-1x2.db", allHotels.indexOf(data.get(rowIndex)),data.get(rowIndex));
                break;
            case 6:  data.get(rowIndex).setOwner((String)aValue);
                break;
        }
     }

    public List<Hotel> getData() {
        return data;
    }

    public List<Hotel> getAllHotels() {
        return allHotels;
    }
     
    
    public  List<String> getLocations(){
        List<String> l = new ArrayList<>();
        for(Hotel h : allHotels){
            l.add(h.getLoc());
        }
        return l;
    }
     
    public void addHotel(String pfad, Hotel h){
        data.add(h);
        allHotels.add(h);
        HotelReadWrite.addNewHotel(pfad,h.getBytes());
        fireTableRowsInserted(data.size(), data.size());
    }
    
}
