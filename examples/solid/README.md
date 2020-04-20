# S.O.L.I.D. elvek
Az elvek szülőatyja Robert C. Martin, aka. Uncle Bob. Az objektumorientált tervezés elősegítésére, támogatására alkotta meg ezeket az alapelveket.

## Single Responsibility Principle: felelősségi kör

*	Egy dologért legyen felelős
*	Csak egy oka lehet az osztály megváltoztatásának
*	Erős kohéziót eredményez

## Open/Closed Principle

*	Az osztályoknak, moduloknak nyíltaknak kell lenni a kiterjesztésre, de zártnak a módosításra
* Egy új funkció beillesztéséhez ne kelljen átírni a meglevő kódot, hanem új kódot írhassunk, amit a meglevő tud használni
*	Absztrakció: absztrakt osztályok, interfészek használata

## Liskov substitution principle

*	Leszármazott átveheti a szülő szerepét anélkül, hogy a működés megváltozna
*	Vigyázni kell, hogy az utódosztályok megtartsák az ősosztály vállalását (Design by Contract)

## Interface segregation principle

*	Több kis interfész
*	Egy nagy interfész láttán általában alacsony kohéziót szimatolunk
*	Loose couplingot biztosít
*	Beépített interfészek is általában csak egy-két tagot tartalmaznak

## Dependency Inversion

*	Függőség interfészre (absztrakcióra), ne konkrét osztályra
*	Csak deklarálja a függőségeket

## Miért hasznos a S.O.L.I.D. elvek betartása?

*	Újrafelhasználhatóság
*	Könnyű karbantarthatóság
*	Könnyű bővíthetőség
*	Könnyebb alkalmazkodás a követelmények változásához
*	Olvashatóbb kód
*	Könnyebb unit tesztelhetőség
*	Kevesebb felesleges függőség


## Ellenőrző kérdések

* Miért kell az osztály módosítását tiltani (miután kész van)?
* A specifikációt milyen kérdésekkel érdemes kiegészíteni?
* Milyen a rugalmas kód?

## Gyakorlat - UserController

Az első feladat megoldását a `base` csomagba tedd!
Készítsünk egy web alkalmazás kontrollert `UserController` néven! Ez egy olyan osztály, ami elment
egy `User` példányt, de csak akkor, ha az adatok megfelelőek, azaz érvényesek
(érvényes, ha legalább 6 karakterből áll, és nem tartalmaz space karaktereket).
Webalkalmazás esetén a bemenetet mindig szűrni kell a szerver oldalon!

Ha csak ennyi van a specifikációban gondolkodjunk el a következőkön:
* Az előző leírás csak egy szöveg egység, de hány programozói egység valójában?
Hány felelősség? Hány különböző "dolgot" kell implementálnunk (elment, ellenőriz)?
* Ha egy osztályban lenne minden, akkor mi lenne a legjobb név az osztálynak?
* Ha később bővülni fog a specifikáció, milyen új igények jöhetnek be?

Ahány felelősség, annyi osztály, ezért érdmes négy entitással kezdeni:

* `User` osztály - feltehetően később lesz életkora, számlaszáma,
címe, ...
Készítsünk egy konstruktort, ami a paraméterében kapott `String`-et elmenti a
`userName` attribútumba. Készítsünk egy gettert az attribútumra!
* `AccountValidator` - ellenőrzést végez.
Valószínű ebből lesz több is, ezért nem egy osztályt hozunk létre, majd az
módosítjuk minden alkalommal, amikor új igény jelentkezik, hanem egy interfészt,
`Validator` néven. Tartalmaz egy
 `public boolean isValid(String userName)` metódust. A feladat most csak két szabályt ír le,
 implementáljuk ezt egy `AccountValidator` osztályban.
* `UserController` - fogadja a kéréseket, használ másokat (felső réteg).
Két külső függősége van: validátor(ok), `userService`
  * Kell egy `public void createUser(String username)` metódus
  * Kell egy konstruktor, ami a függőségeket fogadja, és menti az attribútumba.
  * Kell egy `private boolean validUserName(String userName)` metódus, ami
  használja a már beadott validátorokat. (ha nincs egy külső se, akkor is működnie kell,
  ekkor az input érvényes) Az összes validátort használja, és ha bármelyik
  ellenőrző elutasítja a bemeneti értéket, akkor a bemenet érvénytelen.
* `UserService` - webalkalmazástól függetlenül a userekkel kapcsolatos műveleteket
implementálja. Legyen ez most csak egy interfész, ami csak a mentés `save` metódusát deklarálja.
`void save(User user)`

[rating feedback=java-solid-usercontroller]

## Gyakorlat - Javascript injekció ellen védett `UserController`
A következő osztályt a `security` csomagban hozd létre.
Bővítsük ki az előző kódot, hogy ki tudja szűrni a Javascript injekció alapú támadásokat.
Készítsünk egy `JsInjectionValidator` osztályt, amely implementálja a `Validator` interfészt,
oly módon, hogy a kapott szöveg érvénytelen, ha `null` vagy tartalmazza a `<script>` szöveget.

[rating feedback=java-solid-javascriptinjection]
