# Szöveges állomány beolvasása

## A `Path` interfész

A Java 7-ben megjelent Path interfész egy könyvtárat vagy fájlt reprezentál. 
Egy `Path` típusú objektum a Java 11 óta a `Path.of()` statikus metódussal hozható létre, 
mely egy elérési útvonalat tartalmazó szöveget vár paraméterként. Ez lehet 
abszolút vagy relatív útvonal is.

```java
Path fileInRootDirectory = Path.of("C:\\employees.txt");

Path fileInActualDirectory = Path.of("employees.txt");
```

Az útvonal elválasztó karaktere rendszerfüggő, ezért nem a legjobb megoldás, ha az 
beleégetjük a kódba. Ez a karakter a `FileSystems.getDefault().getSeparator()` metódussal 
lekérdezhető, de maga a `Path` is tartalmaz olyan metódusokat, amellyel elkerülhető az 
elválasztó explicit használata.

```java
Path path = Path.of("employees", "john-doe.txt")

Path file = Path.of("employees").resolve("a.dat");
```

Az első esetben a `path` változó egy relatív útvonalat tart az employees mappában 
lévő john-doe.txt fájlra. A második esetben az employees könyvtárra mutató `Path` objektumot 
kombináljuk a `resolve()` metódusnak átadott útvonallal, amely így végeredményben az 
employees könyvtárban lévő a.dat nevű fájlra mutat.

## Fájl tartalmának beolvasása

A `Files` osztály az állományok kezelésével kapcsolatos metódusokat tartalmaz. 
Ezek közül több is a szöveges fájlok tartalmának beolvasását végzi el nekünk. A 
`readString()` statikus metódusa a paraméterként átadott `Path` objektum által 
hivatkozott fájlt egyetlen komplett szövegként olvassa be, és Stringként adja vissza.

```java
Path file = Path.of("employees.txt");
try {
    String employees = Files.readString(file);
    System.out.println(employees);
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not read file", ioe);
}
```

Ha olvasás közben bármilyen hiba történik, a metódus `IOException` kivételt dob. 
Az olvasott adatok karakterként értelmezése alapértelmezetten UTF-8 kódolás 
szerint történik. Amennyiben a fájl más kódolással készült, akkor a `readString()` 
metódus második paramétereként megadhatunk egy `Charset` objektumot is. Ezt 
kétféleképpen is létrehozhatunk:
* a `Charset.forName()` statikus metódussal, mely paraméterként a karakterkészlet nevét várja, vagy
* a `StandardCharsets` osztályban található konstansokkal.

```java
Charset latin2 = Charset.forName("ISO-8859-2");
Charset utf8 = StandardCharsets.UTF_8;

String employees = Files.readString(file, latin2);
```

Amennyiben soronként külön-külön szeretnénk látni a fájl tartalmát, akkor 
használjuk a `Files.readAllLines()` metódust! Ez `List<String>` objektummal tér 
vissza. Hasonlóan az előzőhöz ez is `Path` objektumot vár paraméterként, hiba 
esetén pedig `IOException` kivételt dob.

```java
try {
    List<String> employees = Files.readAllLines(file);
    for (String employee: employees) {
        System.out.println(employee);
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not read file", ioe);
}
```

## Ellenőrző kérdések

* Hogyan lehet rendszerfüggetlenül létrehozni egy `Path` objektumot?
* Hogyan tudod egy szöveges fájl tartalmát egyszerre beolvasni egy változóba?
* Mi az alapértelmezett karakterkódolás, és hogyan lehet más kódolású fájlokat 
is beolvasni?

## Feladat

### Emberek
A feladat egy szöveges állományból nevek beolvasása és eltárolása egy listába. A megoldáshoz két osztály kell megvalósítanod. A `Human
` osztály reprezentál egy embert. Két adattagja vezeték- illetve keresztnév. A `FileManager` osztály felelős a fájl feldolgozásért. Egy `Path` típusú attribútumon
keresztül érjük el a fájlt, míg a `readFromFile()` metódus felelős a beolvasásért és a `Human` objektumok létrehozásáért.


### Banki tranzakciók
Ehhez a feladathoz két fájl tartalmát is fel kell dolgoznod. Az `accouts.txt` tartalmazza a bankszámla(`BankAccount`) adatokat.
A `transactions.txt` állomány tartalmazza az utalásokat azaz, hogy melyik számlára mennyit utaltunk. 
A feladat, hogy olvasd be a számlaadatokat egy listába, majd egy másik metódusban hajtsd végre a tranzakciókat!

