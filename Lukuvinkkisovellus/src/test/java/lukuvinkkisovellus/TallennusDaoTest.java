package lukuvinkkisovellus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

public class TallennusDaoTest {

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    private File lukuvinkkiFile;
    private LukuvinkkiDao dao;

    @Before
    public void setUp() throws Exception {
        lukuvinkkiFile = testFolder.newFile("lukuvinkkisovellusTest.db");
        dao = new TallennusDao("jdbc:sqlite:" + lukuvinkkiFile.getAbsolutePath());
        try {
            dao.lisaaLinkki(new Linkki("otsake", "urli.com"));
            dao.lisaaLinkki(new Linkki("otsake2", "urli2.com"));

            dao.lisaaKirja(new Kirja("k", "kir1", 1996, "tammi", "www.kirja.net", false, null));
            dao.lisaaKirja(new Kirja("k", "kir2", 1993, "tammi", "www.kirja.net", false, null));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    //Nyt lukuvinkkien määrään lasketaan sekä kirjat että linkit
    public void lukuvinkkienMaaraOnOikea() {
        assertEquals(4, dao.LukuvinkkienMaara());
    }

    @Test
    public void kirjojenMaaraOnOikea() {
        assertEquals(2, dao.KirjojenLukumaara());
    }

    @Test
    public void lukuvinkitLuetaanOikeinTiedostosta() {
        List<Lukuvinkki> lukuvinkit = dao.listaaKaikkiLinkit();
        assertEquals(2, lukuvinkit.size());

        Lukuvinkki lukuvinkki1 = lukuvinkit.get(0);
        assertEquals("Vinkin otsikko: otsake, vinkin linkki: urli.com", lukuvinkki1.toString());

        Lukuvinkki lukuvinkki2 = lukuvinkit.get(1);
        assertEquals("Vinkin otsikko: otsake2, vinkin linkki: urli2.com", lukuvinkki2.toString());
    }

    @Test
    public void kirjatLuetaanOikeinTiedostosta() {
        List<Lukuvinkki> lukuvinkit = dao.listaaKirjat();

        Lukuvinkki lukuvinkki1 = lukuvinkit.get(0);
        assertEquals("Vinkin otsikko: k, kirjailija: kir1, julkaisuvuosi: 1996, julkaisija: tammi, linkki: www.kirja.net", lukuvinkki1.toString());

        Lukuvinkki lukuvinkki2 = lukuvinkit.get(1);
        assertEquals("Vinkin otsikko: k, kirjailija: kir2, julkaisuvuosi: 1993, julkaisija: tammi, linkki: www.kirja.net", lukuvinkki2.toString());
    }

    @Test
    public void lisattyLukuvinkkiTallentuuTiedostoon() throws Exception {
        Linkki lisattava = new Linkki("uusi vinkki", "www.uusi.fi");
        dao.lisaaLinkki(lisattava);
        List<Lukuvinkki> lukuvinkit = dao.listaaKaikki();
        //nyt myös kirjat lasketaan lukuvinkkien määrään
        assertEquals(5, dao.LukuvinkkienMaara());
        Lukuvinkki lukuvinkki = lukuvinkit.get(2);
        assertEquals("Vinkin otsikko: uusi vinkki, vinkin linkki: www.uusi.fi", lukuvinkki.toString());
    }

    @Test
    public void linkinPoistoOnnistuu() throws Exception {
        Linkki lisattava = new Linkki("uusi vinkki", "www.uusi.fi");
        Linkki lisattava2 = new Linkki("uusi vinkki2", "www.uusi2.fi");
        dao.lisaaLinkki(lisattava);
        dao.lisaaLinkki(lisattava2);
        dao.poistaLinkki(lisattava);
        //lasketaan mukaan myös kirjat
        assertEquals(5, dao.LukuvinkkienMaara());
    }

    @Test
    public void kirjanPoistoOnnistuu() throws Exception {
        Kirja lisattava = new Kirja("kirja", "kirjailija", 1997, "tammi", "www.kirja.net");
        Kirja lisattava2 = new Kirja("kirja2", "kirjailija2", 1992, "tammi", "www.kirja.net");
        dao.lisaaKirja(lisattava);
        dao.lisaaKirja(lisattava2);
        dao.poistaKirja(lisattava);
        //lasketaan mukaan myös kirjat
        assertEquals(5, dao.LukuvinkkienMaara());
    }

    @Test
    public void tietokannanTyhjentaminenOnnistuu() throws Exception {
        dao.tyhjennaTietokanta();
        assertEquals(0, dao.listaaKaikki().size());
    }
    @Test
    public void poistaKirjaToimii() throws Exception {
        Kirja lisattava = new Kirja("a", "abc", 2021, "tammi", "www.kirjat.net");
        dao.lisaaKirja(lisattava);
        dao.poistaKirja(lisattava);
        //lasketaan mukaan myös kirjat
        assertEquals(4, dao.LukuvinkkienMaara());
    }
    @Test
    public void merkkaaLuetuksiToimii() throws Exception {
        Kirja lisattava = new Kirja("a", "abc", 2021, "tammi", "www.kirjat.net");
        dao.lisaaKirja(lisattava);
        dao.poistaKirja(lisattava);
        //lasketaan mukaan myös kirjat
        assertEquals(4, dao.LukuvinkkienMaara());
    }

    @Test
    public void vieTiedostoonToimii() throws Exception {
        System.out.println("vieTiedostoonToimii() testi");
        File vertailtava = testFolder.newFile("vertailtava");

        File tuodaan = dao.vieTiedostoon();
        System.out.println(tuodaan.getName());

        try {
            FileWriter kirjoittaja = new FileWriter(vertailtava);
            for (Lukuvinkki vinkki : dao.listaaKaikki()) {
                kirjoittaja.write(vinkki.toString() + "\n");
                System.out.println("kirjoita");
            }
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("Tapahtui virhe.");
            e.printStackTrace();
        }

        byte[] verrattava = Files.readAllBytes(vertailtava.toPath());
        byte[] tuotu = Files.readAllBytes(tuodaan.toPath());
        assertArrayEquals(verrattava, tuotu);
    }
    
    @Test
    public void tiedostostaTuominenToimii() throws Exception {
        File tuotava = testFolder.newFile("uusi");
        
        try {
            FileWriter kirjoittaja = new FileWriter(tuotava);
            
            kirjoittaja.write("tuotavaVinkki;tuotavaUrli\n");
            kirjoittaja.write("tuotavaKirja;tuotavaKirjailija;1979;tuotavaTammi;tuotavaUrli\n");           
            
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("Tapahtui virhe.");
            e.printStackTrace();
        }
        
        dao.tuoTiedostosta(tuotava.getAbsolutePath());
        
        assertEquals(6, dao.listaaKaikki().size());
    }

    @After
    public void tearDown() {
        lukuvinkkiFile.delete();
    }
}
