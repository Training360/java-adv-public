# equals, hashCode

Az `Object` osztálytól két nagyon fontos és gyakran használt metódust örököl minden osztály, az `equals` és a `hashCode` metódust.

## Az `equals()` metódus

Az `equals` metódus két objektum egyezőségét vizsgálja. Mivel az `==` operátor a változók tartalmát, ami objektum esetén a referencia, hasonlítja össze, ezért csak azt tudjuk megnézni, hogy két referencia ugyanarra az objektumra mutat-e. Ha az objektumok állapotát szeretnénk összehasonlítani, akkor az `equals` metódust kell használnunk. Az `Object` osztályban lévő `equals` még az `==` operátorral egyezően viselkedik, de az olyan osztályokban, mint a `String`, `ArrayList`, primitív burkolók már felülírták. Amikor saját osztályt készítünk, amit össze akarunk állapot szerint hasonlítani, akkor nekünk is felül kell definiálnunk az alapértelmezett működést, hogy az attribútumokat vegye figyelembe egyezőség vizsgálatakor, még ha nem is az összeset. Például két személyt tekinthetünk azonosnak, ha megegyezik a személyi igazolvány számuk.

Mivel az `equals` objektumokon dolgozik, ezért példányból hívható. De vajon mi történik az alábbi esetben?

```java
public static void main(String[] args){
    String str = null;
    str.equals("Text");
}
```

A program `NullPointerException` kivételt dob.

Amikor egy `String` típusú változót `String` literállal hasonlítunk össze, akkor jobb megoldás a literálon meghívni az `equals`-t, de ez nem megoldás, ha két változót akarunk összehasonlítani. Ilyenkor mindig vizsgáljuk meg előbb, hogy nem `null` értéken akarjuk hívni az `equalst`, illetve használhatjuk az `Objects` osztály statikus `equals` metódusát, mely a két összehasonlítandó objektumot várja paraméterként. Ha valamelyik `null`, akkor az `Objects.equals` `false` értékkel tér vissza, ha mindkettő `null`, akkor `true`-val. Minden már esetben az első paraméter `equals` metódusát használja.

### Szabályok az `equals()` írásakor

1. Az `equals` reflexív, azaz ha `x` nem `null`, akkor `x.equals(x)` `true` értéket kell visszaadjon.
2. Szimmetrikus, azaz ha `x` és `y` nem `null`, akkor `x.equals(y)` pontosan akkor `true`, ha az `y.equals(x)` `true`.
3. Tranzitív, azaz bármely `x`, `y` és `z` nem `null` változóra ha `x.equals(y)` és `y.equals(z)` is `true` értéket ad, akkor az `x.equals(z)` is `true` értéket kell adjon.
4. Konzisztens, azaz minden `x` és `y` nem `null` értékű változóra, ha a két objektum állapota nem változik, akkor két független `x.equals(y)` hívás ugyanazt az eredményt kell adja.
5. Minden `x` nem `null` változóra az `x.equals(null)` `false` értékkel tér vissza.

```java
public class Card {
    private String suit;
    private String rank;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;	//1. szabály
        if (o == null || getClass() != o.getClass()) return false; //5. szabály és 2. szabály alapfeltétele

        Card card = (Card) o;

        if (!suit.equals(card.suit)) return false; //Most minden attribútumot figyelembe vesz
        return rank.equals(card.rank);
    }
}
```

A 3. és a 4. szabály magától értetődik, ha a fenti mintára készítjük el az `equals` metódust.

## A `hashCode()` metódus

A `hash()` függvényt tipikusan kriptográfiai algoritmusok használják. Tulajdonképpen bármilyen hosszú adatot egy adott hosszú adatra (pl. 0 és 65535 közötti egész számra) leképező függvény, amelyből maga az objektum nem állítható vissza. Elvárás, hogy ugyanarra az adatra ugyanazt az értéket adja, de két különböző adatra is kaphatjuk ugyanazt, még ha kis valószínűséggel is.

Javaban a `hashCode` metódus `int` értékkel tér vissza. Az `Object` `toString` metódusa ezt írja ki a `@` jel után. Kollekciók esetén azért jó, mert az objektumok a `hashCode`-juk alapján osztályozhatóak, így sok adat esetén egyetlen visszakeresése sokkal gyorsabb lehet.

A fenti kártya példában ha konkrét kártyát keresek egy összekevert pakliban, akkor végig kellene nézni egyenként a lapokat, míg a keresettet meg nem találjuk. Ha valamilyen szempont szerint rendezni lehetne a lapokat, akkor nagyon gyorsan megtalálnánk a keresettet, mintha egy bejegyzést keresnénk a szótárban. Sajnos a rendezettség nem mindig megoldható, de csoportokat képezni valamilyen tulajdonság alapján könnyebb. Például ha a lapok értéke szerint csoportosítunk, akkor a pikk 7-est nagyon gyorsan megtaláljuk, mert először csak az érték szerinti 13 csoport között kell megtalálnunk a 7-est, majd csoporton belül a pikket. A `hashCode` tulajdonképpen a csoportosítást végzi.

### Szabályok a `hashCode()` implementálásakor

Ha nem változik az objektum állapota, akkor a `hashCode` ugyanazt az értéket kell adja.

Ha két nem `null` változóra az `equals` metódus `true` értéket ad, akkor a `hashCode` metódus is ugyanazt kell adja.

Ha két nem `null` változóra az `equals` metódus `false` értéket ad, attól még a `hashCode` nem feltétlenül ad más értéket, de nagy valószínűséggel eltér.

Érdemes ugyanazon attribútumokra támaszkodni, mint az `equals` írásakor, de mindenképp csak az ott figyelembe vett attribútumok használhatóak `hashCode` generálásakor. Lehetőleg olyan attribútumokat vegyünk figyelembe, amelyek nem változnak a program futása során.

Az IDEA képes legenerálni az `equals` és a `hashCode` metódust is. Ehhez nyomjuk le az `Alt` + `Insert` billentyűkombinációt és válasszuk az *equals and hashCode* pontot. Az IntelliJ Default (vagy akár egy külső library) template-et választva megadhatjuk, hogy mely attribútumok legyenek figyelembe véve a metódusok generálása során. Segítségünkre lehet az `Objects.hash(Object... values)` metódus is.

## Ellenőrző kérdések

* Mi az `equals()` és `hashCode()` metódusok szerepe? Hogyan kapcsolódik az állapot fogalmához?
* Milyen szabályokat kell betartani az `equals` implementálásakor?
* Ki/mi határozza meg az objektum azonosságát?
* Mit ad az öröklődés az objektumoknak ezek esetében?
* A felülírás elmulasztása mit eredményez?
* Mire való a hash függvény?
* Milyen tulajdonságokkal rendelkezik egy hash függvény?
* Milyen szabályoknak kell megfelelni a `hashCode()` metódusok implementálásakor?
* Mi van, ha eltérnek  az `equals` és a `hashCode` által felhasznált mezők?
* Miért nem dob kivételt a fenti metódusok hiánya illetve a felülírás elmulasztása?

## Gyakorlati feladat - `equals` és `hashCode` metódusok kipróbálása

A `Book` osztály objektumait rakjuk bele `List` és `Set` kollekció típusokba.
Vizsgáljuk meg, hogy mely tesztesetek futnak le sikeresen, és melyek nem a `Book` osztályban felülírt,
vagy éppen kihagyott `equals` és `hashCode` metódusok esetén.
Értelmezzük a tapasztaltakat!

A `BookCatalog` osztályban a következő publikus metódusok találhatók
(ezek persze a tesztesetekből is következnek):

```java
public Book findBookByTitleAuthor(List<Book> books, String title, String author)
public Book findBook(List<Book> books, Book book)
public Set<Book> addBooksToSet(Book[] books)
```

### Hibakezelés

Használjuk ki a kollekciók `boolean contains(Object o)` metódusát! Ha a metódus nem találja a
 keresett objektumot, a visszatérési érték legyen `null`.

### Megvalósítás

A Book osztályban az `equals(Object o)`  és `hashCode()` metódusok megírásával
 és kikommentelésével (összes kombináció!)  próbáljuk ki az egyes tesztesetek sikeres vagy sikertelen lefutását.
 Értelmezzük a tapasztaltakat!


### Tippek

Értelmezzük, hogy mi történik akkor, ha a `List` kollekcióba azonos állapotú objektumokat töltünk.
Mit kapunk vissza keresés esetén?

[rating feedback=java-collectionsequalshash-metodusproba]
