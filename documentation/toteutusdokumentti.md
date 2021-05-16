## Toteutusdokumentti

#### Ohjelman yleisrakenne

`datastructure`-paketin alta löytyy shakkibotin koodi. Koodi on seuraaviin kokonaisuuksiin:

- `board`-paketti sisältää laudan ja siirtojen logiikan
- `bots`-paketti sisältää shakkibotit
- `datastructures`-paketti sisältää yksinkertaiset tietorakenteet
- `evaluators`-paketti sisältää laudan tilanteen pisteyttämisen tekevät evaluaattorit  
- `hashing`-paketti sisältää Zobrist-hashingin
- `pieces`-paketti sisältää nappulat ja niiden logiikan

`PerformanceTest` sisältää performassitestit

#### Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)

&dash;

#### Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)

En ehtinyt testaamaan botteja (minimax, alpha-beta) muuta kuin itseäni ja StockFish12-bottia vastaan. Botit hävisivät molemmille pelaajille. Tarkoituksena oli yrittää selvittää suurinpiirteinen botin ELO-ranking käyttäen Stockfish-botin versioita mittana, mutta en ehtinyt toteuttamaan kattavaa laatutestausta. Alpha-beta-karsinta tekee syvyydellä 4 siirron keskimäärin 30 sekunnissa. Minimaxilla kesti siirron tekemiseen samalla syvyydellä noin 4 min 30 sekuntia. Siirtojen järjestämisellä en saanut Alpha-beta-botin suorituskykyä parannettua. Zobrist-hashinging koodin ehdin toteuttamaan, mutta en ehtinyt tekemään transpositio-tauluja. Näiden avulla suorituskykyä olisi voitu kasvattaa.

#### Työn mahdolliset puutteet ja parannusehdotukset

Ohjelma on tehottomampi ylimääräisten olioiden luonnin takia. Lauta olisi voitu tallentaa kokonaislukutaulukkona, mutta päätin keskittyä koodaamaan mahdollisimman helposti ymmärrettävää ohjelmaa ja toivoin myöhemmin refaktoroinnin olevan helppoa, jos suorituskyvyssä ilmenee ongelmia. En ehtinyt ottamaan käyttöön Zobrist-hashingiä ja siirtojen järjestelyyn liittyy joko bugi tai tehottomuus, joka vie kokonaan sen mahdollisen suorituskykyhyödyn. Sääntölogiikka ei ehdota en passant-siirtoja, mutta muuten pitäisi kattaa kaikki siirrot. 

#### Lähteet

Olen käyttänyt seuraavia lähteitä:

- https://www.youtube.com/watch?v=U4ogK0MIzqk
- 