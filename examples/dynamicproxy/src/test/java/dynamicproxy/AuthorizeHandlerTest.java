package dynamicproxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthorizeHandlerTest {

    @Test
    public void testProxyAuthenticatedPrinter() {
        Printer realPrinter = new HpPrinter();
        Printer printer = (Printer) AuthorizeHandler.createProxy(realPrinter);
        printer.print("Jane Doe");
    }

    @Test
    public void testProxyNotAuthenticatedPrinter() {
        Printer realPrinter = new HpPrinter();
        Printer printer = (Printer) AuthorizeHandler.createProxy(realPrinter);
        assertThrows(SecurityException.class, () -> printer.print("John Doe"));
    }

    @Test
    public void testProxyAuthenticatedDoor() {
        Door realDoor = new EntryDoor();
        Door door = (Door) AuthorizeHandler.createProxy(realDoor);
        door.open("John Doe");
    }

    @Test
    public void testProxyNotAuthenticatedDoor() {
        Door realDoor = new EntryDoor();
        Door door = (Door) AuthorizeHandler.createProxy(realDoor);

        assertThrows(SecurityException.class, () -> door.open("Jane Doe"));

    }

}