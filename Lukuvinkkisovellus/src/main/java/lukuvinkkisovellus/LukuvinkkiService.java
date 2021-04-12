package lukuvinkkisovellus;

import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkkisovellus.Lukuvinkki;

public class LukuvinkkiService {
    
    private LukuvinkkiDao lukuvinkkiDao;

    public LukuvinkkiService(LukuvinkkiDao lukuvinkkiDao) {
        this.lukuvinkkiDao = lukuvinkkiDao;  
    }
    
    public List listaaKaikki() {   
        
        return this.lukuvinkkiDao.listaaKaikki();
                      
    }
    public void lisaaLukuvinkki(Lukuvinkki lukuvinkki) throws Exception {
        this.lukuvinkkiDao.lisaa(lukuvinkki);
    }
    
}
