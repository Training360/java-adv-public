# Properties állományok

A `Properties` osztály olyan `Map`-hez hasonló adatszerkezet, mely kizárólag szöveges kulcs-érték párokat tartalmaz. Mivel ezek általában konfigurációs bejegyzéseket tartalmaznak, a bejegyzések streambe kiírhatók, illetve streamből betölthetők, ami alkalmassá teszi fájlba mentésre és betöltésre. Két támogatott szöveges fájlformátuma a properties és az XML.

## A Properties osztály metódusai

`String getProperty(String key)` : az adott kulcshoz tartozó értéket kérdezi le. Ha nem létezik a kulcs, akkor `null`-al tér vissza.

`String getProperty(String key, String defaultValue)`: a megadott kulcshoz tartozó értéket kérdezi le. Ha nem létezik a kulcs, akkor a `defaultValue` értékét adja vissza.

`Object setProperty(String key, String value)`: új kulcs-érték pár beszúrása. Ha a kulcs már létezett, akkor a hozzá tartozó értéket lecseréli az itt megadottra, és a régi értéket adja vissza.

`Set<String> stringPropertyNames()`: az összes létező kulcs halmazát adja vissza.

`void load(InputStream is)`: kulcs-érték párok betöltése byte streamből.

`void load(Reader reader)`: kulcs-érték párok betöltése karakter streamből.

`void loadFromXML(InputStream is)`: kulcs-érték párok betöltése XML fájlból.

`void store(Outputstream os, String comment)`:  a kulcs-érték párok mentése `OutputStream`-be.

`void store(Writer writer, String comments)`: a kulcs-érték párok mentése karakteres streambe.

`void storeToXML(OutputStream os, String comment)`: a kulcs-érték párokkal XML dokumentumot készít.

`void storeToXML(OutputStream os, String comment, String encoding)`: a kulcs-érték párokkal XML dokumentumot készít a megadott karakterkódolással.

## Properties állomány formátuma

A kulcs-érték párokat egyszerű `.properties` kiterjesztésű szöveges fájlba menthetjük. Az összetartozó kulcs és érték egy sorba kerül, közte `=` vagy `:` karakterrel. A különböző párokat külön-külön sorba írjuk. A sor eleji és az elválasztó karakter körüli szóközöket a `load()` metódus nem veszi figyelembe, de az érték után lévőket igen.

```properties
host = 192.168.0.1
   port   =80		// Ez ugyanaz, mint a port=80
protocol = http
```

Egy properties fájlon belül csak egyféle elválasztó karaktert használj!

## XML állomány formátuma

Ahhoz, hogy az XML dokumentumból `Propeties`-be töltsünk adatokat, a fájlnak speciális szerkezettel kell rendelkeznie. A gyökérelem neve mindig `properties`, ebben helyezkednek el az `entry` tagek, amelyeknek a `key` attribútuma tartalmazza a kulcsot, és a hozzá tartozó értéket írjuk a nyitó és záró tag közé. Ha tartozik megjegyzés hozzá, akkor az az első `entry` tag előtt lévő `comment` tagbe kerül.

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
  <comment>XML properties file</comment>
  <entry key="host">192.168.0.1</entry>
  <entry key="port">80</entry>
  <entry key="protocol">http</entry>
</properties>
```

## Ellenőrző kérdések

* A `Properties` osztály milyen értékek tárolására való?
* Mi a specialitása más kollekciókhoz képest?
* Milyen állományformátumokat ismersz? Hogyan épülnek fel?
* Hogyan lehet állományból beolvasni az értékeket?
* Hogyan lehet állományba kiírni az értékeket?

## Adatbázis konfiguráció beolvasása

Írj egy `properties.DatabaseConfiguration` osztályt, mely properties állományból betölti az adatbázis beállításokat, majd le lehet ezeket kérdezni. Létre lehet hozni paraméter nélküli konstruktorral, ekkor a classpath-ról tölti be a properties állományt. Van egy `File` paramétert váró konstruktora is, melyet megadva a beállításokat a paraméterként megadott fájlból tölti be.

### Megvalósítási javaslatok

Figyelj a karakterkódolásra. IDEA-ban, hogy a properties fájlt UTF-8 kódolással hozza létre, át kell állítani,
File / Settings / Editor / File Encodings ablakon a "Default encoding for properties files" értékét kell UTF-8-ra állítani. A karakterkódolás konstans értékként legyen megadva.

A betöltés a konstruktorban történjen. Érdemes felvenni egy `Properties` típusú attribútumot. A `getHost()`, `getPort()` és
`getSchema()` metódusok ezt hívják.

 [rating feedback=java-properties-adatbazisconfig]

## Java eszközök nyilvántartása

Egy properties állományban tárold el a különböző Java eszközök leírásait a következő formátumban:

```
jdk.name = Java Development Kit
jdk.url = http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html
maven.name = Apache Maven
maven.url = https://maven.apache.org/
junit.name = JUnit
junit.url = http://junit.org/junit4/
```

Írj egy `properties.JavaTools` osztályt, mely betölti ezen eszközöket a properties állományból.
Legyen egy `Set<String> getToolKeys()` metódusa, mely visszaadja az eszközök kulcsait (pl. `jdk`, `maven`).
Legyen egy `Set<String> getTools()` metódusa, mely visszaadja az eszközök neveit.
Legyen egy `String getName(String)` metódusa, mely kulcs alapján visszaadja annak nevét, és
 egy `String getUrl(String)` metódusa, mely kulcs alapján visszaadja annak címét.
 
[rating feedback=java-properties-javaeszkozok]
