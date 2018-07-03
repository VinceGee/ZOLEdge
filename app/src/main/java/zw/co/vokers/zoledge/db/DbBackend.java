package zw.co.vokers.zoledge.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import zw.co.vokers.zoledge.models.Article;
import zw.co.vokers.zoledge.models.Basestation;
import zw.co.vokers.zoledge.models.Contact;
import zw.co.vokers.zoledge.models.Product;

/**
 * Created by VinceGee on 1/8/2018.
 */

public class DbBackend extends DbObject {
    public DbBackend(Context context) {
        super(context);
    }

    public ArrayList<Product> getProductsList(){
        String query = "SELECT * FROM products";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<Product> wordTerms = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndexOrThrow("pname"));
                String summary = cursor.getString(cursor.getColumnIndexOrThrow("psummary"));
                String info = cursor.getString(cursor.getColumnIndexOrThrow("pinfo"));
                String category = cursor.getString(cursor.getColumnIndexOrThrow("pcategory"));

                wordTerms.add(new Product(name, summary, info, category));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return wordTerms;
    }

    public ArrayList<Basestation> getBaseStationsList(){
        String query = "SELECT * FROM basestations ORDER BY sitename";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<Basestation> wordTerms = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String siteNo = cursor.getString(cursor.getColumnIndexOrThrow("SiteNo"));
                String siteID = cursor.getString(cursor.getColumnIndexOrThrow("Site_ID"));
                String siteName = cursor.getString(cursor.getColumnIndexOrThrow("sitename"));
                String cellName = cursor.getString(cursor.getColumnIndexOrThrow("CellName"));
                String cellID = cursor.getString(cursor.getColumnIndexOrThrow("CellID"));
                String bsID = cursor.getString(cursor.getColumnIndexOrThrow("BSID"));
                String sectorID = cursor.getString(cursor.getColumnIndexOrThrow("SectorID"));
                String district = cursor.getString(cursor.getColumnIndexOrThrow("District"));
                String areaName = cursor.getString(cursor.getColumnIndexOrThrow("AreaName"));
                String longitude = cursor.getString(cursor.getColumnIndexOrThrow("Longitude"));
                String latitude = cursor.getString(cursor.getColumnIndexOrThrow("Latitude"));
                String coverageType = cursor.getString(cursor.getColumnIndexOrThrow("CoverageType"));
                String centerFrequency = cursor.getString(cursor.getColumnIndexOrThrow("CenterFrequency"));
                String bandwidth = cursor.getString(cursor.getColumnIndexOrThrow("Bandwidth"));
                String operatorID = cursor.getString(cursor.getColumnIndexOrThrow("OperatorID"));


                wordTerms.add(new Basestation(siteNo, siteID,siteName,cellName,cellID,bsID,sectorID,district,areaName,longitude,latitude,coverageType,centerFrequency,bandwidth,operatorID));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return wordTerms;
    }

    public ArrayList<Contact> getContactsList(){
        String query = "SELECT * FROM contacts ORDER BY contactName";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<Contact> wordTerms = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndexOrThrow("contactName"));
                String cellNumber = cursor.getString(cursor.getColumnIndexOrThrow("cellNumber"));
                String extension = cursor.getString(cursor.getColumnIndexOrThrow("extension"));
                String altExtension = cursor.getString(cursor.getColumnIndexOrThrow("altExtension"));
                String dept = cursor.getString(cursor.getColumnIndexOrThrow("dept"));

                wordTerms.add(new Contact(name, cellNumber, extension, altExtension, dept));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return wordTerms;
    }

    public ArrayList<Article> getArticlesList(){
        String query = "SELECT * FROM articles";
        Cursor cursor = this.getDbConnection().rawQuery(query, null);
        ArrayList<Article> wordTerms = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String aname = cursor.getString(cursor.getColumnIndexOrThrow("aname"));
                String asummary = cursor.getString(cursor.getColumnIndexOrThrow("asummary"));
                String ainfo = cursor.getString(cursor.getColumnIndexOrThrow("ainfo"));
                String acategory = cursor.getString(cursor.getColumnIndexOrThrow("acategory"));

                wordTerms.add(new Article(aname, asummary, ainfo, acategory));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return wordTerms;
    }

    public HashMap<String, String> getProductDetails(String filename) {
        HashMap<String, String> product = new HashMap<String, String>();
        String selectQuery = "SELECT pname, psummary, pinfo, pcategory FROM products WHERE pname ='"+ filename+"'";

        Cursor cursor = this.getDbConnection().rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            product.put("pname", cursor.getString(0));
            product.put("psummary", cursor.getString(1));
            product.put("pinfo", cursor.getString(2));
            product.put("pcategory", cursor.getString(3));
        }
        cursor.close();
        // return user
        Log.d("PPPPPPPPP", "Fetching product from SQLite: " + product.toString());

        return product;
    }

    public HashMap<String, String> getBsDetails(String bsid) {
        HashMap<String, String> product = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM basestations WHERE bsID ='"+ bsid+"'";

        Cursor cursor = this.getDbConnection().rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            product.put("SiteNo", cursor.getString(0));
            product.put("Site_ID", cursor.getString(1));
            product.put("sitename", cursor.getString(2));
            product.put("CellName", cursor.getString(3));
            product.put("CellID", cursor.getString(4));
            product.put("BSID", cursor.getString(5));
            product.put("SectorID", cursor.getString(6));
            product.put("District", cursor.getString(7));
            product.put("AreaName", cursor.getString(8));
            product.put("Longitude", cursor.getString(9));
            product.put("Latitude", cursor.getString(10));
            product.put("CoverageType", cursor.getString(11));
            product.put("CenterFrequency", cursor.getString(12));
            product.put("Bandwidth", cursor.getString(13));
            product.put("OperatorID", cursor.getString(14));
        }
        cursor.close();
        // return user
        Log.d("PPPPPPPPP", "Fetching basestation from SQLite: " + product.toString());

        return product;
    }

    public HashMap<String, String> getArticleDetails(String filename) {
        HashMap<String, String> article = new HashMap<String, String>();
        String selectQuery = "SELECT aname, asummary, ainfo, acategory FROM articles WHERE aname ='"+ filename+"'";

        Cursor cursor = this.getDbConnection().rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            article.put("aname", cursor.getString(0));
            article.put("asummary", cursor.getString(1));
            article.put("ainfo", cursor.getString(2));
            article.put("acategory", cursor.getString(3));
        }
        cursor.close();
        // return user
        Log.d("PPPPPPPPP", "Fetching article from SQLite: " + article.toString());

        return article;
    }

}
