package uk.co.acleaninstall.essentialtools.model;

public class SimModel {

    private String mTitle;
    private String mSubTitle;
    private String mResult;
    private String mIcon;


    public SimModel() {
    }


    // CALL INFORMATION
    public SimModel(String mTitle, String mSubTitle, String mResult, String mIcon) {
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


    public String getResult() {
        return mResult;
    }


    public String getIcon() {
        return mIcon;
    }
}
