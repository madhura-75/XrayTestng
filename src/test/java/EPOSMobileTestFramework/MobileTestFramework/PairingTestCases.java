package EPOSMobileTestFramework.MobileTestFramework;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
//import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PairingTestCases extends EPOSBaseClass{
	
	@BeforeTest
	//To stop appium server for successfull execution
	public void killAllNodes() throws IOException, InterruptedException
	{
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);	
	}
	
	
	
	@Test (priority = 0)
	@XrayTest(key = "CALC-0")
    @Requirement(key = "CALC-0000")
	public void TES_31947() throws InterruptedException, IOException {
		
	
		
		
		
		
		service = startAppiumServer();
		
		
		
		
	
		
		//Fetch the appName from GlobalProperties file
		AndroidDriver<AndroidElement> driver = Capabilities("EPOSMobileApp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		
	
		
		//AndroidDriver<AndroidElement> driver = Capabilities("EPOSMobileApp");
		LaunchScreen ls = new LaunchScreen(driver);
		DeviceScanScreen dss = new DeviceScanScreen(driver);
		
		CommonObjects cObj = new CommonObjects(driver);
	}
	@Test (priority = 1)
    @XrayTest(key = "CALC-1", labels="launch")
    @Requirement(key = "CALC-0001")
	public void TES_31953() throws InterruptedException, IOException {
			
			
			LaunchScreen ls = new LaunchScreen(driver);
			DeviceScanScreen dss = new DeviceScanScreen(driver);
			
			CommonObjects cObj = new CommonObjects(driver);
			
			
		Thread.sleep(6000);
		ls.termCondObj.click();
		ls.allowDataObj.click();
		
		ls.contObj.click();
		Thread.sleep(3000);
		
	try {
		if(ls.grntLocObj.isDisplayed()) {
			ls.grntLocObj.click();
			ls.allowObj.click();
		}
	} catch (Exception e){
		    ls.GrantPermissionObj.click();
			ls.AllowOnceObj.click();
			
		}
	
	
		
		
		ls.contObj.click();
	    Thread.sleep(5000);
	  
	}
	
	@Test(priority = 2)
	 @XrayTest(key = "CALC_2")
    @Requirement(key = "CALC_0002")
    public void TES_31970() throws IOException, InterruptedException {
		
		/*service = startAppiumServer();
		
		//Fetch the appName from GlobalProperties file
		AndroidDriver<AndroidElement> driver = Capabilities("EPOSMobileApp");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//WebDriverWait wait = new WebDriverWait(driver, 10);*/
		
		LaunchScreen ls = new LaunchScreen(driver);
		DeviceScanScreen dss = new DeviceScanScreen(driver);
		
		CommonObjects cObj = new CommonObjects(driver);
	
		Thread.sleep(20000);
	    String headset=getAdapt660Headset();
		WebElement Adapt660=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+headset+"')]"));
		
		System.out.println(Adapt660);
		Thread.sleep(5000);
    	
		Adapt660.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	   
	   
		
		
	  
	    Thread.sleep(4000);
	    try {
	    	if(dss.deviceConfirm.isDisplayed()) {
	    		 getHeadsetVal();
	    		 Thread.sleep(5000);
	    		dss.deviceConfirm.click();	
	    		Thread.sleep(1000);
	    	 dss.devicePair.click();
	    }
	    	
	    	 }
	   
	    catch(Exception e) {
	    	try {
	    		driver.openNotifications();
	    		if(dss.pairingRequest.isDisplayed()) {
	    			dss.pairingRequest.click();
	    			dss.devicePair.click();
	    		}
	    	}
	    	catch(Exception a) {
	    		
	    		System.out.println("Device paired");
	    	}
	    
	    	{
	    		 driver.pressKey(new KeyEvent(AndroidKey.BACK));
		    	System.out.println("Device is already paired");
	    	
	    	}
	    		
	    	
	    }
		
		 Thread.sleep(5000);
		
}	 
	@Test (priority = 3)
	 @XrayTest(key = "CALC-3")
    @Requirement(key = "CALC-0003")
    public void TES_31967() throws IOException, InterruptedException {
	
	
	
	LaunchScreen ls = new LaunchScreen(driver);
	DeviceScanScreen dss = new DeviceScanScreen(driver);
	
	CommonObjects cObj = new CommonObjects(driver);
		 
		try {
		    if(dss.voiceAssistObj.isDisplayed() ) {
		    	//dss.voiceAssistCloseObj.click();
			 cObj.backBtn.click();
		    }
		}
		catch(Exception e) {
			System.out.println("Voice Assistant not found");
		}
		
      Thread.sleep(4000);
		
		try {
		    if(dss.HeadsetUpdate.isDisplayed() ) {
		    	//dss.voiceAssistCloseObj.click();
			 dss.Later.click();
		    }
		}
		catch(Exception e) {
			System.out.println("Headset is up to date");
		}
		
		Thread.sleep(4000);
		
		AssertJUnit.assertEquals(true, cObj.HomeTabObj.isDisplayed());
		cObj.HomeTabObj.click();
		
		Thread.sleep(6000);
	}
	
	@Test(priority = 4)
	@XrayTest(key = "CALC-4")
    @Requirement(key = "CALC-0004")
	 public void TES_33070() throws IOException, InterruptedException {	
		
			 HomeScreen hs=new HomeScreen(driver);
		
			Assert.assertEquals(true, hs.deviceImageObj.isDisplayed());
			Assert.assertEquals(true, hs.deviceTitleObj.isDisplayed(),"Device title not found");
	            Thread.sleep(5000);
		 }
	
	
	@Test(priority = 5)
	@XrayTest(key = "CALC-5")
    @Requirement(key = "CALC-0005")
		public void	TES_33080() throws IOException, InterruptedException {
			
			 HomeScreen hs=new HomeScreen(driver);
			Assert.assertEquals(true, hs.batteryObj.isDisplayed(), "Battery percentage not found");
			Assert.assertEquals(true, hs.batIconObj.isDisplayed(),"Battery icon is not found");
			Thread.sleep(5000);
		}
	
	
	@Test(priority = 6)
	  @XrayTest(key = "CALC-6")
    @Requirement(key = "CALC-0006")
		public void	TES_33079() throws IOException, InterruptedException {
			
			 HomeScreen hs=new HomeScreen(driver);
			 Assert.assertEquals(true, hs.batteryObj.isDisplayed(), "Battery percentage not found");
			Assert.assertEquals(true, hs.batIconObj.isDisplayed(),"Battery icon is not found");
				Thread.sleep(5000);
		}	
	

	@Test(priority = 7)
	 @XrayTest(key = "CALC-7")
    @Requirement(key = "CALC-0007")
		public void	TES_33071() throws IOException, InterruptedException {
			
			CommonObjects cObj= new CommonObjects(driver);
			
			Thread.sleep(4000);
			 Assert.assertEquals(true, cObj.HomeTabObj.isDisplayed(), "Home screen Tab not found");
			Assert.assertEquals(true, cObj.ConnectTabObj.isDisplayed(),"Connect screen Tab not found");
			
			Assert.assertEquals(true, cObj.DeviceTabObj.isDisplayed(),"Device settingsTab not found");
			Assert.assertEquals(true, cObj.AppTabObj.isDisplayed(),"App settings Tab not found");
		      Thread.sleep(5000);
		
		}
		

	       
	        @Test(priority = 8)
	        @XrayTest(key = "CALC-8")
	        @Requirement(key = "CALC-0008")
		 	public void TES_33069() throws IOException, InterruptedException {	
				HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver);
				
				Thread.sleep(4000);
				
				Assert.assertEquals(true, hs.NoiseObj.isDisplayed(),"Noise cancellation not found");
				Assert.assertEquals(true, hs.NoiseStateObj.isDisplayed(),"Noise cancellation status not found");
				Assert.assertEquals(true, hs.NoiseBtnObj.isDisplayed(),"Noise cancellation button not found");
				
				Thread.sleep(4000);
				Assert.assertEquals(true, hs.AcousticObj.isDisplayed(),"Acoustic object is not found");
				Assert.assertEquals(true, hs.AcousticStateObj.isDisplayed(),"Acoustic state is not found");
				Assert.assertEquals(true, hs.AcousticBtnObj.isDisplayed(),"Acoustic button is not found");
	             Thread.sleep(5000);
	        
	        }
	        @Test(priority = 9)
	        @XrayTest(key = "CALC-9")
	        @Requirement(key = "CALC-0009")
		 	public void TES_33072() throws IOException, InterruptedException {  
	        	HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver);
				
	        	hs.NoiseBtnObj.click();
	    		Assert.assertEquals(true, nc.noiseTitleObj.isDisplayed(),"Noise cancellation title not found");
	    		Assert.assertEquals(true, nc.AncTitleObj.isDisplayed(),"Active noise cancellation state is not found");
	    		Assert.assertEquals(true, nc.AncDescObj.isDisplayed(),"Active noise decription is not found");
	    		Assert.assertEquals(true, nc.AncSeekObj.isDisplayed(),"Active noise cancellation slide bar not found");
	    		//cObj.closeBtn.click();
	        	Thread.sleep(5000);
	        }
	        @Test(priority = 10)
	        @XrayTest(key = "CALC-10")
	        @Requirement(key = "CALC-0010")
		 	public void TES_33075() throws IOException, InterruptedException {  
	        	HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver);
				
	        	//hs.NoiseBtnObj.click();
	    		Assert.assertEquals(true, nc.noiseTitleObj.isDisplayed(),"Noise cancellation title not found");
	    		Assert.assertEquals(true, nc.AncTitleObj.isDisplayed(),"Active noise cancellation state is not found");
	    		Assert.assertEquals(true, nc.AncDescObj.isDisplayed(),"Active noise decription is not found");
	    		Assert.assertEquals(true, nc.AncSeekObj.isDisplayed(),"Active noise cancellation slide bar not found");
	    		//cObj.closeBtn.click();
	        	Thread.sleep(5000);
	        }
	        @Test(priority = 11)
	        @XrayTest(key = "CALC-11")
	        @Requirement(key = "CALC-0011")
		 	public void TES_33074() throws IOException, InterruptedException {  
	        	HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver);
				
	        	//hs.NoiseBtnObj.click();
	    		Assert.assertEquals(true, nc.noiseTitleObj.isDisplayed(),"Noise cancellation title not found");
	    		Assert.assertEquals(true, nc.AncTitleObj.isDisplayed(),"Active noise cancellation state is not found");
	    		Assert.assertEquals(true, nc.AncDescObj.isDisplayed(),"Active noise decription is not found");
	    		Assert.assertEquals(true, nc.AncSeekObj.isDisplayed(),"Active noise cancellation slide bar not found");
	    		//cObj.closeBtn.click();
	        	Thread.sleep(5000);
	        }
	        @Test(priority = 12)
	        @XrayTest(key = "CALC-12")
	        @Requirement(key = "CALC-0012")
		 	public void TES_33073() throws IOException, InterruptedException {  
	        	HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver);
				
	        	//hs.NoiseBtnObj.click();
	    		Assert.assertEquals(true, nc.noiseTitleObj.isDisplayed(),"Noise cancellation title not found");
	    		Assert.assertEquals(true, nc.AncTitleObj.isDisplayed(),"Active noise cancellation state is not found");
	    		Assert.assertEquals(true, nc.AncDescObj.isDisplayed(),"Active noise decription is not found");
	    		Assert.assertEquals(true, nc.AncSeekObj.isDisplayed(),"Active noise cancellation slide bar not found");
	    		//cObj.closeBtn.click();
	        
	        
	        	cObj.closeBtn.click();
	        	Thread.sleep(5000);
	        	
	        }
	        @Test(priority = 13)
	        @XrayTest(key = "CALC-13")
	        @Requirement(key = "CALC-0013")
		 	public void TES_33077() throws IOException, InterruptedException {  
	        	HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver);
				
				Assert.assertEquals(true, hs.AcousticObj.isDisplayed(),"Acoustic object is not found");
				Assert.assertEquals(true, hs.AcousticStateObj.isDisplayed(),"Acoustic state is not found");
				Assert.assertEquals(true, hs.AcousticBtnObj.isDisplayed(),"Acoustic button is not found");
	             Thread.sleep(5000);
	        
	        }
	        
	        
	        @Test(priority = 14)
	        @XrayTest(key = "CALC-14")
	        @Requirement(key = "CALC-0014")
		 	public void TES_33076() throws IOException, InterruptedException {  
	        	HomeScreen hs=new HomeScreen(driver);
				CommonObjects cObj= new CommonObjects(driver);
				NoiseCancellationScreen nc=new NoiseCancellationScreen(driver); 
				ModesScreen ms=new ModesScreen(driver);
				
				hs.AcousticBtnObj.click();
				
				Assert.assertEquals(true, ms.modesTitleObj.isDisplayed(),"Acoustic modes title not found");
				
				
				Assert.assertEquals(true, ms.AcousObj.get(0).isDisplayed(),"Neutral object is not found");
				swipeLeft();
				
				Assert.assertEquals(true, ms.AcousObj.get(0).isDisplayed(),"Speech object is not found");
				ms.speechinlineObj.isDisplayed();
				swipeLeft();
				
				Assert.assertEquals(true, ms.AcousObj.get(0).isDisplayed(),"Club object is not found");
				ms.clubinlineObj.isDisplayed();
				swipeLeft();
				
				Assert.assertEquals(true, ms.AcousObj.get(0).isDisplayed(),"Movie object is not found");
				ms.movieinlineObj.isDisplayed();
				swipeLeft();
				
				Assert.assertEquals(true, ms.AcousObj.get(0).isDisplayed(),"Director object is not found");
				Assert.assertEquals(true, ms.customsettingObj.isDisplayed(),"custom object is not found");
				ms.adjustmentObj.click();
				ms.closeObj.click();
			   
				for(int i=0;i<4;i++) {
					swipeRight();
				}
				
			
				cObj.closeBtn.click();
				
				Thread.sleep(3000);
				cObj.HomeTabObj.click();
	        
		service.stop();
		
		 }
	
	}
