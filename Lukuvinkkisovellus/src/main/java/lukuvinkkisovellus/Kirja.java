package lukuvinkkisovellus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Kirja implements Lukuvinkki {

    String otsikko;
    String kirjailija;
    String julkaisija;
    int julkaisuvuosi;
    String url;
    Boolean luettu;
    String merkittyLuetuksi;

    public Kirja(String otsikko, String kirjailija, int julkaisuvuosi, String julkaisija, String url) {
        this.otsikko = otsikko;
        this.kirjailija = kirjailija;
        this.julkaisija = julkaisija;
        this.julkaisuvuosi = julkaisuvuosi;
        this.url = url;
        this.luettu = false;
    }

    public Kirja(String otsikko, String kirjailija, int julkaisuvuosi, String julkaisija, String url, Boolean luettu, String merkittyLuetuksi) {
        this.otsikko = otsikko;
        this.kirjailija = kirjailija;
        this.julkaisija = julkaisija;
        this.julkaisuvuosi = julkaisuvuosi;
        this.url = url;
        this.luettu = luettu;
        this.merkittyLuetuksi = merkittyLuetuksi;
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
        if (this.luettu) {
            return "Vinkin otsikko: " + otsikko + ", kirjailija: " + kirjailija + ", julkaisuvuosi: " + julkaisuvuosi + ", julkaisija: " + julkaisija + ", linkki: " + url + ", luettu: " + this.merkittyLuetuksi;
        } else {
            return "Vinkin otsikko: " + otsikko + ", kirjailija: " + kirjailija + ", julkaisuvuosi: " + julkaisuvuosi + ", julkaisija: " + julkaisija + ", linkki: " + url;
        }
        
    }

    @Override
    public Boolean onkoLuettu() {
        return this.luettu;
    }

    @Override
    public void merkkaaLuetuksi() {
        this.luettu = true;
        System.out.println("luettu: "+ this.luettu);
        SimpleDateFormat ft = new SimpleDateFormat ("E d.MM.yyyy 'klo' HH:mm:ss");
        
        merkittyLuetuksi = ft.format(java.util.Calendar.getInstance().getTime());
        System.out.println("Merkitty luetuksi: "+merkittyLuetuksi);
    }

    @Override
    public String getMilloinLuettu() {
        return this.merkittyLuetuksi;
    }

}
