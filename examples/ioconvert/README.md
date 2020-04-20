# Konvertálás a típusok között

Fájlokat kezelhetünk streamként, azaz bájtfolyamként és karakterek sorozataként is. 
Ha például már rendelkezünk egy streammel, és azt szöveges adatként akarjuk kezelni, 
szükségünk van egy olyan eszközre, amely a kettő közötti konverziót végrehajtja.

Minden fájlkezelő osztály az alábbi négy absztrakt osztály valamelyikének a 
leszármazottja, és ez meghatározza az alapvető funkcióját:

* `InputStream`: bájtok olvasása
* `OutputStream`: bájtok írása
* `Reader`: karakterek olvasása
* `Writer`: karakterek írása

`InputStream`-ből `Reader` konverzióhoz az `InputStream`-re egy `InputStreamReader` példányt kell 
csatolni, ez végzi el a bájt sorozat karakteres adattá történő konvertálását.

![InputStream Reader konverzió](images/io-convert01.png)

`OutputStream`-ből `Writer` konverzióhoz az `OutputStreamWriter` csatolása szükséges 
a létező `OutputStream`-re. Ez végzi el a szöveges adatok bájt sorozattá történő konvertálását.

![OutputStream Writer konverzió](images/io-convert02.png)

Speciális osztály a `PrintStream`, mely tulajdonképpen egy stream, de metódusai az 
adatok szöveges reprezentációjának, azaz karakterek sorozatának az írását teszik lehetővé. 
Metódusai hiba esetén nem dobnak `IOException` kivételt, és képes az automatikus flush hívásra, 
azaz a puffer tartalmát automatikusan kiírja és utána üríti. Ilyen típusú a `System.out` is.

A `PrintStream` magas szintű stream, tipikusan `BufferedOutputStream`-hez csatolva használjuk.

![FileOutputStream, BufferedOutputStream és PrintStream együttműködése](images/io-convert03.png)

A `PrintStream` metódusai bármilyen típusú adat szöveges reprezentációját képes kiírni a 
`print()`, `println()` és `printf()` overloadolt metódusaival. Habár ezek a metódusok 
nem dobnak kivételt, a fájl megnyitásakor keletkező esetleges hibát kezelnünk kell. 
A `PrintStream` automatikus lezárásához try-with-resources szerkezetben nyitjuk meg.

```java
Path file = Path.of("employees.txt");
List<String> employees = List.of("John Doe", "Jane Doe", "Jack Doe");
try (PrintStream outputStream = new PrintStream(
        new BufferedOutputStream(Files.newOutputStream(file)))) {
    for (String employee: employees) {
        outputStream.print(employee);
        outputStream.print(",");
        outputStream.println(200_000);
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not write file", ioe);
}
```

## Ellenőrző kérdések

* Hogyan lehet `InputStream`-et `Reader`-ré konvertálni?
* Hogyan lehet `OutputStream`-et `Writer`-ré konvertálni?
* Mire való a `PrintStream` osztály és hogyan használjuk?

## Feladat

### Bevásárlólista

Az `ioconvert.shopping` csomagba készíts egy `ShoppingListManager` osztályt! A 
`saveShoppingList()` metódusa a paraméterül kapott `OutputStream`-re kiírja a szintén 
paraméterül kapott `List<String>` tartalmát szövegként, minden elemet külön sorba. A 
`loadShoppingList()` metódusa a paraméterül kapott `Inputstream`-ből beolvassa a 
bevásárlólista tartalmát, amit szöveglistaként ad vissza.

### Termékek

Az `ioconvert.products` csomagba hozd létre a `ProductWriter` osztályt, és benne a 
`saveProduct()` metódust, mely két paramétert kap: egy `OutputStream`-et és egy `List<Product>`-ot. 
A feladata a lista adatait kiírni csv formátumban az `OutputStream`-re, azaz 
pontosvesszővel elválasztva a `Product` adatait. Minden termék külön sorba 
kerüljön, a sor végére nem kell `;`. Kiíráshoz használd a `PrintStream` osztályt!

A `Product` tartalmazza a termék nevét (`String`) és az árát (`int`).

