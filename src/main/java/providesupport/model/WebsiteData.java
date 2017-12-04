package providesupport.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "website_data")
public class WebsiteData {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "period_of_time")
    private int periodTime;

    @Column(name = "response_time_OK")
    private Long responseOK;

    @Column(name = "response_time_WARNING")
    private Long responseWARNING;

    @Column(name = "response_time_CRITICAL")
    private Long responseCRITICAL;

    @Column(name = "addmisible_status")
    private String status;

    @Column(name = "byte_min")
    private int byteMin;

    @Column(name = "byte_max")
    private int byteMax;

    @Column(name = "activity")
    private String activity;

    @Column(name = "waitingResponseCode")
    private int waitingResponseCode;

    @OneToMany(mappedBy = "websiteData", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<WebsiteInfo> websiteInfos;


    public WebsiteData() {
    }

    public WebsiteData(String url, int periodTime, Long responseOK, Long responseWARNING, Long responseCRITICAL,
                       String status, int byteMin, int byteMax, String activity, int waitingResponseCode, List<WebsiteInfo> websiteInfos) {
        this.url = url;
        this.periodTime = periodTime;
        this.responseOK = responseOK;
        this.responseWARNING = responseWARNING;
        this.responseCRITICAL = responseCRITICAL;
        this.status = status;
        this.byteMin = byteMin;
        this.byteMax = byteMax;
        this.activity = activity;
        this.waitingResponseCode = waitingResponseCode;
        this.websiteInfos = websiteInfos;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(int periodTime) {
        this.periodTime = periodTime;
    }

    public Long getResponseOK() {
        return responseOK;
    }

    public void setResponseOK(Long responseOK) {
        this.responseOK = responseOK;
    }

    public Long getResponseWARNING() {
        return responseWARNING;
    }

    public void setResponseWARNING(Long responseWARNING) {
        this.responseWARNING = responseWARNING;
    }

    public Long getResponseCRITICAL() {
        return responseCRITICAL;
    }

    public void setResponseCRITICAL(Long responseCRITICAL) {
        this.responseCRITICAL = responseCRITICAL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getByteMin() {
        return byteMin;
    }

    public void setByteMin(int byteMin) {
        this.byteMin = byteMin;
    }

    public int getByteMax() {
        return byteMax;
    }

    public void setByteMax(int byteMax) {
        this.byteMax = byteMax;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getwaitingResponseCode() {
        return waitingResponseCode;
    }

    public void setwaitingResponseCode(int cheking) {
        this.waitingResponseCode = cheking;
    }

    public List<WebsiteInfo> getWebsiteInfos() { return websiteInfos;}

    public void setWebsiteInfos(List<WebsiteInfo> websiteInfos) {
        this.websiteInfos = websiteInfos;
    }

    @Override
    public String toString() {
        return "WebsiteData{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", periodTime=" + periodTime +
                ", responseOK=" + responseOK +
                ", responseWARNING=" + responseWARNING +
                ", responseCRITICAL=" + responseCRITICAL +
                ", addmisibleStatus=" + status +
                ", byteMin=" + byteMin +
                ", byteMax=" + byteMax +
                ", activity='" + activity + '\'' +
                ", cheking='" + waitingResponseCode + '\'' +
                '}';
    }
}
