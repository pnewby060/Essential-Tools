package uk.co.acleaninstall.essentialtools.model;

public class ShellModel {

    /**
     * From the linux man pages to a model
     */

    private String mTitle;
    private String mSynopsis;
    private String mDescription;
    private String mOptions;


    public ShellModel() {
    }


    // COMMAND INFORMATION
    public ShellModel(String mTitle, String mSynopsis, String mDescription, String mOptions) {
        this.mTitle = mTitle;
        this.mSynopsis = mSynopsis;
        this.mDescription = mDescription;
        this.mOptions = mOptions;
    }


    public String getTitle() {
        return mTitle;
    }


    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }


    public String getSynopsis() {
        return mSynopsis;
    }


    public String getDescription() {
        return mDescription;
    }


    public String getOptions() {
        return mOptions;
    }
}
