package lukuvinkkisovellus;

public class Lukuvinkki {

    String otsikko;
    String url;

    public Lukuvinkki(String otsikko, String url) {
        this.otsikko = otsikko;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Lukuvinkki: "  + otsikko + " " + url;
    }
    

}
