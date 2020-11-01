# DeepChess

Projektityö Tietorakenteet ja algoritmit harjoitustyöhön. Projekti forkattu shakki-pohjasta. Opinto-ohjelma TKT. Projektin kieli on suomi.

#### Tietorakenteet

Projektissa on tarkoitus kokeilla useita shakki-tekoälyjä:

- Minimax
- Alpha-beta karsinta
- Markovin päätöspuu/vahvistusoppimisen algoritmit

#### Ongelma

Projektissa yritetään kehittäää mahdollisimman hyvä shakkitekoäly aikataulun puitteissa. Kyseiset algoritmit valittiin, koska niitä on historiassa käytetty shakkitekoälyissä ja olen opiskellut niitä aiemmilla kursseilla.  

#### Käyttöliittymä

Shakkitekoäly pelaa Lichess-bottina ja käyttää Lichess-APIa siirtojen tekemiseen. Siirroissa yritettän tasapainoilla siirron laadun ja ajankäytön välillä

#### Tila- ja aika-vaativuudet

Minimax: aikavaativuus O(b<sup>m</sup>) ja tilavaativuus O(bm), b on laillisten siirtojen lukumäärä ja m on hakusyvyys

Alpha-beta karsinta: Minimax alpha-beta karsinnalla huonoin aikavaativuus on sama kuin minimaxilla, optimaalisella puun haarojen läpikäynnillä aikavaativuus O(b<sup>m/2</sup>)

Markovin päätöspuu: Ilmeisesti [NC](https://en.wikipedia.org/wiki/NC_(complexity)), mutta oma ymmärrykseni ei riitä analyysiin, [lähde](https://stuff.mit.edu/people/jnt/Papers/J016-87-mdp-complexity.pdf)

#### Lähteet

Tulen käyttämään projektissa seuraavia lähteitä:

- https://docplayer.fi/19360067-Koneoppiminen-shakkitekoalyissa.html
