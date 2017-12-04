package providesupport.service;

import providesupport.model.WebsiteData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface WebsiteDataService {

    void createWebsite(WebsiteData data);
    List <WebsiteData> websiteDataList();
    void updateWebsiteData(WebsiteData data);
    void remoweWebsiteData(Long id);
    WebsiteData getWebsiteDataById(Long id);


}
