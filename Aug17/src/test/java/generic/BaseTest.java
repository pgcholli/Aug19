package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IConst {
	public WebDriver driver;
	public WebDriverWait wait;

	@Parameters({"hubUrl","browser"})
	@BeforeMethod
	public void openApp(@Optional(dURL) String hubUrl,@Optional(dBrowser) String browser) throws MalformedURLException
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		

		//grid integration
		URL remoteURL=new URL(hubUrl);
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setBrowserName(browser);
		driver=new RemoteWebDriver(remoteURL, dc);
		
		wait=new WebDriverWait(driver,ITO);
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(APP_URL);
	}
	
	@AfterMethod
	public void closeApp()
	{
		driver.quit();
	}
}
