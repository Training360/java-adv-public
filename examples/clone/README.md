# A clone() metódus, deep clone

Az objektum klónozásának célja, hogy olyan új objektumot hozzunk létre, amely állapota megegyezik az eredeti objektum állapotával. A `Cloneable` marker interfésszel jelezhetjük, hogy az osztályunk példányai klónozhatók. A klónozást a `clone()` metódus implementálásával érhetjük el, mely nem a `Cloneable` interfészben, hanem az `Object` osztályban van deklarálva `protected` metódusként. Ennél jobb gyakorlat, ha copy konstruktort készítünk.

```java
public class Auction {

    private Product product;

    private User user;

    private LocalDateTime start;

    private double price;

    // Konstruktorok, getter, setter metódusok

    public Auction(Auction auction) {
        product = auction.product;
        user = auction.user;
        start = auction.start;
        price = auction.price;
    }

}
```

Láthatjuk, hogy a copy konstruktor az `Auction` egy példányából úgy készít újat, hogy a referencia változói ugyanarra az objektumra mutatnak. Ezt a másolási módot **shallow copy**-nak nevezzük. A primitív típusú változókból másolat készül, a referencia típusú attribútumokon viszont osztozkodik a klónozott objektummal. Ha ez immutable, akkor nem jelent gondot, de ha nem az, akkor bármelyiken történő változtatás kihat mind a klónozott, mind a klón objektumra.

Másik megvalósítási mód a **deep copy**, amikor a referencia típusú attribútumokat is klónozzuk.

```java
public Auction(Auction auction) {
    product = new Product(auction.product);
    user = auction.user;
    start = auction.start;
    price = auction.price;
}
```

## Ellenőrző kérdések

* Mit értünk objektum klónozás alatt?
* Mi a standard megoldás?
* Mit érdemes inkább alkalmaznunk?
* Mit kell eldöntenünk a klónozás implementálásakor?

# TimeSheetItem klónozása

A `clone.timesheet.TimeSheetItem` osztály tartalmazza, hogy egy alkalmazott mikor, min dolgozott. Van egy `employee`, `project`, `from` és egy `to` attribútuma.

Legyen egy konstruktora, mely ezekkel az adatokkal inicializálja. Azonban legyen egy copy konstruktora is, mely paraméterül kap egy `TimeSheetItem` példányt, és annak adatait átmásolja az új példányba.

Legyen egy statikus `withDifferentDay(TimeSheetItem, LocalDate)` metódusa is, mely lemásolja a paraméterként átadott bejegyzést, azonban a `from` és `to` attribútumokban szereplő dátumokat kicseréli a másodikként megadott napra, de az időket érintetlenül hagyja. Ez a statikus metódus hívja a copy konstruktort.

 [rating feedback=java-clone-timesheetitem]

# Gyakorlat - Issue klónozása

Egy hibabejelentő rendszer egy alkalmazással kapcsolatosan bejelentett hibákat tartalmazza.

Legyen egy `clone.issuetracker.Issue` osztály, mely a rendszerben lévő hibákat reprezentálja, egy `name` attribútummal, `LocalDateTime time` és egy `Status status` attribútummal. A `Status` egy enum `NEW`, `IN_PROGRESS` és `CLOSED` értékekkel.

Az `Issue` tartalmazzon egy `clone.issuetracker.Comment` listát. A `Comment` tartalmazzon egy `String text` és egy `LocalDateTime time` attribútumot.

A `Issue` osztálynak legyen egy copy konstruktora, mely kap egy másik `Issue` példányt, valamint egy `CopyMode` enum értéket. Ez vagy `CopyMode.WITH_COMMENTS` vagy `CopyMode.WITHOUT_COMMENTS`. Előbbi esetben
a megjegyzéseket is másolja, utóbbi esetben nem.

A megjegyzések is copy konstruktorral legyenek másolhatóak, és ez kerüljön meghívásra (deep copy).

 [rating feedback=java-clone-issue]
