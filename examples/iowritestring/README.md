# String kiírása szöveges állományba

Szöveges fájl írása szintén a `Files` osztály segítségével történik. A `writeString()` 
metódusának átadva a fájl elérhetőségét és a bele írandó szöveget az egy lépésben 
kiírja a teljes szöveget. Bármilyen hiba esetén a metódus `IOException` kivételt dob.

```java
Path file = Path.of("employees.txt");
try {
    Files.writeString(file, "John Doe\nJane Doe\n");
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not write file", ioe);
}
```

A fájl alapértelmezett kódulása UTF-8 lesz, de a metódus harmadik paramétereként egy 
`Charset` példányt megadva ezen módosíthatunk.

```java
Files.writeString(file, "John Doe\nJane Doe\n", Charset.forName("ISO-8859-2"));
```

Amennyiben az írandó fájl még nem létezik, akkor a metódus hívásának hatására létrejön, 
ha viszont létezik, akkor az előző tartalma törlődik, és csak az újonnan beleírt 
szöveg lesz benne. Amennyiben az új szöveget a már meglévő tartalom végéhez szeretnénk 
hozzáfőzni, akkor paraméterként a `StandardOpenOption.APPEND` enum értéket is át kell adnunk.
A `StandardOpenOption` enum az `OpenOption` interfészt implementálja, és sok más beállítás is átadható vele 
a `writeString()` metódusnak.

```java
Files.writeString(file, "John Doe\nJane Doe", StandardOpenOption.APPEND);
```

Nem csak egyetlen szöveg, hanem szövegek listája is kiírható fájlba a `Files.write()` 
metódus segítségével. Ebben az esetben minden listaelem külön-külön sorba kerül.

```java
List<String> employees = List.of("John Doe", "Jane Doe");
Files.write(file, employees);
```

A `Files.write()` metódusnak is lehet karakterkészletet, illetve `OpenOption` 
példányokat átadni paraméterként.

## Ellenőrző kérdések

* Hogyan lehet szöveges fájlba tartalmat írni?
* Milyen karakterkészlet az alapértelmezett és hogyan lehet ezt módosítani?
* Mi történik, ha az írandó fájl már létezik?
* Hogyan lehet lézető fájl végére hozzáfűzni az új tartalmat?

## Feladat

### Napló

Ebben a feladatban egy iskolai naplózó rendszert kell megvalósítanod.

* Készíts a `school` csomagban egy `Diary` nevű osztályt benne egy `newMark()` metódussal, ami paraméterül várja a tanuló nevét és egy jegyet!
  Ha létezik a "tanulo_neve.txt" akkor a jegyet hozzáfűzi az állomány végére. Ha nem, akkor egy új állományt hoz létre "tanulo_neve.txt" formátumban, és beleírja a jegyet.
  Azt, hogy létezik-e egy fájl a `Files.exists(path)` metódussal tudod eldönteni. A fájlok a **src/main/resources/** könyvtárban legyenek!
  
* Az év végén a tanár szeretné a tanuló fájl utolsó sorába az átlagot beírni. Írj egy metódust `average()` névvel, ami a fájl utolsó sorába a jegyek átlagát írja be! 
Például "average: 5".
