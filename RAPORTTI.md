## Miniprojektin raportti

**Ryhmän jäsenet:**


Matias Aalto-Setälä (github mazaalto)

Valtteri Andström (github nettivastaava)

Eevi Bengs (github eevib)

Julia Bergman (github jullebli) 

Antti Halmetoja (github AnttiHal)



### Projektin aikana kohdatut ongelmat

#### Ensimmäinen sprintti

Ensimmäisessä sprintissä meillä oli teknisinä haasteina oikeanlaisen gradleprojektin luominen, lopulta päädyimme kopioimaan ohjaajan luvalla pohjaksi erään kurssin tehtävän. Emme saaneet projektipohjaa tukemaan Cucumberia joten aluksi ennen ongelman löytymistä meillä oli kaksi gradleprojektia. Näin ratkaistiin se että seuraavat voivat työstää taskejään. Build.gradlesta löytyi pähkäilyn jälkeen tylsä virhe ja tämän jälkeen pääsimme hyvin alkuun ja saimme kaikki user stroyt ja taskit toteutettua katselmointiin mennessä.

Prosessihallintaa ja projektityöskentelyyn vaikutti vahvasti se, että sprint backlogin täyttäminen oli meille uutta ja kipuilimme burndown-käyrän muodostamisessa. Taskien estimoiminen oli haastavaa kun meillä on vain vähän kokemusta ohjelmistoprojekteista ja ryhmätyöskentelystä. Emme ehtineet ohjaajan kanssa pidetyssä suunnittelutuokiossa puhua Definiton of Done:sta (DoD) vaan se jäi ryhmämme itse päätettäväksi. Prosessinhallinnan kannalta tämä olisi kannattanut päättää aivan alussa sillä se vaikutti merkittävästi prosessiin. Daily scrum oli vaikea pitää lyhyenä kun kaikkea suunnittelua ei tehty alussa. Teimme myös aluksi päätöksen, että emme työskentele branchien avulla vaan laitamme kaiken päähaaraan.

Käytimme koko projektin ajan retrospektiiveissa Mad, Sad, Glad -tekniikkaa ja Flinga-alustaa, jossa laitoimme digitaalisia muistilappuja eri kohtien alle. Ensimmäisen sprintin Mad-kohdan alla oli koronatilanteen aiheuttamia asioita: ei lähityöskentelyä ja lounaspaikat sekä kumpula ovat kiinni. Sad-kohdan alla olevissa muistilapuissa oli vaikeuksista löytää yhteensopivia aikoja työskennellä, Definition of Done (DoD) tehtiin aika myöhään ja työaikoja koettiin olevan hankala arvioida. Koimme että DoD:n tekeminen aiemmin olisi auttanut enemmän sprint backlogin ylläpitämisessä ja siinä, että milloin voimme sanoa taskien ja user storyjen olevan done. Glad-kohdan alle tuli paljon projektityöskentelyyn liittyviä asioita: kommunikointi eli viestintä tiimin kesken toimi, työt etenevät hyvin ja koodikatselmointi meni hyvin.

#### Toinen sprintti

Toisen sprintin haastavimmaksi tekniseksi ongelmaksi muodostui jarin tekeminen. Kuvittelimme sen olevan helppo homma ja suunnittelimme tekevämme sen katselmoinnin aamuna. Onneksi tiimin muitakin jäseniä saatiin paikalle auttamaan ongelman ratkomisessa ja ohjaajakin ehti auttaa. Jarissa varsinkin tiedostoon kirjoittamisessa oli haasteita.

Toisessa sprntissä emme jakaneet taskeja yhtä tarkkaan kuin ensimmäisessä ja tästä tuli myös pieniä ongelmia, että mitä on vielä tekemättä ja kuka tekee mitäkin. Saimme kuitenkin lopulta kaiken tehtyä ajallaan ja demokin onnistui erittäin hyvin. User storyihin tarvittava aika oli edelleen vaikeaa arvioida ja myös backlogin päivittäminen meinasi ajoittain unohtua. Tietokannan käyttöönotto sujui kuitenkin erittäin hyvin ja jopa helpommin kuin kukaan oli uskaltanut aavistaa etukäteen. Tästä sprintistä opimme ainakin sen, että taskeja ei pitäisi jättää user storyjen ulkopuolelle, vaan jokainen task pitäisi pyrkiä ujuttamaan johonkin user storyyn. 

Retrossa meillä oli samoja ilonaiheita kuin ensimmäisellä viikolla, kommunikaatio ja yhteistyö sujui hyvin. Sad-kohtaan lisättiin ainakin jarin tekeminen liian myöhään ja jacocon kanssa säätäminen pääsi mad-kategoriaan. 

#### Kolmas sprintti

Projektityöskentelyn ja prosessinhallinan kannalta tässä sprintissä osasimme jo paremmin muotoilla taskejä. Sprintin suunnittelumme oli tehokkaampaa kuin aiemmin ja nyt osasimme liittää kaikki taskit johonkin user storyyn. Työskentely tiimin kesken oli nopeampaa ja user storyt valmistuivat nopeammin. Testaaminen vei kuitenkin jälleen kerran yllättävän paljon aikaa ja se taisi olla ainoa asia, jossa hieman jäimme tavoitteestamme. Lisäksi meillä oli haasteita jatkuvan integraation kanssa. Meillä ei main branchi varsinaisesti hajonnut missään vaiheessa, mutta testikattavuus laski alle 80 %, jolloin codecov näytti punaista. Luulimmekin ensin, että joku on oikeasti hajalla, mutta lopulta asia selvisi. Eri branchien käyttö aiheutti myös merge konflikteja ja meillä ei ollut tarpeeksi selkeää suunnitelmaa branchien käyttöön vaan se muotoutui vasta sprintin edetessä. Loppudemo oli onnistunut ja olimme tiiminä tyytyväisiä aikaansaannokseemme. 

Koko ryhmä oli hieman harmissaan, että projekti loppui, oli niin huikeaa tehdä yhteistyötä muutaman viikon ajan. Kaikki olivat samaa mieltä siitä, että toisten opiskelijoiden naamojen näkeminen ja yhdessä tekeminen olivat yksi kurssin parhaista asioista mielenkiintoisen ja opettavaisen sisällön lisäksi. 

### Mitä opimme ja missä voi parantaa

Ryhmän mielestä miniprojekti oli kokonaisuutena onnistunut, opettavainen ja hyvin mielenkiintoinen. Projektin aikana opittiin paljon muun muassa gitin ja githubin käytöstä, ryhmässä tapahtuvasta sovelluskehityksestä, scrumin perusteista käytännössä ja ryhmätyöstä yleisestikin. Jokaisen sprintin jälkeen Glad-kohdan alla olivat toimiva kommunikaatio ja hyvä työn eteneminen. Onnistuimme kuitenkin korjaamaan lähes kaikki Sad-kategorian huomautukset aina seuraavassa sprintissä. Ainoastaan yhteisten työaikojen löytäminen pysyi haasteena projektin loppuun asti. Ottaen huomioon projektin keston ja viikoittaisten työtuntien pienen määrän, ei tämä kuitenkaan tunnu kovin suurelta puutteelta. 

Monta asiaa on helpompi parantaa kun tulevissa projekteissä tuskin on näin rajallista aikataulua ja meilläkin on kokemusta enemmän. Opimme muokkaamaan backlogeja työskentelyämme paremmin tukeviksi ja suunnitteluun, refaktorointiin sekä käyttäjämukavuuteen voi käyttää enemmän aikaa. Sen opimmekin että työskentelyyn oleva aika voi olla hyvin rajallista ja kannattaa olla maltillinen asiakkaalle annetuista lupauksista toteutettavien user storyjen suhteen. Saavutimme kuitenkin jokaisessa sprintissä asettamamme tavoitteet.

Tiimi kehittyi viestinnässä jatkuvasti ja saavutimme tavoitteet aikataulussa.

