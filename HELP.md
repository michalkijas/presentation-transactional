# Transactional Sandbox

### Podstawy
* @Transacitonal begin … commit
* częste błędy
* dziwne zachowania
* inne sposoby otwarcia sesji - zastąpienie Open session in view
* różne poziomy izolacji transakcji
* sprawdzić hotatkic z szkolenia Kubryńskiego - Hibernate
* [java-transactions](https://www.baeldung.com/java-transactions)
* [transaction](https://vladmihalcea.com/?s=transaction&submit=Go)


### Przykłady - oderwane od pracy



### Przykłady na bazie Mediesk - przetwarzanie zdarzeń?
* zamiana save() -> @Transactional - zastąpienie zapisu Lad i Inerakcji tylko zapisem Lead ?
* zakres tansakcji przy imporcie bazy pacjentów - co poszło nie tak
* [routing dla read-replica](https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/)


### Notatki
* projekt do testowania na przyszłość
* test architektury uwzględniający umieszczanie @transactional ?? private/public/protected/package + method/service
* zdarzenia DDD ???
* zapis encji + kolekcji (lead+interakcje)


Open session in view - controller vs service

[ArchUnit](https://www.archunit.org/userguide/html/000_Index.html)
