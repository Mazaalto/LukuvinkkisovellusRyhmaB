package lukuvinkkisovellus;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import lukuvinkki_dao.LukuvinkkiDao;
import lukuvinkki_dao.StubLukuvinkkiDao;
import lukuvinkki_dao.TallennusDao;
import static org.junit.Assert.*;

public class Stepdefs {

    //Tänne tulee Cucumber testit
    LukuvinkkiService lukuvinkkiService;
    LukuvinkkiDao lukuvinkkiDao;

    @Given("lukuvinkkisovellus is initialized")
    public void lukuvinkkisovellusIsInitialized() {
        lukuvinkkiDao = new StubLukuvinkkiDao();
        lukuvinkkiService = new LukuvinkkiService(lukuvinkkiDao);
    }

    @Given("lukuvinkki lisätään otsikolla {string} ja urlilla {string}")
    public void lukuvinkkiLisataan(String otsikko, String url) throws Exception {
        Lukuvinkki vinkki = new Lukuvinkki(otsikko, url);
        lukuvinkkiService.lisaaLukuvinkki(vinkki);
    }

    @When("lukuvinkki otsikolla {string} ja urlilla {string} lisätään")
    public void lukuvinkkiOtsikollaJaUrlillaLisataan(String otsikko, String url) throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki(otsikko, url));
    }

    @Then("arvon tulisi olla {int}")
    public void arvonTulisiOlla(Integer val) {
        assertEquals(val.intValue(), lukuvinkkiDao.LukuvinkkienMaara());
    }

    @Then("Järjestelmä palauttaa merkkijonon {string}")
    public void jarjestelmaPalauttaaMerkkijonon(String merkkijono) {
        assertTrue(lukuvinkkiDao.listaaKaikki().toString().contains(merkkijono));
    }

    @When("lukuvinkki otsikolla {string} poistetaan")
    public void lukuvinkkiPoistetaan(String otsikko) throws Exception {
        List<Lukuvinkki> lukuvinkit = lukuvinkkiService.listaaOtsikonPerusteella(otsikko);
        Lukuvinkki vinkki = lukuvinkit.get(0);
        lukuvinkkiService.poistaLukuvinkki(vinkki);
    }

    @Then("lukuvinkkiä ei löydy otsikolla {string}")
    public void lukuvinkkiaEiLoydyJosSeOnPoistettu(String otsikko) throws Exception {
        lukuvinkkiService.lisaaLukuvinkki(new Lukuvinkki("uusiVinkki", "vinkki.url"));
        assertEquals(0, lukuvinkkiService.listaaOtsikonPerusteella(otsikko).size());
    }

}
