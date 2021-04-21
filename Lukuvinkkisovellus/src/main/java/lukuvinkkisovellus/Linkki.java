package lukuvinkkisovellus;

import java.util.Date;

public class Linkki implements Lukuvinkki {

    String otsikko;
    String url;
    Boolean luettu;
    Date merkittyLuetuksi;

    public Linkki(String otsikko, String url) {
        this.otsikko = otsikko;
        this.url = url;
        this.luettu = false;
    }

    @Override
    public String getOtsikko() {
        return otsikko;
    }

    public String getUrl() {
        return url;
    }
    
    @Override
    public String toString() {
        if(onkoLuettu()) {
            return "Vinkin otsikko: "  + otsikko + ", linkki: " + url + ", luetttu " + merkittyLuetuksi;
        }
        return "Vinkin otsikko: "  + otsikko + ", vinkin linkki: " + url;
    }

    @Override
    public Boolean onkoLuettu() {
        return this.luettu;
    }
    @Override
     public void merkkaaLuetuksi() {
        this.luettu = true;
        merkittyLuetuksi = java.util.Calendar.getInstance().getTime();
        System.out.println(merkittyLuetuksi + onkoLuettu().toString());
        
    }
    @Override
     public String getMilloinLuettu() {
         return this.merkittyLuetuksi.toString();
     }
    

}
