package uk.co.acleaninstall.essentialtools.model;

import android.support.annotation.Nullable;

public class OtherToolModel {

    private String mTitle;
    private String mSubTitle;
    private String mIcon;


    public OtherToolModel() {
    }


    public OtherToolModel(String mTitle, String mSubTitle, @Nullable String mIcon) {
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
