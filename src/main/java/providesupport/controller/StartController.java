package providesupport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import providesupport.manager.ManagerCheckingAddWebsiteData;
import providesupport.model.WebsiteData;
import providesupport.model.WebsiteInfo;
import providesupport.service.WebsiteDataService;
import providesupport.sheduller.ScheduleManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import providesupport.test.test.quantum.configuration.AppStaticVariableSettings;


@Controller
@RestController
public class StartController {

    @Autowired
    private WebsiteDataService dataService;
    ManagerCheckingAddWebsiteData dataChecking = new ManagerCheckingAddWebsiteData();

    @Autowired
    TaskScheduler taskScheduler;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView start() throws IOException {



        ModelAndView modelAndView = new ModelAndView("test");
        return modelAndView;
    }


    @RequestMapping(name = "/createWebsite", method = RequestMethod.POST)
    public ModelAndView createWebsiteData(@RequestParam(value = "url") String url,
                                          @RequestParam(value = "time") int periodTime,
                                          @RequestParam(value = "time_ok") Long responseOK,
                                          @RequestParam(value = "time_warning") Long responseWARNING,
                                          @RequestParam(value = "time_critical") Long responseCRITICAL,
                                          @RequestParam(value = "min_byte") int byteMin,
                                          @RequestParam(value = "max_byte") int byteMax,
                                          @RequestParam(value = "waitingResponseCode") int waitingResponseCode) throws IOException {


        ModelAndView modelAndView = new ModelAndView("redirect:/websitedatalist");

        String activity = "Activ";
        String status = null;
        Date dateTime = new Date();
        String dataStr = dateTime.toString();

        dataChecking = new ManagerCheckingAddWebsiteData(url, responseOK, responseWARNING,
                responseCRITICAL, byteMin, byteMax,
                waitingResponseCode);

        try {

            dataChecking.checkingUrl();

            String statusR = dataChecking.getStatus();
            WebsiteData data = new WebsiteData(url, periodTime, responseOK, responseWARNING, responseCRITICAL,
                    statusR, byteMin, byteMax, activity, waitingResponseCode, new ArrayList<>());
            dataService.createWebsite(data);
//            ------------------------------------Web-Info

            String content = dataChecking.getContentStr();

            List<WebsiteInfo> websiteInfoList = data.getWebsiteInfos();
            websiteInfoList.add(new WebsiteInfo(data, url, statusR, dataStr, content, "Provider created"));
            data.setWebsiteInfos(websiteInfoList);
            dataService.updateWebsiteData(data);



//            taskScheduler.scheduleAtFixedRate(new TaskRunnable(data.getId(), data.getUrl(), data.getResponseOK()
//                    , data.getResponseWARNING(), data.getResponseCRITICAL(),
//                    data.getByteMin(), data.getByteMax(), data.getStatus(), data.getwaitingResponseCode() ,dataService),  periodTime);

        } catch (NullPointerException e) {
            System.out.println("Одно из значений не заполнено пользователем" + e);
        } finally {
            return modelAndView;
        }
//        return modelAndView;
    }


    @RequestMapping(value = "/websitedatalist", method = RequestMethod.GET)
    public ModelAndView websiteDataList() {
        ModelAndView modelAndView = new ModelAndView("assigment_website");

        modelAndView.addObject("websiteBy", dataService.websiteDataList());


        return modelAndView;
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    private ModelAndView removeWebsiteData(@RequestParam("websitedata_id") Long websiteDataId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/websitedatalist");
        dataService.remoweWebsiteData(websiteDataId);
        System.out.println("remove");

       ScheduleManager.getInstance().cancelTask(websiteDataId);

        return modelAndView;
    }


}
