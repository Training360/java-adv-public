# JVM

## Ellenőrző kérdések

* Hogyan valósítja meg a Java a hordozhatóságot, platform függetlenséget?
* Hogy hívják a Sun/Oracle féle megvalósítást?
* Mi felel az osztályok betöltéséért? Honnan képes osztályokat betölteni?
* Mi biztosítja a Java alkalmazások gyors futását annak ellenére, hogy egy virtuális gép futtat egy interpretált nyelvet?
* Mi alapján érdemes választani, hogy 32 vagy 64 bites JVM-et telepítsünk?
* Hogyan működik a garbage collector? Mi az az elv, ami alapján felépítették?

## JVM működésének naplózása

Indítsd el az alkalmazást parancssorból.

Írasd ki a GC működését a `-verbosegc` kapcsolóval. Milyen értékeket ír ki, és
ezek mit jelentenek?

Írasd ki a JIT működését a `-XX:+PrintCompilation` kapcsolóval. Milyen értékeket ír ki, és
ezek mit jelentenek?

## Bónusz feladat 1.

Elemezd a forráskódot és a tesztesetet! Mit csinál az alkalmazás?
Történhet olyan, hogy futás közben előbb-utóbb elfogy a memória?

## Bónusz feladat 2.

Amennyiben van három objektum, mely körkörösen egymásra hivatkoznak, de kívülről
rájuk más objektum nem hivatkozik, képes a GC eldobni őket egyszerre?

## Bónusz feladat 3.

Indítsd el a Java VisualVM alkalmazást, és csatlakozz a futó virtuális géphez.
Mit mutat meg az alkalmazás a JVM belső működéséről?

## Bónusz feladat 4.

A Tools/Plugins menüpontban telepítsd a Visual GC plugint, majd csatlakozz újra
a JVM-hez. Miket mutat meg a Visual GC plugin?

## Forrás

OCA - Chapter 1/Destroying Objects, Benefits of Java
