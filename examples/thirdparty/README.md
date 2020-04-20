# Third party library-k

## Ellenőrző kérdések

* Mi szükség van third party library-kre?
* Mi az a library, ami nem a Java SE része, és eddig is használtuk?
* Mi az a CLASSPATH?
* Hogyan lehet third party library-t használni Mavennel?
* Melyek a leggyakrabban használt third party library-k?

## Szavak számlálása

Írj egy `WordCounter` osztályt, mely `public int numberOfWords(String s)` metódusa a paraméterként
átadott szöveget szavakra bontja, és visszaadja, hogy mennyi szóból áll. Tegyük fel, hogy
space, vessző és pont karakterek az elhatároló karakterek.

Próbáld ki először a `String` `split()` metódusát.

A Guava library `Splitter` osztálya paraméterezhető, használd azt!
Először a függőséget kell definiálni a `pom.xml` állományban.

```xml
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>21.0</version>
</dependency>
```

Adjon vissza egy listát (`splitToList`), és annak a méretét (`size()` metódus).

Futtasd az alkalmazást parancssorból!

A `java -classpath target\classes thirdparty.WordCounterMain "foo bar"` parancssor futtatásakor
a következő hibaüzenetet kapjuk:

```
D:\vicziani\Work\yellowroad-java\thirdparty>java -classpath target\classes thirdparty.WordCounterMain "foo bar"
Exception in thread "main" java.lang.NoClassDefFoundError: com/google/common/base/CharMatcher
        at thirdparty.WordCounter.numberOfWords(WordCounter.java:9)
        at thirdparty.WordCounterMain.main(WordCounterMain.java:10)
```

A JAR-t futtatva ugyanezt a hibaüzenetet kapjuk: `java -jar target\thirdparty-1.0-SNAPSHOT.jar "foo bar"`.

Megoldásként a CLASSPATH-ra rá kell tenni a Guava third party library-t. Töltsd le a következő címről:
http://repo1.maven.org/maven2/com/google/guava/guava/21.0/guava-21.0.jar, és másold a `target`
könyvtárba. (Vigyázz, az `mvn clean` parancs futtatásakor a `target` könyvtár törlésre kerül.)

Futtassuk a következő parancssorral: `java -classpath target\classes;target\guava-21.0.jar thirdparty.WordCounterMain "foo bar"`.

A `pom.xml`-t egészítsük ki a következőképpen:

```xml
<plugin>
    <artifactId>maven-jar-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <mainClass>thirdparty.WordCounterMain</mainClass>
                <addClasspath>true</addClasspath>
            </manifest>
        </archive>
    </configuration>
</plugin>
```

Futtassuk a következő paranccsal: `java -jar target\thirdparty-1.0-SNAPSHOT.jar "foo bar"`.

Mi változott a JAR állományban a `META-INF\MANIFEST.MF` fájlban?

Egészítsd ki a `pom.xml` állományt a következőképpen:

```xml
<plugin>
    <artifactId>maven-assembly-plugin</artifactId>
    <configuration>
        <archive>
            <manifest>
                <mainClass>thirdparty.WordCounterMain</mainClass>
            </manifest>
        </archive>
        <descriptorRefs>
            <descriptorRef>jar-with-dependencies</descriptorRef>
        </descriptorRefs>
    </configuration>
</plugin>
```

A következő parancsot kell futtatni: `mvn clean compile assembly:single`, majd az
alkalmazást: `java -jar target\thirdparty-1.0-SNAPSHOT-jar-with-dependencies.jar "foo bar"`.

Mi a különbség a két JAR között?

[rating feedback=java-thirdparty-wordcounter]

## Bónusz feladat 1.

A Maven hol keresi a `guava-21.0.jar` állományt a tesztesetek futtatásakor?
