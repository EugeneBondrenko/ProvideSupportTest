package providesupport.sheduller;

import providesupport.manager.ManagerCheckingURLs;
import providesupport.service.WebsiteDataService;

import java.io.IOException;


public class TaskRunnable implements Runnable {


     ManagerCheckingURLs managerControllerURLs = new ManagerCheckingURLs();



    public Long id;
    public String url;
    public Long responseOK;
    public Long responseWARNING;
    public Long responseCRITICAL;
    public int byteMin;
    public int byteMax;
    public String status;
    public int resCode;
    WebsiteDataService web;

    public TaskRunnable(Long dataId, String s, Long responseOK, Long responseWARNING,
                        Long responseCRITICAL, int byteMin, int byteMax, String status, int resCode, WebsiteDataService web) {
        this.id = dataId;
        this.url = s;
        this.responseOK = responseOK;
        this.responseWARNING = responseWARNING;
        this.responseCRITICAL = responseCRITICAL;
        this.byteMin = byteMin;
        this.byteMax = byteMax;
        this.status = status;
        this.resCode = resCode;
        this.web = web;
    }



    @Override
    public void run() {
//        try {
//            managerControllerURLs.updateUrl();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("The data was not changed by the given URL.");
//        }
        try {
            managerControllerURLs.addData(id, url, responseOK, responseWARNING,
                    responseCRITICAL, byteMin, byteMax, status, resCode, web);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sheduller could not transfer data for verification.");
        }
        try {
            managerControllerURLs.chekingURL();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The data was sent for verification, the method did not complete the check.");
        }
    }
}
