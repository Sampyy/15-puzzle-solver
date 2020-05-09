### Toteutus

Ohjelman ratkaisee ongelman joka on annettu yhtenä listana 
vasemmalta yläkulmasta oikealle lähtien. Metodi toimii kaiken kokoisiin ruudukoihin, kunhan rivien ja sarakkeiden koko on sama, joskin tehokkuus ei välttämättä riitä isompiin ruudukoihin. Ohjelma ratkaisee ongelman A*-algoritmilla sekä IDA*-algoritmilla.

A*-algoritmissa pidetään yllä prioriteeettijonoa openSet, johon lisätään aina tilanteessa seuraavat mahdolliset askeleet. Nämä järjestetään niiden f-arvon perusteella. Jonon ensimmäisenä on aina pienimmän f-arvon omaava tila, "paras arvaus". Tämä f-arvo lasketaan tilanteeseen johtavien askelten, sekä jonon h-arvon summana. H-arvo lasketaan kaikkien ruutujen etäisyyden omasta "maaliruudustaan" summana. Mikäli vastaus löytyy, palautetaan se listana, joka sisältää siirrot järjestyksessä kuvattuna "r=right, l=left, u=up, d=down".

IDA*-algoritmissa on arvo bound, joka alussa pistetään aloitustilan h-arvoon. Sitten suoritetaan loopissa rekursiivista hakua, jolla käydään syvyyshaulla läpi tiloja, leikkaamalla oksat pois siinä vaiheessa kun tilan f-arvo on suurempi kuin bound muuttuja. Nämä haut palauttavat tilan, mikäli se on lopputila, ja null muuten. Mikäli alkulooppiin palautuu null, kasvatetaan bound arvoa, ja suoritetaan haku uudelleen, nyt hieman suuremmalla bound arvolla.

