package odsFiles.odsFiles;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.model.style.StyleTableCellProperties;

import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.github.javafaker.Faker;

/**
 * To Create number of ODS files using jopendocument.
 */
public class App 
{

	public static void main( String[] args ) throws IOException
	{
		Faker fakerInstance = Faker.instance(); 
		String[] columns = new String[] {"Serial Number", "First Name","Last Name","Address","Date of birth",
				"SSN", "Secondary School Education", "Phone Number", "Email Address","Monthly Salary in USD"};
		int noFilesToGenerate = 2; // Change number of File to create.
		int noRows = 100; // Change value to create number of rows in the ods file.
			for(int i =0; i<noFilesToGenerate;i++) {
				String fileName = "temperature"+i+".ods";
				final Object[][] data = new Object[noRows][columns.length];
				for( int j =0; j<noRows;j++) {
					data[j] = new Object[] { 
							
							fakerInstance.number().numberBetween(1, 1000000000),
							fakerInstance.name().firstName(),
							fakerInstance.name().lastName(),
							fakerInstance.address().fullAddress(),
							fakerInstance.date().birthday(),
							fakerInstance.idNumber().ssnValid(),
							fakerInstance.educator().secondarySchool(),
							fakerInstance.phoneNumber().phoneNumber(),
							fakerInstance.internet().emailAddress(),
							fakerInstance.number().numberBetween(5000, 15000)};
				}
				TableModel model = new DefaultTableModel(data, columns); 
				final File file = new File(fileName);
				SpreadSheet.createEmpty(model).saveAs(file);
				System.out.println("Created File : " +fileName);
		}
	}
}


