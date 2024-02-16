package EPOSMobileTestFramework.MobileTestFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class EPOSBaseClass {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement>  driver;
	
	
	
	//Code to start Appium Server
	public AppiumDriverLocalService startAppiumServer() {
		
		boolean flag =	checkIfServerIsRunnning(4723);
		if(!flag)
		{
			
			service=AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
			return service;

	}
	
	public static boolean checkIfServerIsRunnning(int port) {
		
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws IOException {
		
		//Load GlobalProperty File
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
		Properties prty = new Properties();
		prty.load(fis);
		
		//Fetch the application location
		File f = new File("src");
		File fs = new File(f, (String) prty.get(appName));
		

		//Initiate the device and the application under test
		DesiredCapabilities dc = new DesiredCapabilities();
		String device = (String) prty.get("deviceType");          
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		dc.setCapability(MobileCapabilityType.FULL_RESET, "False");
	    dc.setCapability(MobileCapabilityType.NO_RESET, "True");
	   
		
	   
	   
		//SetUp Connection to Appium Server
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),dc);
		return driver;
		

	}
	
	//Function to swipe screen left 
	public static void swipeLeft() {
		
		Dimension dimensions = driver.manage().window().getSize();
	    Double point = dimensions.getWidth() * 0.45;
	    int pointAsAnInteger = point.intValue();
	    TouchAction action = new TouchAction(driver);
	    action.press(PointOption.point(pointAsAnInteger*2 , pointAsAnInteger))
	    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
	    .moveTo(PointOption.point( 0, pointAsAnInteger))
	    .release()
	    .perform();
	    
	}
	
	//Function to swipe screen right
	 public static void swipeRight() {
		  // duration should be in milliseconds
		 Dimension size = driver.manage().window().getSize();
            int startX = 0;
		    int endX = 0;
		    int startY = 0;
		    int endY = 0;
		    startY = (int) (size.height / 2);
            startX = (int) (size.width * 0.05);
            endX = (int) (size.width * 0.90);
		            TouchAction action = new TouchAction(driver);
		                   action .press(PointOption.point(startX, startY))
		                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		                    .moveTo(PointOption.point(endX,startY))
		                    .release()
		                    .perform();
	
	 }
	 
	//Function to scroll down
	public void scroll(AndroidDriver<AndroidElement> driver,double start_xd, double start_yd, double end_xd, double end_yd) throws InterruptedException {
		Dimension dimension=driver.manage().window().getSize();
		int start_x =(int) (dimension.width * start_xd);
		int start_y =(int) (dimension.height * start_yd);
		int end_x =(int) (dimension.width*end_xd);
		int end_y =(int)(dimension.height*end_yd);
		
		TouchAction touch =new TouchAction(driver);
		touch.press(PointOption.point(start_x,start_y))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(end_x, end_y))
		.release()
		.perform();
	}
	
	//Function to capture screenshot
	public static void getScreenshot(String testName) throws IOException
	{
		//cast the driver to capture screenshot
		File scrfile =	((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile,new File(System.getProperty("user.dir")+"\\"+testName+".png"));
	
	}
	   public static void getHeadsetVal() throws IOException
	    {
	        //Fetch device values and redirect to output file
	                ProcessBuilder builder = new ProcessBuilder(
	                             "cmd.exe", "/k", "cd  "+System.getProperty("user.dir")+"\\src\\NautilusCli && NautilusCLI.exe < input.txt > output.txt");

	                        builder.redirectErrorStream(true);
	                         Process p = builder.start();

	    }
	   
	  
	   public static List<String> getAudiolimiterVal() throws IOException
	   {
		   String[] Audio = {"Audio Limiter","Off","EU/US Limiter","AU Limiter"}; 
		   List<String> audioType = Arrays.asList(Audio);
		   return audioType;
	   }
	   
	   public static List<String> getToneVoicePrompt() throws IOException
	   {
		   String[] TonePrompt = {"Off","Tone Prompts","Voice Prompts"}; 
		   List<String> TonePromptType = Arrays.asList(TonePrompt);
		   return TonePromptType;
		}
	   
	   public static List<String> getHeadsetType() throws IOException
	   {
		   String[] Header= {"ADAPT 230/260","ADAPT 360","ADAPT 460/460T","ADAPT 560","ADAPT 660/660 AMC","C20","C50","IMPACT 1030/1060","MB 660/660 MS"};
		   List<String> headsetType = Arrays.asList(Header);
		   return headsetType;
		}
	   
	 /*  public static List<String> getHeadsetValue() throws IOException
	   {
	   String[] HeaderVal = {"MB 660 MS","ADAPT 360","ADAPT 460","ADAPT 560"};
	   List<String> headset = Arrays.asList(HeaderVal);
	   return headset;
	   }*/
	   
	   public String getHeadsetName() throws IOException {
		   
		 //Load GlobalProperty File
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
			Properties prty = new Properties();
			prty.load(fis);
		   
		   String headsetName = (String) prty.get("scropioHeadsetType"); 
		   return headsetName;
		   
	   }
	 
	   public String getAdapt660Headset() throws IOException {
		   
			 //Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
			   
			   String headset = (String) prty.get("headsetType"); 
			   return headset;
			   
		   }
	   public String getOsbourneHeadset() throws IOException {
		   
			 //Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
			   
			   String headset = (String) prty.get("OsbourneHeadsetType"); 
			   return headset;
			   
		   }
	   
	   
	   public static List<String> getAutoPowerOff() throws IOException
	   {
		   String[] AutoPower = {"Off","After 1 hour","After 2 hours","After 6 hours"}; 
		   List<String> AutoPowerOffType = Arrays.asList(AutoPower);
		   return AutoPowerOffType;
		}
	   
	   
	   public String getEposManager() throws IOException {
		   
			 //Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
			   
			   String EposLink = (String) prty.get("eposManager"); 
			   return EposLink;
			   
		   }
	   
	   public String getUserName() throws IOException {
		   
			 //Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
			   
			   String User = (String) prty.get("username"); 
			   return User;
			   
		   }
	   public String getPassWord() throws IOException {
		   
			 //Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
			   
			   String PassWord = (String) prty.get("password"); 
			   return PassWord;
			   
		   }
	   
	   public String getClientVersion() throws IOException {
		   
			 //Load GlobalProperty File
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EPOSMobileTestFramework\\MobileTestFramework\\Global.properties");
				Properties prty = new Properties();
				prty.load(fis);
			   
			  String Version = (String) prty.get("ClientVersion"); 
			   return Version;
			   
		   }   
	   public static void Link() {
	   WebElement element = driver.findElement(By.id("com.eposaudio.eposconnect:id/footer_textView"));
      	Point point = element.getLocation();

      	//you can change follwing point based on your link location
      	int x = point.x +1;  
      	int y = point.y + element.getSize().getHeight() - 1;

      	TouchAction action = new TouchAction(driver);
	    action.press(PointOption.point(x , y))
	    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
	    .moveTo(PointOption.point( x,y))
	    .release()
	    .perform();
	   
}
	   
	   public static void UninstallApp() {
		   
			driver.removeApp("com.eposaudio.eposconnect");
		   
	   }
	   
}



