package zw.co.vokers.zoledge.models;

/**
 * Created by VinceGee on 3/24/2018.
 */

public class ContactModel {
    private final Contact mContactName;
    private final Contact mContactCell;
    private final Contact mContactExtension;
    private final Contact mContactDepartment;

    public ContactModel(Contact text, Contact text1, Contact text2, Contact text3){
        mContactName = text;
        mContactCell = text1;
        mContactExtension = text2;
        mContactDepartment = text3;
    }

    @Override
    public String toString() {
        return getmContactName().getContactName() + getmContactCell().getCellNumber() + getmContactExtension().getExtension() + getmContactDepartment().getDepartment() ;
               /* "mContactName=" + mContactName +
                ", mContactCell=" + mContactCell +
                ", mContactExtension=" + mContactExtension +
                ", mGender=" + mGender +
                ", mNatId=" + mNatId +
                '}';*/
    }

    public Contact getmContactName(){
        return mContactName;
    }

    public Contact getmContactCell(){
        return mContactCell;
    }

    public Contact getmContactExtension(){
        return mContactExtension;
    }

    public Contact getmContactDepartment(){
        return mContactDepartment;
    }

}
