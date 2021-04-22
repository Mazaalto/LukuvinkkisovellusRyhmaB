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

            s.execute("CREATE TABLE IF NOT EXISTS Linkit (id INTEGER PRIMARY KEY, otsikko TEXT, url TEXT, onkoLuettu INTEGER, milloinLuettu TEXT)");
            s.execute("CREATE TABLE IF NOT EXISTS Kirjat (id INTEGER PRIMARY KEY, otsikko TEXT, kirjailija TEXT, julkaisuvuosi INTEGER, julkaisija TEXT, url TEXT, onkoLuettu INTEGER, milloinLuettu TEXT)");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        lukuvinkit = new ArrayList<>();
    }

// Muutetaan myöhemmin listaamaan sekä linkit että kirjat.
    @Override
    public List<Lukuvinkki> listaaKaikki() {
        lukuvinkit.clear();
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            Statement s = db.createStatement();
            ResultSet vinkit = s.executeQuery("SELECT * FROM Linkit");

            while (vinkit.next()) {
                int onkoluettu = vinkit.getInt("onkoLuettu");
                Boolean luettu = false;
                if (onkoluettu==1) {
                    luettu=true;
                }
                lukuvinkit.add(new Linkki(vinkit.getString("otsikko"), vinkit.getString("url"), luettu, vinkit.getString("milloinLuettu")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lukuvinkit;
    }

    @Override
    public void lisaaLinkki(Linkki lukuvinkki) throws Exception {
        Connection db = DriverManager.getConnection(tietokantaosoite);
        PreparedStatement stmt = db.prepareStatement("INSERT INTO Linkit(otsikko, url, onkoLuettu, milloinLuettu) VALUES(?,?,?,?)");
        String otsikko = lukuvinkki.getOtsikko();
        String url = lukuvinkki.getUrl();
        int onkoLuettu = 0; // 0 = ei luettu, 1 = luettu;
        String milloinLuettu = null;
        stmt.setString(1, otsikko);
        stmt.setString(2, url);
        stmt.setInt(3, onkoLuettu);
        stmt.setString(4, milloinLuettu);
        stmt.execute();
        db.close();
        System.out.println("Lisääminen onnistui");
        lukuvinkit.add(lukuvinkki);

    }

    //Vaihdetaan myöhemmin koskemaan pelkkiä linkkejä
    @Override
    public int LukuvinkkienMaara() {
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
    public void poistaLinkki(Lukuvinkki lukuvinkki) throws Exception {
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
        PreparedStatement stmt = db.prepareStatement("INSERT INTO Kirjat(otsikko, kirjailija, julkaisuvuosi, julkaisija, url, onkoLuettu, milloinLuettu) VALUES(?,?,?,?,?,?,?)");
        String otsikko = lukuvinkki.getOtsikko();
        String kirjailija = lukuvinkki.getKirjailija();
        int julkaisuvuosi = lukuvinkki.getJulkaisuvuosi();
        String julkaisija = lukuvinkki.getJulkaisija();
        String url = lukuvinkki.getUrl();
        int onkoLuettu = 0; //0 = ei luettu, 1 = luettu;
        String milloinLuettu = null;
        stmt.setString(1, otsikko);
        stmt.setString(2, kirjailija);
        stmt.setInt(3, julkaisuvuosi);
        stmt.setString(4, julkaisija);
        stmt.setString(5, url);
        stmt.setInt(6, onkoLuettu);
        stmt.setString(7, milloinLuettu);
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
    public void merkkaaLuetuksi(Lukuvinkki lukuvinkki) {
        String otsikko = lukuvinkki.getOtsikko();
        String milloinLuettu = lukuvinkki.getMilloinLuettu();
        
        try {
            Connection db = DriverManager.getConnection(tietokantaosoite);
            PreparedStatement stmt = db.prepareStatement("UPDATE Linkit SET onkoLuettu = ? WHERE otsikko = ?");
            stmt.setInt(1, 1);            
            stmt.setString(2, otsikko);
            stmt.executeUpdate();

            PreparedStatement stmt2 = db.prepareStatement("UPDATE Linkit SET milloinLuettu = ? WHERE otsikko = ?");
            stmt2.setString(1, milloinLuettu);          
            stmt2.setString(2, otsikko);
            stmt2.executeUpdate();
            db.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
