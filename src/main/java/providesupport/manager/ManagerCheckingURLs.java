package providesupport.manager;

//import org.apache.commons.lang.time.StopWatch;

//import org.apache.commons.lang.time.StopWatch;

//import org.hibernate.validator.constraints.URL;

import org.springframework.util.StopWatch;
import providesupport.model.WebsiteData;
import providesupport.model.WebsiteInfo;
import providesupport.service.WebsiteDataService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import static org.jboss.netty.handler.codec.rtsp.RtspHeaders.Names.USER_AGENT;

public class ManagerCheckingURLs {

    WebsiteData websiteData = new WebsiteData();
    WebsiteDataService web;

    long ID;
    String url;
    int resCode;
    long timeOk;
    long timeWarning;
    long timeCritical;
    int byteMin;
    int byteMax;
    String statusData ;

    String infoLogger = null;
    String statusReal = null;
    public void addData(Long id, String url, Long responseOK, Long responseWARNING,
                        Long responseCRITICAL, int byteMin, int byteMax, String status, int resCode, WebsiteDataService web) throws IOException {
        this.ID = id;
        this.url = url;
        this.resCode = resCode;
        this.timeOk = responseOK;
        this.timeWarning = responseWARNING;
        this.timeCritical = responseCRITICAL;
        this.byteMin = byteMin;
        this.byteMax = byteMax;
        this.statusData = status;
        this.web = web;
        System.out.println("Out Run");
    }

    public void chekingURL() throws IOException {

            System.out.println("_______________Status  " +  statusData);
            System.out.println("URL :   " + url + "    ID  : " + ID);

            StopWatch watch = new StopWatch();
            watch.start();

            URL obj = new URL(url);

            HttpURLConnection con = null;
           // try {
                con = (HttpURLConnection) obj.openConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            // optional default is GET
         //   try {
                con.setRequestMethod("GET");
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//            }
            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

//if
            if (responseCode != resCode) {
                statusReal = "CRITICAL";
                infoLogger = "Response code does not match the specified parameters. Status - Critical";
            }
            System.out.println("Response Code : " + responseCode + " --Status" + statusReal + " --ID: " + ID);

            BufferedReader in = null;
          //  try {
                in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            String inputLine;
            StringBuffer response = new StringBuffer();

           // try {
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
           // try {
                in.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            watch.stop();

            long time = watch.getLastTaskTimeMillis();
//            System.out.println("Time connection: " + time);
            if (time <= timeOk && !statusReal.equals("CRITICAL")) {
                statusReal = "OK";
                System.out.println("Real time: (" + time +
                        ")          Time data: (" + timeOk + ")          Status: (" + statusReal
                        + ")          Id: (" + ID + ")");
                infoLogger = infoLogger + "Specified time status OK";
            } else if (time <= timeWarning && !statusReal.equals("CRITICAL")) {
                statusReal = "WARNING";
                System.out.println("Real time: (" + time +
                        ")          Time data: (" + timeWarning + ")          Status: (" + statusReal
                        + ")          Id: (" + ID + ")");
                infoLogger = infoLogger + "Specified time status WARNING";
            } else if (time >= timeCritical) {
                statusReal = "CRITICAL";
                System.out.println("Real time: (" + time +
                        ")          Time data: (" + timeCritical + ")          Status: (" + statusReal
                        + ")          Id: (" + ID + ")");
                infoLogger = infoLogger + "Specified time status CRITICAL";
            }
//print result
//        System.out.println(response.toString());
//        System.out.println(response.toString().getBytes());
            int first_elements = 0;
            int sum_bytes = 0;

            String s = "UTF-8";
//        String set = response.toString();
//        if (set.contains(s)) {
//            System.out.println(s);
//        } else {
//            System.out.println("No signale");
//        }
            final byte[] byte_line = response.toString().getBytes();

            try {
                for (int i = 0; i <= byte_line.length; i++) {
//                System.out.print(byte_line[i] + ",  ");
                    if (byte_line[i] >= -128 & byte_line[i] <= 127) {
                        first_elements = first_elements + 1;
                        sum_bytes = sum_bytes + 1;
                    }
                }
            } finally {
//               System.out.println("First elements: " + first_elements);
                System.out.println("Sum bytes: " + sum_bytes);

                if (sum_bytes <= byteMin || sum_bytes >= byteMax) {
                    System.out.println("                                            Byte CRITICAL");
                    statusReal = "CRITICAL";
                    infoLogger = infoLogger + "The bytes of this contact exceed the specified parameters. Status - CRITICAL";
                }
                System.out.println("StatusReal                                     " + statusReal);
                System.out.println("StatusData                                     " + statusData);
                System.out.println("_________________________________________________________________");

                updateUrl();

               }
    }

    public void updateUrl()throws IOException {
if (!statusData.equals(statusReal)) {
    System.out.println("RENAME STATUS +++++++++++");
    Date dateTime = new Date();
    String dataStr = dateTime.toString();

    WebsiteData dataByURL = web.getWebsiteDataById(ID);
//        System.out.println("Статус: " + dataByURL.getStatus());

    dataByURL.setStatus(statusReal);
    web.updateWebsiteData(dataByURL);

    List<WebsiteInfo> websiteInfoList = dataByURL.getWebsiteInfos();
    websiteInfoList.add(new WebsiteInfo(dataByURL, "Previously created ", statusReal, dataStr, infoLogger));
    dataByURL.setWebsiteInfos(websiteInfoList);

    web.updateWebsiteData(dataByURL);
}
    }


}
