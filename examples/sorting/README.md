# Rendezés

## Rendezési algoritmusok

Nagyon sokféle rendezési algoritmus létezik. Az, hogy melyik a leghatékonyabb, nagyban függ attól, hogy processzor- vagy memóriaerős gépünk van, illetve milyen a rendezendő tömb (kollekció) felépítése.

Rendezhetünk új kollekcióba másolással és ugyanazon sorozaton helyben is. Míg az első sokkal természetesebb és könnyebb, addig ez utóbbi helykímélőbb módja a rendezésnek.

### Beszúrásos rendezés (Insertion sort)

A módszer lényege, hogy vesszük az elemeket egyenként, és a már rendezett sorozatban a helyére tesszük. Egy elem mindig rendezett, tulajdonképpen csak a második elem beszúrásától kezdve kell a helyét megkeresni. Helyben rendezésnél a sorozat eleje rendezett, és a még nem rendezett részből választott elemet szúrjuk be a helyére.

https://www.youtube.com/watch?v=kU9M51eKSX8

### Buborékos rendezés (Bubble sort)

A módszer lényege, hogy csak az egymás melletti elemeket hasonlítjuk, és ha szükséges, megcseréljük őket. Így a legnagyobb elem a sorozat legvégére, a helyére kerül. Most újra elkezdjük a szomszédosok cseréjét az utolsó előtti elemig. A sorozat vége így mindig rendezett, csak az elején kell az elemeket újra cserélgetni. A nagy elemek így a sorozat vége felé "szállnak", mint egy buborék.

https://www.youtube.com/watch?v=RT-hUXUWQ2I

### Gyorsrendezés (Quicksort)

A sorozatot egy kiemelt eleménél (általában az utolsó) kisebb és nagyobb két részsorozatra bontjuk, a kisebbeket elé, a nagyobbakat mögé mozgatjuk, így a kiválatszott elem a helyére kerül. Az előtte és utána lévő még rendezetlen részsorozattal ugyanígy járunk el, míg a teljes sorozat rendezett nem lesz.

https://www.youtube.com/watch?v=aQiWF4E8flQ

## Rendezés Javaban

Tömbök rendezésére az `Arrays.sort()` statikus metódust használhatjuk.  Az elemeket természetes rendezettségük szerint rendezi, de `Comparator`-t is figyelembe tud venni.

A `TreeSet` és `TreeMap` eleve rendezetten adja vissza az elemeket, mert új elem hozzáadásakor már a helyére szúrja be.

Listák rendezésére a `List` `sort()` metódusát vagy bármilyen kollekcióhoz a `Collections.sort()` statikus metódust használhatjuk. Mindkettő az elemek természetes rendezettségét használja, illetve képes átadott `Comparator`-t is használni.

Ezek a fentieknél hatékonyabb Timsort algoritmust használják. Ha részletesebben is érdekel, nézd meg az alábbi videót.

https://www.youtube.com/watch?v=jVXsjswWo44

## Ellenőrző kérdések

* Milyen rendezési algoritmusokat ismersz?
* Hogyan rendezed le a tömbök elemeit?
* Milyen eleve rendezett kollekciókat ismersz?
* Hogyan rendezed le egy lista elemeit?

## Gyakorlati feladat 1 - OrderedArrayLibrary

Készítsünk olyan osztályt, ami egy könyvtárban tárolt könyvek szoftveres rendezéseit valósítja meg.
A könyvtárban a könyveket tömb segítségével tároljuk, ezt kell rendezni igény esetén különböző szempontok alapján.

## Megvalósítás

`Book` osztály `int id` `String title` és  `String author` attribútumokkal.

A `public int compareTo(Book o)` metódust az igényeknek megfelelően készítsük el. Alapértelmezett az `id` szerinti rendezés.


`OrderedArrayLibrary` osztály `private Book[] bookArray` attribútummal. Ezt konstruktorból tudjuk feltölteni.

 publikus metódusok:    
```java
 public OrderedArrayLibrary(Book[] bookArray)
 public Book[] sortingById()
 public Book[] sortingByTitle()
```
 A rendezés során az eredeti tömb egy másolatát adjuk vissza, a megfelelő szempont szerint rendezve!

### Tipp

 A rendezéshez szükséges `Comparator` objektumot előállíthatjuk külön osztályból, vagy névtelen osztályból is.
 
 [rating feedback=java-sorting-orderedarraylibrary]



## Gyakorlati feladat 2 - OrderedLibrary

 Készítsünk olyan osztályt, ami a könyvtárban tárolt könyveket alapvetően rendezett formában tárolja.

## Megvalósítás

 `Book` osztály `int id` `String title` és  `String author` attribútumokkal.

 A `public int compareTo(Book o)` metódust az igényeknek megfelelően készítsük el. Alapértelmezett az `id` szerinti rendezés.


 `OrderedLibrary` osztály `Set<Book> library` attribútummal. Ezt konstruktorból tudjuk feltölteni.

  publikus metódusok:    
```java
public OrderedLibrary(Set<Book> library)
public Book lendFirstBook()
```
  A rendezett kollekcióból adjuk ki az első könyv referenciáját "kölcsönzéshez"!

### Tipp

  Amennyiben a kollekció üres, a `lendFirstBook()` metódus dobjon egy `NullPointerException`-t a megfelelő tájékoztató
  szöveggel.
  
[rating feedback=java-sorting-orderedlibrary]  
