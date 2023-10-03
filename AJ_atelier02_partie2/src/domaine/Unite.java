package domaine;

public enum Unite {
    GRAMME("gr"),KILOGRAMME("kg"),LITRE("l"),MILLILITRE("ml"),CENTILITRE("cl"),DECILITRE("dl"),CUILLER_A_CAFE("cf"),CUILLER_A_THE("ct"),CUILLER_A_DESSERT("cd"),CUILLER_A_SOUPE("cs"),PINCEE("pincee"),UN_PEU("peu"),NEANT("");

    private String abbreviation;
    Unite(String abbreviation){
        this.abbreviation=abbreviation;
    }
    public String toString(){
        return this.abbreviation;
    }
}
