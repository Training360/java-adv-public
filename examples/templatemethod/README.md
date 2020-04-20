# Template method tervezési minta

Akkor használjuk, ha adott egy algoritmus, amelynek nem minden részlete ismert.

Az osztály valójában csak egy vázat ad meg ami bővíthető. A nem ismert részt _hook_-nak is hívják. Ezzel lehetőséget adhatunk más fejlesztőknek, hogy személyre szabja az algoritmust.

_Hollywood elv_: ne hívj majd én hívlak. Ez azt takarja, hogy nem a felhasználó hívja meg a megfelelő metódust, hanem az algoritmus ad lehetőséget bővítésre, és az algoritmus hívja meg az implementált metódust.

## Implementáció

Absztrakt ősosztályként valósítjuk meg, amiben deklaráljuk az absztrakt metódusokat, majd ebből leszármazunk, és implementáljuk a metódusokat.

A Java standard osztálykönyvtár is tartalmaz ilyen template method tervezési mintát. Például a `java.io.InputStream`, `java.io.OutputStream`, `java.io.Reader` és `java.io.Writer`.

## Ellenőrző kérdések

* Honnan lehet meghívni a védett metódust?
* Hogyan biztosítja a minta, hogy a meghívandó metódust később, máshol adjuk meg?

## Gyakorlat - Banki termék kezelése

`GeneralProduct` absztrakt osztály `int price` attribútummal a termék árának tárolására.
Deklarálj egy absztrakt metódust, amely előírja a kerekítés implementációját.
Ez legyen protected, hogy a konkrét termékek felül tudják írni
saját kerekítési szabályaik szerint.
`protected abstract int round(double newPrice)`

publikus metódusok:

```java
public GeneralProduct(int price)
public int getPrice()
public int increase(double percent) //áremelés, használjuk az int round(double) metódust a kerekítésre
```

Konkrét hitel `LoanProduct` termék, amely a `GeneralProduct` osztályból származik.
Hozd létre a `round` metódus implementációját úgy, hogy öt tizedestől már
a következő egészet adja vissza! (12.5 -> 13)

publikus metódus:

```java
public LoanProduct(int price)
```

Konkrét hitel `YieldProduct` termék, amely a `GeneralProduct` osztályból származik.
Hozd létre a round metódus implementációját úgy, hogy mindig a valós szám egész részét adja
vissza ! (12.9 -> 12)

publikus metódus:

```java
public YieldProduct(int price)
```

[rating feedback=java-templatemethod-bank]
