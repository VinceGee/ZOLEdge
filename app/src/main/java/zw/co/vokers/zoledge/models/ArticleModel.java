package zw.co.vokers.zoledge.models;

/**
 * Created by VinceGee on 1/17/2018.
 */

public class ArticleModel {
    private final Article mArticleName;
    private final Article mArticleSummary;
    private final Article mArticleCategory;

    public ArticleModel(Article mArticleName, Article mArticleSummary, Article mArticleCategory) {
        this.mArticleName = mArticleName;
        this.mArticleSummary = mArticleSummary;
        this.mArticleCategory = mArticleCategory;
    }

    @Override
    public String toString() {
        return "ArticleModel{" +
                "mArticleName=" + mArticleName +
                ", mArticleSummary=" + mArticleSummary +
                ", mArticleCategory=" + mArticleCategory +
                '}';
    }

    public Article getmArticleName() {
        return mArticleName;
    }

    public Article getmArticleSummary() {
        return mArticleSummary;
    }

    public Article getmArticleCategory() {
        return mArticleCategory;
    }
}
