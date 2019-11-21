package ciolli.rob.bank

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BankTests {

    private val testName1 = "TestName1"
    private val testName2 = "TestName2"

    private val depositAmount = 30
    private val withdrawAmount = -20

    /*
    Create Customer Tests
     */

    @Test
    fun createCustomer_BankHasOneCustomer() {

        val bank = Bank().createCustomer(testName1)

        assertEquals(1, bank.customers.count())
    }

    @Test
    fun createCustomer_CustomerHasCorrectName() {

        val bank = Bank().createCustomer(testName1)
        val customerName = bank.customers.first { it.name == testName1 }.name

        assertEquals(testName1, customerName)
    }

    @Test
    fun createCustomer_CustomerHasZeroBalance() {

        val bank = Bank().createCustomer(testName1)
        val customerBalance = bank.customers.first { it.name == testName1 }.balance

        assertEquals(0, customerBalance)
    }

    @Test
    fun createCustomerInBankWithOneCustomer_BankHasTwoCustomers() {

        val numCustomers = Bank()
            .createCustomer(testName1)
            .createCustomer(testName2)
            .customers.count()

        assertEquals(2, numCustomers)
    }

    /*
    Adjust Balance Tests
     */

    @Test
    fun adjustBalanceCustomerDoesNotExist_ThrowsCustomException() {

        assertThrows<CustomerDoesNotExist> { Bank().adjustCustomerBalance(1, 0) }
    }

    /*
     Deposit Tests
     */

    @Test
    fun depositCash_CustomerIsCreditedWithMoney() {

        val bank = Bank().createCustomer(testName1)
        val customerId = bank.customers.first { it.name == testName1 }.id

        val updatedBank = bank.adjustCustomerBalance(customerId, depositAmount)

        assertEquals(depositAmount, updatedBank.customers.first().balance)
    }

    @Test
    fun depositMoreToMakeBalanceMoreThanMax_ThrowsCustomException() {

        val bank = Bank().createCustomer(testName1)
        val customerId = bank.customers.first { it.name == testName1 }.id

        val updatedBank = bank.adjustCustomerBalance(customerId, Int.MAX_VALUE)

        assertThrows<BalanceException> { updatedBank.adjustCustomerBalance(customerId, depositAmount) }
    }

    /*
     Withdrawal Tests
     */

    @Test
    fun withdrawFromCustomerWithCash_CustomerIsDebitedCash() {

        val bank = Bank().createCustomer(testName1)
        val customerId = bank.customers.first { it.name == testName1 }.id

        val newBalance = bank
            .adjustCustomerBalance(customerId, depositAmount)
            .adjustCustomerBalance(customerId, withdrawAmount)
            .customers.first { it.name == testName1 }.balance

        assertEquals(depositAmount + withdrawAmount, newBalance)
    }

    @Test
    fun withdrawMoreThanAvailableFunds_ThrowsCustomException() {

        val bank = Bank().createCustomer(testName1)
        val customerId = bank.customers.first { it.name == testName1 }.id

        assertThrows<NotEnoughFundsException> { bank.adjustCustomerBalance(customerId, withdrawAmount) }
    }

    /*
     Bank Balance Tests
     */

    @Test
    fun multipleTransactionsWithMultipleCustomers_ReturnsCorrectBankBalance() {

        val bank = Bank()
            .createCustomer(testName1)
            .createCustomer(testName2)

        val customerId1 = bank.customers.first { it.name == testName1 }.id
        val customerId2 = bank.customers.first { it.name == testName2 }.id

        val bankBalance = bank
            .adjustCustomerBalance(customerId1, depositAmount)
            .adjustCustomerBalance(customerId2, depositAmount + depositAmount)
            .adjustCustomerBalance(customerId1, withdrawAmount)
            .adjustCustomerBalance(customerId2, withdrawAmount + withdrawAmount)
            .balance

        assertEquals(3 * (depositAmount + withdrawAmount), bankBalance)
    }
}