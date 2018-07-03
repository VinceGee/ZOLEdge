package zw.co.vokers.zoledge.models;

public class ErrorModel {
    public String errorCode;
    public String probIdent;
    public String possSolution;

    public ErrorModel(String errorCode, String probIdent, String possSolution) {
        this.errorCode = errorCode;
        this.probIdent = probIdent;
        this.possSolution = possSolution;
    }
}
