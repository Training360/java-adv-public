# Primitívek használata streamekben

A `Stream` interfészt objektumok feldolgozására hozták létre. Nagyon gyakran találkozunk azzal, hogy primitív típusú adatokat szeretnénk streamben feldolgozni. Mivel minden primitívhez létezik csomagoló osztály, ez nem probléma. Ha az elemek `int` típusúak, akkor abból `Stream<Integer>` lesz. Vannak azonban olyan számítások, amiket nagyon gyakran végzünk primitív típusú adatokkal, és némelyiket nem könnyű objektum streamen végrehajtani. Számok összegzésére már láttunk példát:

```java
Stream<Integer> numbers = Stream.of(1, 2, 3);
int sum = numbers.reduce(0, (s, n) -> s + n); //autounboxing a visszaadott Integerre
```

Ez primitív streammel:

```java
IntStream numbers = IntStream.of(1, 2, 3);
int sum = numbers.sum();
```

Háromféle primitív stream létezik:

- `IntStream`
- `LongStream`
- `DoubleStream`

Mindháromban megtalálhatók a `Stream` eddig megismert műveletei, bár kisebb-nagyobb eltérések előfordulnak. A műveletek paraméterei nagyon gyakran funcionális interfészek, amelyek szintén objektumokkal működnek. Éppen ezért a funkcionális interfészeknek is megvan a maga primitívekkel dolgozó megfelelője. Például egy objektumot szolgáltató `Supplier<T>` `int`, `short`, `byte` és `char` primitívvel dolgozó megfelelője az `IntSupplier`, `long` primitívre a `LongSupplier`, `double`-ra és `float`-ra a `DoubleSupplier`. Ahol a be- és kimenet más-más típusú, ott lehet az egyik primitív, a másik objektum, vagy mindkettő primitív, akár ugyanolyan, akár különböző típusú. Elsőre kicsit bonyolultnak hangzik, de lambda kifejezés használata esetén ez teljesen automatikus. 

Primitív stream létrehozása ugyanúgy történik, mint az objektum streamek esetén, kivéve, hogy kollekcióból nem tudunk közvetlen primitív streamet csinálni, mivel az csak objektumokat képes tárolni. 

```java
DoubleStream empty = DoubleStream.empty();
DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
DoubleStream random = DoubleStream.generate(Math::random);
DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2); 
```

Nagyon gyakori, hogy egymás utáni egészek sorozatával kell dolgoznunk, ezért ezek előállítására külön is van lehetőség.

`IntStream.range(int start, int end)`: `start` és `end` között egyesével növekvő számsorozat, `start`-ot beleértve, `end`-et nem.

`IntStream.rangeClosed(int start, int end)`: `start` és `end` között egyesével növekvő számsorozat, `start`-ot és `end`-et is beleértve.

```java
IntStream numbers = IntStream.range(2, 6); //2 3 4 5
IntStream numbersClosed = IntStream.rangeclosed(2, 6); //2 3 4 5 6
```

## Váltás különböző típusú streamek között

A `Stream` `map` metódusához hasonló, de primitív be- és/vagy kimenettel működő metódusok is léteznek.

Míg a `map` ugyanolyan típusú be- és kimenetet feltételez, a `mapToInt()` kimenete `IntStream`, `a mapToLong()` kimenete `LongStream`, a `mapToDouble()` kimenete `DoubleStream`, a `mapToObj()` kimenete `Stream`. Attól függően, hogy mi a bemenet és a kimenet, a kettő között konvertáló függvényt kell megadnunk. Az ennek megfelelő funkcionális interfészek összefoglalását az alábbi táblázatban találod.

| metódus       | bemenet     | kimenet        | paraméter típusa                   |
| ------------- | ----------- | -------------- | ---------------------------------- |
| `map`         | `Stream<T>` | `Stream<R>`    | `Function<? super T, ? extends R>` |
| `map`         | `XStream`   | `XStream`      | `XUnaryOperator`                   |
| `mapToInt`    | `Stream<T>` | `IntStream`    | `ToIntFunction<? super T>`         |
| `mapToInt`    | `XStream`   | `IntStream`    | `XToIntFunction`                   |
| `mapToLong`   | `Stream<T>` | `LongStream`   | `ToLongFunction<? super T>`        |
| `mapToLong`   | `XStream`   | `LongStream`   | `XToLongFunction`                  |
| `mapToDouble` | `Stream<T>` | `DoubleStream` | `ToDoubleFunction<? super T>`      |
| `mapToDouble` | `XStream`   | `DoubleStream` | `XToDoubleFunction`                |
| `mapToObj`    | `XStream`   | `Stream<R>`    | `XFunction<? extends R>`           |

X lehet `Int`, `Long` vagy `Double`

```java
Stream<String> objStream = Stream.of("penguin", "fish");
IntStream intStream = objStream.mapToInt(s -> s.length()); 
```

Nézz utána a többi primitívekkel dolgozó funkcionális interfésznek is!

## Gyakori számítások

Primitív típusú streameken a `min()` és `max()` metódusokon kívül létezik még kettő gyakran használt záró művelet, a `sum()` és az `average()`. A `sum()` üres streamre 0-t ad, az `average()` viszont nem tud értéket visszaadni. Erre találták ki az `Optional` burkoló osztályt, azonban az csak objektumokat tud fogadni. Primitívekre elkészült az `OptionalInt`, `OptionalLong` és `OptionalDouble` burkoló osztály. Ezekből az eredményt a típusától függően`getAsInt()`, `getAsLong()` vagy `getAsDouble()` metódussal lehet kinyerni, de minden más `Optional` metódus létezik benne.

Tudjuk, hogy a stream a záró művelet után megszűnik létezni, ezért ha több számítást szeretnénk végezni rajta, akkor a `summaryStatistics()` metódussal mindenféle összesítést tartalmazó `XXXSummaryStatistics` objektumot kapunk, ahol XXX a stream típusának megfelelően `Int`, `Long` vagy `Double`.

Műveletei:

- `getMin()`,
- `getMax()`,
- `getSum()`,
- `getAverage()`,
- `getCount()`.

Mindegyik mindig szolgáltat adatot, még üres streamre is. Üres streamre a minimum a típus legnagyobb értéke, a maximum a legkisebb, az átlag és az összeg pedig 0.

```java
IntStream integers = IntStream.range(1, 6);
IntSummaryStatistics stats = ints.summaryStatistics();
int max = stats.getMax();
int min = stats.getMin(); 
```

## Ellenőrző kérdések

- Milyen primitívekkel dolgozó streamek vannak, és melyik melyik primitív(ek)nek felel meg?
- Milyen módon lehet primitív streamet létrehozni?
- Hogyan lehet `Double` elemeket tároló listából primitív streamet készíteni?
- Milyen számítások végezhetők primitív streamekkel?
- Milyen primitívekkel dolgozó funkcionális interfészeket ismersz?

## Gyakorlat - Sportbolt

Hozz létre egy `Product` osztályt, amely a sportszer nevét, árát, darabszámát tárolja! A konstruktora is ezeket kapja meg ugyanebben a sorrendben. A `SportGadgetStore` osztály tárolja a termékek listáját, és különböző statisztikákat készít belőle. Az osztály kapja meg a listát kívülről. 

Készítsd el a következő metódusokat streamek segítségével:

`getNumberOfProducts()`: összesen hány termék van a boltban,

`getAveragePrice()`: átlagosan mennyibe kerül egy termék. Ha nincs termék, 0-t adjon vissza.

`getExpensiveProductStatistics(double minPrice)`: adott árnál drágább termékek darabszámáról szolgáltat statisztikát. Az összesítést szövegként adja vissza az alábbi formában:

`Összesen 3 féle termék, amelyekből minimum 1 db, maximum 52 db, összesen 74 db van.` 

Ha nincs ilyen, akkor a visszaadott szöveg a `Nincs ilyen termék.` legyen!

[rating feedback=java-lambdaprimitives-sportbolt]

