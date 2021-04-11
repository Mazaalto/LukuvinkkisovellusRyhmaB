# LukuvinkkisovellusRyhmaB
![GitHub Actions](https://github.com/mazaalto/LukuvinkkisovellusRyhmaB/workflows/Java%20CI%20with%20Gradle/badge.svg)
[![codecov](https://codecov.io/gh/Mazaalto/LukuvinkkisovellusRyhmaB/branch/main/graph/badge.svg?token=7SU2S7H2WX)](https://codecov.io/gh/Mazaalto/LukuvinkkisovellusRyhmaB)


Ohjelmistotuotannon 1 op harjoitustyö (miniprojekti). Javaa, Scrumia ja ketterää ohjelmistokehitystä.

[Ryhmän product backlog ja sprintin board](https://docs.google.com/spreadsheets/d/1jNElPr7eKHpAMRChD1hGu87NYBHCjy90qCQLWPjbxsk/edit#gid=0)

## Asennusohje

Käyttääksesi sovellusta sinun täytyy kopioida tämä repositorio koneellesi. Mene terminaalissa hakemistoon johon haluat sijoittaa repositorion kopion. Aja hakemiston sisällä komento
```
git clone https://github.com/Mazaalto/LukuvinkkisovellusRyhmaB.git
```
Jos haluat antaa repositoriolle oman nimen niin syötä komennon perään välilyönti ja haluamasi nimi.

## Käyttöohje

Mene terminaalissa repositorion kopiosi hakemistoon Lukuvinkkisovellus ja aja komento käynnistä sovellus komennolla
```
./gradlew run
```

Sovellusta käytetään tekstikäyttöliittymän avulla terminaalista. Sovellukseen voi tallentaa lukuvinkkejä ja tallennetut lukuvinkit voi nähdä listana. Sovelluksen käynnistämisen jälkeen sovellus listaa mahdolliset komennot ja pyytää valitsemaan niistä tai lopettamaan sovelluksen käytön. Jos valitaan komennoksi lukuvinkin lisääminen niin sovellus pyytää ensin syöttämään otsikon ja sitten lukuvinkin url-osoitteen. Jos komennoksi valitaan lukuvinkkien listaaminen niin terminaaliin tulostuu jokainen tallennettu lukuvinkki omalle rivilleen, otsikko ensin ja sitten lukuvinkin url-osoite. Sovelluksen käyttämisen voi lopettaa antamalla tyhjän syötteen eli painamalla enter.

Lukuvinkit pysyvät tallessa vaikka suljet sovelluksen.
