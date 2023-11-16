package be.vinci.aj.domaine;
 class QueryImpl implements Query {
    private String url;
    private QueryMethod HTTPmethod;

    public QueryImpl(String url, QueryMethod HTTPmethod){
        this.url=url;
        this.HTTPmethod=HTTPmethod;
    }

     public QueryImpl() {

     }

     @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public QueryMethod getHTTPmethod() {
        return HTTPmethod;
    }

    @Override
    public void setHTTPmethod(QueryMethod HTTPmethod) {
        this.HTTPmethod = HTTPmethod;
    }

}
