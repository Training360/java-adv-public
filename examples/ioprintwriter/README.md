# Különböző típusok írása PrintWriterrel

A különböző típusú adatok kiírása szöveggé konvertálás nélkül is lehetséges a 
`PrintWriter` osztály segítségével. A `print()`, `println()` és a `printf()` metódusa 
overloadolt, minden primitív típusú, valamint `String` és általános `Object` típusú 
adattal is paraméterezhető.

`PrintWriter` objektumot `BufferedWriter` objektum becsomagolásával készíthetünk.

![FileWriter, BufferedWriter és PrintWriter](images/io-printwriter01.png)

A fájl megnyitása `IOException` típusú kivételt dobhat, melyet kezelnünk kell, az írást 
megvalósító metódusok azonban sosem dobnak kivételt.

```java
List<String> employees = List.of("John Doe", "Jane Doe", "Jack Doe");
Path file = Path.of("employees.txt");
try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(file))) {
    for (String employee: employees) {
        writer.print(employee);
        writer.print(",");
        writer.println(200_000);
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not read file", ioe);
}
```

## Ellenőrző kérdések

* Milyen előnyei vannak a `PrintWriter`-nek a `BufferedWriter`-hez képest?
* Mi okozhat kivételt `PrintWriter` használata során?

## Feladatok

### Fizetések

Ebben a feladatban emberek fizetését kell meghatároznod titulus alapján. A `SalaryWriter` osztály
konstruktorban kap egy név listát. A `writeNamesAndSalaries(Path file)` metódus kiírja a fájlba `név: összeg` formátumban.
A fizetések a következő képpen alakulnak:

* Ha tartalmazza a név a "Dr" előtagot, akkor 500000
* Ha a "Mr" vagy "Mrs" előtagot akkor 200000
* Különben 100000

### Szavazatszámlálás

Ebben a feladatban egy tehetségkutató showt fogunk szimulálni. A feladat kicsit összetettebb. A `talents.txt` tartalmazza az
indulók listáját, míg a `votes.txt` a leadott szavazatokat, azaz az előadó kódját.

A te feladatod, hogy készíts egy kimutatást egy fájlba. A fájlnak tartalmaznia a kódot az előadás nevét illetve, hogy az adott
előadás hány szavazatot kapott! Ezen felül az utolsó sornak tartalmaznia kell a győztes nevét a következő formátumban:
`Winner: győztes neve`. 

A megoldáshoz használj nyugodtan private metódusokat.

