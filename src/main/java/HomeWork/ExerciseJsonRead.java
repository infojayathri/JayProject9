package HomeWork;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import Utility.*;
import ExtentReport.*;



public class ExerciseJsonRead {
	WebDriver driver;
	JSONReader json = new JSONReader();
	ExtentReport extentreport = new ExtentReport();
	 
@BeforeTest
	public void setup() throws IOException, ParseException
	{
	
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(json.ReadJSONFile("URL",".\\Data\\data.json"));
		extentreport.setUpReport();
		
	}
@Test
public void testAdd() throws IOException, ParseException, InterruptedException
	{
		extentreport.startTestCase("New Insurance User");
		driver.findElement(By.xpath("/html/body/div[3]/a")).click();
		
		Select title = new Select(driver.findElement(By.id("user_title")));
		title.selectByValue(json.ReadJSONFile("Title",".\\Data\\data.json"));
		
		//Select title = new Select(driver.findElement(By.id("user_title")));
		//title.selectByVisibleText("Miss");
		driver.findElement(By.id("user_firstname")).sendKeys(json.ReadJSONFile("Firstname",".\\Data\\data.json"));
		driver.findElement(By.id("user_surname")).sendKeys(json.ReadJSONFile("Surname",".\\Data\\data.json"));
		driver.findElement(By.id("user_phone")).sendKeys(json.ReadJSONFile("Phone",".\\Data\\data.json"));
		
		
		Select drpyear = new Select (driver.findElement(By.id("user_dateofbirth_1i")));
		drpyear.selectByVisibleText(json.ReadJSONFile("Year",".\\Data\\data.json"));
		
		Select drpmonth = new Select(driver.findElement(By.id("user_dateofbirth_2i")));
		drpmonth.selectByVisibleText(json.ReadJSONFile("Month",".\\Data\\data.json"));
		
		
		Select drpdate = new Select (driver.findElement(By.id("user_dateofbirth_3i")));
		drpdate.selectByVisibleText(json.ReadJSONFile("Date",".\\Data\\data.json"));
		
		WebElement radio1 = driver.findElement(By.id("licencetype_t"));
		WebElement radio2 = driver.findElement(By.id("licencetype_f"));
		String Status = json.ReadJSONFile("LicenceType",".\\Data\\data.json");
		
		if (Status.equals(radio1))
				{
			radio1.click();
				}else
				{
					radio2.click();
				}
		

		Select drplicencePeriod = new Select (driver.findElement(By.id("user_licenceperiod")));
		drplicencePeriod.selectByVisibleText(json.ReadJSONFile("LicencePeriod",".\\Data\\data.json"));
		
		Select drpoccupation = new Select (driver.findElement(By.id("user_occupation_id")));
		drpoccupation.selectByVisibleText(json.ReadJSONFile("Occupation",".\\Data\\data.json"));
		
		driver.findElement(By.id("user_address_attributes_street")).sendKeys(json.ReadJSONFile("Address",".\\Data\\data.json"));
		driver.findElement(By.id("user_address_attributes_city")).sendKeys(json.ReadJSONFile("City",".\\Data\\data.json"));
		driver.findElement(By.id("user_address_attributes_county")).sendKeys(json.ReadJSONFile("Country",".\\Data\\data.json"));
		driver.findElement(By.id("user_address_attributes_postcode")).sendKeys(json.ReadJSONFile("PostCode",".\\Data\\data.json"));
		driver.findElement(By.id("user_user_detail_attributes_email")).sendKeys(json.ReadJSONFile("Email",".\\Data\\data.json"));
		driver.findElement(By.id("user_user_detail_attributes_password")).sendKeys(json.ReadJSONFile("Password",".\\Data\\data.json"));
		driver.findElement(By.id("user_user_detail_attributes_password_confirmation")).sendKeys(json.ReadJSONFile("ConfirmPassword",".\\Data\\data.json"));
		driver.findElement(By.xpath("//*[@id=\"new_user\"]/div[5]/input[2]")).click();
		//driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);
		Thread.sleep(2000);
		String webTitle = driver.getTitle();
		
	if (webTitle.equals("Insurance Broker System - Login"))
		{
			extentreport.logEventsPass("My titile is passing");			
		//Assert.assertEquals(webTitle, "Guru99 Bank New Customer Entry Page");
	
		}
		else 
		{
			extentreport.logEventsFail("This is failing");
			//Assert.assertEquals(webTitle, "Guru99 Bank Customer Registration Page");
		}
		
		extentreport.createFinalReport();
	}


@AfterTest
	public void tearDown() throws IOException
	{
	driver.close();
	driver.quit();
	}

}
