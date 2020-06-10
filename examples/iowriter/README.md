# String írása Writerrel

Fájlt nem csak olvasni, de írni is tudunk darabokban egyetlen megnyitás és 
lezárás között, nem kell a teljes tartalmát először összegyűjteni a memóriában. 

![Szöveges fájlok írása](images/io-writer01.png)

Erre szolgálnak a `Writer` absztrakt osztály különböző megvalósításai. Az optimalizált 
pufferelt írást a `BufferedWriter` osztály valósítja meg, melynek egy példányát a 
`Files.newBufferedWriter()` metódussal tudunk előállítani. A metódus a fájlt 
reprezentáló `Path` objektumot várja paraméterként. A fájlba szöveget írni a 
`write()` metódussal lehet. Sorvége jelet külön karakterként nekünk kell kiírni. 
Mivel ez rendszerfüggő, a `BufferedWriter` külön `newLine()` metódust biztosít hozzá. 
Hiba esetén mind a `BufferedReader` létrehozása, mind a `write()` metódus 
`IOException` kivételt dob.

```java
List<String> employees = List.of("John Doe", "Jane Doe", "Jack Doe");
Path file = Path.of("employees.txt");
try (BufferedWriter writer = Files.newBufferedWriter(file)) {
    for (String employee: employees) {
        writer.write(employee + "\n");
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not write file", ioe);
}
```

A `BufferedWriter` példányt le kell zárni, ezért try-with-resources szerkezetben 
hozzuk létre. Alapértelmezett karakterkódolása UTF-8, de a `Files.newBufferedWriter()` 
metódusnak paraméterként egy `Charset` is átadható.

## Java 8 előtti írás

A Java 8 előtt még nem lehetett a `Files` osztállyal `BufferedWriter` példányt gyártatni, 
a `Reader`-ekhez hasonlóan itt is egy alacsony szintű `Writer`-re, a `FileWriter`-re építettük 
az olyan magas szintű `Writer` megvalósításokat, mint a `BufferedWriter`.


![FileWriter és BufferedWriter](images/io-writer02.png)

```java
BufferedWriter writer = new BufferedWriter(new FileWriter("employees.txt"));
```

A pufferelt írás tulajdonképpen annyit jelent, hogy a `write()` metódus hívása 
nem jelent azonnali háttértárra írást, a `BufferedWriter`  először egy bizonyos mennyiséget 
összegyűjt, és csak utána adja át az egészet a `FileWriter` objektumnak írásra. 

## Ellenőrző kérdések

* Hogyan lehet `Writer` segítségével szöveges fájlt írni?
* Hogyan lehet a tartalmat sorokra tördelni?
* Milyen az alapértelmezett karakterkódolás? Hogyan lehet az írott fájl kódolását megadni?

## Feladat

### Nevek

Az első feladatban egyszerűen neveket fogunk eltárolni fájlban és listában egyaránt. 
A `NameWriter` osztály konstruktorban megkapja az írni kívánt fájlt. Az `addAndWrite()` metódus egy nevet vár paraméterül, amelyet 
hozzáfűzi a fájlhoz. A hozzáfűzéshez használd a korábban megismert `StandardOpenOption.Append` paramétert a `newBufferedWriter()` metódusban.

### Zenekarok

Ebben a fájl olvasását és írását is gyakorolhatod. Adott a `bands_and_years.txt` állomány, melyben zenekarok nevét és alapítási évét találod.
A te feladatod az lesz, hogy készíts egy metódust melynek a paramétere egy fájl és egy évszám. Ez a metódus ki fogja írni a fájlba az évszámnál régebben alakult zenekarokat. 
Megoldási javaslat, hogy készíts egy privát metódust, ami kigyűjti ezeket a zenekarokat egy listába. 
Minden zenekart egy `Band` objektum reprezentál, melynek attribútumai a név és az évszám.

