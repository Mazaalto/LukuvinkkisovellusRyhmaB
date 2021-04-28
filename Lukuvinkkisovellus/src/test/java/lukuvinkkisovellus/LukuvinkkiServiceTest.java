
package lukuvinkkisovellus;

import java.util.ArrayList;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.StubLukuvinkkiDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author anttihalmetoja, jullebli, mazaalto
 */
public class LukuvinkkiServiceTest {
    
    private LukuvinkkiService lukuvinkkiService;
    private LukuvinkkiDao lukuvinkkiDao;
       
    @Before
    public void setUp() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
        
    }
    
    @Test
    public void LinkinLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLinkki(new Linkki("a", "v"));                
        assertEquals(1, lukuvinkkiDao.lukuvinkkienMaara());
    }
    
    @Test
    public void LinkinLisaysEpaonnistuuIlmanOtsikkoa() throws Exception {
        lukuvinkkiService.lisaaLinkki(new Linkki("", "a"));
        assertEquals(0, lukuvinkkiDao.lukuvinkkienMaara());
    }
    
    @Test
    public void LinkinLisaysEpaonnistuuIlmanUrlia() throws Exception {
        lukuvinkkiService.lisaaLinkki(new Linkki("a", ""));
        assertEquals(0, lukuvinkkiDao.lukuvinkkienMaara());
    }
    
    @Test
    public void KirjanLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaKirja(new Kirja("kirja", "kirjailija", 1997, "tammi", "www.kirja.net"));
        assertEquals(1, lukuvinkkiDao.kirjojenLukumaara());
    }
    
    @Test
    public void KirjanLisaysEpaonnistuuIlmanOtsikkoa() throws Exception {
        lukuvinkkiService.lisaaKirja(new Kirja("", "kirjailija", 1997, "tammi", "www.kirja.net"));
        assertEquals(0, lukuvinkkiDao.kirjojenLukumaara());
    }
    
    @Test
    public void KirjanLisaysEpaonnistuuIlmanUrlia() throws Exception {
        lukuvinkkiService.lisaaKirja(new Kirja("kirja", "kirjailija", 1997, "tammi", ""));
        assertEquals(0, lukuvinkkiDao.kirjojenLukumaara());
    }

    @Test
    public void LukuvinkinPoistoOnnistuu() throws Exception {
        Linkki lukuvinkki = new Linkki("otsikko1", "testiurl.com");
        lukuvinkkiService.lisaaLinkki(lukuvinkki); 
        lukuvinkkiService.poistaLukuvinkki(lukuvinkki);              
        assertEquals(0, lukuvinkkiService.listaaKaikki().size());
    }
    
    @Test
    public void LukuvinkkienListausOnnistuu() throws Exception {
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikko1", "testiurl.com"));
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikko2", "testiurl2.com"));                
        assertTrue(lukuvinkkiService.listaaKaikki().toString().contains("Vinkin otsikko: otsikko2, vinkin linkki: testiurl2.com"));
    }
    
    @Test
    public void KirjojenListausOnnistuu() throws Exception {
        lukuvinkkiService.lisaaKirja(new Kirja("kirja", "kirjailija", 1997, "tammi", "www.kirja.net"));
        lukuvinkkiService.lisaaKirja(new Kirja("kirja2", "kirjailija2", 1992, "tammi", "www.kirja.net"));
        
        assertTrue(lukuvinkkiService.listaaKirjat().toString().contains("Vinkin otsikko: kirja, kirjailija: kirjailija, julkaisuvuosi: 1997, julkaisija: tammi, linkki: www.kirja.net"));
        
    }
    
    @Test
    public void LukuvinkinLisaaminenKasvattaaLukuvinkkienMaaraa() throws Exception {
        int maaraAluksi = lukuvinkkiService.getLukuvinkkienMaara();
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikko1", "testiurl.com"));               
        assertEquals(maaraAluksi + 1, lukuvinkkiDao.lukuvinkkienMaara());
    }
    @Test
    public void LukuvinkinLisaaminenKasvattaaLukuvinkkienMaaraaJosLisataanMyosKirja() throws Exception {
        int maaraAluksi = lukuvinkkiService.getLukuvinkkienMaara();
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikko1", "testiurl.com"));  
        lukuvinkkiService.lisaaKirja(new Kirja("kirja", "kirjailija", 1997, "tammi", "www.kirja.net"));
        assertEquals(maaraAluksi + 2, lukuvinkkiDao.lukuvinkkienMaara());
    }
    
    @Test
    public void ListaaOtsikonPerusteellaPalauttaaOikeanLukumaaranLukuvinkkeja() throws Exception {
        lukuvinkkiService.lisaaLinkki(new Linkki("otsi", "testiurl.com"));
        lukuvinkkiService.lisaaLinkki(new Linkki("otsik", "testiurl2.com"));
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikk", "testiur3.com"));
        lukuvinkkiService.lisaaLinkki(new Linkki("otsikko", "testiurl4.com"));
        
        assertEquals(4, lukuvinkkiService.listaaOtsikonPerusteella("otsi").size());
    }
    
    @Test
    public void ListaaOtsikonPerusteellaPalauttaaOikeanListanLukuvinkkeja() throws Exception {
        Linkki a = new Linkki("otsi", "testiurl.com");
        Linkki b = new Linkki("otsik", "testiurl2.com");
        Linkki c = new Linkki("otsikk", "testiurl3.com");
        Linkki d = new Linkki("otsikko", "testiurl4.com");
        
        List<Linkki> lukuvinkit = new ArrayList<>();
        lukuvinkit.add(a);
        lukuvinkit.add(b);
        lukuvinkit.add(c);
        lukuvinkit.add(d);
        
        lukuvinkkiService.lisaaLinkki(a);
        lukuvinkkiService.lisaaLinkki(b);
        lukuvinkkiService.lisaaLinkki(c);
        lukuvinkkiService.lisaaLinkki(d);
        assertArrayEquals(lukuvinkit.toArray(), lukuvinkkiService.listaaOtsikonPerusteella("otsi").toArray());
    }
    
    @Test
    public void LuetuksiMerkattuLinkkiOnMerkkittyLuetuksi() throws Exception {
        Linkki a = new Linkki("otsi", "testiurl.com");  
        lukuvinkkiService.merkkaaLuetuksi(a);
        assertTrue(a.onkoLuettu());
    }
    @Test
    public void EiLuetuksiMerkattuOnkoLuettuOnFalse() {
        Linkki a = new Linkki("otsi", "testiurl.com"); 
        assertFalse(a.onkoLuettu());
    }
    
    @Test
    public void kirjanPoistaminenOnnistuu() throws Exception {
        Kirja kirja = new Kirja("Aakkoset", "incognito", 1500, "tammi", "www.aakkoset.net");
        Kirja kirja2 = new Kirja("Keittokirja", "incognito", 2000, "wsoy", "www.keittokirja.net");
        lukuvinkkiService.lisaaKirja(kirja);
        lukuvinkkiService.lisaaKirja(kirja2);
        lukuvinkkiService.poistaKirja(kirja);
        assertEquals(0, lukuvinkkiService.listaaOtsikonPerusteella("Aakkoset").size());        
    }
    
    @Test
    public void tietokannanTyhjentaminenOnnistuu() throws Exception {
        lukuvinkkiService.tyhjennaTietokanta();
        assertEquals(0, lukuvinkkiService.listaaKaikki().size());
    }  
}
