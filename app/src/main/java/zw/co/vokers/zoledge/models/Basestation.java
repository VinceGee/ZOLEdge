package zw.co.vokers.zoledge.models;

public class Basestation {

    String siteNo, siteID,siteName,cellName,cellID,bsID,sectorID,district,areaName,longitude,latitude,coverageType,centerFrequency,bandwidth,operatorID;

    public Basestation(String siteNo, String siteID, String siteName, String cellName, String cellID, String bsID, String sectorID, String district, String areaName, String longitude, String latitude, String coverageType, String centerFrequency, String bandwidth, String operatorID) {
        this.siteNo = siteNo;
        this.siteID = siteID;
        this.siteName = siteName;
        this.cellName = cellName;
        this.cellID = cellID;
        this.bsID = bsID;
        this.sectorID = sectorID;
        this.district = district;
        this.areaName = areaName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.coverageType = coverageType;
        this.centerFrequency = centerFrequency;
        this.bandwidth = bandwidth;
        this.operatorID = operatorID;
    }

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    public String getSiteID() {
        return siteID;
    }

    public void setSiteID(String siteID) {
        this.siteID = siteID;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getCellID() {
        return cellID;
    }

    public void setCellID(String cellID) {
        this.cellID = cellID;
    }

    public String getBsID() {
        return bsID;
    }

    public void setBsID(String bsID) {
        this.bsID = bsID;
    }

    public String getSectorID() {
        return sectorID;
    }

    public void setSectorID(String sectorID) {
        this.sectorID = sectorID;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public String getCenterFrequency() {
        return centerFrequency;
    }

    public void setCenterFrequency(String centerFrequency) {
        this.centerFrequency = centerFrequency;
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }
}
