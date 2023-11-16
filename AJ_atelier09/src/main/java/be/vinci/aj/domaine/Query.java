package be.vinci.aj.domaine;

public interface Query {
    String getUrl();

    void setUrl(String url);

    QueryMethod getHTTPmethod();

    void setHTTPmethod(QueryMethod HTTPmethod);

    public enum QueryMethod {
        GET,
        POST
    }
}
