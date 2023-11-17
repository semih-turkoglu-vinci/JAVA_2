package be.vinci.aj.domaine;

public interface QueryFactory {
    static Query getQuery() {
        return new QueryImpl();
    }
}
