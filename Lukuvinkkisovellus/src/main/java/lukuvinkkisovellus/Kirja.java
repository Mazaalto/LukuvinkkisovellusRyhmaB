
package lukuvinkkisovellus;

public class Kirja implements Lukuvinkki {
    String otsikko;
    String kirjailija;
    String julkaisija;
    int julkaisuvuosi;
    String url;
    
    public Kirja(String otsikko, String kirjailija, int julkaisuvuosi, String julkaisija, String url) {
        this.otsikko = otsikko;
        this.kirjailija = kirjailija;
        this.julkaisija = julkaisija;
        this.julkaisuvuosi = julkaisuvuosi;
        this.url = url;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public String getKirjailija() {
        return kirjailija;
    }

    public String getJulkaisija() {
        return julkaisija;
    }

    public int getJulkaisuvuosi() {
        return julkaisuvuosi;
    }

    public String getUrl() {
        return url;
    }
    
    @Override
    public String toString() {
        return "Vinkin otsikko: "  + otsikko + ", kirjailija: " + kirjailija + ", julkaisuvuosi: " + julkaisuvuosi + ", julkaisija: " + julkaisija + ", linkki: " + url;
    }
    
    
}
