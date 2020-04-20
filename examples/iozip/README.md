# Bájtok írása tömörítéssel

A Java lehetőséget biztosít arra, hogy a fájlokat tömörített állományba írjunk. 
A tömörített zip állomány önmagában is fájlokat és könyvtárakat tartalmaz, melyek 
létrehozása a `ZipEntry` osztály használatával lehetséges.

A `ZipOutputStream` teszi lehetővé, hogy a tömörített állományba új `ZipEntry` kerüljön 
elhelyezésre, illetve ebbe `byte[]` típusú adat kerüljön. A `ZipOuputStream` 
magas szintű stream, tipikusan `BufferefOutputStream`-re épülve használjuk.

![FileOutputStream, BufferedOutputStream és ZipOutputStream együttműködése](images/io-zip01.png)

Új `ZipEntry`-t a fájl vagy mappa nevének a megadásával hozhatunk létre. 
A mappa neve utáni `/` jel jelöli, hogy a létrehozandó oobjetum mappa lesz. Ha a fájlt 
valamelyik tömörített mappába akarjuk elhelyezni, akkor a nevének megadásakor a zip fájl 
gyökeréhez képesti relatív útvonalat kell megadnunk. Például a `new ZipEntry("folder/")` egy 
folder nevű mappát hoz létre, a `new ZipEntry("folder/data.dat")` egy data.dat nevű 
fájlt hoz létre a folder mappában.

`ZipEntry`-t a tömörített állományba a `ZipOutputStream` `putNextEntry()` metódusával lehet.
Ezután az írás mindaddig ebbe az állományba történik, amíg új `ZipEntry` nem kerül 
elhelyezésre vagy a `ZipOutputStream` lezárásra nem kerül.

```java
Path file = Path.of("data.zip");
try (ZipOutputStream outputStream = new ZipOutputStream(
        new BufferedOutputStream(Files.newOutputStream(file)))) {
    outputStream.putNextEntry(new ZipEntry("data.dat"));
    for (int i = 0; i < 1100; i++) {
        outputStream.write("abcde".getBytes());
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not write file", ioe);
}
```

## Ellenőrző kérdések

* Milyen osztályok segítik zip tömörített állományok írását?
* Hogyan hozhatunk létre új fájlt egy tömörített állományba?
* Hogyan írhatunk egy fájlt a tömörített állományban?
* Hogyan hozhatunk létre egy új mappát egy tömörített állományba?

## Feladat

### Alkalmazottak

Az `iozip.names` csomagba készíts egy `EmployeeFileManager` osztályt! Egyetlen metódusa van, a 
`saveEmployees()`, mely a paraméterként kapott `Path` által reprezentált zip fájlba létrehoz 
egy names.dat nevű bináris fájlt, és a szintén paraméterként kapott névlista tartalmát beleírja.

### Napi tranzakciók

Az `iozip.transactions` csomagba készíts egy `Transaction` immutable osztályt, 
melyben a tranzakció azonosítója (`long id`), a 
tranzakció pontos időpontja (`LocalDateTime time`), az érintett bankszámla száma 
(`String account`) és a tranzakció összege (`double amount`) található. 

A `TransactionFileManager` osztály `saveTransactions()` metódusa a paraméterként kapott `Path`-ra 
elmenti a `Transaction` listában kapott tranzakciókat tömörítve. Minden tranzakció külön szöveges fájlba kerüljön! 
A fájl neve a tranzakció azonosítója legyen! Tartalma sortöréssel (`\n`) elválasztva az időpont, 
a bankszámlaszám és az összeg.

Például:

```
2018-02-13T15:08:43
10092365-37255375-33310000
23000.0
```

