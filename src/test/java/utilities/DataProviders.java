package utilities;

// Import necessary classes
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
    // This class contains DataProviders used in TestNG to provide test data to test methods.

    /*
     * DataProvider will generate the data and pass the same
     * data into the testing methods.
     */

    /*
     * In this example, we read test data from an Excel file
     * and store it into a 2D array, which will then be returned
     * and used in test cases.
     */

    // DataProvider 1: Named "LoginData"
    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        // Define the path to the Excel file using relative path.
        String path = ".\\testData\\Opencart_LoginData.xlsx";

        // Create an instance of ExcelUtility class, passing the path to the constructor.
        ExcelUtility xlutil = new ExcelUtility(path);

        // Get the total number of rows in the Excel sheet named "Sheet1".
        int totalrows = xlutil.getRowCount("Sheet1");

        // Get the total number of columns in the second row (index 1) of "Sheet1".
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        // Create a 2D String array to store the login data.
        String logindata[][] = new String[totalrows][totalcols];

        // Loop through each row and column to read data from the Excel sheet
        for (int i = 1; i <= totalrows; i++) { // Start from i = 1 to skip header row (if any)
            for (int j = 0; j < totalcols; j++) {
                // Store the cell data into the array
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // i-1 adjusts index to start from 0
            }
        }

        // Return the filled array to be used in test methods
        return logindata;
    }

    // Placeholder for additional DataProviders (can be implemented as needed)
    // @DataProvider2
    // @DataProvider3
    // @DataProvider4
}
