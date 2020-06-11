# Adatok írása OutputStreamre, és olvasás

## `DataOutputStream`

Bináris állományba nem csak bájtokat, hanem egyéb primitív típusú adatokat és 
szöveget is írhatunk `DataOutputStream` segítségével. A metódusai az adatokat 
bájtsorozattá alakítják, majd átadják az alatta lévő `OutputStream`-nek. Minden 
adattípusnak saját metódusa van, például `writeInt()`, `writeDouble`, szöveghez 
a `writeUTF()`.

A `DataOutputStream` magas szintű `OutputStream`, ezért más `OutputStream` megvalósításokon át 
dolgozik. Tipikusan már létező `BufferedOutputStream` objektummal hozzuk létre.

![FileOutputStream, BufferedOutputStream és DataOutputStream együttműködése](images/io-datastream01.png)

A `DataOutputStream`-et a többihez hasonlóan try-with-resources szerkezetben 
hozzuk létre, így az automatikusan lezárja. Az íráshoz használt változót 
`DataOutputStream`-ként kell deklarálni, hogy a speciális metódusait elérhessük. 
Megnyitás és írás közben `IOException` kivétel keletkezhet, melyet kezelnünk kell. 

```java
Path file = Path.of("data.dat");
try (DataOutputStream outputStream = new DataOutputStream(
        new BufferedOutputStream(Files.newOutputStream(file)))) {
    outputStream.writeUTF("John Doe");
    outputStream.writeInt(200_000);
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not write file", ioe);
}
```

## `DataInputStream`

A bináris fájlok tartalmát nem csak `byte[]`-be, de közvetlen primitív típusú 
és szöveges típusú változókba is beolvashatjuk. Az átalakítást a `DataInputStream` 
teszi lehetővé, mely ugyanúgy magas szintű stream, mint a `DataOutputStream`.

![FileInputStream, BufferedInputStream és DataInputStream együttműködése](images/io-datastream02.png)

Adattípustól függően olvassa be a bájtokat, azaz a metódusai különböző méretű 
`byte[]`-öt alakítanak át a megfelelő típusú adatra. Például `int` típusú adat olvasásakor 
4 bájt kerül beolvasásra és átalakításra. A primitív adatok mind előre meghatározott 
méretűek, azonban a szöveg esetén ez nem működik. A `DataOutputStream` `writeUTF()` 
metódusa először két bájton kiírja a szöveg bájtban vett hosszát, csak ezután következik a `String` 
értéke. Beolvasáskor ezért az első két bájt értéke határozza meg, hogy mekkora mennyiségű 
adatot kell szövegként értelmeznie a `readUTF()` metódusnak.

```java
Path file = Path.of("data.dat");
try (DataInputStream inputStream = new DataInputStream(
        new BufferedInputStream(Files.newInputStream(file)))) {
    String name = inputStream.readUTF();
    System.out.println(name);
    int salary = inputStream.readInt();
    System.out.println(salary);
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not read file", ioe);
}
```

## Ellenőrző kérdések

* Milyen osztály segítségével lehet bináris fájlba primitív típusú adatokat és 
szöveget írni?
* Hogyan történik ezek írása?
* Milyen osztállyal lehet bináris fájlból bármilyen primitív típusú adatot és szöveget olvasni?
* Hogyan történik az olvasás?
* Hogyan alakítja a szövegeket a `DataOutputStream` `byte[]` típusú adattá?

## Feladat

### Számok

Az `iodatastream.statistics` csomagba készíts egy `HeightStatistics` osztályt, bele pedig egy `saveHeights()` 
metódust. A metódus egy kosárcsapat tagjainak testmagasságát kapja meg `List<Integer>` típusú 
paraméterben. A  paraméterben kapott `Path` objektumként reprezentált fájlba menti 
először a lista méretét, majd egyenként a lista elemeit.

### BankAccount

Az `iodatastream.bank` csomagba készítsd el a `BankAccount` osztályt, 
melyben attribútumként a számlaszám (`String`), 
a tulajdonos neve (`String`) és az egyenleg (`double`) szerepel! A konstruktor mindhárom 
attribútumot várja, valamint mindegyikhez van getter.

Készíts egy `BankAccountManager` osztályt, melyben csak 2 metódus van. A `saveAccount()` 
metódus egy `BankAccount` példány állapotát menti a 
bankszámla számával megegyező nevű .dat kiterjesztésű fájlba
`DataOutputStream` segítségével. Paraméterként megkapja a mentési mappát is `Path` objektumknt. 
A `loadAccount()` ugyanilyen szerkezetű fájlból betölti 
egy `BankAccount` adatait, és paraméterként csak egy `InputStream`-et vár.

A fájl minden adatot tartalmazzon az alábbi sorrendben: 
számlaszám, tulajdonos neve, egyenleg!

