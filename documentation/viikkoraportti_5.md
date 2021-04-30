# Viikkoraportti 5

#### Aikaansaannokset

Sain RandomBotin pelaamaan peliä Lichessissä Stockfish-bottiani vastaan. Löysin useita bugeja ja korjasin ne. Refactoroin MoveUtilsin olemaan täysin laudasta tai puolesta riippumaton, kaikki funktiot saavat nämä nyt parametrina. Lisäsin laudalle tiedon linnottautumisten laillisuudesta, jota tarvitsin Minimax-bottia varten. Tein minimax-botin, en ehtinyt testaamaan sitä. Tein minimax-bottia varten kaksi eri laudan evaluaattoria. 

#### Ohjelma

Ohjelma osaa pelata shakkia laillisesti. Ohjelma osaa pelata (luultavasti) shakkia minimax-botilla.

#### Mitä opin?

Opin oliosuunnittelusta ja lautojen evaluoinnista

#### Mikä on epäselvää?

-

#### Mitä teen seuraavaksi?

Testaan seuraavaksi alpha-beta-karsinnan minimaxiin ja lisään testejä ja dokumentaatiota ohjelmaan.