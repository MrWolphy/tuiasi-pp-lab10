# Rezumat Lab 10

Am implementat lanțul dublu CEO → Executive → Manager → HappyWorker cu handler-e `suspend` care fac `delay()` și deleagă prin `async { }.await()`, plus factory-urile, apoi pipeline-ul cu două `Channel<Int>` și factorialul cu 4 consumatori `async` care citesc dintr-un canal de lucru. Am greșit de două ori: în `ExecutiveHandler` am folosit `launch`, care întoarce un `Job` și nu un `String` (eroare de compilare, am trecut pe `async` + `await`), iar în pipeline am uitat `close()` pe primul canal, așa că etapa de sortare aștepta la nesfârșit și testele se blocau. Am presupus că „pasarea răspunsului pe lanțul ascendent” înseamnă propagarea valorii întoarse prin stiva de apeluri, nu apeluri explicite pe `previous`, iar „etapa de afișare” din Tema 2 înseamnă un `println` la colectare. La nivel de 9 am lăsat câteva lucruri: legătura `previous` nu este folosită efectiv, factorialul face overflow peste 20! fără vreo verificare, iar logarea e făcută cu `println`, fără un logger.

Verificarea automată de pe GitHub arată roșu la commit-urile Tema 1 și Tema 2 pentru că rulează toată suita (`gradle test`), iar testele temelor următoare pică până când sunt rezolvate; abia commit-ul Tema 3 ar trebui să fie verde.

Ore notate cu `date`: start 10:36:11 UTC (prima rulare a testelor), final 10:39:16 UTC (după commit-ul Temei 3).

Timp de implementare: 3 minute (fără pregătirea mediului).
