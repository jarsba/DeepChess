## Testausdokumentti

Testuksen voi jakaa kahteen osioon: yksikkötestit sekä suorituskyky- ja laatutestit

#### Yksikkötestit

Yksikkötestien kattavuus on ~50% ja testeissä testataan suurin osa luokkien ydintoiminnallisuuksista. Jätin testaamatta Zobrist-hashing-luokan, koska en ehtinyt käyttämään sitä missään. Suurin osa luokkien funktioista on saanut yhdenstä pariin testiä. Yksikkötestit voidaan ajaa IDEssä tai ajamalla komento 

```
./gradlew run test
```

#### Suorituskyky ja laatutestit

Tärkein laatutesti oli kymmenet pelit, jota pelasin Lichess-alustalla botteja vastaan joko itse tai käyttäen Stockfish-bottia. Suorituskykytestit on myös implementoitu `PerformanceTest`-luokkaan, jossa vertaillaan Minimax-botin, Alpha-beta-botin siirtojen järjestelyllä sekä Alpha-beta-botin ilman siirtojen järjestelyä suoritusaikoja sekä evaluoitujen positioiden määrää. Suorituskykytestit eivät käytä Lichessiä, vaan tekevät siirtoja ainoastaan sisäisellä lauta-reprentaatiollaan. Testeihin voi vaihtaa laudan tilanteen vaihtamalla testin alussa määritellyn FEN-merkkijonon. 

`PerformaceTest`-luokan testit voi ajaa IDEssä tai komennolla

```
./gradlew run performanceTest
```