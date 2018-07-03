package zw.co.vokers.zoledge.models;

public class WifiModel {
    String ssid, type;

    public WifiModel(String ssid, String type) {
        this.type = type;
        this.ssid = ssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
