package zw.co.vokers.zoledge.models;

/**
 * Created by VinceGee on 1/17/2018.
 */

public class Article {
    String articleName, articleSummary, articleInfo, articleCategory;

    public Article(String articleName, String articleSummary, String articleInfo, String articleCategory) {
        this.articleName = articleName;
        this.articleSummary = articleSummary;
        this.articleInfo = articleInfo;
        this.articleCategory = articleCategory;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleInfo() {
        return articleInfo;
    }

    public void setArticleInfo(String articleInfo) {
        this.articleInfo = articleInfo;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }
}
