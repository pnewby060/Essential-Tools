package uk.co.acleaninstall.essentialtools.model;

public class GameModel {

    private String mTitle;
    private String mSubTitle;
    private String mIcon;


    public GameModel() {
    }


    public GameModel(String mTitle, String mSubTitle, String mIcon) {
        this.mTitle = mTitle;
        this.mSubTitle = mSubTitle;
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


    public String getIcon() {
        return mIcon;
    }


    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }
}
