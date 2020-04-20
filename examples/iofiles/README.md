# Files osztály használata

A Java 7 verzióban megjelenő `Path` és `Files` osztályt a korábbi `File` osztály 
kiváltására hozták létre. Nevét meghazudtoló módon a `File` osztály nem csak fájlokat 
reprezentál, hanem mappákat is, illetve a fájlrendszer kezeléséhez szükséges 
metódusokat is tartalmazza. A két funkció az új API-ban kettévált: a `Path` osztály 
kizárólag egy útvonalat reprezentál, és ezek kezeléséhez (összevonás, abszolút 
útvonallá alakítás) szükséges metódusokat tartalmaz, míg a fájlok és mappák műveletei a 
`Files` osztályba kerültek. Ezek a műveletek kiegészültek újabbakkal, mint például 
mappák bejárása, de még az olyan alapvető művelet, mint a fájl másolása, is újonnan 
került bele.

A `File` és a `Path` közötti konverzióra új metódus jelent meg a `File` osztályban, 
illetve a régebbi rendszerekkel való kompatibilitás miatt egy `Path` objektum `File` 
típusúvá alakítható.

```java
File file = new File("docs/foo.txt");
Path path = file.toPath();
```

```java
Path path = Paths.get("docs/foo.txt");
File file = path.toFile();
```

## A `Files` osztály gyakori műveletei

A `Files` osztály fájltartalmat kezelő (pl. `readAllLines()`) és fájlkezelő 
objektumokat gyártó (pl. `newBufferedReader()`) metódusain kívül még nagyon sok más, 
fájlrendszert kezelő metódussal is rendelkezik. Ezek közül a leggyakrabban használtak:

Lekérdező műveletek:

* `exists(Path)`: létezik-e a paraméterként átadott `Path` által 
reprezentált fájl vagy könyvtár
* `isDirectory(Path)`: az átadott `Path` könyvtár-e
* `isRegularFile(Path)`: az átadott `Path` normál fájl-e
* `size(Path)`: a fájl méretét adja vissza bájtban mérve

Módosító műveletek:

* `createDirectory(Path)`: létrehozza a `Path` által reprezentált könyvtárat 
feltéve, hogy annak minden szölőkönyvtára már létezik
* `createDirectories(Path)`: létrehozza a `Path` által reprezentált könyvtárat, és 
a hiányzó szülőkönyvtárakat is
* `copy(Path source, Path target, CopyOption... options)`: fájl másolása
* `copy(InputStream in, Path target, CopyOption... options)`: `InputStream` tartalmának 
fájlba írása
* `copy(Path source, OutputStream out)`: fájl tartalmának `OutputStream`-re helyezése 
* `move(Path source, Path target, CopyOption... options)`: fájl vagy könyvtár mozgatása
* `delete(Path)`: könyvtár vagy fájl törlése, ha nem létezik kivételt dob
* `deleteIfExists(Path)`: könyvtár vagy fájl törlése, ha létezik

## `CopyOption` interfész

A másolás, mozgatás műveletek működése finomhangolható. A különböző beállításokat `CopyOption` 
implementációkkal adhatjuk át. Például a fájl másolásakor mi történjen, ha a cél helyen már 
létezik az adott fájl. A `CopyOption`-t váró metódusoknak például a `StandardCopyOption` enum 
különböző értékei adhatók át.

* `StandardCopyOption.REPLACE_EXISTING`: a célfájl létezése esetén azt felül kell írni
* `StandardCopyOption.COPY_ATTRIBUTES`: a fájlattribútumokat is át kell másolni
* `StandardCopyOption.ATOMIC_MOVE`: a művelet atomi műveletként kezelendő

## Ellenőrző kérdések

* Sorold fel a `Files` osztály legalább két lekérdező műveletét! Mire valók?
* Sorold fel a `Files` osztály legalább öt módosító műveletét! Mire valók?
* Mire való a `CopyOption` interfész? Milyen implementációját ismered?

## Feladat

### Telepítés

Készíts egy `Installer` osztályt, egyetlen `install()` metódussal, mely paraméterként a 
telepítési mappát kapja meg `String`-ként. A megadott mappán belülre másold át a 
classpathról az install mappában lévő fájlokat és mappákat az install mappából! Az install.txt 
fájlban a `/` jelre végződő sorok mappákat jelölnek, ezeket létre kell hoznod, mielőtt fájlokat 
másolnál bele. Az esetleges sikertelen telepítésből visszamaradt fájlokat mindig írd felül!
Ha a paraméterül kapott mappa nem létezik vagy nem is mappa, dobj `IllegalArgumentException` kivételt!

