package providesupport.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import providesupport.model.WebsiteData;
import providesupport.service.WebsiteDataService;

import java.io.IOException;

@Controller
public class ManagerUpdateDataByUrlId {


    @Autowired
    private WebsiteDataService dataService;


    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/edit-website/{website-id}/", method = RequestMethod.GET)
    public ModelAndView editWebsiteData(@PathVariable("website-id") Long id) throws IOException {

//        @RequestMapping(value = "/edit-website", method = RequestMethod.POST)
//        private ModelAndView editWebsiteData(@RequestParam("website-id") Long id){

        ModelAndView modelAndView = new ModelAndView("redirect:/websitedatalist");
        WebsiteData data = dataService.getWebsiteDataById(id);
        System.out.println(data.getId());
        System.out.println(data.getActivity());

        String status = data.getActivity();
        String activ = "Activ";
        String noActiv = "No activ";
        try {
            data.getActivity().equals(noActiv);
            data.setActivity(activ);
            dataService.updateWebsiteData(data);
        } catch (NullPointerException e) {
            System.out.println("не удалось перезеписать таблицу");
        }
        if (status.equals(activ)) {
            data.setActivity(noActiv);
            dataService.updateWebsiteData(data);
        }
        return modelAndView;
    }
    //@RequestMapping(value = "/edit-website/{website-id}/", method = RequestMethod.GET)
    public void upd(){

    }



//
//    @RequestMapping(name = "/test", method = RequestMethod.GET)
//    public ModelAndView test() throws IOException {
//        StopWatch watch = new StopWatch();
//
//        watch.start();
//
//        String url = "http://www.google.com/search?q=mkyong";
//
////        String url = "ghgdhfjhdhs";
//
//
////        HttpClient client = new HttpClient();
//       // HttpMethod method = new HeadMethod("http://stackoverflow.com/");
//
//        URL obj = null;
//
//        ModelAndView modelAndView = new ModelAndView("redirect:/websitedatalist");
//
//        try {
//            obj = new URL(url);
//        } catch (MalformedURLException e) {
//            System.out.println("False:  " + e);
//            return modelAndView;
//        } finally {
//            System.out.println("Такого URL не существует");
//        }
//        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//        watch.stop();
//        System.out.println("Time connection: " + watch.getLastTaskTimeMillis());

//        // optional default is GET
//        con.setRequestMethod("GET");
//
//        //add request header
//        con.setRequestProperty("User-Agent", USER_AGENT);
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//        System.out.println(response.toString().getBytes());
//
//        int ziro_elements = 0;
//        int first_elements = 0;
//        int sum_bytes = 0;
//
//
//        String s = "UTF-8";
//
//        String set = response.toString();
//        if (set.contains(s)) {
//            System.out.println(s);
//        } else {
//            System.out.println("No signale");
//        }
//
//
//        final byte[] byte_line = response.toString().getBytes();
//        try {
//            for (int i = 0; i <= byte_line.length; i++) {
//
//                System.out.print(byte_line[i] + ",  ");
//
//                if (byte_line[i] >= -128 & byte_line[i] <= 127) {
//                    first_elements = first_elements + 1;
//                    sum_bytes = sum_bytes + 1;
//                }
//            }
//        } finally {
//
//            System.out.println();
//            System.out.println("ziro: " + ziro_elements);
//            System.out.println("First elements: " + first_elements);
//            System.out.println("Sum bytes: " + sum_bytes);
//
//
//            List<WebsiteData> websiteData = dataService.websiteDataList();
//            for (WebsiteData web : websiteData) {
//                System.out.println(web.getUrl());
//
//                return modelAndView;
//            }
//
//        }

//        return modelAndView;

//    }

}
