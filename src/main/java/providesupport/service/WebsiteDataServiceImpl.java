package providesupport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import providesupport.dao.WebsiteDataDao;
import providesupport.model.WebsiteData;

import java.util.List;

@Transactional
@Service("websiteDataService")
public class WebsiteDataServiceImpl implements WebsiteDataService {



    @Autowired
    private WebsiteDataDao dataDao;

    @Override
    public void createWebsite(WebsiteData data) {
        System.out.println("Сработал сервис+++++++++++++++++++++++");
        dataDao.createWebsite(data);
    }


    @Override
    public List<WebsiteData> websiteDataList() {
        List <WebsiteData> websiteData = dataDao.websiteDataList();

//        for (WebsiteData web: websiteData){
//            System.out.println(web);
//        }
        return websiteData;
    }


    @Override
    public void updateWebsiteData(WebsiteData data) {
        dataDao.updateWebsiteData(data);
    }


    @Override
    public void remoweWebsiteData(Long id) {
        dataDao.removeWebsite(id);
    }


    @Override
    public WebsiteData getWebsiteDataById(Long id) {
        System.out.println("Сервис сработал +++++++++++++++++++++");
        return dataDao.getWebsiteDataById(id);

    }



}
