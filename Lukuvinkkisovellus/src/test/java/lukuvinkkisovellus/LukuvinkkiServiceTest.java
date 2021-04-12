/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lukuvinkkisovellus;

import java.util.ArrayList;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.StubLukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anttihalmetoja
 */
public class LukuvinkkiServiceTest {
    
    LukuvinkkiService lukuvinkkiService;
    LukuvinkkiDao lukuvinkkiDao;
       
    @Before
    public void setUp() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
        
    }
    
    @Test
    public void LukuvinkinLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko1", "testiurl.com"));                
        assertEquals(1, lukuvinkkiDao.LukuvinkkienMaara());
    }
    
    @Test
    public void LukuvinkkienListausOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko1", "testiurl.com"));
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko2", "testiurl2.com"));                
        assertTrue(lukuvinkkiService.listaaKaikki().toString().contains("Vinkin otsikko: otsikko2, vinkin linkki: testiurl2.com"));
    }
    
}
