package cn.bin2.sport.common.domain;

import org.springframework.data.annotation.Id;



public class Site {

    private Integer siteId;


    private String siteName;


    private String siteInfo;


    private String siteTitle;


    private String siteSeo;

    private String createUser;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * @return site_id
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * @param siteId
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * @return site_name
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * @param siteName
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * @return site_info
     */
    public String getSiteInfo() {
        return siteInfo;
    }

    /**
     * @param siteInfo
     */
    public void setSiteInfo(String siteInfo) {
        this.siteInfo = siteInfo;
    }

    /**
     * @return site_title
     */
    public String getSiteTitle() {
        return siteTitle;
    }

    /**
     * @param siteTitle
     */
    public void setSiteTitle(String siteTitle) {
        this.siteTitle = siteTitle;
    }

    /**
     * @return site_seo
     */
    public String getSiteSeo() {
        return siteSeo;
    }

    /**
     * @param siteSeo
     */
    public void setSiteSeo(String siteSeo) {
        this.siteSeo = siteSeo;
    }
}