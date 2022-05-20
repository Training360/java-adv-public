package swing;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterTest {

    @Test
    void sayHello() {
        assertEquals("Hello John", new Greeter().sayHello("John"));
    }

    @Test
    void sayHelloHtml() {
        assertEquals("<html>Hello <u>John</u></html>", new Greeter().sayHelloHtml("John"));
    }
}
