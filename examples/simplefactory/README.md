# Simple factory tervezési minta

Nem egy GoF tervezési minta, de elég gyakran használjuk, ha azonos típusú, de eltérű osztályú objektumokat szeretnénk visszakapni.

A példányosítást a Simple factory végzi. A használó nem tudja hogyan kell a példányosítást elvégezni, ő csak a közös típust ismeri (ősosztály, interface). Hasznos, ha új osztályt akarunk bevezetni, akkor csak a simple factory-t kell módosítani. 


## Ellenőrző kérdések

* Hogy biztosítja a minta azt, hogy ne kelljen a klienst módosítani egy új termék
bevezetése esetén?
* A létrehozó metódusok miért nem `static` minősítésűek?
* A konkrét gyár honnan tudja, hogy melyik terméket kell létrehoznia?

## Állatok létrehozása

`Animal` interfész

implementálandó metódusa:

```java
String speak(); // az állat által keltett hangutánzó szó, lásd a teszt eseteknél
```

 `Cock` osztály implements `Animal`,
 `Horse` osztály implements `Animal`,
 `Frog` osztály implements `Animal`

 A fenti osztályoknak nincs egyéb eleme.
 
`FarmAnimalFactory` konkrét factory, a kapott állatnév (`animalType`) alapján dolgozik

publikus metódusok:

```java
public Animal create(String animalType)
```

 Amennyiben null vagy üres string értéket kap, dobjon `IllegalArgumentException`-t. Szintén dobjon kivételt akkor,
 ha olyan paramétert kap, amely állat nincs a "repertoárjában".


`Farm` osztály,  `FarmAnimalFactory animalFactory` és `List<Animal> animals` attribútumokkal.

publikus metódusok:

```java
public Farm(AnimalFactory animalFactory)
public void newAnimalArrived(String animalType) //üres paraméterre dobjon IllegalArgumentException kivételt
public List<String> getAnimalVoices() //összeszedi az előforduló állatok egyedi hangjait string formában
```

### Tipp
 A paraméter vizsgálatát célszerű egy külön privát metódus segítségével végezni.

 A `Farm` osztály attribútumait nem használjuk kivülről. Ezt a forráskódban érdemes ki is fejezni.
