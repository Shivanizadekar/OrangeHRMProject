package com.Scenario2;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/OrangeHRMReport2.html");
            reporter.config().setReportName("OrangeHRM Login Test Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Shivani Zadekar");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
