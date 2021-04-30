# LukuvinkkisovellusRyhmaB
![GitHub Actions](https://github.com/mazaalto/LukuvinkkisovellusRyhmaB/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/Mazaalto/LukuvinkkisovellusRyhmaB/branch/main/graph/badge.svg?token=7SU2S7H2WX)](https://codecov.io/gh/Mazaalto/LukuvinkkisovellusRyhmaB)


Ohjelmistotuotannon 1 op harjoitustyö (miniprojekti). Javaa, Scrumia ja ketterää ohjelmistokehitystä.

[Ryhmän product backlog ja sprintin board](https://docs.google.com/spreadsheets/d/1jNElPr7eKHpAMRChD1hGu87NYBHCjy90qCQLWPjbxsk/edit#gid=0)

Linkki [loppuraporttiin](https://github.com/Mazaalto/LukuvinkkisovellusRyhmaB/blob/main/RAPORTTI.md).

## Asennusohje

Käyttääksesi sovellusta sinun täytyy kopioida tämä repositorio koneellesi. Mene terminaalissa hakemistoon johon haluat sijoittaa repositorion kopion. Aja hakemiston sisällä komento
```
git clone https://github.com/Mazaalto/LukuvinkkisovellusRyhmaB.git
```
Jos haluat antaa repositoriolle oman nimen niin syötä komennon perään välilyönti ja haluamasi nimi.

## Käyttöohje

Mene terminaalissa repositorion kopiosi hakemistoon LukuvinkkisovellusryhmaB/Lukuvinkkisovellus ja mene hakemistoon ja käynnistä sovellus komennolla
```
./gradlew run
```

Sovellusta käytetään tekstikäyttöliittymän avulla terminaalista. Sovellukseen voi tallentaa lukuvinkkejä ja tallennetut lukuvinkit voi nähdä listana. Sovelluksen käynnistämisen jälkeen sovellus listaa mahdolliset komennot ja pyytää valitsemaan niistä tai lopettamaan sovelluksen käytön. Jos komennoksi valitaan lukuvinkin lisääminen niin sovellus pyytää ensin syöttämään otsikon ja sitten lukuvinkin url-osoitteen. Jos komennoksi valitaan lukuvinkkien listaaminen niin terminaaliin tulostuu jokainen tallennettu lukuvinkki omalle rivilleen, otsikko ensin ja sitten lukuvinkin url-osoite. Sovelluksen käyttämisen voi lopettaa antamalla tyhjän syötteen eli painamalla enter.

Lukuvinkit pysyvät tallessa vaikka suljet sovelluksen.

## Definition of done

- Testikattavuus yksikkötestien osalta on vähintään 80 % ominaisuuksien testaamiseen, eli esimerkiksi gettereitä ja settereitä ei testata. 
- User Storyjen definition of done on dokumentoitu Cucumberin fetureiksi, ne löytyvät [täältä](https://github.com/Mazaalto/LukuvinkkisovellusRyhmaB/tree/main/Lukuvinkkisovellus/src/test/resources/lukuvinkkisovellus)
- Koodi on selkeää ja helppolukuista
- Luokat, metodit ja muuttujat ovat nimetty kuvaavasti
- Asiakas voi seurata testikattavuutta ja koodin tilannetta Github Actionin avulla

# Releaset
Täältä löytyy ohjelman uusin release:[Final](https://github.com/Mazaalto/LukuvinkkisovellusRyhmaB/releases/tag/Final)
Lataa ohjelma koneellesi. Pääset käyttämään ohjelmaa ajamalla koneellasi jar-tiedoston komennolla java -jar Lukuvinkkisovellus.jar.  Ohjelma toimii jos olet samassa hakemistossa, jossa tiedosto on. Esim. Downloads. 

Linkki testikattavuusraportin [screenshottiin](https://github.com/Mazaalto/LukuvinkkisovellusRyhmaB/blob/main/Lukuvinkkisovellus/Screenshot%20from%202021-04-27%2014-11-31.png), voit myös ajaa testit komennolla ./gradlew jacocoTestReport kansiossa, jossa ohjelma sijaitsee.

