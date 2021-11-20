import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for Bank class")
class BankTest {

    @Test
    @DisplayName("Should withdraw amount only if available")
    void checkAmountWithdrawal() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;

        b.add_list(name, address, aadhaarNo, pin);

        long depositAmount = 100;

        // checking if amount more than current balance could be withdrawn
        assertEquals(false,b.withdrawAmount(accountNo,pin,depositAmount));

        b.depositAmount(accountNo,pin, depositAmount);

        // checking if amount less than current balance could be withdrawn
        assertEquals(true,b.withdrawAmount(accountNo,pin,depositAmount));
    }

    @Test
    @DisplayName("Should have 0 initial balance")
    void checkBalance() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;

        b.add_list(name, address, aadhaarNo, pin);

        long initialBal = 0;

        // checking initial balance is equal to zero or not
        assertEquals(initialBal,b.check_balance(accountNo,pin));
    }

    @Test
    @DisplayName("Should verify identity if correct pin is provided")
    void checkVerifyIdentity() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;
        int wrongPin = 8000;

        b.add_list(name, address, aadhaarNo, pin);

        // first verifying identity with correct pin
        assertEquals(true,b.verify(accountNo,pin));

        // second verifying identity with incorrect pin
        assertEquals(false,b.verify(accountNo,wrongPin));

    }

    @Test
    @DisplayName("Should create account successfully")
    void checkIfAccountExists() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;

        b.add_list(name, address, aadhaarNo, pin);

        assertEquals(true,b.getIndex(accountNo) >= 0);

        String wrongAccountNo = "ourbankXX1";

        assertEquals(-1,b.getIndex(wrongAccountNo));
    }

    @Test
    @DisplayName("Should store account details properly")
    void checkAccountDetails() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;

        b.add_list(name, address, aadhaarNo, pin);

        assertEquals(accountNo,b.getAccountNo());
        assertEquals(name,b.getName(b.getIndex(accountNo)));
        assertEquals(address,b.getAddress(b.getIndex(accountNo)));
        assertEquals(aadhaarNo,b.getAadhar(b.getIndex(accountNo)));

    }

    @Test
    @DisplayName("Should change pin properly")
    void checkUpdatePin() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;

        b.add_list(name, address, aadhaarNo, pin);

        int newPin = 8000;

        b.update_pin(accountNo,newPin);

        // verifying identity with new pin
        assertEquals(true, b.verify(accountNo, newPin));

        // verifying identity with old pin
        assertEquals(false, b.verify(accountNo, pin));
    }

    @Test
    @DisplayName("Should deposit valid amount only")
    void checkAmountDeposit() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;

        b.add_list(name, address, aadhaarNo, pin);

        long depositAmount = -100;

        // checking if negative amount could be deposited in initial balance
        b.depositAmount(accountNo,pin, depositAmount);

        fail("Shouldn't deposit negative amount");

    }

    @Test
    @DisplayName("Should create account with valid aadhaar no. only")
    void checkValidEmail() {
        Bank b = new Bank();

        String accountNo = "ourbankXX0";
        String name = "Yash";
        String address = "Delhi";
        String aadhaarNo = "100";
        int pin = 8080;


        // checking if account could be created with invalid aadhaar number
        b.add_list(name, address, aadhaarNo, pin);

        fail("Shouldn't create account with invalid aadhar number");

    }
}