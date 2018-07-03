package zw.co.vokers.zoledge.models;

public class AccountQuotaModel {
    public String packageName;
    public String sandvineName;
    public String emailAccounts;
    public String diskQuota;
    public String domainsHosted;
    public String websiteHosted;
    public String freeDomainsRegistered;

    public AccountQuotaModel(String packageName, String sandvineName, String emailAccounts, String diskQuota, String domainsHosted, String websiteHosted, String freeDomainsRegistered) {
        this.packageName = packageName;
        this.sandvineName = sandvineName;
        this.emailAccounts = emailAccounts;
        this.diskQuota = diskQuota;
        this.domainsHosted = domainsHosted;
        this.websiteHosted = websiteHosted;
        this.freeDomainsRegistered = freeDomainsRegistered;
    }
}
