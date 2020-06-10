# Comparator módosítások

A Java 8 verzióval bejött statikus interfész metódusok lehetővé tették, hogy a 
`Comparator` interfészbe olyan metódusok kerüljenek be, melyek elkészítik a 
`Comparator` implementációt bármilyen osztályra, amennyiben az összehasonlítás 
logikáját megadjuk neki.

## `Comparator.comparing()`

A `comparing()` metódus többféle bemenetből el tudja készíteni a szükséges `Comparator`-t. 
Elég egy olyan `Function`-t adnunk neki, amely megmondja, hogy az osztály két 
példányát milyen kulcs alapján kell összehasonlítani, amennyiben a kulcs maga már implementálja 
a `Comparable` interfészt. Például ha egy `List<Person> people` listát az emberek neve szerint szeretnénk 
rendezni, akkor az ehhez szükséges `Comparator`-t az alábbi hívás állítja elő:
 
```java
Comparator.comparing(Person::getName)
``` 

Ennek persze feltétele, hogy a `Person` `getName()` metódusa által visszadott  `String` 
a Javában összehasonlítható. Amennyiben az adott kulcs nem implementálja a `Comparable` interfészt, vagy 
nem az implementált logika alapján szeretnénk összehasonlítani, szükséges az ezeket 
összehasonlító logika, azaz egy újabb `Comparator` átadása második paraméterként.

```java
Comparator.comparing(Person::getName, (s, t) -> s.trim().toLowerCase().compareTo(t.trim().toLowerCase()));
```

Amennyiben nem egy szempont alapján akarunk rendezni, akkor eddig egy elég bonyolult `Comparator` implementációt 
kellett megfogalmazni, amely az elsődleges szempont egyezősége esetén külön vizsgálta a másodlagos szempontot, annak egyezősége esetén a harmadlagosat stb.
Most elég az elsődleges szempont megadása a `comparing()` metódusban, majd láncoltan hívható sorban a `thenComparing()` 
metódus a többi szemponttal.

Például ha a `Person` objektumokat elsődlegesen vezetéknév szerint, azok egyezősége esetén pedig a keresztnév szerint 
szeretnénk rendezni:

```java
Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName);
```

## `int`, `long` és `double` típusú kulcs

A `comparing()` metódus objektumokat összehasonlító `Comparator`-t ad, nem használható primitív értékek összehasonlítására. 
Amennyiben az embereket valamilyen primitív kulcs szerint szeretnénk rendezni, akkor más metódusokat kell használnunk. 
Ha az összehasonlítás `int` típusú kulccsal történik, akkor a `comparingInt()` metódust használjuk, de létezik még 
`comparingLong()` és `comparingDouble()` is.

Az embereket a nevü hossza szerint rendező `Comparator` előállítása:

```java
Comparator.comparingInt(p -> p.getName().length());
```

Itt a lambda kifejezéssel a `Person` objektumból a nevük hosszát vontuk ki kulcsnak.

## `null`, natural order és fordított rendezés

Az eddigiekben nem foglalkoztunk azzal az esettel, ha a kulcsot kivonó függvény `null` értéket 
ad vissza. Ebben az esetben a rendezés csődöt mondana,  mert nem tudja a `null`-t összehasonlítani a nem `null` értékellel. 
Ebben az esetben megadhatjuk, hogy a `null` mindennél kisebbnek vagy nagyobbnak számít-e a `Comparator.nullsFirst()`, illetve a 
`Comparator.nullsLast()` metódusokkal.

Amennyiben a nem kötelező középső név szerint szeretnénk rendezni, és a `null` értéket 
minden elé szeretnénk tenni, az alábbi `Comparator`-t kell elkészítenünk:

```java
Comparator.comparing(Person::getMiddleName, Comparator.nullsFirst(Comparator.naturalOrder()));
```

Láthatjuk, hogy a `Comparator.nullFirst()` egy újabb `Comparator`-t vár. Amennyiben a természetes rendezettséget szeretnénk megtartani, akkor 
ehhez használhatjuk a `Comparator.naturalOrder()` metódust. Ezt mindenhol használhatjuk, 
ahol egy metódus `Comparator`-t vár, de mi nem akarunk változtatni a típusban definiált rendezettségen.

Ha a természetes rendezettséghez képest pont fordítottan szeretnénk az elemek sorrendjét, akkor a 
`Comparator.reverseOrder()` metódusa által legyártott `Comparator`-t kell használnunk, míg ha egy bármilyen más, `Comparator`-ral 
definiált sorrendet szeretnénk megfordítani, akkor a `reversed()` metódusra van szükségünk.

```java
people.sort(Comparator.comparing(Person::getMiddleName, Comparator.reverseOrder()));

people.sort(Comparator.comparing(Person::getName, Comparator.comparingInt(String::length)).reversed());
```

## Ellenőrző kérdések

* Milyen új statikus metódusokkal bővült a `Comparator` interfész? Mikor hasznosak?
* Hogyan lehet egy listát rendezni, ha az elemek nem implementálják a `Comparable` interfészt, és 
több szempont szerint is rendezni akarunk?
* Hogyan lehet egy `Comparator` objektumban definiált rendezettséget megfordítani?

## Feladat

### Felhő tárhelyek

Különböző felhő tárhely szolgáltatókat szeretnénk összehasonlítani, ezért a `CloudStorage` osztályban 
eltároljuk a különböző adataikat. A tárhely mérete GB-ban adott, az árak pedig mindenhol ugyanabban a pénznemben. 
A `PayPeriod` enum a fizetési gyakoriság, ahol a `length` értéke a hossz hónapokban megadva (lifetime esetén 60 hónap). 
Az ingyenes csomagok esetén a fizetési gyakortiság nincs megadva. A 
`CloudStorage` implementálja a `Comparable` interfészt, a természetes rendezettségét az 1000 GB-ra eső éves díj nagysága adja.

![CloudStorage UML](images/cloud_class.png)

A `Clouds` osztály metódusai a paraméterként kapott listából bizonyos szempont szerint a legjobba(ka)t adják vissza. 
Amennyiben több ugyanolyan van, akkor közülük bármelyik visszaadható.

* `alphabeticallyFirst()`: a szolgáltató neve alapján betűrendben a legelső `CloudStorage`. Kis-nagybetű nem számít.
* `bestPriceForShortestPeriod()`: a legrövidebb időszakra vonatkozó legolcsóbb `CloudStorage`. Ha van 
ingyenes, akkor azok közül bármelyik megadható.
* `worstOffers()`: a természetes rendezettség szerinti 3 legrosszabb ajánlat.

