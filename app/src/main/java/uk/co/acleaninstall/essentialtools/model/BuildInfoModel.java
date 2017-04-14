package uk.co.acleaninstall.essentialtools.model;

public class BuildInfoModel {

    private String mTitle;
    private String mSubTitle;
    private String mResult;
    private String mIcon;


    public BuildInfoModel() {
    }


    public BuildInfoModel(String mTitle, String mSubTitle, String mResult, String mIcon) {
        this.mTitle = mTitle;
        this.mSubTitle = mSubTitle;
        this.mResult = mResult;
        this.mIcon = mIcon;
    }


    public String getTitle() {
        return mTitle;
    }


    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    public String getSubTitle() {
        return mSubTitle;
    }


    public void setSubTitle(String mSubTitle) {
        this.mSubTitle = mSubTitle;
    }


    public String getResult() {
        return mResult;
    }


    public void setResult(String mResult) {
        this.mResult = mResult;
    }


    public String getIcon() {
        return mIcon;
    }


    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }
}
