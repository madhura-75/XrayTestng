package resources;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentReporterNG implements IReporter {

	
	  private ExtentReports extent;
	 
    @SuppressWarnings("unchecked")
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        JSONArray results = new JSONArray();
        suites.forEach(element->{
            results.add(createSuiteJsonObject(element));
        });
        try (FileWriter file = new FileWriter(System.getProperty("user.dir")+"\\Reports\\jsonReport.json")) {
            file.write(results.toJSONString());
        } catch (IOException e) {
            //handle
        }

    }

    @SuppressWarnings("unchecked")
    public JSONObject createSuiteJsonObject(ISuite suite) {
        JSONObject result = new JSONObject();
        JSONArray passedMethods = new JSONArray();
        JSONArray failedMethods = new JSONArray();
        JSONArray skippedMethods = new JSONArray();
        suite.getResults().entrySet().forEach(element -> {
            ITestContext context = element.getValue().getTestContext();
            passedMethods.addAll(createResultJsonArray(context.getPassedTests().getAllResults()));
            failedMethods.addAll(createResultJsonArray(context.getFailedTests().getAllResults()));
            skippedMethods.addAll(createResultJsonArray(context.getSkippedTests().getAllResults()));
        });
        result.put("name", suite.getName());
        result.put("passed", passedMethods);
        result.put("failed", failedMethods);
        result.put("skipped", skippedMethods);
        return result;
    }

    @SuppressWarnings("unchecked")
    public JSONArray createResultJsonArray(Set<ITestResult> results) {
        JSONArray result = new JSONArray();
        results.forEach(element ->{
            JSONObject currentJsonResult = new JSONObject();
            currentJsonResult.put("name", element.getName());
            result.add(currentJsonResult);
        });
        return result;
    }

    private void buildTestNodes(IResultMap tests, Status status) {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.createTest(result.getMethod().getMethodName());
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
 
                test.log(status, message);

            }
        }
    }
 
    private Date getTime(long millis) {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();   
        
    }


}
