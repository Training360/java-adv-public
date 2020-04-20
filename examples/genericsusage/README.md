# Generikusok használata, diamond operator

Amikor kollekcióban szeretnénk sok elemet tárolni, relációs jelek között kell megadnunk az elemek típusát. Mi van, ha nem adjuk meg? Mivel a Java 5 előtt nem léteztek generikusok, ezért a kollekciók alapértelmezetten is működnek, `Object` típussal (raw type). Ebben az esetben bármilyen objektum beletehető a kollekcióba, hiszen minden osztály `Object` leszármazott. A probléma ott kezdődik, amikor valamilyen műveletet szeretnénk az elemen végezni, de nem tudjuk, hogy az pontosan micsoda. Mivel a deklarált típusa `Object`, ezért csak az `Object` metódusai érhetőek el. Ha mást szeretnénk, akkor az elemet típuskényszeríteni kell, hogy meghívhassuk a megfelelő metódust. De mi történik, ha az adott elem nem az általunk feltételezett típusú?

```java
List list = new ArrayList();
list.add(Integer.valueOf(5));
System.out.println((String) list.get(0)).toUpperCase());
```

A programunk elvileg hibátlan, fordításkor nem történik semmi, de futás közben `ClassCastException` kivételt dob. Ez elkerülhető, ha az elemen először típusellenőrzést végzünk az `instanceof` operátorral.

A Java 5 bevezette a generikusok használatát, amivel már korlátozni lehet, hogy milyen típusú elem kerülhet a kollekcióba. Ez az ellenőrzés már fordításkor végbemegy. A kollekció deklarációjakor megadhatjuk, hogy milyen típusú elemeket fogad be, és csak ezek, illetve ezek leszármazottai, interfész esetén pedig csak az adott interfészt implementáló objektumok kerülhetnek bele. Java 7 óta a kollekció példányosításakor nem kell a befogadható típust megismételnünk, elég, ha diamond operátort (`<>`) használunk.

```java
List<String> list = new ArrayList<>();
```

Még mindig használhatjuk a kollekciókat generikus típus nélkül, de a fordító figyelmeztet ennek veszélyeire.

```
ArrayList is a raw type. References to generic type ArrayList<E> should be parameterized
```

Sajnos, abban nem segít, hogy hol van a hiba. `-Xlint:unchecked` paraméterrel hívva a fordítót már pontosan beazonosítja a helyet is.

```
Main.java uses unchecked or unsafe operations.
```

A kollekció generikus típusa nem kovariáns, azaz kizárólag olyan kollekció tehető bele, ahol az elemek deklarált típusa pontosan megegyezik.

```java
List<Number> list1 = new ArrayList<Integer>(); // Nem fordul le!
List<Number> list2 = new ArrayList<Number>();  // Lefordul
list2.add(Integer.valueOf(2));				  // Lefordul, az elem lehet bármi, ami Number
```

## Ellenőrző kérdések

* Mi a generics jelentősége?
* Mi van a régebbi Java programokkal, ahol a generics még ismeretlen volt?
* Mi az a diamond operátor?
* Mi történik, ha generikussal ellátott osztályon próbálsz úgy műveletet végezni, hogy nem definiáltad a generikus típust?

## Gyakorlati feladat - Library

Implementálj egy könytárat, ahol könyveket tárolhatunk.
Valósítsuk meg a `getFirstBook()` metódust generics használata nélkül, és generics használatával is.

### Hibakezelés

* Ha a könyvtárat reprezentáló kollekció `null`, dobjon `NullPointerException` kivételt
* Ha a könyvtárat reprezentáló kollekció üres, dobjon `IllegalArgumentException` kivételt

[rating feedback=java-genericsusage-library]
