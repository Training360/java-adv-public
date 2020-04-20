# Keresés

Sok-sok objektum között nem könnyű keresni. Laikusként szépen egyesével megvizsgálnánk mind, míg meg nem találjuk a számunkra megfelelőt. Ez bizony hosszadalmas lehet, ha nincs szerencsék, csak az utolsó megvizsgált elem lesz jó. N elem esetén legrosszabb esetben n, de átlagosan is n/2 elemet meg kell vizsgálnunk. Sokkal könnyebb a keresés, ha az elemek a keresési szempont szerint rendezettek. Ebben az esetben ha megvizsgáljuk a középső elemet, és az nem a keresett, máris tudjuk, hogy csak az előtte vagy csak az utána lévőket között lehet, amit keresünk. Máris megfeleztük az átvizsgálandó elemek számát. Ha ezt a stratégiát folytatjuk, akkor legrosszabb esetben is log(n) lépés alatt megtaláljuk a keresettet, vagy éppen biztosan állíthatjuk, hogy nincs a sokaságban. Az első módszert lineáris, a másodikat bináris keresésnek nevezzük. Bármilyen hatékonynak is tűnik a második módszer, az sajnos csak rendezett sorozatra használható, különben hibás eredményt ad.

## Keresés tömbben

Rendezett tömbben az `Arrays.binarySearch()` statikus metódussal tudunk egy adott elemet binárisan megkeresni. Első paraméterként a tömböt, másodikként a keresett elemet kell átadni. A kereséshez kell, hogy az elemeknek legyen természetes rendezettsége, illetve ha nincs, akkor harmadik paraméterként egy `Comparator`-t is át kell adni.

Amennyiben nem találja meg az elemet, akkor negatív számmal tér vissza. A szám azt is mutatja, hogy hol kellene lennie a keresett elemnek. Ha például a kapott index -6, akkor az azt jelenti, hogy a keresett elemnek a 6.-nak, azaz az 5-ös indexű elemnek kellene lennie.

## Keresés kollekciókban

Arra a kérdésre, hogy egy adott elem létezik-e a kollekcióban, a kollekció `contains()` metódusa ad választ. Ha egyszerre több elem létezését szeretnénk megvizsgálni, akkor a `containsAll()` metódust kell használnunk, mely a keresett elemek kollekcióját várja paraméterként, és csak akkor ad vissza igazat, ha mind megtalálta. Lineáris kereséssel a keresett elem helyét az `indexOf()` metódussal kaphatjuk meg. Ez csak listákra értelmezett, mert csak itt létezik az index fogalma. Ha a keresett elem nem létezik, -1-et ad. Ezek a keresések kizárólag az elemeken értelmezett `equals()` metódussal működnek.

Bináris keresésre a `Collections.binarySearch()` statikus metódust használhatjuk, amely listákban képes egy elemet megkeresni és a találat indexével tér vissza. A metódus a rendezettséget definiáló `Comparator`-t is megkaphatja, ha az elemeknek nincs természetes rendezettsége vagy az eltérő, mint ami szerint keresünk. Természetesen most is a rendezettnek kell lennie a listának, különben nem kapunk helyes eredményt.

Kollekciókban megkereshetjük a legnagyobb és legkisebb elemet is a `Collections.min()` és a `Collections.max()` statikus metódusokkal. Paraméterként a kollekciót kell átadnunk. Az elemeknek természetes rendezettsége alapján fogja visszaadni a kollekció legnagyobb, illetve legkisebb elemét, de magunk is adhatunk meg `Comparator`-t.

## Ellenőrző kérdések

* Milyen keresési algoritmusokat ismersz?
* Hogyan keresel egy rendezett tömbben?
* Hogyan nézed meg egy kollekcióban, hogy egy elem benne van-e?
* Hogyan vizsgálod meg, hogy egy elem egy listában hányadik indexen szerepel?
* Hogyan keresel egy rendezett kollekcióban?
* Mely metódus működik natural ordering, és mely metódus `Comparator` alapján?

## Gyakorlati feladat - BookArraySearch

Készítsünk olyan osztályt, ami egy könyvtárban tárolt könyvek között tud keresni adott szerzőre és címre.
Az osztályban a könyveket objektum tömb formájában tároljuk, a keresés legyen bináris keresés.

## Megvalósítás

`Book` osztály `int id` `String title` és  `String author` attribútumokkal.

A `public int compareTo(Book o)` metódust az igényeknek megfelelően készítsük el.
Alapértelmezetten szerző és ezen belül cím alapján rendezi a `Book` objektumokat.

`BookArraySearch` osztály `private Book[] bookArray` attribútummal. Ezt konstruktorból tudjuk feltölteni.

 publikus metódusok:    
```java
 public BookArraySearch(Book[] bookArray)
 public Book findBookByAuthorTitle(String author, String title)
```

### Kivételkezelés

 A két String paramétert le kell ellenőrizni, üres String esetén dobjon `IllegalArgumentException`-t.
 Ha a bináris keresés nem talál könyvet, dobjon `IllegalArgumentException`-t
 a megfelelő tájékoztató szöveggel.
 
[rating feedback=java-searching-bookarraysearch] 
