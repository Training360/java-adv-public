# Comparator és Comparable

Ahhoz, hogy egy osztály példányait rendezni tudjuk, tudnunk kell, melyik számít kisebbnek. Ezt számok esetén már természetesnek vesszük, a `String`-ek esetén viszont már nem működik mindig jól az alfabetikus rendezés, hiszen a karakterkódok szerint az `a` betű nagyobb, mint a `Z`, az ékezetesekkel meg még több probléma van. Két `Person` objektum közül meg nem is tudjuk, melyik számít nagyobbnak. Aki idősebb, vagy aki magasabb? Vagy esetleg a névsorrend számít?

Bárhogy is döntünk, ezt tudatnunk kell a futtatókörnyezettel, hogy el tudja végezni a rendezést. `TreeSet` és `TreeMap` megköveteli tőlünk, hogy az elemek, illetve a kulcsok rendezhetőek legyenek. Döntésünket kétféle módon közölhetjük. Az osztályunk implementálhatja a `Comparable` interfészt vagy átadhatunk a rendező metódusnak vagy a kollekció konstruktorának egy `Comparator` interfészt implementáló osztályt.

## `Comparable` interfész

```java
public interface Comparable<T> {
  public int compareTo(T o);
}
```

Amikor az osztályunk implementálja ezt az interfészt, akkor az általa megvalósított rendezettséget hívjuk természetes sorrendnek (*natural order*). Ilyen módon kizárólag egyféle rendezettséget valósíthatunk meg, de az akár több attribútum értékét is figyelembe veheti. A `compareTo()` metódus az osztály egy példányát kapja paraméterként és egy egész számot kell visszaadjon, mely negatív, ha a hívó példány kisebb, mint a paraméterül kapott, 0, ha egyenlők és pozitív, ha a paraméter számít nagyobbnak. Jó gyakorlat úgy megalkotni ezt a metódust, hogy a megfelelő attribútumra delegáljuk a hívást.

Figyeljünk arra, hogy a `compareTo()` és az `equals()` metódus összhangban legyen! Ha az `equals()` metódus igazzal tér vissza, akkor a `compareTo()` metódus értéke 0 legyen és viszont.

A következő példa a `Trainer` objektumokra az id alapján definiál növekvő rendezettséget.

```java
class Trainer implements Comparable<Trainer> {

    private int id;

    private String name;

    @Override
    public int compareTo(Trainer other) {
        return id.compareTo(other.id);
    }
}
```

## `Comparator` interfész

A `Comparator` előnye, hogy egyféle osztályhoz többféle rendezettséget is definiálhatunk, illetve olyan osztályok rendezettségét is definiálhatjuk, amelyek nem implementálják a `Comperable` interfészt. A `Comparator` interfész egyetlen metódust tartalmaz, a `compare()`-t, mely a két összehasonlítandó objektumot kapja meg paraméterként, és egy egész számmal tér vissza.

```java
public interface Comparator<T>{
	int compare(T o1, T o2);
}
```

Ezt az osztályt felhasználhatjuk a kollekciók rendezésekor, illetve `TreeSet` és `TreeMap` esetén a konstruktornak átadva ez adja meg a rendezési szempontot.

A `Trainer` osztályhoz definiálhatunk egy név szerinti rendezettséget is.

```java
public class NameComparator implements Comparator<Trainer> {

    public int compare(Trainer o1, Trainer o2) {
		    return o1.getName().compareTo(o2.getName());
	  }
}
```

## `Collator` absztrakt osztály

Említettük, hogy a `String`-ek rendezése a különböző ékezetes és speciális karakterek miatt nehézkes. Erre nyújt megoldást a `Collator` absztrakt osztály, mely implementálja a `Comparator` interfészt, és figyelembe veszi a helyi adottságokat, vagy az átadott `Locale` objektumot.

Ha a fenti `Trainer` osztályban a nevek a magyar ékezeteseket is tartalmazhatnak, akkor a `NameComparator` már nem fog helyesen működni, módosítanunk kell a kódon. A megfelelő `Collator`-t a `Collator.getInstance()` metódussal kérhetjük le.

## Ellenőrző kérdések

* Mit takar a "natural ordering"?
* Hogyan kell használni a `Comparable` interfészt?
* A `compareTo()` metódus visszatérési értékei mit jelentenek?
* A `compareTo()` metódusnak milyen más metódussal kell összhangban lennie?
* Milyen osztályokat ismersz, amelyek implementálják a `Comparable` interfészt?
* Mire való a `Collator`?
* Hogyan lehet hozzájutni egy `Collator` példányhoz?

## Gyakorlati feladat - OrderedLibrary

Készítsünk olyan osztályt, ami egy könyvtárban tárolt könyvek szoftveres menedzselését valósítja meg. A könyvtárat feltöltjük könyvekkel, és kereséshez cím vagy szerő szerint rendezhetjük a könyveket. Alapértelmezett a cím szerinti rendezés.
Egy külön metódusban valósítsuk meg a könyvcímek magyar ékezetes karaktereket is figyelembe vevő rendezését.
Csak a könyvcímek kellenek, nem a könyvek!

## Megvalósítás

`Book` osztály `int regNumber` `String title` és  `String author` attribútumokkal.

A `public int compareTo(Book o)` metódust az igényeknek megfelelően készítsük el.

`OrderedLibrary` osztály `List<Book> libraryBooks` attribútummal. Ezt konstruktorból tudjuk feltölteni.

publikus metódusok:    
```java
 public OrderedLibrary(List<Book> libraryBooks)
 public List<Book> orderedByTitle()
 public List<Book> orderedByAuthor()
 public List<String> orderedByTitleLocale(Locale locale)
```

Egy külön osztályban (`AuthorComparator`) valósítsuk meg a szerző szerint történő rendezéshez szükséges `Comparator` osztályt.

[rating feedback=java-collectionscomp-orderedlibrary]


### Bónusz feladat - Collator

 Az ékezetes karaktereket is figyelembe vevő String rendezéshez a `Collator` osztály egy példányára lesz szükségünk. Nézz utána,
 ez hogyan állítható elő és hogyan paraméterezhető!

### Bónusz feladat - Comparator

 A `Comparator` objektumot többnyire névtelen osztály példányaként állítjuk elő. Nézz utána, ez hogyan valósítható meg!
