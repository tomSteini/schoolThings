package lab14;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * Stellt die graphische Komponente zum editieren des Datums dar
 * @author Thomas
 */
public class DateEditor extends AbstractCellEditor implements TableCellEditor {

  // JDateChooser als grafische Komponente
  private JDateChooser dateChooser;
  // Statisches Element zum formatieren des datums
  private static DateFormat df = DateFormat.getDateInstance();

  /**
   * Erzeugt einen neuen DateEditor
   */
  public DateEditor() {
    dateChooser = new JDateChooser();
    // Es wird ein PropertyChangeListener registriert, um von jeder Aenderung der
    // Eigenschaft date in JDateChooser informiert zu werden.
    dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("date")) {   // es wurde die Prperty date veraendert
          Date old = (Date) evt.getOldValue();       // alter Wert
          Date neu = (Date) evt.getNewValue();       // neuer Wert
          if(old == null || old.equals(neu)) { 
            fireEditingCanceled();
          }
          fireEditingStopped();
        }
      }
    });
  }

 
  /**
   * Liefert den durch den Editor eingestellten Wert. 
   * Muss ueberschrieben werden - ist in AbstractCellEditor abstrakt
   * @return eingestellter Wert
   */
  @Override
  public Object getCellEditorValue() {
    return dateChooser.getDate();
  }


  /**
   * Liefert die grafische Komponente des Editors. 
   * Muss ueberschrieben werden - kommt aus dem Interface TableCellEditor
   * @param table JTable
   * @param value eingestellter Wert
   * @param isSelected gibt an, ob die Zelle selektiert ist
   * @param row Zeilenindex
   * @param column Spealtenindex
   * @return graphische Komponente des Editors
   */
  @Override
  public Component getTableCellEditorComponent(JTable table,
                                               Object value,
                                               boolean isSelected,
                                               int row,
                                               int column) {
    dateChooser.setDate((Date) value);
    return dateChooser;
  }
}
