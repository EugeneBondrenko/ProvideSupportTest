package providesupport.manager;

import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static org.jboss.netty.handler.codec.rtsp.RtspHeaders.Names.USER_AGENT;

public class ManagerCheckingAddWebsiteData {

//    @Autowired
//    private WebsiteDataService service;

    String status = null;
    String contentStr = null;

    String url;

    Long time_ok;
    Long time_warning;
    Long time_critical;

    int min_byte;
    int max_byte;

    int waitingResponseCode;

    public String getStatus() {return status;}
    public String getContentStr() {return contentStr;}

    public ManagerCheckingAddWebsiteData() {}

    public ManagerCheckingAddWebsiteData(String url, Long responseOK, Long responseWARNING, Long responseCRITICAL,
                                         int byteMin, int byteMax, int waitingResponseCode) {
        this.url = url;
        this.time_ok = responseOK * 100;
        this.time_warning = responseWARNING * 1000;
        this.time_critical = responseCRITICAL * 10000;
        this.min_byte = byteMin;
        this.max_byte = byteMax;
        this.waitingResponseCode = waitingResponseCode;
    }

    public String checkingUrl() throws IOException {

        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(url);

        URL obj = new URL(url);

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // optional default is GET
        try {
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
//If
        if (responseCode != waitingResponseCode) {
            status = "CRITICAL";
        }
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String inputLine;
        StringBuffer response = new StringBuffer();

        try {
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        watch.stop();
        long time = watch.getLastTaskTimeMillis();
        System.out.println("Time connection: " + time);
        if (time <= time_ok & status == null) {
            System.out.println("Time OK");
            status = "OK";
        } else if (time <= time_warning & !status.equals("CRITICAL")) {
            System.out.println("Time WARNING");
            status = "WARNING";
        } else if (time >= time_critical) {
            System.out.println("Time CRITICAL");
            status = "CRITICAL";
        }
//        System.out.println("Time connection: " + watch.getLastTaskTimeMillis());
        int first_elements = 0;
        int sum_bytes = 0;

        contentStr = response.toString();

        contentStr = contentStr.substring(0, 5000);
        System.out.println("Длина строки     " + contentStr.length());

        final byte[] byte_line = response.toString().getBytes();

        try {
            for (int i = 0; i <= byte_line.length; i++) {

                if (byte_line[i] >= -128 & byte_line[i] <= 127) {
                    first_elements = first_elements + 1;
                    sum_bytes = sum_bytes + 1;
                }
            }
        } finally {
            System.out.println("First elements: " + first_elements);
            System.out.println("Sum bytes: " + sum_bytes);
            System.out.println("-----------------------------------------------------");
            if (sum_bytes <= min_byte || sum_bytes >= max_byte) {
                System.out.println(" Byte CRITICAL");
                status = "CRITICAL";
            }

            return null;
        }
    }



}




