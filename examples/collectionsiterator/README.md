# Bejárás

A kollekciók bejárása iterátor alkalmazásával is lehetséges. Az `Iterator` interfészt implementáló osztály képes a kollekció elemeit bejárni. Az iterátort a kollekció `iterator()` metódusával lehet lekérni.

Műveletei:

`boolean hasNext()`: igazzal tér vissza, ha van még elem a kollekcióban

`E next()`: a következő elem referenciájával tér vissza. Ha nincs több elem, `NoSuchElementException`-t dob.

`void remove()`: törli az utoljára visszaadott elemet. Nem minden kollekció engedi, ezért dobhat `UnsupportedOperationExceprion`-t.

A kollekció bejárása közbeni módosítás (elem hozzáadása, törlése) az iterátor inkonzisztens állapotához vezet, és a program `ConcurrentModificationException`-t dob.
A következő kód törli a páros számokat a listából.

```java
List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
for (Iterator<Integer> i = values.iterator(); i.hasNext()) {
    int value = i.next();
    if (value % 2 == 0) {
        i.remove();
    }
}
```

## Ellenőrző kérdések

* Mire való az `Iterator` interfész?
* Hogyan lehet hozzájutni egy `Iterator` példányhoz?
* Milyen metódusait ismered?
* Mi történik, ha iterátoros bejárás közben akarsz módosítani egy `Collection` példányt?

## Gyakorlati feladat - LibraryManager

Készítsünk olyan osztályt, ami egy könyvtárban tárolt könyvek szoftveres menedzselését valósítja meg. A könyvtárat feltöltjük egyedi
könyvekkel, és egyedi regisztrációs szám alapján kikereshetjük, eltávolíthatjuk a könyvtár állományából,
illetve szerző szerint kigyűjthetünk könyveket.

## Megvalósítás

`Book` osztály `int regNumber` `String title` és  `String author` attribútumokkal.

Az `equals()` és `hashCode()` metódusokat az igényeknek megfelelően készítsük el, egyedi a `regNumber` attribútum.


`LibraryManager` osztály `Set<Book> libraryBooks` attribútummal. Ezt konstruktorból tudjuk feltölteni.

publikus metódusok:    
```java
 public Book findBookByRegNumber(int regNumber)
 public int removeBookByRegNumber(int regNumber)
 public Set<Book> selectBooksByAuthor(String author)
```

 Mindhárom metódus saját `MissingBookException`-t (`RuntimeException`) dob, ha a regisztrációs számnak megfelelő könyv nem
  található, vagy adott szerzővel nem található könyv.

### Tipp

 Használjunk iterátoros bejárást a könyvek megtalálására és kigyűjtésére.
 A tesztelés segítésére készítsünk egy `public int libraryBooksCount()` metódust is, ami visszaadja a `Book` kollekció méretét.
 
 [rating feedback=java-collectionsiterator-librarymanager]
