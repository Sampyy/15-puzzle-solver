## Aihe

---
[15-ongelman](https://en.m.wikipedia.org/wiki/15_puzzle) yksinkertainen toteutus, ja algoritminen ratkaisu. Ratkaisussa käytetään [A*-algoritmia](https://en.wikipedia.org/wiki/A*_search_algorithm) sekä [IDA*-algoritmia](https://en.wikipedia.org/wiki/Iterative_deepening_A*).
---

Ohjelmalle annetaan syötteenä ruudukko, joka halutaan ratkaista. Ohjelma ratkaisee tämän käyttäen A* perustuvaa ratkaisua, sekä IDA*-perustuvaa ratkaisua ja palauttaa molemmista ratkaisun, sekä siihen kuluneen ajan.

#### Tietorakenteet

Työssä tarvitaan prioriteettijonoa, ArrayList toteutusta sekä normaalia jonoa.

#### Tavoitteet

Pyrkimyksenä on ratkaista mikä tahansa 5x5 taulukko sekunneissa.
