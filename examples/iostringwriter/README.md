# Kiírás Stringbe StringWriterrel

A `StringWriter` osztály az írást nem fájlba, hanem a memóriába egy `String`-be végzi. 
Az elkészült szöveget metódussal le tudjuk kérdezni. Tulajdonképpen ennél többre is képes a 
`StringBuilder`, de mivel a `StringWriter` `Writer` leszármazott, mindenhol 
használható, ahol egy létező metódus `Writer`-t vár. Főként tesztelési célból használjuk.

Paraméter átadása nélkül példányosítható, és írni a `write()` metódussal lehet. `IOException` kivételt 
egyedül a `close()` metódusa dobhat, ezért nem szükséges a try-with-resources szerkezetben létrehozni, de 
át kell adni neki a fejrészben.

A tartalmát a `toString()` metódussal kérdezhetjük le.

```java
StringWriter sw = new StringWriter();
try (sw) {
    for (String employee: employees) {
        sw.write(employee);
        sw.write(", ");
    }
}
catch (IOException ioe) {
    throw new IllegalStateException("Can not write", ioe);
}
System.out.println(sw.toString());
```

Amennyiben egy létező metódus vár `Writer`-t, az nem tudja, milyet is fog kapni.

```java
public void writeTo(List<String> employees, Writer writer) {
    try {
        for (String employee : employees) {
            writer.write(employee);
            writer.write(", ");
        }
    } catch (IOException ioe) {
        throw new IllegalStateException("Can not read file", ioe);
    }
}
```

A fenti metódus híváskor kaphat például `BufferedWriter`  példányt

```java
<String> employees = List.of("John Doe", "Jane Doe", "Jack Doe");
try (BufferedWriter writer = Files.newBufferedWriter(Path.of("employees.txt"))) {
    writeTo(employees, writer);
} catch (IOException ioe) {
    throw new IllegalStateException("Can not write file", ioe);
}
```

de kaphat `StringWriter`-t is.

```java
StringWriter stringWriter = new StringWriter();
try (stringWriter) {
    writeTo(employees, stringWriter);
} catch (IOException ioe) {
    throw new IllegalStateException("Can not write", ioe);
}
System.out.println(stringWriter.toString());
```

Az első esetben a `writeTo()` metódus az employees.txt fájlba, a második esetben a 
memóriába írt.

## Feladat

* Mire való a `StringWriter` osztály? Hasonlítsd össze a `BufferedWriter`-rel és a 
`StringBuilder`-rel!

### Hosszú szavak

Az első feladatban hosszú szavakat kell kezelned `StringWriter` segítségével. Írj egy metódust, ami egy `Writer`-t és egy listát kap paraméterül, és minden lista beli elem után írja a szó hosszát! 
Majd írj egy metódust, ami csak egy listát vár, és az előzőleg megírt metódust használja a paraéterül kapott listával és egy példányosított `StringWriter`-rel!