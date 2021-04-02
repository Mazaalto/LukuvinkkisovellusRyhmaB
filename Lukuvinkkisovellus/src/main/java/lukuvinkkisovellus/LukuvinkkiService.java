package lukuvinkkisovellus;

import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkkisovellus.Lukuvinkki;

public class LukuvinkkiService {
    
    private LukuvinkkiDao lukuvinkkiDao;

    public LukuvinkkiService(LukuvinkkiDao lukuvinkkiDao) {
        this.lukuvinkkiDao = lukuvinkkiDao;  
    }
    
    public void listaaKaikki() {
        this.lukuvinkkiDao.listaaKaikki().stream().forEach(lv -> System.out.println(lv));  
    }
    public void lisaaLukuvinkki(Lukuvinkki lukuvinkki) {
        this.lukuvinkkiDao.lisaa(lukuvinkki);
    }
    
}
