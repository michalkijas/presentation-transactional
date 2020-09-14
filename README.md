# `@Transactional` Sandbox

## Transakcje
  * https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html
    * https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#transaction-declarative-attransactional-settings
  * https://www.baeldung.com/java-transactions
  * https://vladmihalcea.com/?s=transaction&submit=Go
#### Pitfals  
  * [5 common Spring @Transactional pitfalls](https://codete.com/blog/5-common-spring-transactional-pitfalls/)
    * [Checked and Unchecked Exceptions](https://www.codejava.net/java-core/exception/java-checked-and-unchecked-exceptions)
  * [Spring Transactional propagation modes](https://codete.com/blog/spring-transaction-propagation-modes/)
  * https://www.ibm.com/developerworks/java/library/j-ts1/index.html
  * https://www.javacodegeeks.com/2011/12/spring-pitfalls-transactional-tests.html

## Testowanie użycia annotacji `@Transactional` 
  * [ArchUnit - dokumentacja](https://www.archunit.org/getting-started)
  * [ArchUnit - gradle plugin](https://github.com/societe-generale/arch-unit-gradle-plugin)


## read-only and read-write operation
  * https://vladmihalcea.com/read-write-read-only-transaction-routing-spring/
  * https://fable.sh/blog/splitting-read-and-write-operations-in-spring-boot/
  * https://blog.pchudzik.com/201911/read-from-replica/
## disable autocommit
  * https://vladmihalcea.com/why-you-should-always-use-hibernate-connection-provider_disables_autocommit-for-resource-local-jpa-transactions/
  * https://dzone.com/articles/best-performance-practices-for-hibernate-5-and-spr
## Open session in view
  * https://vladmihalcea.com/the-open-session-in-view-anti-pattern/
  * [The OSIV Anti-Pattern](https://stackoverflow.com/questions/30549489/what-is-this-spring-jpa-open-in-view-true-property-in-spring-boot/48222934#48222934)

## Do zastanowienia się
* jak działą Transactional(readOnly=true) jeżęli sesja jest otwierana przez OpenSessionInView któ©y chyba domyślnie jest read-write i wymusza commit nawet jeżęli tylko dane są odczytywane
* zamiana save() -> @Transactional - zastąpienie zapisu Lad i Inerakcji tylko zapisem Lead ? -  zapis encji + kolekcji (lead+interakcje)
* zakres tansakcji przy imporcie bazy pacjentów - co poszło nie tak
* różne poziomy izolacji transakcji
* zdarzenia DDD ???
* sprawdzić notatkic z szkolenia Kubryńskiego - Hibernate

#### Derby DB Replica
http://db.apache.org/derby/docs/10.4/adminguide/cadminreplicstartrun.html

