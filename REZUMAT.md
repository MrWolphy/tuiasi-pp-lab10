# Rezumat — Lab 10

Am rezolvat cele trei teme pe rând, cu commit și push după fiecare, și toate cele 23 de teste trec: lanțul CEO → Executive → Manager → HappyWorker cu handler-e `suspend` care deleagă prin `async`/`await` (plus factory-urile), pipeline-ul cu două canale `BUFFERED` și factorialul cu 4 consumatori `async` dintr-un `Channel`.
Am greșit de trei ori și am corectat singur: în `ExecutiveHandler` am folosit `launch` în loc de `async`, deși aveam nevoie de rezultat, și n-a compilat (`Job` în loc de `String`); în pipeline am uitat `close()` pe primul canal și testul s-a blocat; la factorial am scris dintr-un `mutableMapOf` de pe mai multe fire (`Dispatchers.Default`), un data race pe care testele nu-l prindeau, și l-am înlocuit cu `ConcurrentHashMap`.
Am presupus că răspunsul „urcă” spre CEO prin valoarea returnată de `suspend fun`, cu `previous` setat doar ca legătură, că „etapa 3: afișare” înseamnă `println` și colectarea în listă, și că factorialul trebuie să încapă în `Long` (corect până la n = 20, fără verificare de overflow).
La nivel de 9 am lăsat: `async { ... }.await()` imediat în handler-e (cerut de enunț, dar fără câștig real de concurență), cod duplicat între Executive și Manager (nicio clasă de bază comună), log-uri cu `println` și niciun test în plus față de cele primite.

Timp de implementare: 4 minute (fără pregătirea mediului).
