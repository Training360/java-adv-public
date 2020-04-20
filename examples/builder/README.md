# Builder tervezési minta

Ezt a tervezési mintát akkor használjuk, ha egy példányt több lépésben, bonyolultan kell létrehozni. A builder tervezési minta ezeket a lépéséket elrejti.

Jól használható ha nem áll rendelkezésre minden adat, például hálózaton folyamatosan, egymás után érkeznek. Ekkor gyűjtjük az adatokat és egy `build()` metódussal példányosítunk.

Alapértelmezett állapotot is megadhatunk, valamint a `build()` hívás előtt ellenőrizhetők az adatok.

## Implementáció részei

A használónak nem kell tudnia a bonyolult felépítésről és létrehozásról, neki csak az információt kell átadni. A builder tervezési minta nagyon jól olvasható, hiszen lépésenként hozunk létre.  Az utolsó lépés mindenképpen a `build()` metódus, ami az összegyűjtött adatok alapján példányosít.


## Ellenőrző kérdések
* Hogyan könnyíti meg a minta részek létrehozását?
* Milyen feltételeknek kell megfelelnie a settereknek, hogy láncolhatóak legyenek?
* Miért nem statikusak a metódusok?

## Gyakorlat - Robot vezérlő keretek létrehozása builder minta segítségével

A robot egységeit soros porton (usb) keresztül vezéreljük úgy, hogy byte
folyamokat írunk arra. Egy beágyazott rendszer fut, amely értelmezi a kereteket, és
kiadja a hardverre a megfelelő feszültségeket. A keretek fix hosszúságúak,
szabályok rögzítik a felépítésüket, melyek a következők: (lényegéban egyfajta protokollt határozunk meg)

* Minden keret a parancs azonosítójával kezdődik.
* Minden keret egy checksummal fejeződik be!
(A checksumot úgy állítjuk elő, hogy egy byte változóban összegezzük a byte tömb összes értéket.
Nem probléma, ha túlcsordulás következik be - ez éppúgy jellemző az
összegzett értékekre, mint bármi más - nem törekszünk egyedi "azonosítóra")

Érdemes egy `private byte checksum()` metódust létrehozni erre a builderekben!

Hozz létre egy `Frame` osztályt, amely attribútumként tartalmazza a byte tömböt és ebből
elő tudja állítani a vezérlésre használt, adott szerkezetű hexa stringet.

```java
public Frame(byte[] bytes)
public byte[] getBytes()
public String toHexString() //tárolt byte tömb string reprezentáció
```
A byte-okat kötőjellel válaszd el, és a tömb elemek értékeit
hexadecimális értékben fűzd be, két karakteren! (pl.: `7D-01-02-03-04-87`)


Hozz létre egy mozgató parancs buildert `MoveFrameBuilder`! Ez képes olyan kereteket létrehozni, amelyek
mozgatják a robotot.

* parancs kód: 7D (hexa) =  125 (decimális)
* általános felépítés: `7D-XX-YY-RR-MV-CM`
* ahol a paraméterek:
  * XX velocity: a sebesség vektor X komponense (oldalra) világkoordinátában értelmezve. Értéke 127 - -127 lehet.
  * YY velocity: a sebesség vektor y komponense (előre) világkoordinátában értelmezve. Értéke 127 - -127 lehet.
  * RR - forgás sebessége:
    * 0 értékre balra forog teljes sebességgel,
    * 62 értékre nem forog (0x3e)
    * 124 értékre jobbra forog teljes sebességgel
  * MV - maximális sebesség: maga a sebesség a 127 érték mind x, mind y komponenseknél ezt a sebességet jelenti.
  * CM - checksum: ellenőrző összeg
* példák:
  * `7D-00-5E-3E-80-99`  1 m/s előre
  * `7D-00-00-3E-80-3B` megállít

publikus metódusok:
```java
public MoveFrameBuilder setXSpeed(byte speed)
public MoveFrameBuilder setYSpeed(byte speed)
public MoveFrameBuilder setRotationSpeed(byte speed)
public MoveFrameBuilder setMaximumSpeed(byte speed)
public Frame build()
```

Hozz létre egy beállító parancs buildert, lásd `SetMovementCapabilityFrameBuilderTest`!
Ez képes olyan kereteket létrehozni, amellyel a robot max sebessége, gyorsulása beállítható.
A default értékeket a változó deklarálásakor érdemes megadni.

* parancs kód: 04 (hexa) =  4 (decimális)
* általános felépítés: `04-VE-AV-AC-AA-CM`
* ahol a paraméterek:
  * VE - Maximális sebesség (**default érték: 96**)
  * AV - Maximális forgási sebesség (**default érték: 10**)
  * AC - Maximális gyorsulás (**default érték: 98**)
  * AA - Maximális forgási gyorsulás (**default érték: 8**)
  * CM - checkSum: ellenőrző összeg
* Példák:
  * `04-0C-05-0C-02-23` - jelentése 101,10,101,10-re állítja a megfelelő értékeket.

publikus metódusok:
```java
public SetMovementCapabilityFrameBuilder setMaximumSpeed(byte speed)
public SetMovementCapabilityFrameBuilder setMaximumRotationSpeed(byte speed)
public SetMovementCapabilityFrameBuilder setMaximumAcceleration(byte acceleration)
public SetMovementCapabilityFrameBuilder setMaximumRotationalAcceleration(byte acceleration)
public Frame build()
```

[rating feedback=java-builder-robotvezerles]
