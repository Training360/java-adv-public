# Autoboxing használata kollekcióknál

Kollekciók csak objektumokat tudnak tárolni, primitív típusokat nem. Minden primitív típusnak megvan a csomagoló osztálya, amely már megadható a kollekció generikus típusaként. Amikor primitív típusú elemet adnánk hozzá, az automatikusan a neki megfelelő osztályba "csomagolódik" be (autoboxing), és amikor a kivett elemet primitív típusú változóba tennénk, akkor a Java futtatókörnyezet "kicsomagolja" a kivett objektumot (autounboxing).

```java
List<Integer> numbers = new ArrayList<>();
int a = 1;
numbers.add(a);
int b = numbers.get(0);
```

## Primitív típusok és csomagoló osztályaik

| Primitív típus | Csomagoló osztálya |
| -------------- | ------------------ |
| `boolean`      | `Boolean`          |
| `byte`         | `Byte`             |
| `short`        | `Short`            |
| `int`          | `Integer`          |
| `long`         | `Long`             |
| `float`        | `Float`            |
| `double`       | `Double`           |
| `char`         | `Character`        |

## A `null` érték használata

A kollekciók többsége megengedi a `null` érték tárolását. Ez alól csak a `TreeSet` és a `TreeMap` a kivétel, hiszen ezek az elemeket rendezetten tárolják, a `null` értékről pedig nem dönthető el, hogy egy elemnél kisebb vagy nagyobb.  (Egyedi `Comparator` készítésével ez kiküszöbölhető, de erről később lesz szó.) Problémába ütközik a futtatókörnyezet, amikor a kollekcióban lévő `null` elemet kellene kicsomagolnia primitív típussá, mert ennek megfelelő érték nem létezik. Ilyenkor futási időben `NullPointerException`-t kapunk.

```java
List<Integer> numbers = new ArrayList<>();
numbers.add(null);		//ez megengedett művelet
int a = numbers.get(0);	//ez is megengedett művelet, de futás közben NullPointerException-t dob
```

## Ellenőrző kérdések

* Mi az autoboxing szerepe a kollekciók esetén?
* "Feltalálása" előtt hogyan lehetett egy kollekcióba primitív értékeket rakni és kivenni?
* Hogyan kezelik a `null` értéket a kollekciók?
* Mikor kaphatunk autoboxing esetén `NullPointerException` kivételt?

## Gyakorlati feladat - Az autoboxing működése Integer objektumok összeadása és kollekció esetében.

Implementáld az `IntegerOperations` osztályban a következő metódusokat!

```java
public List<Integer> createList(int... numbers)
public int sumIntegerList(List<Integer> integerList)
public int sumIntegerObjects(Integer... integers)
```

Az egyes kollekciók esetében vizsgáljuk meg és értelmezzük a `toString()` metódus működését,
 a kiírás sorrendjének elemzésével.

### Megvalósítás

Használjunk varargs típusokat a paraméterek megadásához.

[rating feedback=java-collectionsautoboxing-autoboxing]
