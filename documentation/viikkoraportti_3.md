# Viikkoraportti 3

#### Aikaansaannokset

Olen tehnyt sääntöloogikan siirron suorittamiseen. Tilanteet, jossa oma kuningas on shakissa ovat vielä mahdollisesti kesken ja en passant siirtoja en ole tehnyt, mutta toivon ensi viikolla aloittavani mini-maxin alpha-beta karsinnalla.

#### Ohjelma

Ohjelma on kohta toimiva siinä mielessä, että se voi pelata shakkia laillisesti.

#### Mitä opin?

Opin paljon tällä viikolla shakkibotin koodaamisesta katsottuani asiaa käsittelevän Youtube-videon (https://www.youtube.com/watch?v=U4ogK0MIzqk). Opin Zobrist-hashingistä, Shannonin-numerosta, nappuloiden "heat-mapeista", mahdollisen siirtojen järjestämisestä "parhaimmasta" "huonoimpaan" ja transpositio-tilanteiden tunnistamisesta.

#### Mikä on epäselvää?

Pohdin kuinka paljon logiikkaa siirron tekemiseen lisätä vs "kokeile ja testaa onko siirto laillinen". Esimerkiksi jos valkoinen kuningas, valkoinen torni ja musta torni ovat kaikki samalla linjalla, valkoinen torni suojaa kuningasta shakilta eikä näin ollen valkoinen torni voi liikkua muuta kuin pysyen samalla linjalla. Mietin miten voisin valkoisen tornin siirtoja etsiessä mahdollisimman tehokkasti löytää linjan ja olla ehdottamatta muita siirtoja. Toinen vaihtoehto on vain tarkistaa jokainen mahdollinen siirto valkoiselle tornille. Selvää kuitenkin on, että vain lailliset siirot pitää ottaa huomioon mini-maxissa, koska ylimääräiset "laittomat puut" tuovat valtavia tehokkuustappioita.

#### Mitä teen seuraavaksi?

Testaan seuraavaksi kuinka laillisesti ja tehokkasti Random-botti pelaa ja korjaan virheitä sitä mukaan kuin löydän. Sen jälkeen siirryn tekemään mini-maxia alpha-beta-karsinnallla.