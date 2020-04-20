# Naplózás

## Ellenőrző kérdések

* Miért használunk naplózó keretrendszert?
* Milyen elvárásaink lehetnek egy naplózó keretrendszerrel kapcsolatban?
* Milyen Java megvalósításokat ismersz?
* Mi az a logger és naplózási szint?

## Gyakorlati feladat - karakterek keresése

Készíts egy `logging.CharacterCounter` osztályt, benne egy
`int countCharacters(String source, String chars)` metódust,
mely megszámolja hogy az első paraméterként megadott szövegben
hányszor szerepel a második paraméterként megadott karakterek egyike.

A metódus elején naplózd ki, hogy a metódus meghívásra került, és 
milyen paraméterekkel.

Pl. `Finding 'ae' characters in 'abcdabcdabcdabce'`.
 
Amennyiben találat van, naplózd ki, hogy melyik karaktert találta meg
az algoritmus, és hanyadik karakteren.

Pl. `'a' character found at 12. index`

### Tipp

Függőségként fel kell venni a `pom.xml` állományban az SLF4J-t
a következőképpen:

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>${slf4j.version}</version>
</dependency>

<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>${slf4j.version}</version>
</dependency>
```

A verziószámot a `properties` tagen belül kell definiálni:

```xml
<slf4j.version>1.7.22</slf4j.version>
```

[rating feedback=java-logging-karakterkereses]

