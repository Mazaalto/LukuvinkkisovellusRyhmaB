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
 * @author anttihalmetoja, jullebli
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
    public void LukuvinkinPoistoOnnistuu() throws Exception {
        Lukuvinkki lukuvinkki = new Lukuvinkki("otsikko1", "testiurl.com");
        lukuvinkkiService.lisaaLukuvinkki(lukuvinkki); 
        lukuvinkkiService.poistaLukuvinkki(lukuvinkki);              
        assertEquals(0, lukuvinkkiService.listaaKaikki().size());
    }
    
    @Test
    public void LukuvinkkienListausOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko1", "testiurl.com"));
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko2", "testiurl2.com"));                
        assertTrue(lukuvinkkiService.listaaKaikki().toString().contains("Vinkin otsikko: otsikko2, vinkin linkki: testiurl2.com"));
    }
    
    @Test
    public void LukuvinkinLisaaminenKasvattaaLukuvinkkienMaaraa() throws Exception {
        int maaraAluksi = lukuvinkkiService.getLukuvinkkienMaara();
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko1", "testiurl.com"));               
        assertEquals(maaraAluksi + 1, lukuvinkkiDao.LukuvinkkienMaara());
    }
    
    @Test
    public void ListaaOtsikonPerusteellaPalauttaaOikeanLukumaaranLukuvinkkeja() throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsi", "testiurl.com"));
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsik", "testiurl2.com"));
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikk", "testiur3.com"));
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("otsikko", "testiurl4.com"));
        
        assertEquals(4, lukuvinkkiService.listaaOtsikonPerusteella("otsi").size());
    }
    
    @Test
    public void ListaaOtsikonPerusteellaPalauttaaOikeanListanLukuvinkkeja() throws Exception {
        Lukuvinkki a = new Lukuvinkki("otsi", "testiurl.com");
        Lukuvinkki b = new Lukuvinkki("otsik", "testiurl2.com");
        Lukuvinkki c = new Lukuvinkki("otsikk", "testiurl3.com");
        Lukuvinkki d = new Lukuvinkki("otsikko", "testiurl4.com");
        
        List<Lukuvinkki> lukuvinkit = new ArrayList<>();
        lukuvinkit.add(a);
        lukuvinkit.add(b);
        lukuvinkit.add(c);
        lukuvinkit.add(d);
        
        lukuvinkkiService.lisaaLukuvinkki(a);
        lukuvinkkiService.lisaaLukuvinkki(b);
        lukuvinkkiService.lisaaLukuvinkki(c);
        lukuvinkkiService.lisaaLukuvinkki(d);
        assertArrayEquals(lukuvinkit.toArray(), lukuvinkkiService.listaaOtsikonPerusteella("otsi").toArray());
    }
    
}
