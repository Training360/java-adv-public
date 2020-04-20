# String olvasása Readerrel

Nagyméretű szöveges fájlok esetén nem célravezető, ha a teljes tartalmat egyszerre 
olvassuk be a memóriába. Ebben az esetben jobb, ha olvasás közben dolgozzuk fel az adatokat. 
Olvashatunk karakterenként, soronként vagy bármekkora egységenként, ehhez pusztán 
egy `Reader` példányra van szükségünk. A `Reader` egy absztrakt osztály, melynek több konkrét 
megvalósítása is van. Ezek közül az alapján választhatunk, hogy milyen módon szeretnénk 
a szöveges fájlt feldolgozni. Másik nagy előnye a darabokban olvasásnak, hogy 
bármikor megszakíthatjuk, nem kell feltétlenül a teljes fájlt felolvasni.

![Szöveges állomány olvasása fájlrendszerből](images/io-reader01.png)

Először mindenképpen egy `Path` objektumra van szükségünk, mely az olvasandó fájlt 
reprezentálja. Ha soronként szeretnénk feldolgozni a fájlt, akkor ezt a 
`BufferedReader` segítségével tehetjük meg. Ennek egy példányát a `Files.newBufferedReader()` 
metódussal készíthetjük el, amelynek átadjuk a `Path` objektumot. A `BufferedReader` 
`readLine()` metódusa a fájlnak egyetlen sorát adja vissza. Amikor vége a fájlnak, akkor a 
metódus visszatérési értéke `null` lesz, ezért a sorok olvasása történhet while ciklusban. 
A `BufferedReader` objektumot le kell zárni, ezért try-with-resources szerkezetben hozzuk létre. 
Megnyitáskor és olvasás közben `IOException` kivétel keletkezhet, melyet megfelelően kezelnünk kell.

```java
Path file = Path.of("employees.txt");
try (BufferedReader reader = Files.newBufferedReader(file)) {
    String line;
    while((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not read file", ioe);
}
```

Alapértelmezetten UTF-8 kódolású fájlt vár, de ez felülírható, ha a `Files.newBufferedReader()` 
metódusnak paraméterként egy `Charset` példányt is átadunk.

```java
BufferedReader reader = Files.newBufferedReader(file, Charset.forName("ISO-8859-2"))
```
## Java 8 előtt

Mint láttuk, a `Files` osztály itt is a segítségünkre volt, de a `newBufferedReader()` metódusa csak a 
Java 8-ban jelent meg, míg a `Reader` és a `BufferedReader` osztályok mindig is léteztek. 
A különböző `Reader` megvalósításokat két szintre sorolhatjuk:
* Alacsony szintű a `FileReader`, mely minimális műveletre képes, de közvetlen 
hozzáférést biztosít az erőforráshoz. Ezzel karakterenként olvashatunk.
* A magas szintű `Reader`-ek valamilyen szempontból hatékonyabb olvasást biztosítanak. Ilyen 
a pufferelt olvasást támogató `BufferedReader`.

Magas szintű `Reader` más, már létező `Reader`-t burkol be, ezért amikor mi magunk 
akarunk `BufferedReader` példányt készíteni, akkor először egy `FileReader`-t kell 
példányosítanunk.

```java
BufferedReader reader = 
    new BufferedReader(new FileReader("employees.txt"))

BufferedReader reader = 
    new BufferedReader(new FileReader(new File("employees.txt")))
``` 

![FileReader és BufferedReader kapcsolata](images/io-reader02.png)

Elsőre ez bonyolultnak tűnhet, de nagy előnye ennek a felépítésnek, hogy egy magasabb 
szintű `Reader` nem csak a fájlrendszerből való olvasást tudja optimalizálni, 
hanem például a hálózatról érkező adatok olvasását is, azaz független az adat forrásától.

A `FileReader` példányosításakor az alapértelmezett karakterkódolás mindig a 
futtató platformon alapértelmezett lesz, de mi is megadhatjuk `Charset` példány 
átadásával.

```java
BufferedReader reader = new BufferedReader(new FileReader("employees.txt", StandardCharsets.UTF_8))
```

## Ellenőrző kérdések

* Hogyan lehet egy szöveges fájlt részletekben olvasni?
* Milyen karakterkódolással dolgozik a `Files.newBufferedReader()` metódussal 
létrehozott `Reader`? Hogyan lehet ezt megváltoztatni?

## Feladat

### Személyi igazolvány számok

A `idread` csomagban készítsd el az `IdManager` osztályt. Ez az osztály felelős az `idnumbers.txt` (kitalált) személyi igazolvány
számokat tartalmazó szöveges állomány feldolgozásáért. A `readIdsFromFile()` metódus megkap egy fájlnevet, és annak sorait tárolja el az `List<String> ids` listában. 

### USA tagállamok
  A következő feladatban a `states` csomagban kell dolgoznod! A `stateregister.txt` állomány tartalmazza az Amerikai Egyesült Államok államait és azok
  fővárosait kötőjellel elválasztva. Neked ezt a fájlt kell feldolgoznod és egy keresés algoritmust készítened!
  
  * Hozzd létre a `State` java osztályt melynek két attribútuma az állam neve `stateName` és a főváros neve
 `capital`! (Konstruktor,getterek!)
 
 * A `StateRegister` osztály felelős a file beolvasásáért és a `State` objektumok létrehozásáért, valamint a keresésért.
 Legyen benne egy lista `states` , `State` generikussal, amibe a `readStatesfromFile(stringFileName)` eltárolja a beolvasott adatokat.
 A `findCapitalByStateName(String stateName)` megkeresi a paraméterül kapott állam fővárosát. Ha nincs ilyen állam akkor `IllegalArgument` exception-t dob!


### Osztálynapló

A követező feladat egy osztálynapló nyilvántartása. A `grades.txt` fájl minden sora tartalmaz egy nevet és utána a tanuló jegyeit.
Készíts egy `Student` osztályt mely a tanuló nevét és jegyeinek listáját képes tárolni! Legyen benne egy átlagszámító metódus, valamint
egy metódus, ami képes eldönteni, hogy a tanuló jegyei emelkednek-e.

Készíts egy `SchoolRecordsManager` osztályt, ami beolvassa fájlból az adatokat, eltárolja, és ezen felül még képes egy osztályátlag számítására is!