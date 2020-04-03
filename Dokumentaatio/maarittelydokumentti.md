## Aihe
---
[15-ongelman](https://en.m.wikipedia.org/wiki/15_puzzle) yksinkertainen toteutus, ja algoritminen ratkaisu. Ratkaisussa käytetään [A*-algoritmia](https://en.wikipedia.org/wiki/A*_search_algorithm).
Aluksi Tietorakenteista käytetään prioriteettijonoa, sekä jonoa, joista jono tosin voisi toteuttaa myös tavallisena listana.
---

Ohjelmalle annetaan syötteenä ratkaistava ruudukko joka halutaan ratkaista. Ohjelma jokaisessa kohdassa etsii mahdolliset siirrot, 
ja lisää kaikista niistä syntyvän tilanteen prioriteettijonoon. Prioriteettijonossa käytetään f-arvoa, joka on tähän tilanteeseen tarvittujen 
askeleitten sekä h-arvon summa. H-arvo on ruutujen etäisyyksien summa niiden määränpäästä. Seuraavaksi tutkittavaksi valitaan aina listalla oleva
pienimmän f-arvon omaava tila. Palautuksena tulee lista siirroista joilla ongelma ratkeaa. 

Pyrkimyksenä on ratkaista mikä tahansa 5x5 taulukko alle sekunnissa. Algoritmin tilavaativuus on 2^d, missä d on ratkaisun askeleitten määrä. 
