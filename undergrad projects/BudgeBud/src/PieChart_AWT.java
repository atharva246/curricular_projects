
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import backend.Commons;
import backend.DbHelper;

public class PieChart_AWT extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DbHelper dbHelper;
	private static List<String> categoryTitlesList;
	private static List<Double> categoryWiseAmountTotalList;
	
	public PieChart_AWT( String title ) {
		super( title );
		dbHelper = new DbHelper();
		
		categoryTitlesList = dbHelper.getExpenseCategoryTitles(Commons.currentLogin);
		categoryWiseAmountTotalList = new ArrayList<>();
		
		for(String categoryTitle : categoryTitlesList) {
			categoryWiseAmountTotalList.add(dbHelper.getExpenseForCategory(categoryTitle, Commons.currentLogin));
		}
		
		setContentPane(createDemoPanel( ));
	}

	private static PieDataset createDataset( ) {
		DefaultPieDataset dataset = new DefaultPieDataset( );
		
		for(int i=0;i<categoryTitlesList.size();i++) {
			dataset.setValue( categoryTitlesList.get(i) , categoryWiseAmountTotalList.get(i) );
		}
		return dataset;         
	}

	private static JFreeChart createChart( PieDataset dataset ) {
		JFreeChart chart = ChartFactory.createPieChart(      
				"Budget Bud",   // chart title 
				dataset,          // data    
				true,             // include legend   
				true, 
				false);

		return chart;
	}

	public static JPanel createDemoPanel( ) {
		JFreeChart chart = createChart(createDataset( ) );  
		return new ChartPanel( chart ); 
	}

	public static void main( String[ ] args ) {
		PieChart_AWT demo = new PieChart_AWT( "Budge Bud" );  
		demo.setSize( 560 , 367 );    
		RefineryUtilities.centerFrameOnScreen( demo );    
		demo.setVisible( true ); 
	}
}


