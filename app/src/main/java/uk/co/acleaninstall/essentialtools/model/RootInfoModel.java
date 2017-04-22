package uk.co.acleaninstall.essentialtools.model;

public class RootInfoModel {

    private String mTitle;
    private String mSubTitle;
    private String mResult;


    public RootInfoModel() {
    }


    // CALL INFORMATION
    public RootInfoModel(String mTitle, String mSubTitle, String mResult) {
        this.mTitle = mTitle;
        this.mSubTitle = mSubTitle;
        this.mResult = mResult;
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

}
