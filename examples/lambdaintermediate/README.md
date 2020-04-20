# Részletek

A közbenső műveletek (intermediate operations) kimenete mindig stream, bár a benne lévő elemek típusa, száma, sorrendje változhat. Intermediate műveleteket láncolva hívhatunk, de vigyázzunk az olvashatóságra.

## `filter()`

`Stream<T> filter(Predicate<? super T> predicate)`

Csak a `predicate`-nek megfelelő elemeket engedi át, a többit kiszűri. A bemenő és a kijövő adatok típusa megegyezik.

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
s.filter(x -> x.startsWith("m")).forEach(System.out::print); //monkey
```

## `distinct()`

`Stream<T> distinct()`

Csak az `equals` metódus szerint különböző elemeket engedi át, azaz kiszűri a duplikációkat. A bemenő és a kijövő adatok típusa megegyezik.

```java
Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
s.distinct().forEach(System.out::print); // duckgoose 
```

## `limit()` és `skip()`

`Stream<T> limit(long maxSize)`

Csak a paraméterként átadott darabszámú elemet engedi át.

`Stream<T> skip(long n)`

A megadott számú elemet kihagyja, és csak a többit engedi át.

```java
Stream<Integer> s = Stream.iterate(1, n -> n + 1);
s.skip(5).limit(2).forEach(System.out::print); // 67 
```

## `map()`

`<R> Stream<R> map(Function<? super T, ? super R> mapper)`

Az elemeket a függvény segítségével egyenként átkonvertálja más típusú elemre. Primitív típusú streammé konvertáláshoz nem ezt a metódust használjuk. A stream feldolgozásának további részében az eredeti adat már nem elérhető.

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
s.map(String::length).forEach(System.out::print); // 676 


```

## `flatMap()`

`<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)`

A bemeneti stream tartalmazhat kollekciókat és streameket is. Ezek elemeit egyetlen streammé tudjuk konvertálni. Primitív streammé konvertáláshoz nem ezt használjuk.

```java
List<String> zero = Arrays.asList();
List<String> one = Arrays.asList("Bonobo");
List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
Stream<List<String>> animals = Stream.of(zero, one, two);
animals.flatMap(l -> l.stream()).forEach(System.out::println); 
```

`flatmap` nélkül a kimeneten a listák jelennének meg, így minden sorba egy-egy `String` kerül.

## `sorted()`

`Stream<T> sorted()`

`Stream<T> sorted(Comparator<? super T> comparator)`

A bemenő elemeket természetes sorrendjükben vagy a comparator által definiált sorrendben engedi át. Be kell várnia az összes elemet, ezért végtelen streamekre sosem áll le.

```java
Stream<String> s = Stream.of("brown-", "bear-");
s.sorted().forEach(System.out::print); // bear-brown 
```

```java
Stream<String> s = Stream.of("brown bear-", "grizzly-");
s.sorted(Comparator.reverseOrder())
    .forEach(System.out::print); // grizzly-brown bear- 
```

## `peek()`

`Stream<T> peek(Consumer<? super T> action)`

Menet közben belepillanthatunk a streambe. Nem csak az első elemén, hanem mindegyiken végrehajtódik az adott `action`. Fontos, hogy az `action` ne változtasson a stream állapotán. Főként debug célokra használható.

```java
Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
long count = stream.filter(s -> s.startsWith("g"))
    .peek(System.out::println)		// grizzly 
    .count();
System.out.println(count); 			// 1 
```

## Ellenőrző kérdések

- Mi jellemzi az intermediate műveleteket?
- Sorolj fel öt közbenső műveletet! Mit csinálnak?

## Gyakorlat - Kávézó

Készíts egy `Coffee` osztályt! Attribútumai: `type` a kávé típusa, `price` a kávé ára. A konstruktor is ebben a sorrendben kapja meg az adatokat. Az ár lehet tört, 2 tizedesjegy pontossággal számolj!

A kávé típusához készíts egy `CoffeeType` enum-ot. Lehetséges értékei: ESPRESSO, MACHIATTO, RISTRETTO, MOCHA, LATTE, CAPPUCCINO, AMERICANO.

A `CoffeeOrder` osztály tárolja egy vásárló által megrendelt és leszámlázott kávékat. Attibútumai: `coffeeList` a megrendelt kávék listája, `dateTime` a vásárlás időpontja.

A `Cafe` osztály tartalmazza a kávézó összes rendelését egy listában. A listát a konstruktorban kapja meg, de legyen lehetőség új rendelést hozzáadni. Készítsd el benne az alábbi metódusokat streamek segítségével:

`getTotalIncome()`: az eddigi összes bevétel

`getTotalIncome(LocalDate date)`: adott napi teljes bevétel

`getNumberOfCoffee(CoffeeType type)`: az adott típusú kávéból eladott összmennyiség

`getOrdersAfter(LocalDateTime from)`: a megadott időpont utáni rendelések listája

`getFirstFiveOrder(LocalDate date)`: adott napon az első 5 vásárlásban lévő kávék listája 

[rating feedback=java-lambdaintermediate-kavezo]
