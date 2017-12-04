package providesupport.dao;

import providesupport.model.WebsiteData;

import java.util.List;

public interface WebsiteDataDao {

    Long createWebsite(WebsiteData data);
    List websiteDataList();
    public void updateWebsiteData(WebsiteData data);
    public void removeWebsite(Long id);
    public WebsiteData getWebsiteDataById(Long id);
}
