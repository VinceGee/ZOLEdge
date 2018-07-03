package zw.co.vokers.zoledge.models;

/**
 * Created by VinceGee on 1/8/2018.
 */

public class ProductModel {
    private final Product mProductName;
    private final Product mProductSummary;
    private final Product mProductCategory;

    public ProductModel(Product text, Product text1, Product text2){
        mProductName = text;
        mProductSummary = text1;
        mProductCategory = text2;

    }

    @Override
    public String toString() {
        return getmProductName().getProductName() + getmProductSummary().getProductSummary() + getmProductCategory().getProductInfo() /*+ getmGender().getGender() + getmNatId().getProductCategory()*/;/*"ProductModel{" +
                "mProductName=" + mProductName +
                ", mProductSummary=" + mProductSummary +
                ", mProductCategory=" + mProductCategory +
                ", mGender=" + mGender +
                ", mNatId=" + mNatId +
                '}';*/
    }

    public Product getmProductName(){
        return mProductName;
    }

   public Product getmProductSummary(){
        return mProductSummary;
    }

    public Product getmProductCategory(){
        return mProductCategory;
    }
/*
    public Product getmGender(){
        return mGender;
    }

    public Product getmNatId(){
        return mNatId;
    }*/
}
