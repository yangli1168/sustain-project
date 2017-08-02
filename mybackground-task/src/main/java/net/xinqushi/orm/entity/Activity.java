package net.xinqushi.orm.entity;

public class Activity {
    private Long activityId;

    private Long createTime;

    private Long startTime;

    private Long endTime;

    private String headImgUrl;

    private String listImgUrl;

    private String contentHtml;

    private Integer jumpTabId;

    private String shareTitle;

    private String shareDescr;

    private String shareUrl;

    private Long updateTime;

    private Byte jumpType;

    private String appVersion;

    private String buttonText;

    private String subject;

    private Integer status;

    private String type;

    private String jumpContent;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    public String getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(String listImgUrl) {
        this.listImgUrl = listImgUrl == null ? null : listImgUrl.trim();
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml == null ? null : contentHtml.trim();
    }

    public Integer getJumpTabId() {
        return jumpTabId;
    }

    public void setJumpTabId(Integer jumpTabId) {
        this.jumpTabId = jumpTabId;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getShareDescr() {
        return shareDescr;
    }

    public void setShareDescr(String shareDescr) {
        this.shareDescr = shareDescr == null ? null : shareDescr.trim();
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getJumpType() {
        return jumpType;
    }

    public void setJumpType(Byte jumpType) {
        this.jumpType = jumpType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText == null ? null : buttonText.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getJumpContent() {
        return jumpContent;
    }

    public void setJumpContent(String jumpContent) {
        this.jumpContent = jumpContent == null ? null : jumpContent.trim();
    }
}