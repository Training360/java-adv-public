# Régi típusok, `Date`, `Calendar`, parse

#### Időzóna fogalma
Az időzóna a földfelszín azon területe, ahol az időmérő eszközök azonos időt mutatnak. Technikai okok miatt ezek általában ország-, államhatárokhoz vannak igazítva szélességi kör alapján. Az „origo” a korrigált világidő (UTC), régebbi nevén greenwichi középidő (GMT). Magyarország a téli-nyári időszámítás miatt télen a közép-európai időzónához (CET, UTC+1), nyáron a középeurópai nyári időzónához (CEST, UTC+2) tartozik. 

#### `Date` osztály

Javaban ez az osztály felelős a dátum kezelésre és az 1970 00:00:00 (GMT) óta eltelt időt tárolja ezredmásodpercben. A `Date` osztály a `java.util` csomagban helyezkedik el. Az osztály pédányosítható paraméter nélküli konstruktorral, de a háttérben valójában egy `System.currentTimeMillis()` hívás van amely visszaadja az 1970 óta eltelt időt, de ez egy `getTime()` metódussal le is kérdezhető.
A `Date` nem tárol időzónát, a rendszer időzónája alapján dolgozik. Az osztály immutable és nem használható adott idő létrehozására vagy műveletekre. Ezekre a `Calendar` interface-t használjuk.


#### `Calendar` interface

 Különböző naptárakkal tudunk dolgozni. A naptár definiálja  év, hónap illetve nap fogalmát. A `Calendar.getInstance()` metódus visszaadja a rendszer által használt naptárt (Magyarországon Gergely naptár - GregorianCalendar). 

#### `Calendar` beállításai
A `Calendar` implementációi módosíthatók.
 ```java
 c.set(2015, Calendar.JANUARY,1)
 ```
A fenti metódussal állíthatunk be dátumot, illetve akár időt is.

A `Calendar` tartalmaz konstansokat is `Calendar.YEAR`, `Calendar.MONTH`.  Ezeket természetesen `get()` metódussal le is tudjuk kérdezni. 

#### Calendar műveletei
*	`getTime()`-`Date` objektummá lehet konvertálni
*	`setTime(Date)`-Dátum alapján beállítani
*	`add(Calendar.YEAR,3)`-Háromévet hozzáad az adott évhez (nappal, hónappal, negatív számmal  is működik)
*	Összehasonlító műveletek: `after()`, `before()`
*	Lenient: túl nagy érték esetén engedi a túlcsordulást például 36. hónapot (kikapcsolható)
*	A hónapok 0-tól indexeltek

#### Stringből olvasás Stringé alakítás
A `Stringből` olvasáshoz a `DateFormat` interfacet és annak a `SimpleDateFormat` implementációját kell használni. Fontos a MM jelöli a hónapot és a mm jelöli a percet. 

```java

DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
try {
    Date date = dateFormat.parse("2017-04-04 12:00");
    System.out.println(dateFormat.format(date));
}
catch (ParseException pe) {
    pe.printStackTrace();
}
```
A `parse()` metódussal lehet Stringből dátumot, a `format()` metódussal pedig dátumból Stringet konvertálni. 



## Ellenőrző kérdések

* Mi az az időzóna?
* Mi a különbség a `Date` és `Calendar` objektumok között? Melyik mire való?
* Mit tárol a `Date`?
* `Date` példányosításával mi lesz annak az értéke?
* Hogyan hozhatunk létre kifejezetten `GregorianCalendar` objektumot? Mitől függ a `Calendar` objektum konkrét típusa?
* Miért ne használjuk a `Date` deprecated metódusait?
* Milyen `Calendar` konstansokat ismersz?
* Hogy lehet két dátumot összehasonlítani?
* Hogy lehet egy dátumot eltolni? (Pl. 5 napot hozzáadni?)
* Hogyan lehet dátumot formázni? Hogy lehet egy dátumot tartalmazó szöveget dátummá alakítani?
* Mi a `lenient` beállítás szerepe és mi a default értéke?

## Gyakorlati feladat - Nevezetes dátumok

Egy olyan osztályt akarunk létrehozni, amely életünk nevezetes dátumaival kapcsolatos extra információkat szolgáltat.
Például meg tudja mondani, a hét melyik napján születtünk, vagy akár csak azt,
hogy a nevezetes dátum hétköznapra vagy hétvégére esett. :)

### Hibakezelés

Biztosítsuk a teszteseteknek megfelelően, hogy illegális év, hónap és nap paraméter értékek esetén, valamint hiányos dátum és
formázó string paraméterek esetén dobjon `IllegalArgumentException` kivételt a megfelelő tájékoztató szöveggel,
illetve `null` paraméter esetén dobjon `NullPointerException`-t, szintén a megfelelő szöveggel.

### Megvalósítási javaslatok

Az osztály objektumait többféle módon is létre lehessen hozni.
Figyeljünk a `lenient` flag beállítására!

publikus metódusok:
```java
public DateOfBirth(int year, int month, int day)
public DateOfBirth(String dateString, String pattern, Locale locale)
public DateOfBirth(String dateString, String pattern)
public String findDayOfWeekForBirthDate(Locale locale)
public String toString(String pattern)
public boolean isWeekDay()
```
### Tippek

A paraméter string ellenőrzésére készüljön külön metódus, amit többször is fel tudunk használni.
Hasonlóképpen a konstrukció során többször használandó közös utasításokat szervezzük ki külön metódusba.

```java
boolean isEmpty(String str)
void setDateOfBirth(String dateString, DateFormat dateFormat)
```

[rating feedback=java-dateoldtypes-nevezetesdatumok]
