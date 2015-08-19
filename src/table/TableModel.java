/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package table;

import javax.swing.table.*;


public class TableModel extends AbstractTableModel  {
//    // These are the names for the table's columns.
//      private static final String[] columnNames = {"File Name", "Size","Speed",
//        "Progress", "Status"};
//      
//       // These are the classes for each column's values.
//    private static final Class[] columnClasses = {String.class,
//        String.class, String.class,String.class , String.class};
//     
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String[] columnNames = {"First Name", "Surname", "Country"
                        , "Event", "Place", "Time", "World Record" };
        
        Object[][] data = {
            {"César Cielo", "Filho", "Brazil", "50m freestyle",1 , "21.30", false },
            {"Amaury", "Leveaux", "France", "50m freestyle", 2, "21.45", false },
            {"Alain", "Bernard", "France", "50m freestyle", 3, "21.49", false },
            {"Alain", "Bernard", "France", "100m freestyle", 1, "47.21", false },
            {"Eamon", "Sullivan", "Australia", "100m freestyle", 2, "47.32", false },
            {"Jason", "Lezak", "USA", "100m freestyle", 3, "47.67", false },
            {"César Cielo", "Filho", "Brazil", "100m freestyle", 3, "47.67", false },
            {"Michael", "Phelps", "USA", "200m freestyle", 1, "1:42.96", false },
            {"Park", "Tae-Hwan", "South Korea", "200m freestyle", 2, "1:44.85", false },
            {"Peter", "Vanderkaay", "USA", "200m freestyle", 3, "1:45.14", false },
            {"Park", "Tae-Hwan", "South Korea", "400m freestyle", 1, "3:41.86", false },
            {"Zhang", "Lin", "China", "400m freestyle", 2, "3:42.44", false },
            {"Larsen", "Jensen", "USA", "400m freestyle", 3, "3:42.78", false },
        };
    
    
    
//     private Object[][] data = {
//        { "Mary", "Campione", "Snowboarding", new Integer(5),
//            new Boolean(false) },
//        { "Alison", "Huml", "Rowing", new Integer(3), new Boolean(true) },
//        { "Kathy", "Walrath", "Knitting", new Integer(2),
//            new Boolean(false) },
//        { "Sharon", "Zakhour", "Speed reading", new Integer(20),
//            new Boolean(true) },
//        { "Philip", "Milne", "Pool", new Integer(10),
//            new Boolean(false) } };
    @Override
    public int getRowCount() {
return 0;    }

    @Override
    public int getColumnCount() {
    return 0 ;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return 0;//columnClasses[rowIndex][columnIndex];
    }

}
