package be.vinci.aj.server;

import be.vinci.aj.domaine.Query;
import be.vinci.aj.domaine.QueryFactory;
import java.util.Scanner;

public class ProxyServer {

    private QueryFactory queryFactory;

    public ProxyServer(QueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }
    public void startServer() {

        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                System.out.println("Entrez l'url");
                String url = scan.nextLine();
                Query quer = QueryFactory.getQuery();
                quer.setUrl(url);
                quer.setHTTPmethod(Query.QueryMethod.GET);
                QueryHandler query = new QueryHandler(quer);
                query.start();
            }
        }
    }
}
