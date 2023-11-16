package com.SpurCumber.Utils;

import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZapUtil {

    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = "mclr3li64hs7e561emst17gjj0";
    private static final String ZAP_ADDRESS = "localhost";
    // private static final String TARGET = "https://www.calculator.net/";
    //private static final String TARGET = "https://reqres.in/api/users/1";
    private static final String TARGET = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
    // private static final String TARGET = "http://field-release-iceberg.stag.kyndi.com:3001/amber/v1/engines/ner/jobs/becdd9fd-b3c2-41e5-9857-ffb6b9f34098/status";
    private static final String REPORT_PATH = "test-output/ZAP_Report.html";
    //public static String _target = util.getPropValues("ZAP_app_URL");
    //public static ClientApi _api = new ClientApi(util.getPropValues("ZAP_host"), Integer.parseInt(util.getPropValues("ZAP_port")), util.getPropValues("ZAP_API_KEY"));
    private static final ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
    private final ConfigUtil util = new ConfigUtil();

    @Test
    public static void ActiveScan(String TARGET) {

        try {
            // TODO : explore the app (Spider, etc) before using the Active Scan API, Refer the explore section
            System.out.println("Active Scanning target : " + TARGET);
            ApiResponse resp = api.ascan.scan(TARGET, "True", "False", null, null, null);
            String scanid;
            int progress;

            // The scan now returns a scan id to support concurrent scanning
            scanid = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.ascan.status(scanid)).getValue());
                System.out.println("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }

            System.out.println("Active Scan complete");
            // Print vulnerabilities found by the scanning
            System.out.println("Alerts:");
            System.out.println(new String(api.core.xmlreport(), StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void Spider(String TARGET) {
        try {
            // Start spidering the target
            System.out.println("Spidering target : " + TARGET);
            ApiResponse resp = api.spider.scan(TARGET, null, null, null, null);
            String scanID;
            int progress;

            // The scan returns a scan id to support concurrent scanning
            scanID = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000);
                progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanID)).getValue());
                System.out.println("Spider progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            System.out.println("Spider completed");
            // If required post process the spider results
            List<ApiResponse> spiderResults = ((ApiResponseList) api.spider.results(scanID)).getItems();

            // TODO: Explore the Application more with Ajax Spider or Start scanning the application for vulnerabilities

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void PassiveScan() {
        int numberOfRecords;

        try {
            // TODO : explore the app (Spider, etc) before using the Passive Scan API, Refer the explore section for details
            // Loop until the passive scan has finished
            while (true) {
                Thread.sleep(2000);
                api.pscan.recordsToScan();
                numberOfRecords = Integer.parseInt(((ApiResponseElement) api.pscan.recordsToScan()).getValue());
                System.out.println("Number of records left for scanning : " + numberOfRecords);
                if (numberOfRecords == 0) {
                    break;
                }
            }
            System.out.println("Passive Scan completed");

            // Print Passive scan results/alerts
            System.out.println("Alerts:");
            System.out.println(new String(api.core.xmlreport(), StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void StartZAP() {
        Process p;
        try {

            List<String> cmdList = new ArrayList<String>();
            // adding command and args to the list
            cmdList.add("sh");
            cmdList.add("/Applications/OWASP ZAP.app/Contents/Java/zap.sh");
            cmdList.add("-daemon");
            cmdList.add("-config api.key=mclr3li64hs7e561emst17gjj0");
            ProcessBuilder pb = new ProcessBuilder(cmdList);
            p = pb.start();
            p.waitFor(30, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    public static void StartNewSession() throws IOException {
        URL obj = new URL("http://zap/JSON/core/action/newSession/");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }

    public static void ShutdownZAP() {
        try {
            ApiResponse resp = api.core.shutdown();
            if ("OK" == resp.getName())
                System.out.println("ZAP shutdown success ");
        } catch (ClientApiException e) {
            e.printStackTrace();
        }

    }

    @Test
    public static void ScanTest() throws InterruptedException, IOException {
        //ZapUtil.StartZAPDaemon();

        //String spiderScanId = StartSpidering(_target);
        // PollTheSpiderTillCompletion(spiderScanId);

        // StartPassiveScanning();
        StartZAP();
        //Thread.sleep(20000);

        //StartNewSession();

        // Spider();

        Thread.sleep(30000);

        // ActiveScan();

        ShutdownZAP();
        //PassiveScan();
        //WriteXmlReport(reportFileName);
        WriteHtmlReport(REPORT_PATH);
        //PrintAlertsToConsole();

        // ShutdownZAP();

    }

    @SuppressWarnings("deprecation")
	public static void WriteHtmlReport(String reportFileName) {

        try (FileOutputStream stream = new FileOutputStream(reportFileName)) {
            stream.write(api.core.htmlreport());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClientApiException e) {
            e.printStackTrace();
        }
    }


}
