# JAVA-BSC - Java SE haladó

## Javasolt haladás

* Először nézd meg a videót! A videóban szereplő forráskódot a [demos](demos) könyvtárban találod.
* Dolgozd fel a videóhoz tartozó írott anyagot, amelynek itt találod a tartalomjegyzékét, és a következő részekből áll:
	* Olvasd el a megfelelő elméleti részt, értelmezd!
	* Próbálj válaszolni az ellenőrző kérdésekre!
	* Old meg a gyakorlati feladatot, melynek itt találod a feladatleírását. Dolgozhatsz ugyanabba a projektbe. Minden leckéhez hozz létre külön csomagot! Figyelj, hogy a minden leckéhez vannak tesztesetek, ezeket másold be a projektedbe!

## Tartalomjegyzék

* [ioreadstring - Szöveges állomány beolvasása](examples/ioreadstring/README.md)
* [iowritestring - String kiírása szöveges állományba](examples/iowritestring/README.md)
* [ioreadwritebytes - Bájtos beolvasása fájlból és kiírása fájlba](examples/ioreadwritebytes/README.md)
* [ioreader - String olvasása Readerrel](examples/ioreader/README.md)
* [iowriter - String írása Writerrel](examples/iowriter/README.md)
* [ioprintwriter - Különböző típusok írása PrintWriterrel](examples/ioprintwriter/README.md)
* [iostringwriter - Kiírás Stringbe StringWriterrel](examples/iostringwriter/README.md)
* [ioreadbytes - Bájtok olvasása InputStreammel](examples/ioreadbytes/README.md)
* [ioreaderclasspath - String olvasása classpath-ról](examples/ioreaderclasspath/README.md)
* [iowritebytes - Bájtok írása OutputStreammel](examples/iowritebytes/README.md)
* [iodatastream - Adatok írása OutputStreamre, és olvasás](examples/iodatastream/README.md)
* [iozip - Bájtok írása tömörítéssel](examples/iozip/README.md)
* [ioconvert - Konvertálás a típusok között](examples/ioconvert/README.md)
* [iofiles - Files osztály használata](examples/iofiles/README.md)
* [iofilestest - Fájlkezelés tesztelése](examples/iofilestest/README.md)
* [genericsusage - Generikusok használata, diamond operátor](examples/genericsusage/README.md)
* [collectionsequalshash - equals, hashCode](examples/collectionsequalshash/README.md)
* [collectionslist - List, ArrayList kontra LinkedList](examples/collectionslist/README.md)
* [collectionsset - Set](examples/collectionsset/README.md)
* [collectionsqueue - Queue](examples/collectionsqueue/README.md)
* [collectionsmap - Map](examples/collectionsmap/README.md)
* [collectionsautoboxing - Autoboxing használata kollekcióknál](examples/collectionsautoboxing/README.md)
* [collectionsiterator - Bejárás](examples/collectionsiterator/README.md)
* [collectionscomp - Comparable és Comparator](examples/collectionscomp/README.md)
* [searching - Keresés](examples/searching/README.md)
* [sorting - Rendezés](examples/sorting/README.md)
* [collectionsclass - Collections osztály (keresésen, rendezésen felüli metódusok)](examples/collectionsclass/README.md)
* [clone - A clone() metódus, deep clone](examples/clone/README.md)
* [properties - Properties állományok](examples/properties/README.md)
* [lambdaintro - Bevezetés a lambda kifejezések használatába](examples/lambdaintro/README.md)
* [lambdaoptional - Optional használata](examples/lambdaoptional/README.md)
* [lambdacomparator - Comparator módosítások](examples/lambdacomparator/README.md)
* [lambdastreams - Streamek](examples/lambdastreams/README.md)
* [lambdaintermediate - Intermediate műveletek](examples/lambdaintermediate/README.md)
* [lambdaprimitives - Primitívek használata streamekben](examples/lambdaprimitives/README.md)
* [lambdacollectors - Collectors](examples/lambdacollectors/README.md)
* [datenewtypes - Új típusok, LocalDate, LocalTime, parse](examples/datenewtypes/README.md)
* [dateoldtypes - Régi dátum- és időkezeléssel kapcsolatos típusok](examples/dateoldtypes/README.md)
* [jvm - JVM](examples/jvm/README.md)
* [thirdparty - Third party library-k](examples/thirdparty/README.md)
* [logging - Naplózás](examples/logging/README.md)
* [dpintro - Bevezetés a tervezési minták használatába](examples/dpintro/README.md)
* [solid - S.O.L.I.D. elvek](examples/solid/README.md)
* [singleton - Singleton tervezési minta](examples/singleton/README.md)
* [builder - Builder tervezési minta](examples/builder/README.md)
* [templatemethod - Template method tervezési minta](examples/templatemethod/README.md)
* [simplefactory - Simple factory tervezési minta](examples/simplefactory/README.md)

## Java fejlesztőeszközök

Ellenőrizd, lehet, hogy a gépeden már előre van telepítve a 
Java SE Development Kit!

Nézd meg a `C:\Program Files\Java` könyvtárat!

A Java SE Development Kit már nem állítja be sem a `JAVA_HOME`
környezeti változót, sem a `PATH`-t, ezért
kézzel kell beállítani a környezeti változók között.

Ez ettől függetlenül nem szükséges, mert a 
Java SE Development Kitet kizárólag fejlesztőkörnyezetből
használjuk, parancssorból nem.

Amennyiben mégis be kell állítani, a következő kettőt kell:

```
JAVA_HOME=C:\Program Files\Java\jdk-12.0.0
```

Valamint módosítani kell a `PATH` környezeti változó
értékét, fel kell venni egy új sort:

```
%JAVA_HOME%\bin
```
