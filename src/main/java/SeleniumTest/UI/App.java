package SeleniumTest.UI;

import java.io.File;
//import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;

public class App 
{
    public static void main( String[] args ) throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
    {
      // Including File system to read excel sheet for test data      
      Workbook workbook = WorkbookFactory.create(new File("src//test//java//TestData.xlsx"));
      
      // Reading first sheet
      Sheet sheet = workbook.getSheetAt(0);
      
      // Initialising the rows and columns
 	  Row row = sheet.getRow(0);
 	  Cell cell = row.getCell(0);
 	  
 	  // Getting the length of the column
      int colNum = row.getLastCellNum();
   
      // looping through each test data and testing the UI
      for (int i=0 ;i< colNum ;i++) {
    	  
    	  row = sheet.getRow(i+1);
          cell = row.getCell(0);
          String suburb = cell.getStringCellValue();
          System.out.println("Suburb: "+ suburb);
          cell = row.getCell(1);
          String service_centre = cell.getStringCellValue();
          System.out.println("Service Centre:  " + service_centre); 
          
          // Initiate driver 
          String OSName=System.getProperty("os.name");	
          ChromeOptions options = new ChromeOptions();
          options.addArguments("--headless");
          System.setProperty("webdriver.chrome.driver", "chromedriver");
          System.out.println("[OS: ]"+OSName);
          WebDriver driver= new ChromeDriver();
        
          // Launching UI
          launchApp(driver,suburb,service_centre);  
      }
      System.out.println("Test ends");  
    }
      public static void launchApp(WebDriver driver, String suburb, String service_centre) throws InterruptedException {
      	// Step 1: URL definition
      	String URL = "https://www.service.nsw.gov.au/";
      	driver.get(URL);
      	
      	// Step 2: Serach for "Apply for a number plate"
      	driver.findElement(By.id("edit-contains")).sendKeys("Apply for a number plate");
      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      	
      	// Step 3: Click on locate 
      	driver.findElement(By.xpath("//*[@id=\"ui-id-2\"]")).click();
      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      	
      	// Step 4:  locate text box to enter suburb
      	driver.findElement(By.xpath("/html/body/div[3]/header/div/div[2]/div[1]/a")).click();
      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  		
  		if(suburb.equalsIgnoreCase("sydney 2000"))
  		{
  			// Send suburb value
  	      	driver.findElement(By.id("locatorTextSearch")).sendKeys(suburb);
  	      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	      	
  	      	// click in drop down
  	      	driver.findElement(By.xpath("//*[@id=\"locatorAutocomplete\"]/li[1]")).click();
  	      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	      	
  	      	// Scroll down
  	  		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
  	  		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	  		
  	      	// click service centre
  	      	driver.findElement(By.xpath("//*[@id=\"locatorListView\"]/div/div/div/div[5]/a")).click();
  	      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  		}
  		
  		if(suburb.equalsIgnoreCase("Parramatta 2150")) {
  			// Send suburb value
  	      	driver.findElement(By.id("locatorTextSearch")).sendKeys(suburb);
  	      	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	      	
  	      	// Click in drop down
  			driver.findElement(By.xpath("//*[@id=\"locatorAutocomplete\"]/li[1]")).click();
  			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  			
  			// Scroll down
  	  		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
  	  		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  	  		
  	  		// click on service centre
  			driver.findElement(By.xpath("//*[@id=\"locatorListView\"]/div/div/div/div[1]/a")).click();
  			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  		}
  		
      	driver.close();
      }
         
     
    }
    
    

