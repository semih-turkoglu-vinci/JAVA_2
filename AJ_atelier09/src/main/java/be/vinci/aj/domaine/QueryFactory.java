package be.vinci.aj.domaine;

public class QueryFactory {
    public static Query getQuery(){
        return new QueryImpl();
    }
}
