package providesupport.dao;

import org.hibernate.Session;
import providesupport.model.WebsiteData;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WebsiteDataDaoImpl implements WebsiteDataDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Long createWebsite(WebsiteData data) {
        System.out.println("Сработал Dao ++++++++++++++++");
        return (Long) factory.getCurrentSession().save(data);
    }

    @Override
    public List <WebsiteData> websiteDataList() {
        List <WebsiteData> websiteData = (List <WebsiteData>) factory.getCurrentSession()
                .createQuery("FROM WebsiteData").list();
        return websiteData;
    }

    @Override
    public void updateWebsiteData(WebsiteData data) {
        factory.getCurrentSession().update(data);
    }

    @Override
    public void removeWebsite(Long id) {
        Session session = factory.getCurrentSession();
        WebsiteData websiteData = session.get(WebsiteData.class, id);
        if (websiteData != null){
            session.delete(websiteData);
        }
    }

    @Override
    public WebsiteData getWebsiteDataById(Long id) {
        System.out.println("Дао сработал +++++++++++++++++++++");
        Session session = factory.getCurrentSession();
        WebsiteData data = session.get(WebsiteData.class, id);

        return data;
    }
}
