## Yksikkötestaus

Yksikkötestauksen testikattavuutta voi katsoa painamalla grafiikkaa

[![codecov](https://codecov.io/gh/Sampyy/15-puzzle-solver/branch/master/graphs/badge.svg)](https://codecov.io/gh/Sampyy/15-puzzle-solver)


## Suorituskykytestaus

Testattu erilaisilla h-arvon kertoimilla, sekä erikokoisilla taulukoilla. Keskimääräisiä tuloksia ajoista sekunteina, sekä siirtojen määrästä. Testit suoritettu käyttäen 2048 MB heapia. OOM = Out of Memory.

#### A*

| Kerroin | 3x3 | 4x4 | 5x5 |           
| --- | --- | --- | --- |               
| 1   | 0,001, 3 | 2,3 | OOM
| 2   | 0,001, 3 | 0.538, 108 | 0.8, 190
| 3   | 0,001, 3 | 0.021, 120 
| 4   | 0,001, 3 | 0.617, 140


#### IDA*

| Kerroin | 3x3 | 4x4 | 5x5 |
| --- | --- | --- | --- |
| 1   | 0,0001, 3 | 8.584, 52| 115.362 |
| 2   | 0,0001, 3 | 0.007, 70 | 165.556, 142 |
| 3   | 0,0001, 3 | 0.023, 105 | 1.923, 213 |
| 4   | 0,0001, 3 | 0.005, 150 |
