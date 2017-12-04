package providesupport.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "website_info")
public class WebsiteInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "web_data_id")
    private WebsiteData websiteData;

    @Column(name = "url_id")
    private String url;

    @Column(name = "status_id")
    private String status;

    @Column(name = "data_id")
    private String data;

    @Column(name = "content_id")
    private String content;

    @Column(name = "message")
    private String message;


    public WebsiteInfo() {
    }

    public WebsiteInfo(WebsiteData websiteData, String url, String status, String data, String message) {
        this.websiteData = websiteData;
        this.url = url;
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public WebsiteInfo(WebsiteData websiteData, String url, String status, String data, String content, String message) {
        this.websiteData = websiteData;
        this.url = url;
        this.status = status;
        this.data = data;
        this.content = content;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WebsiteData getWebsiteData() {
        return websiteData;
    }

    public void setWebsiteData(WebsiteData websiteData) {
        this.websiteData = websiteData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebsiteInfo{" +
                "id=" + id +
                ", websiteData=" + websiteData +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", data='" + data + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
