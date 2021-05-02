# Viikkoraportti 6

#### Aikaansaannokset

Tein alpha-beta karsinnan loppuun, lisäsin Zobrist-hashingin transpositio-tauluja varten. Lisäin siirtojen alustavan järjestämisen alpha-beta-karsintaa varten

#### Ohjelma

Ohjelma osaa pelata shakkia laillisesti. Ohjelma osaa pelata shakkia alpha-beta-botilla. Ohjelma ei juurikaan kaadu enää pelin aikana ja ohjelma osaa pelata useamman pelin putkeen

#### Mitä opin?

&dash;

#### Mikä on epäselvää?

Ohjelman siirtojen laatu ja siirtoihin käytetty aika (+30 sekuntia per siirto syvyydellä 3) on surkea ja luulen, että alpha-beta karsinnassa on joku vika taikka heuristiikat ovat huonot siirtojen järjestyksessä.

#### Mitä teen seuraavaksi?

Lisään testejä ja yritän saada performanssitestejä myös aikaiseksi, jotta voin varmistaa alpha-beta-karsinnan vähentävän evaluoitujen siirtojen määrää ja siirtojen järjestämisen vähentävän niitä entisestään.