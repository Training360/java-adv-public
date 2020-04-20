# Collections osztály (keresésen, rendezésen felüli metódusok)

A `Collections` osztály ún. utility osztály, azaz kollekciókhoz tartalmaz metódusokat. Már megismerkedtünk a kereső és rendező metódusaival, most ismerjük meg a többit is.

Az `emptyXxx()` metódusok üres kollekciót adnak vissza. Lehet `emptyList()`, `emptySet()` vagy `emptyMap()`, sőt néhány másik is. Ezek immutable kollekciókat adnak.

A `singletonXxx(T o)` metódusok egyelemű immutable kollekciót adnak vissza. Lehet `singleton(T o)`, `singletonList(T o)` és `singletonMap(K key, V value)`. Az első neve nem tartalmazza a kollekció típusát: `Set`-tel tér vissza.

A fenti két metóduscsoportnál figyeljünk arra, hogy elemeket sem hozzáadni, sem elvenni, sem módosítani nem lehet.

A `synchronizedXxx()` metódusok a paraméterül kapott kollekciók szálbiztos burkoló osztályával térnek vissza. Ezek mind nézetek, ami azt jelenti, hogy kapcsolatban vannak az eredeti kollekcióval, azaz az ezen történő változások megjelennek abban is és viszont. A szálbiztos kollekciók akkor is jól működnek, ha egyszerre több szál is módosítani próbálja a tartalmát.

Az `unmodifiableXxx()` metódusok a paraméterként átadott kollekció csak olvasható nézetét adják vissza, azaz ezek tartalma nem módosítható. Az eredeti lista módosítható marad, ha abban változtatunk, akkor az látszani fog az itt visszakapott listán is.

A listákon még további két gyakran használt metódust tartalmaz. A `reverse(List<?> list)` a paraméterül kapott listában megfordítja az elemek sorrendjét, a `shuffle(List<?> list)` pedig véletlenszerűen összekeveri az elemeket. Ez utóbbihoz második paraméterként átadhatjuk a keveréshez használt `Random` példányt is, ami mint már láttuk, seeddel irányítható véletlenszám generátor. Az így kapott keverés már kiszámítható és jól tesztelhető.

## Ellenőrző kérdések

* Mire való a `Collections` osztály?
* Milyen metódusokat ismersz? Keresésre, rendezésre?
* Hogyan hozol létre üres kollekciókat?
* Hogyan hozol létre egy elemből álló kollekciókat?
* Hogyan hozol létre szinkronizált burkoló példányokat?
* Hogyan hozol létre módosíthatatlan burkoló példányokat?
* Hogyan fordítod meg egy lista elemeinek sorrendjét?
* Hogyan kevered meg egy lista elemeit? Hogy lehet ez pszeudorandom?
* Az elemek sorrendjének megfordítása, vagy a keverés miért csak listákon működik?
* Mit jelent az, hogy a burkoló példányok nézetek? Milyen viselkedéssel rendelkeznek?

## Gyakorlati feladat - CollectionManager

Készítsünk olyan osztályt, ami a `Collections` osztály kiválasztott metódusai segítségével
"könyvtári szolgáltatásokat" nyújt.

## Megvalósítás

`Book` osztály `int id` `String title` és  `String author` attribútumokkal.

A `public int compareTo(Book o)` metódust az igényeknek megfelelően készítsük el.
Alapértelmezetten id alapján rendezi a `Book` objektumokat és az `equals()` metódus is ezen
az attribútumon alapul.

`CollectionManager` osztály `private List<Book> library` attribútummal. Ezt konstruktorból tudjuk feltölteni.

 publikus metódusok:    
```java
public CollectionManager(List<Book> library)
public List<Book> createUnmodifiableLibrary() //módosíthatatlan listát eredményez
public List<Book> reverseLibrary() //az eredeti lista másolatán dolgozik!
public Book getFirstBook() //a legrégebbi (legkisebb id) könyvet adja vissza
public Book getLastBook() // a legújabb (legnagyobb id) könyvet adja vissza
```

[rating feedback=java-collectionsclass-collectionmanager]
