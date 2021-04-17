# **<div align = "center">Sudoku_Solver</div>**


## Vizija
>Sudoku predstavlja zagonetnu igru u vidu matrice 9x9. Cilj igre je da se popune sva prazna polja sa brojevima od 1 do 9.
>Jedino pravilo je da se brojevi ne mogu ponavljati ni u jednom redu, koloni ili 3x3 regionu.
>Sudoku_Solver predstavlja amaterski projekat programa za resavanje sudoku-a. Cilj ovog projekta je da se napravi slover bez upotrebe ulancavanja


## Alogritmi
>+ Sole Candidate (Modifikovan)
>+ Unique Candidate
>+ X Wing
>+ Y Wing
>+ Naked Subset (X I Y)


## Testiranje
>Testiranje vršeno primenom zagonetki sa Sudoku.com. Pokazalao se jako uspesnim, ukpno izvrsenih  testiranja 50. 
>Resenenost expertih je bila 82% (Pokušanih: 28 | Rešenih: 23)
>Resenenost teskih (Hard) je bila 95% (Pokusanih: 21 | Rešenih: 20)

### Sole candidate
>Kada određeno polje može da sadži samo  jedan broj.
>Modfikovano: 
>Ukoliko takovg polja nema bira se polje sa najmanjom listom mogućih brojeva. Pri poređenju sa “sličnim” poljima bira se broj koja ta polja  ne poseduju.
 
### Unique candidate
>Kada u nekom redu, koloni ili kvadrantu moguće staviti samo jedan određen broj.

### X_Wing
>Kada u redu imamo samo dva polja koja mogu da sadže  isti broj i vertikalno od njih imamo isto dva polja.  

### Y_Wing
>Kada u koloni imamo samo dva polja koja mogu da sadže isti broj i horizontalno od njih  imamo isto dva polja.  

### Naked_Subset (x i y)
>Kada u nekom redu/koloni imamo dva polja koji mogu samo da budu dva neka broja, oni čine Naked Subset. 
>Ostali brojevi u tom redu/koloni  i kvadrantu ne mogu biti taj broj.


