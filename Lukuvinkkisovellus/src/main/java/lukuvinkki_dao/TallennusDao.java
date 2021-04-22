package lukuvinkki_dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkisovellus.Linkki;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import lukuvinkkisovellus.Kirja;
import lukuvinkkisovellus.Lukuvinkki;

public class TallennusDao implements LukuvinkkiDao {

    private List<Lukuvinkki> lukuvinkit;
    private String tietokantaosoite;

    public TallennusDao(String tietokantaosoite) throws Exception {
        this.tietokantaosoite = tietokantaosoite;
        //Tietokannan luonti
        Class.forName("org.sqlite.JDBC");
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            System.out.println("Yhdistäminen tietokantaan onnistui!");

            s.execute("CREATE TABLE IF NOT EXISTS Linkit (id INTEGER PRIMARY KEY, otsikko TEXT, url TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS Kirjat (id INTEGER PRIMARY KEY, otsikko TEXT, kirjailija TEXT, julkaisuvuosi INTEGER, julkaisija TEXT, url TEXT)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        lukuvinkit = new ArrayList<>();
    }

    @Override
    public List<Lukuvinkki> listaaKaikki() {
        lukuvinkit.clear();
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet vinkit = s.executeQuery("SELECT * FROM Linkit");

            Connection db2 = DriverManager.getConnection(tietokantaosoite);
            Statement b = db2.createStatement();
            ResultSet kirjat = b.executeQuery("SELECT * FROM Kirjat");

            while (vinkit.next()) {
                lukuvinkit.add(new Linkki(vinkit.getString("otsikko"), vinkit.getString("url")));
            }
            while (kirjat.next()) {
                // Kirja(String otsikko, String kirjailija, int julkaisuvuosi, String julkaisija, String url)
                lukuvinkit.add(new Kirja(kirjat.getString("otsikko"), kirjat.getString("kirjailija"), kirjat.getInt("julkaisuvuosi"), kirjat.getString("julkaisija"), kirjat.getString("url")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lukuvinkit;
    }

    public List<Lukuvinkki> listaaKaikkiLinkit() {
        lukuvinkit.clear();
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet vinkit = s.executeQuery("SELECT * FROM Linkit");

            while (vinkit.next()) {
                lukuvinkit.add(new Linkki(vinkit.getString("otsikko"), vinkit.getString("url")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lukuvinkit;
    }

    @Override
    public void lisaaLinkki(Linkki lukuvinkki) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO Linkit(otsikko,url) VALUES(?,?)");
        String otsikko = lukuvinkki.getOtsikko();
        String url = lukuvinkki.getUrl();
        stmt.setString(1, otsikko);
        stmt.setString(2, url);
        stmt.execute();
        db.close();
        System.out.println("Lisääminen onnistui");
        lukuvinkit.add(lukuvinkki);

    }

    //Vaihdetaan myöhemmin koskemaan pelkkiä linkkejä
    public int LinkkienMaara() {
        int vinkkienMaara = -5;
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet linkit = s.executeQuery("SELECT * FROM Linkit");

            ArrayList<String> kaikki = new ArrayList<>();
            while (linkit.next()) {
                kaikki.add(linkit.getString("otsikko"));
            }

            vinkkienMaara = kaikki.size();

        } catch (SQLException ex) {
            Logger.getLogger(TallennusDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vinkkienMaara;
    }

    //Vaihdetaan myöhemmin koskemaan pelkkiä linkkejä
    @Override
    public int LukuvinkkienMaara() {
        return KirjojenLukumaara() + LinkkienMaara();
    }

    @Override
    public int KirjojenLukumaara() {
        int maara = 0;

        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet kirjat = s.executeQuery("SELECT * FROM Kirjat");

            ArrayList<String> kaikki = new ArrayList<>();
            while (kirjat.next()) {
                kaikki.add(kirjat.getString("otsikko"));
            }

            maara = kaikki.size();

        } catch (SQLException ex) {
            Logger.getLogger(TallennusDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maara;
    }

    @Override
    public void poistaLinkki(Linkki lukuvinkki) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("DELETE FROM Linkit WHERE otsikko = ?");

        stmt.setString(1, lukuvinkki.getOtsikko());
        stmt.execute();
        db.close();
        System.out.println("Lukuvinkki poistettu");
        lukuvinkit.remove(lukuvinkki);
    }

    @Override
    public void tyhjennaTietokanta() throws SQLException {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("DELETE FROM Linkit");
        stmt.execute();

        PreparedStatement stmt2 = db.prepareStatement("DELETE FROM Kirjat");
        stmt2.execute();

        db.close();
        System.out.println("Tietokanta tyhjennetty");
        lukuvinkit.removeAll(lukuvinkit);
    }

    @Override
    public void lisaaKirja(Kirja lukuvinkki) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO Kirjat(otsikko, kirjailija, julkaisuvuosi, julkaisija, url) VALUES(?,?,?,?,?)");
        String otsikko = lukuvinkki.getOtsikko();
        String kirjailija = lukuvinkki.getKirjailija();
        int julkaisuvuosi = lukuvinkki.getJulkaisuvuosi();
        String julkaisija = lukuvinkki.getJulkaisija();
        String url = lukuvinkki.getUrl();

        stmt.setString(1, otsikko);
        stmt.setString(2, kirjailija);
        stmt.setInt(3, julkaisuvuosi);
        stmt.setString(4, julkaisija);
        stmt.setString(5, url);
        stmt.execute();
        db.close();
        System.out.println("Lisääminen onnistui");
        lukuvinkit.add(lukuvinkki);
    }

    @Override
    public List<Lukuvinkki> listaaKirjat() {
        lukuvinkit.clear();
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet vinkit = s.executeQuery("SELECT * FROM Kirjat");

            while (vinkit.next()) {
                lukuvinkit.add(new Kirja(vinkit.getString("otsikko"), vinkit.getString("kirjailija"), vinkit.getInt("julkaisuvuosi"), vinkit.getString("julkaisija"), vinkit.getString("url")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lukuvinkit;
    }

    @Override
    public void poistaKirja(Kirja kirja) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("DELETE FROM Kirjat WHERE otsikko = ?");

        stmt.setString(1, kirja.getOtsikko());
        stmt.execute();
        db.close();
        System.out.println("Lukuvinkki poistettu");
        lukuvinkit.remove(kirja);
    }

}
