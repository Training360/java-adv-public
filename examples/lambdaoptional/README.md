# `Optional` osztály

Megismerkedtünk pár olyan algoritmussal, ahol az, hogy el tudjuk-e végezni a számítást, erősen függött a bemenettől. Például a minimum- és maximumkiválasztás előfeltétele, hogy a bemenő listának vagy tömbnek legyen legalább egy eleme, és az elemek összehasonlíthatók legyenek. Mit tegyünk, ha nincs egy elem sem vagy nem összehasonlíthatók? Mit adjon vissza a keresés, ha nincs meg a keresett elem?

Amikor valami kivételes történik, dobhatunk kivételt. Ilyen az, ha úgy akarok szélsőértéket keresni, hogy az elemek nem összehasonlíthatók. Az azonban nem kivételes eset, ha a listánk üres, vagy nincs meg a keresett elem. Ekkor jelezhetjük valamilyen speciális visszatérési értékkel ezt. Például a hiányzó elem esetén `null`-t adunk vissza, vagy index esetén -1-t. A probléma csak az, hogy az első `NullPointerException`-höz, a második `IndexOutOfBoundsException`-höz vezethet, ha nem kezeljük a speciális visszatérési értéket a hívó metódusban.

Van, amikor nem is tudjuk meghatározni, mi legyen az a speciális érték. Például nulla darab szám összege 0, de mennyi az átlaguk?

```java
public static double average(int... scores) {
    if (scores.length == 0) {
        //???
    }
    int sum = 0;
    for (int score: scores) {
        sum += score;
    }
    return (double) sum / scores.length;
} 
```

Amikor lehetséges, hogy valamilyen speciális bemenő adatra nem tudunk értelmes eredményt adni, akkor használjuk az `Optional<T>` generikus burkoló osztályt. Az osztály definiálja a "nincs eredmény" állapotot, így az vizsgálható a hívó kódban. Az értéket az `Optional` osztály factory metódusaival tudjuk előállítani:

`Optional.empty()`: üres `Optional`-t ad vissza, amely a "nincs eredmény" állapotnak felel meg.

`Optional.of(T t)`: az adott objektumot `Optional` osztályba csomagolja. A paraméter nem lehet `null`.

`Optional.ofNullable(T t)`: az adott objektumot `Optional` osztályba csomagolja. A paraméter lehet `null`, ekkor az üres `Optional`-t állítja elő.

```java
public static Optional<Double> average(int... scores) {
    if (scores.length == 0) {
        return Optional.empty();
    }
    int sum = 0;
    for (int score: scores) {
        sum += score;
    }
    return Optional.of((double) sum / scores.length);
}
```

Azt, hogy tartalmaz-e értéket az `isPresent()` metódussal tudjuk megvizsgálni. Ha ez hamis, akkor nincs értelmes eredmény, ha igaz, akkor az eredményt a `get()` metódussal kapjuk meg. Ellenőrzés nélküli `get()` hívás `NoSuchElementException`-höz vezet, ha az `Optional` üres.

```java
Optional<Double> opt = average(90, 100);
if (opt.isPresent()) {
    Double d = opt.get();
} 
```

### További metódusok

`void ifPresent(Consumer<? super T> consumer)`: ha az `Optional` tartalmaz értéket, meghívja rajta a paraméterként átadott metódust.

`T orElse(T other)`: ha az `Optional` tartalmaz értéket, akkor azt adja vissza, ha nem, akkor a paraméterül kapottat.

`T orElseGet(Supplier<? extends T> other)`: ha tartalmaz értéket, akkor azt adja vissza, különben meghívja a paraméterül kapott `other`-t és az onnan kapott értéket adja vissza.

`<X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier)`: ha tartalmaz értéket, akkor azt adja vissza, ha nem, akkor a paraméterül kapott `exceptionSupplier` által előállított kivételt dobja.

## Ellenőrző kérdések

* Mire való az `Optional` osztály?
* Milyen metódusait ismered?

## Gyakorlat - Közösségi háló

Hozz létre egy közösségi hálózatot, melyen kereséseket lehet végezni.

Hozz létre egy `Member` osztályt, `name`, `skills` (mely egy `List<String>`), `gender` attribútumokkal.

Hozz létre egy `SocialNetwork` osztályt, mely `Member` objektumokat képes tárolni.

A `findFirst` metódusa paraméterként egy keresési feltételt kap.
Visszatérési típusa `Optional`. Ha talál a keresési feltételnek
megfelelő tagot, akkor az elsőt adja vissza, ha nem talál, üres értékkel tér vissza.

A `averageNumberOfSkills` üres értékkel tér vissza, ha a közösségi háló nem tartalmaz tagot.
Ellenkező esetben átlagolja a tagok szakértelmének számát, és azzal tér vissza. 

[rating feedback=java-lambdaoptional-kozossegihalo] 

