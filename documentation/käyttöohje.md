## Käyttöohje

Projekti käyttää Gradlea ja on tehty toimimaan Lichess-alustan kanssa. Ohjelman ajamista varten tarvitset Lichess API-tokenin (https://lichess.org/account/oauth/token/create). 

Voit ajaa ohjelman joko suoraan käyttäen gradlea tai luomalla jar-paketin ja ajamalla sen.

#### Ohjelman ajaminen suoraan gradlella

Ohjelman voi ajaa gradlen avulla komennolla

```
./gradlew run --args="--lichess --token=<lichess-token>"
```

#### Jar-paketin luominen ja ajaminen

Jar-paketin voi luoda komennolla 

```
./gradlew run build
```

Jar-paketti luodaan build/libs-kansion alle nimellä `chess-all.jar`

Jar-paketin voi ajaa komennolla

```
java -jar chess-all.jar --lichess --token=<lichess-token>
```