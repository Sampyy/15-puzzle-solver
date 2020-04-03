### Toteutus

Ohjelman ytimenä on luokka Solver, joka metodilla solve(ratkaistava ruudukko) ratkaisee ongelman joka on annettu yhtenä listana 
vasemmalta yläkulmasta oikealle lähtien. Metodi toimii kaiken kokoisiin ruudukoihin, kunhan rivien ja sarakkeiden koko on sama, joskin 
tehokkuus ei välttämättä riitä isompiin ruudukoihin.

Solve kutsuu lopussa metodia findSolution. FindSolutionissa luodaan prioriteettijono openSet, ja siihen lisätään alkutilanne AStarState luokkana.
AStarStatessa pidetään tyhjän ruudun paikkaa, g-arvoa, ruudukkoa sekä tähän tilaan johtanutta listaa siirroista.

FindSolutionissa käydään läpi looppia, jossa otetaan aina "paras" AStarState opensetistä, katsotaan siitä seuraavat siirrot sekä lisätään ne opensettiin.
Tätä jatketaan kunnes ratkaisuu löytyy, tai tilat loppuvat opensetistä, jolloin ongelma ei ole ratkaistavissa.