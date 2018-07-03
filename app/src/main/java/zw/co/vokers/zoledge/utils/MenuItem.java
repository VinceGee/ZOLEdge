package zw.co.vokers.zoledge.utils;

/**
 * Created by VinceGee on 5/17/2017.
 */

public class MenuItem {
    private int icon;
    private String text;
    private boolean selected;
    private int numNotifications;
    private boolean notifications;


    public MenuItem(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }


    public MenuItem(int icon, String text, boolean selected) {
        this.icon = icon;
        this.text = text;
        this.selected = selected;
    }



    public MenuItem(int icon, String text, int numNotifications, boolean notifications) {
        this.icon = icon;
        this.text = text;
        this.numNotifications = numNotifications;
        this.notifications = notifications;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getNumNotifications() {
        return numNotifications;
    }

    public void setNumNotifications(int numNotifications) {
        this.numNotifications = numNotifications;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

}
