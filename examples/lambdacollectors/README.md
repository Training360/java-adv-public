# Collectors

Az leggyakrabban használt záró művelet a `collect()`, mely legtöbbször a stream elemeivel egy kollekciót ad vissza. `Collectors` paraméterrel hívva akár összesítést is tud végezni az elemeken.

## Összesítések

Az elemek összefűzése egyetlen szöveggé. Az elemeknek `CharSequence` típusúaknak kell lennie (a `String` is ez).

### `joining()`

`Collector<CharSequence,?,String> joining()`: atz elemek nincsenek elválasztva egymástól

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
System.out.println(ohMy.collect(Collectors.joining())); // lionstigersbears
```

`Collector<CharSequence,?,String> joining(CharSequence delimiter)`: az elemek közé a `delimiter` szöveg kerül

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
System.out.println(ohMy.collect(Collectors.joining(";"))); // lions;tigers;bears
```

`Collector<CharSequence,?,String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix)`: az elemek közé a `delimiter` kerül, az egész szöveg elé a `prefix`, utána a `suffix`

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
System.out.println(ohMy.collect(Collectors.joining(";", "Állatok: ", "")));
								// Állatok: lions;tigers;bears
```

### `counting()`

`<T> Collector<T,?,Long> counting()`: az elemek számát adja vissza.

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
System.out.println(ohMy.collect(Collectors.counting())); // 3
```

Önmagában nem használjuk streamekre, mert ugyanazt adja vissza, mint a `count()`, de nagyon hasznos, amikor csoportosításkor minden csoportra meg akarjuk határozni az elemszámot.

### `minBy()`, `maxBy()`

`<T> Collector<T,?,Optional<T>> minBy(Comparator<? super T> comparator)`: a comparator szerinti legkisebb elem, ha van

`<T> Collector<T,?,Optional<T>> maxBy(Comparator<? super T> comparator)`: a comparator szerinti legnagyobb elem, ha van

Ha nincs egyetlen elem sem, akkor mindkettő üres `Optional`-t ad vissza.

### `summingXXX()`, `averagingXXX()`

Mindkét metódusnak három verziója létezik, attól függően, hogy milyen típusú adatokra alkalmazzuk. XXX lehet `Int`, `Long` és `Double`.

`Collector<T,?,XXX> summingXXX(ToXXXFunction<? super T> mapper)`: az elemeket XXX primitív típusúra konvertálva összegzi azokat. A konvertáló függvényt paraméterben adjuk át. Üres streamre az eredmény 0.

`Collector<T,?,Double> averagingXXX(ToXXXFunction<? super T> mapper)`: az elemeket XXX primitív típusúra konvertálva átlagolja azokat. A konvertáló függvényt paraméterben adjuk át. Üres streamre az eredmény itt is 0.

```java
Stream<Integer> numberStream = Stream.of(2,7,3,5,9,10,-4);
System.out.println(numberStream.collect(Collectors.summingInt(x -> x.intValue()))); // 32
```

```java
Stream<Integer> numberStream = Stream.of(2,7,3,5,9,10,-4);
System.out.println(numberStream.collect(Collectors.averagingInt(x -> x.intValue()))); // 5.5714
```

### `summerizingXXX()`

`<T> Collector<T,?,XXXSummaryStatistics> summarizingXXX(ToXXXFunction<? super T> mapper)`: az elemeket XXX primitív típusúra konvertálva statisztikát készít róluk. XXX lehet `Int`, `Long` és `Double`

```java
Stream<Integer> numberStream = Stream.of(2,7,3,5,9,10,-4);
System.out.println(numberStream.collect(Collectors.summarizingInt(x -> x.intValue())));
// IntSummaryStatistics{count=7, sum=32, min=-4, average=4,571429, max=10}
```

## Kollekcióba gyűjtés

`<T> Collector<T,?,List<T> > toList()`: a stream elemeit listába gyűjti.

`<T> Collector<T,?,Set<T> > toSet()`: a stream elemeit halmazba gyűjti.

`<T,C extends Collection<T>> Collector<T,?,C>  toCollection(Supplier<C> collectionFactory)`: a stream elemeit a paraméterként átadott `collectionFactory` által előállított kollekcióba gyűjti.

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
TreeSet<String> result = ohMy.filter(s -> s.startsWith("t"))
                             .collect(Collectors.toCollection(TreeSet::new));
System.out.println(result); // [tigers] 
```

`<T,K,U> Collector<T,?,Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T,? extends U> valueMapper)`: a stream elemeit `Map`-be gyűjti. Paraméterként át kell adnunk a kulcsot és az értéket előállító függvényt. Ha két elemnél ugyanazt a kulcsot állítjuk elő, akkor `IllegalStateException`-t dob.

`<T, K, U> Collector<T,?,Map<K,U> toMap(Function<? super T,? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction)` : a stream elemeit `Map`-be gyűjti. Paraméterként át kell adnunk a kulcsot és az értéket előállító függvényt, valamint azt a függvényt, amely kulcsütközés esetén a már a `Map`-ben lévő és az új elemet egyesíti.

`<T,K,U,M extends Map<K,U>> Collector<T,?,M>  toMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier)` : ez előbbieken túl azt is átadjuk, hogy milyen `Map`-et szeretnénk visszakapni.

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
TreeMap<Integer, String> map = 
    ohMy.collect(Collectors.toMap(String::length, //keyMapper
                                  k -> k, //valueMapper
                                  (s1, s2) -> s1 + "," + s2, //mergeFunction
                                  TreeMap::new)); //mapSupplier
System.out.println(map); // {5=lions,bears, 6=tigers}
System.out.println(map.getClass()); // class. java.util.TreeMap 
```

## Partícionálás feltétel alapján: `partitioningBy`

`static <T> Collector<T,?,Map<Boolean,List<T> >> partitioningBy(Predicate<? super T> predicate)`:  a stream elemeit kettévállogatja aszerint, hogy megfelelnek-e az átadott `predicate`-nek. A két elkészült listát `Map`-be szúrj, ahol a kulcs a `true` és a `false` .

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, List<String>> map =
    ohMy.collect(Collectors.partitioningBy(s -> s.length() <= 5)); System.out.println(map); // {false=[tigers], true=[lions, bears]} 
```

`public static <T,D,A> Collector<T,?,Map<Boolean,D>> partitioningBy(Predicate<? super T> predicate, Collector<? super T,A,D> downstream)`: ha lista helyett mást szeretnénk használni, akkor második paraméterként megadhatunk egy `downstream` `Collector`-t, amely azt határozza meg, hogy az elemek milyen kolleckióba kerüljenek, illetve hogy egyáltalán mi kerüljön a `Map`-be értékként.

Például ha `Set`-be szeretnénk megkapni a partíciókat, akkor

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, Set<String>> map =
    ohMy.collect(Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
System.out.println(map);// {false=[], true=[lions, tigers, bears]}
```

Ha csak az elemek számát szeretnénk megkapni partíciónként, akkor

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, Long> map =
    ohMy.collect(Collectors.partitioningBy(s -> s.length() <= 7,
                                           Collectors.counting()));
System.out.println(map); // {false=0, true=3}
```

## Tulajdonság alapján csoportosítás: `groupingBy`

Működése és a paraméterei nagyon hasonlítanak a `partitioningBy` metóduséhoz, azzal az eltéréssel, hogy itt  a csoportok kulcsa nem csak logikai, hanem bármi más lehet, így akárhány csoportot képezhetünk. Az első paramétere éppen ezért `Function`, nem `Predicate`. Második paraméterként megadhatunk egy `Map`-et szolgáltató `Supplier`-t, ha meg akarjuk határozni, hogy milyen `Map`-et kapjunk.

`<T,K> Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T,? extends K> classifier)`

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, List<String>> map = 
    ohMy.collect(Collectors.groupingBy(String::length));
System.out.println(map); // {5=[lions, bears], 6=[tigers]} 
```

`<T,K,A,D> Collector<T,?,Map<K,D>> groupingBy(Function<? super T,? extends K> classifier, Collector<? super T,A,D> downstream)`

Ha nem listába, hanem halmazba szeretnénk gyűjteni a csoport elemeit:

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Set<String>> map =
    ohMy.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
System.out.println(map); // {5=[lions, bears], 6=[tigers]}
```

Ha nem az elemeket, hanem az elemek számát szeretnénk a `Map`-be értékként:

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Long> map =
    ohMy.collect(Collectors.groupingBy(String::length, Collectors.counting()));
System.out.println(map); // {5=2, 6=1} 
```

`<T,K,D,A,M extends Map<K,D>> Collector<T,?,M> groupingBy(Function<? super T,? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream)`

Ha a csopportokat tartalmazó `Map` `TreeMap` legyen, a csoportok elemei pedig `Set`-be kerüljenek:

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
TreeMap<Integer, Set<String>> map =
    ohMy.collect(Collectors.groupingBy(String::length,
                                       TreeMap::new,
                                       Collectors.toSet()));
System.out.println(map); // {5=[lions, bears], 6=[tigers]} 
```

## Elemek konvertálása összegyűjtés előtt `mapping`

Ha az összegyűjtött elemek nem egyeznek a stream elemeivel, de azokból valamilyen függvénnyel előállítható, akkor használjuk a `mapping` metódust.

`<T,U,A,R> Collector<T,?,R>  mapping(Function<? super T,? extends U> mapper, Collector<? super U,A,R> downstream)` 

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Optional<Character>> map =
    ohMy.collect(
    	Collectors.groupingBy(
            String::length, //a csoportosítás alapja a szöveg hossza
            Collectors.mapping(
                s -> s.charAt(0),
                Collectors.minBy(Comparator.naturalOrder()))));
/* Nem a szövegeket, hanem csak az első karaktereket gyűjtjük össze, és azokból is csak a "legkisebbet"*/
System.out.println(map); // {5=Optional[b], 6=Optional[t]}
```

## Ellenőrző kérdések

- Milyen fő csoportjai vannak a Collectors metódusainak?
- Sorolj fel öt összesítő metódust! Mit csinálnak?
- Hogyan lehet meghatározni a kimeneti kollekció fajtáját?
- Hogyan tudsz az eredményből csoportokat képezni?
- Hogyan lehet összegyűjtés előtt az elemeket másféle objektummá konvertálni?

## Gyakorlat - Kávézó v2

Készíts egy `Coffee` osztályt! Attribútumai: `type` a kávé típusa, `price` a kávé ára. A konstruktor is ebben a sorrendben kapja meg az adatokat. Az ár lehet tört, 2 tizedesjegy pontossággal számolj!

A kávé típusához készíts egy `CoffeeType` enum-ot. Lehetséges értékei: ESPRESSO, MACHIATTO, RISTRETTO, MOCHA, LATTE, CAPPUCCINO, AMERICANO.

A `CoffeeOrder` osztály tárolja egy vásárló által megrendelt és leszámlázott kávékat. Attibútumai: `coffeeList` a megrendelt kávék listája, `dateTime` a vásárlás időpontja.

A `Cafe` osztály tartalmazza a kávézó összes rendelését egy listában. A listát a konstruktorban kapja meg, de legyen lehetőség új rendelést hozzáadni. Készítsd el benne az alábbi metódusokat streamek segítségével:

`Map<CoffeeType, Long> getCountByCoffeeType()`: az eladott kávék mennyiségét adja vissza kávétípusonként

`double getAverageOrder()`: átlagosan hány kávét rendelnek egyszerre

[rating feedback=java-lambdacollectors-kavezov2]
