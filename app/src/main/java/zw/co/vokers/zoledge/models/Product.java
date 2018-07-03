package zw.co.vokers.zoledge.models;

/**
 * Created by VinceGee on 1/8/2018.
 */

public class Product {
    String productName, productSummary, productInfo, productCategory;

    public Product(String productName, String productSummary, String productInfo, String productCategory) {
        this.productName = productName;
        this.productSummary = productSummary;
        this.productInfo = productInfo;
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSummary() {
        return productSummary;
    }

    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


}
