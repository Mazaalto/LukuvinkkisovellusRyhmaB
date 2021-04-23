package lukuvinkkisovellus;

import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.StubLukuvinkkiDao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class KirjaTest {

    private LukuvinkkiService lukuvinkkiService;
    private LukuvinkkiDao lukuvinkkiDao;

    @Before
    public void setUp() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
    }

    @Test
    public void LukuvinkkiKirjanLisaysOnnistuu() throws Exception {
        lukuvinkkiService.lisaaKirja(new Kirja("kirjanen", "incognito", 1997, "tammi", "www.kirja.net"));
        String vinkki = lukuvinkkiDao.listaaKirjat().get(0).toString();
        assertEquals("Vinkin otsikko: kirjanen, kirjailija: incognito, "
                + "julkaisuvuosi: 1997, julkaisija: tammi, "
                + "linkki: www.kirja.net", vinkki);
    }

    @Test
    public void luetunLinkintoStringOikeanlainen() throws Exception {
        Kirja kirja = new Kirja("kirjanen", "incognito", 1997, "tammi", "www.kirja.net");
        lukuvinkkiService.lisaaKirja(kirja);
        lukuvinkkiService.merkkaaLuetuksi(kirja);
        String vinkki = lukuvinkkiDao.listaaKaikki().get(0).toString();
        assertEquals("Vinkin otsikko: kirjanen, kirjailija: incognito, "
                + "julkaisuvuosi: 1997, julkaisija: tammi, "
                + "linkki: www.kirja.net, luettu: "
                + kirja.getMilloinLuettu(), vinkki);
    }

    @Test
    public void kunKirjanMerkitseeLuetuksiNiinLuettuOnTrue() throws Exception {
        Kirja kirja = new Kirja("Ohjelmointi", "Javaohjeimoija", 2020, "Ohjelmoijat Oy", "www.ohjelmointi.con");
        lukuvinkkiService.lisaaKirja(kirja);
        lukuvinkkiService.merkkaaLuetuksi(kirja);
        assertTrue(kirja.onkoLuettu());
    }
}
