# Singleton tervezési minta
A legjobban vitatott tervezési minta, implementáció során elég sok problémába bele lehet futni. A probléma a következő: szeretnénk, hogy egy osztálynak csak egyetlen egy példánya legyen és ezen egységes interfészen keresztül lehessen hozzáférni.

Kérdés: mikor jöjjön létre ez a példány? Vagy az alkalmazás indításakor, vagy akkor amikor először szükség van rá (lazy initialization). Figyelni kell a párhuzamosságra, is. Ha két szál egyszerre módosítja a singletont akkor inkonzisztens lehet. Singletont általában alkalmazásban nem írunk, keretrendszerekben használjuk, például többrétegű alkalmazásoknál a controller.



## Ellenőrző kérdések

* Hogyan biztosítja a tervezési minta, hogy ne lehessen kívülről létrehozni egy új példányt?
* Miért érdemes bevezetni interfészt a funkciókra?
* Miért nem javasolt a statikus metódust hívni a felhasználó osztályokból?

## Gyakorlat - Termelő, fogyasztó, raktár - Singleton

A fogyasztó (`Consumer`) saját ritmusában, saját algoritmusa szerint működve felhasználja a termelő (`Producer`)
által előállított és a raktárba `LocalStore` elhelyezett terméket (`Product`), a soron következőt mindig a raktárból véve elő.
Legyen a raktár egy, csak egy példányban létező objektum (a termékek tárolását valamilyen kollekció biztosítja)
amelybe a termelő beteszi a terméket, a fogyasztó pedig kiveszi.
A raktár `LocalStore` mérete limitált, csak 3000 terméket képes tárolni, funkcióit a `Store` interfész írja le.

`Store` interfész

publikus metódusok:

```java
void add(Product product);
Product remove(); //a legrégebben beadott terméket veszi ki, FIFO elven
void reset(); //kiüríti a raktárt
int getProductCount(); // a raktárban tárol termékek száma
```
  `LocalStore` implementálja a `Store` interfészt `List<Product> products`, `int CAPACITY = 3000`
 és `static LocalStore instance` attribútumokkal.
 Singleton pattern szerint épül fel, azaz nem példányosítható közvetlenül, csak egy példánya létezhet.

publikus metódusok:

```java
public static LocalStore getInstance();
public void reset();
public void add(Product product);
public Product remove();
public int getProductCount();
```
 Ha a raktár üres, és mégis ki akarunk venni terméket, dobjon `IllegalStateException`-t a megfelelő szöveggel.
 Ha a raktárba a kapacitás felett akarunk betenni terméket, hagyja figyelmen kívül az utasítást.

`Product` osztály (immutable) `String name` attribútummal.

`Producer` osztály `Store store` attribútummal.

publikus metódusok:

```java
public Producer(Store store)
public Product produce(String name) // Előállítja a terméket és raktárba helyezi
```

`Consumer` osztály `Store store` attribútummal

publikus metódusok:

```java
public Consumer(Store store)
public Product consume() // Előállítja a terméket és raktárba helyezi
```

 Ha a raktár üres, dobjon `IllegalStateException`-t a megfelelő szöveggel.
 
 [rating feedback=java-singleton-raktar]
