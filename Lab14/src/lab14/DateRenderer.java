/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab14;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Thomas
 */
public class DateRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel jl = new JLabel();
        jl.setHorizontalAlignment(JLabel.CENTER);
        SimpleDateFormat df = new SimpleDateFormat("dd.mm.yyyy");
        jl.setText(df.format((Date)value));
        return jl;
    }
    
}
