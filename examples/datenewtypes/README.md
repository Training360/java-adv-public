# Új típusok, `LocalDate`, `LocalTime`, parse

Ezeket a típusokat Java-8 ban vezették be és a `java.time` csomagban találhatóak. A `LocalDate` csak dátum, a `LocalTime` idő, míg a `LocalDateTime` idő és dátum tárolására alkalmas. Egyik sem tartalmaz időzónát erre a `ZonedDateTime` használható. Fontos, hogy mindegyik immutable. 

#### Használatuk
A `now()` metódussal lehet létrehozni, egy új dátumot. A `toString()` metódus is megfelelően implementálva van. Konkrét dátum `of()` metódussal adható meg.

Például:

```java
LocalDate date = LocalDate.of(2015, Month.JANUARY, 20);
LocalDate date = LocalDate.of(2015, 1, 20);
```
Ezek az osztályok nem engedik meg a túlcsordulást, ebben az esetben `DateTimeException`-t dob.

#### Műveletek

*	`plusXxx()`,`minusXxx()`
*	A műveletek láncolhatók
*	`DayOfWeek`, `Month enumok`
*	`isAfter()`, `isBefore()` összehasonlításra használhatóak

```java
LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
System.out.println(date); // 2014-01-20
date = date.plusDays(2);
System.out.println(date); // 2014-01-22
date = date.plusWeeks(1);
System.out.println(date); // 2014-01-29

LocalDate.of(2014, Month.JANUARY, 20).plusDays(2).plusWeeks(1);
```


### Átjárás a típusok között
```java
LocalDateTime localDateTime = LocalDateTime.now();
LocalDate localDate = localDateTime.toLocalDate();
LocalTime localTime = localDateTime.toLocalTime();
LocalDateTime newLocalDateTime = LocalDateTime.of(localDate, localTime);
```

#### Formázás és parse-olás
Itt is lehetőség van Stringből beolvasni illetve Stringé alakítani a dátumokat. Itt a `DateTimeFormatter` osztályt kell használnunk. 
Egy ilyet többféleképpen létrehozhatunk:

  *	Konstanssal:  `DateTimeFormatter.ISO_LOCAL_DATE`
  *	Lokalizált stílussal: `DateTimeFormatter.ofLocalizedDate(FormateStyle.SHORT)`
  *	Formátum Stringgel: `DateTimeFormatter.ofPattern("MM dd, yyy, hh:mm")`
     
A formázáshoz vagy a formatter vagy `LocalDateTime` metódusait hívhatjuk. A `DateTimeFormatter` immutable és szálbiztos. 
    
Nézzünk néhány példát a formázásra:
   
```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm");
LocalDateTime now = LocalDateTime.of(2017, Month.JANUARY, 1, 12, 0);
System.out.println(formatter.format(now));
System.out.println(now.format(formatter));

LocalDateTime start = LocalDateTime.parse("2017.01.01. 12:00", formatter);
System.out.println(start);
```

## Ellenőrző kérdések

* Mire valók a `LocalDate`, `LocalTime` és `LocalDateTime` osztályok?
* Mit értünk az alatt, hogy ezen osztályok immutable-ök?
* Mi történik, ha rossz a hónap, vagy a nap paraméter?
* Május hónapnak milyen integer érték felel meg?
* Mi a szerepe a `DayOfWeek` és `Month` enumoknak?
* Hogyan lehet módosítani az időt, dátumot tároló objektumokat?
* Hogyan lehet összehasonlítani őket?
* Hogyan hozhatunk létre egy `LocalDateTime` objektumot az aktuális idővel?
* Hogyan hozhatunk létre egy `LocalDateTime` objektumot egy előre megadott értékkel?
* Milyen módokon adhatunk meg formátum leírásokat?
* Hogyan formázhatjuk a `LocalDate`, `LocalTime` és `LocalDateTime` osztályok példányait?
* Hogyan alkalmazhatjuk a `Locale`-t a formázásoknál?

## Gyakorlati feladat - Születésnap

Életünk nevezetes dátumairól (`DateOfBirth` osztály) szeretnénk speciális információkat kapni,
mint például: a hét mely napján születtünk, eddig összesen hány napot éltünk,
vagy a saját és barátunk/párunk születése között pontosan hány nap telt el.
A dátumokat tetszőlegesen formázott `String` formájában is szeretnénk látni.

### Hibakezelés

Üres pattern esetén dobjon `IllegalArgumentException`-t az adott metódus.
A `Locale` nem lehet `null`.
Ha rossz dátumot adtunk meg születési dátumként, a `countDaysSinceBirth()` metódus dobjon `IllegalStateException`-t

### Tippek

Használd a `ChronoUnit` enumot arra, hogy kiszámold, hány nap van két dátum között!
Szervezzük külön metódusba az azonos paraméterek ellenőrzését!

### Megjegyzés

Érdemes a dokumentációban átnézni a `LocalDate`, `LocalTime` osztályok nyújtotta lehetőségeket!

[rating feedback=java-datenewtypes-szuletesnap]

## Gyakorlati feladat - Találkozó

Fontos találkozó előtt állunk, és nem akarjuk lekésni, ezért tudnunk kell, hány perc van addig. Azzal is számolunk,
hogy az időpont módosulhat. Egy `Rendezvous` osztályt készítünk a funkciókhoz.

### Hibakezelés

Üres pattern esetén dobjon `IllegalArgumentException`-t. Hasonlóképpen a sikertelen
parszolás is dobjon `IllegalArgumentException`-t.
Ha elfeledkezünk az időpontról és már késő elmenni, a `countMinutesLeft()` metódus dobjon `MissedOpportunityException`-t.
Ezt nekünk kell megírni, ez is egy `RuntimeException`.

### Tippek

Használd a `ChronoUnit` enumot arra, hogy kiszámold, hány perc van két idő között!
Szervezzük külön metódusba a paraméterek ellenőrzését!

[rating feedback=java-datenewtypes-talalkozo]

## Gyakorlat - Szülinapok

Írj egy `FamilyBirthdays` osztályt, mely konstruktor paraméterül kap születésnapokat. Implementáld benne
az `isFamilyBirthday` és `nextFamilyBirthDay` metódusokat, a tesztben szereplő method reference-ek alapján.

A `isFamilyBirthday` visszaadja, hogy a paraméterként átadott dátum születésnap-e. A `nextFamilyBirthDay`
metódus visszaadja, hány nap van a legközelebbi születésnapig.

### Implementáció

Nézd meg `LocalDate` `query()` metódusát, hogy mit kap paraméterül. Használd a `ChronoUnit` osztályt annak meghatározására, hogy két dátum között hány nap telt el.

[rating feedback=java-datenewtypes-szulinapok]

## Gyakorlati feladat

Egy olyan osztályt - `DateOfBirth` - akarunk létrehozni, amely életünk nevezetes dátumaival kapcsolatos extra információkat szolgáltat.
Például meg tudja mondani, a hét melyik napján születtünk, vagy akár csak azt,
hogy a nevezetes dátum hétköznapra vagy hétvégére esett. :)
Az osztály `dateOfBirth` attribútuma tárolja a kérdéses nevezetes dátumot, ebben az esetben a születésünk dátumát.
Arra is kíváncsiak lehetünk, hogy a 40. születésnapunk milyen napra fog esni (nem lehet elég korán elkezdeni a készülést).

### Megvalósítás

Az osztály objektumait többféle módon is létre lehessen hozni, számokkal vagy akár szöveges dátum formátumból.


publikus metódusok:
```java
public DateOfBirth(int year, int month, int day)
public DateOfBirth(String dateString)

public String findDayOfWeek()
public boolean isWeekDay()
public boolean wasItALeapYear()
public String findBirthDayOfWeekLater(int year)
```

### Tippek

Privát metódus segíthet abban, hogy a `findDayOfWeek` metódust eltérő paraméterezéssel újra tudjuk hasznosítani.
Érdemes utánanézni a `LocalDate` osztály leírásának a Java dokumentációban, és megismerkedni a `DayOfWeek` enum használatával is.

[rating feedback=java-datenewtypes-dateofbirth]

## Gyakorlati feladat 2

Phileas Fogg egy rendkívül precíz angol gentleman, aki a napjait percnyi pontossággal osztja be.
Nyelvtanára sajnos nem ennyire pontos, minden délelőtt 9 óra körül kezdi az órákat, és három 45 perces nyelvórát tart egy 25 perces szünettel.

Fogg úr percre pontosan szeretné tudni, hogy mikor fogják befejezni, és azt is, hogy be fogják-e fejezni déli 12 előtt az órákat. Segítsünk neki ebben!

Egy olyan osztályt - `DailyRoutine` - hozzunk létre, amelynek objektuma tárolja a mindenkori órakezdetet percnyi pontossággal, és ki tudja számolni,
mikor fejeződik be az oktatás, valamint azt is meg tudja mondani, hogy ez még déli 12 óra előtt megtörténik-e.

Az osztály `startTime` attribútuma tárolja a nyelvórák kezdetét, ez megadható óra és perc, de megadható standard szöveges formában is (hh:mm).


### Megvalósítás

Az osztály objektumait többféle módon is létre lehessen hozni, számokkal vagy akár szöveges dátum formátumból. Publikus metódusai segítségével
a tárolt időpont módosítható a befejezés időpontjára, és lekérdezhető, hogy ez az időpont hogyan viszonyul a déli 12 órához, azt megelőzi-e.


publikus metódusok:
```java
public DailyRoutine(int hour, int minute)
public DailyRoutine(String timeString)

public void setFutureTime(int minutes)
public boolean isBeforeNoon()
```


### Tippek

Érdemes utánanézni a `LocalTime` osztály leírásának a Java dokumentációban, és megismerkedni az osztály által biztosított `LocalTime.NOON` final static értékkel.

[rating feedback=java-datenewtypes-dailyroutine]
