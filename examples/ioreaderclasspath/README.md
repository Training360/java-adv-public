# String olvasása classpath-ról

A felhasználóknak általában jar állományban adjuk át az
alkalmazást. Ebben nem csak osztályok, hanem más állományok is
találhatóak. Hogyan tudjuk ezeket a fájlokat beolvasni, hiszen ezek nem képezik 
a fájlrendszer részét?

Ezek a fájlok a classpath-on találhatók, a JVM itt keresi őket. Maven használata 
esetén ez a `src/main/resources` könyvtárat jelenti, ezek az állományok a jar állomány gyökerébe kerülnek. A 
könyvtárba alkönyvtárak hozhatók létre. A fájlok útvonala ekkor egy harmadik módon is megadható: a  
**gyökér útvonal** `/` jellel kezdődik, és mindig a classpath gyökér könyvtárához viszonyított 
útvonalat tartalmaz.

A classpath-on elhelyezett fájlokat a `Class` osztály `getResourceAsStream()` metódusával lehet 
megnyitni. A metódust az aktuális osztályon kell meghívni, szövegként várja az 
útvonalat, és egy `InputStream`-mel tér vissza. Ha ezt szöveges fájlként 
szeretnénk olvasni, akkor `Reader`-ré kell alakítani. Erre alkalmas az `InputStreamReader` 
osztály. Létrehozásakor egy `InputStream`-et vár, és egy `Reader`-rel tér vissza. 
Ezt `BufferedReader`-be csomagolva már akár soronként is olvashatjuk.

![InputStream átcsövezése BufferedReaderbe](images/io-readerclasspath01.png)

```java
try (BufferedReader reader = new BufferedReader(new InputStreamReader(
        EmployeeService.class.getResourceAsStream("/employees.txt")))) {
    String line;
    while((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not read file", ioe);
}
```

Mindig figyeljünk az útvonal helyes megadására! Ha elhagyjuk a kezdő `/` jelet, 
akkor a relatív útvonal az osztály csomagjának megfelelő classpath-on lévő mappához 
viszonyított útvonalat jelent!

Tehát ha van egy `ioreaderclasspath.EmployeeReader` osztályon meghívott 
`getResourcesAsStream("employees.txt")`, akkor az employee.txt állományt az
`src/main/resources/ioreaderclasspath/` könyvtárban kell elhelyezni.

## Ellenőrző kérdések

* Hova kell tenni az alkalmazás részét képező erőforrás állományokat Maven 
használata esetén? Jar készítésekor hova kerülnek ezek az állományok a jar-on belül?
* Hogyan lehet hivatkozni ezekre a fájlokra? Mi az a gyökér útvonal?
* Hogyan lehet a classpath-on lévő fájlokat megnyitni?
* Hogyan lehet a classpath-on lévő szöveges állományokat soronként beolvasni?

## Feladat

### Országok

A `src/main/resources/country.txt` állományban országnevek és a szomszédos országok száma található.
Hozz létre egy az ország tárolására alkalmas osztályt `Country` néven! 

Hozz létre egy `CountryStatistics` osztályt, ahol beolvasod  file tartalmát egy listába, amjd írj metódusokat amelyek visszatérési értéke választ ad a következő kérdésekre:
* Hány országot olvastál be?
* Melyik országnak van a legtöbb szomszédja?