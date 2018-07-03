package zw.co.vokers.zoledge.models;

/**
 * Created by VinceGee on 3/24/2018.
 */

public class Contact {
    String contactName, cellNumber, extension, altExtension, department;

    public Contact(String contactName, String cellNumber, String extension, String altExtension, String department) {
        this.contactName = contactName;
        this.cellNumber = cellNumber;
        this.extension = extension;
        this.altExtension = altExtension;
        this.department = department;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getAltExtension() {
        return altExtension;
    }

    public void setAltExtension(String altExtension) {
        this.altExtension = altExtension;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
