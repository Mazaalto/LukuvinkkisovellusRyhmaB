package lukuvinkkisovellus;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Linkki implements Lukuvinkki {

    private String otsikko;
    private String url;
    private Boolean luettu;
    private String merkittyLuetuksi;

    public Linkki(String otsikko, String url) {
        this.otsikko = otsikko;
        this.url = url;
        this.luettu = false;
    }

    public Linkki(String otsikko, String url, Boolean luettu, String merkittyLuetuksi) {
        this.otsikko = otsikko;
        this.url = url;
        this.luettu = luettu;
        this.merkittyLuetuksi = merkittyLuetuksi;
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
        System.out.println("onkoluettu: "+onkoLuettu());
        if(onkoLuettu()) {
            return "Vinkin otsikko: "  + otsikko + ", linkki: " + url + ", luettu " + merkittyLuetuksi;
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
        System.out.println("luettu: "+ this.luettu);
        SimpleDateFormat ft = new SimpleDateFormat ("E d.MM.yyyy 'klo' HH:mm:ss");
        
        merkittyLuetuksi = ft.format(java.util.Calendar.getInstance().getTime());
        if (this.luettu) {
            System.out.println("Merkitty luetuksi: "+merkittyLuetuksi);
        } else {
            System.out.println("Lukuvinkkiä ei ole vielä luettu");
        }
        
        
    }
    @Override
     public String getMilloinLuettu() {
         return this.merkittyLuetuksi;
     }
    

}
