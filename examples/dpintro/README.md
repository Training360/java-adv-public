# Bevezetés a tervezési minták használatába

Első körben érdemes áttekinteni néhány tervezési alapot. Fontos, hogy használjuk a S.O.L.I.D. elveket, de ezeken túl van még pár szabály, amit érdemes betartani.
*	High cohesion, low coupling
*	Általában egyszerre módosuló részek egységbe zárása
* Külön módosuló részek megkülönböztetése
*	Interfészekkel való programozás
*	Delegálás
*	Öröklődés helyett kompozíció

A high cohesion azt jelenti, hogy egy osztály csak egy felelősségi körrel rendelkezik (single responsibility). A low coupling az osztályok laza kapcsolatát jelenti, ez garantálja a jól struktúráltságot és a cserélhetőséget. Ehhez járul hozzá az interfészek használata is. A delegálás fogalma is felelős a single responsibility-ért. Ez nem más tulajdonképpen, mint egy másik osztály metódus hívása, de fe fontos, hogy lehetőleg ne példányosítsuk a másik osztályt, mert akkor már egy erős kapcsolat lesz a két osztály között.

## Tervezési tanácsok

Ha objektumorientáltan tervezünk érdemes a következő tanácsokat megfogadni.

*	Törekedjünk a minimális interfész használatra, csak a legszükségesebb adattagok és metódusok legyenek interfészben, a többit próbáljuk elrejteni. Az interfészeket próbáljuk folyamatosan fejleszteni, ha szükséges.
*	Rejtsük el az implementációt.
*	Törekedjünk az újrafelhasználhatóságra
*	YAGNI (You ain't gonna need it) – ne komplikáljuk túl, csak a követelményeknek feleljen meg
*	KISS (Keep it simple, stupid) – ne komplikáljunk
*	DRY (Don’t repeat yourself) – ne másoljunk kódot, inkább emeljünk ki külön metódusba
*	A tesztelés interfészen keresztül történjen, ne teszteljük a privát adattagokat


##Tervezési minták
A tervezési mintákat objektum-orientált programozásán újra és újra felmerülő problémák megoldására hozták létre. Fontos, hogy ezek nem konkrét megoldások, implementációk inkább gyakorlatok, alapelvek. A tervezési minták egy közös nyelvet adnak a fejlesztőknek. Három csoportra bonthatók:
*	Létrehozási
*	Szerkezeti
*	Viselkedési
A létrehozási minták, felelősek az objektumok példányosításáért. A szerkezeti minták az osztályok kapcsolatát írják le. A viselkedési minták pedig a működés alapelvét fogalmazzák meg.


### Tervezési minták felépítése
A 23 tervezési minta a GoF (Gang of Four) könyvben a következőképpen épül fel :
*	Cél
*	Egyéb nevek
*	Feladat
*	Alkalmazhatóság
*	Szerkezet – általában UML
*	Résztvevők – osztályok, interfészek
*	Együttműködés – általában UML interakció diagram
*	Következmények
*	Megvalósítás – tippek az implementáláshoz
*	Példakód – nem feltétlenül Java nyelven

## Ellenőrző kérdések

* Mi a kompozíció, hogyan kell implementálnunk?
* Miért alkalmazzuk a kompozíciót?
* Milyen tervezési minta csoportok vannak?


## Gyakorlat 1 - öröklődés és interfész implementáció

`Crocodile` osztály, `LandDweller` absztrakt osztály és `CanSwim` interfész.

A `Crocodile` konkrét osztály tulajdonságai "összerakhatók" a szárazföldi állatok `LandDweller`
és az úszni tudók `CanSwim` tulajdonságaiból.

`CanSwim` interfész:

publikus metódus:    
```java
 void swim();
```

`LandDweller` absztrakt osztály `int energy` attribútummal, ami a mozgáshoz szükséges energiát reprezentálja:

publikus/protected metódusok:    
```java
public LandDweller(int energy)
protected void decreaseEnergy(int value)
public int getEnergy()
```

Amennyiben a meglévő energia nem elegendő a megkívánt mozgáshoz, a megfelelő metódus dobjon
`IllegalArgumentException`-t a megfelelő szöveggel.

`Crocodile` osztály, ami a `LandDweller` osztály leszármazottja és implementálja a `CanSwim` interfészt:

publikus metódusok:    
```java
public Crocodile(int energy)
public void swim()
public void walk()
```

Mindkét mozgás konstans értékkel csökkenti az állat energiáit, gyalogolva 20 egység legyen, úszva 5 egység.

[rating feedback=java-dpintro-oroklodesinterfeszimpl]

## Gyakorlat 2 Iterátor tervezési minta implementációja

Az iterátor egy kollekció (tömb vagy lista) elemein képes végigmenni, for ciklus nélkül :). Egy
kurzort használ és minden lépés előtt ellenőrzi, hogy van-e még elem a kollekcióban, majd továbblép a következő elem elé.
Minden lépés referenciát ad az aktuális elemre, így az olvasható, manipulálható.

Felépítése az `Iterator` és a `Container` interfészeken alapul, az első a bejáráshoz szükséges metódusokat adja meg,
a második a konténer által biztosított iterátort adja meg.

`Iterator` interfész:

publikus metódusok:    
```java
public boolean hasNext()
public Object next()
```

`Container` interfész:

publikus metódus:    
```java
public Iterator getIterator()
```

Egy állatkert állat kollekcióját akarjuk bejárni iterátor segítségével. Az állatokat az `Animal` osztály objektumai
reprezentálják, jelen esetben minden állatnak csupán neve van.

`Zoo` osztály implementálja a `Container` interfészt

publikus metódusok:

```java
public Zoo(List<Animal> animals)
public Iterator getIterator()
```

`ZooIterator` osztály implementálja az `Iterator` interfészt, `int index` és `List animalList` attribútumokkal:

publikus metódusok:

```java
public ZooIterator(List<Animal> animalList)
public boolean hasNext()
public Object next()
```

[rating feedback=java-dpintro-iteratortervezesimpl]

