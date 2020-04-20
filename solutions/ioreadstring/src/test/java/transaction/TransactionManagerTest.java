package transaction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionManagerTest {

    private  TransactionManager tm = new TransactionManager();


    @Test
    public void testGetterOfList(){
        assertEquals(0,tm.getAccountList().size());

        tm.getAccountList().add(new BankAccount("John Doe","123",2300));

        assertEquals(0,tm.getAccountList().size());


    }


    @Test
    public void testUploadAccounts(){
        assertEquals(0,tm.getAccountList().size());

        tm.uploadListFromFile("src/main/resources/accounts.txt");

        assertEquals(5,tm.getAccountList().size());
        assertEquals("Jack Doe",tm.getAccountList().get(3).getName());
        assertEquals(234050200,tm.getAccountList().get(1).getBalance(),0.0000001);

    }

    @Test
    public void makeTransactionsTest(){
        tm.uploadListFromFile("src/main/resources/accounts.txt");

        tm.makeTransactions("src/main/resources/transactions.txt");

        assertEquals(1201000,tm.getAccountList().get(0).getBalance(),0.0000001);
        assertEquals(721220,tm.getAccountList().get(3).getBalance(),0.00001);

    }
}
