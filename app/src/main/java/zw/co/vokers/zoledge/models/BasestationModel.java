package zw.co.vokers.zoledge.models;

public class BasestationModel {
    private final Basestation mSiteID;
    private final Basestation mSiteName;
    private final Basestation mBsID;
    private final Basestation mArea;

    public BasestationModel(Basestation bs1, Basestation bs2,Basestation bs3, Basestation bs4){
        mSiteID = bs1;
        mSiteName = bs2;
        mBsID = bs3;
        mArea = bs4;
    }

    @Override
    public String toString() {
        return getmSiteName().getSiteName() + getmSiteID().getSiteID() + getmBsID().getBsID() + getmArea().getAreaName();

        /*"BasestationModel{" +
                "mSiteID=" + mSiteID +
                ", mSiteName=" + mSiteName +
                ", mBsID=" + mBsID +
                ", mArea=" + mArea +
                '}';*/
    }

    public Basestation getmArea() {
        return mArea;
    }

    public Basestation getmBsID() {
        return mBsID;
    }

    public Basestation getmSiteID() {
        return mSiteID;
    }

    public Basestation getmSiteName() {
        return mSiteName;
    }
}
