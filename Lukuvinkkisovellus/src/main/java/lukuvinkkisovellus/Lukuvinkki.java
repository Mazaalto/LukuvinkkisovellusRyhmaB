package lukuvinkkisovellus;

public class Lukuvinkki {

    String otiskko;
    String url;

    public Lukuvinkki(String otiskko, String url) {
        this.otiskko = otiskko;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Lukuvinkki{" + "otiskko=" + otiskko + ", url=" + url + '}';
    }
    

}
