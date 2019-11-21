package ciolli.rob.bank

import java.lang.Exception

/**
 * Exception thrown when upper limit of number of customers that the system can handle is reached
 */
class CreateCustomerException: Exception("Cannot create customer - exceeds maximum")

/**
 * Exception thrown when requested customer cannot be found
 */
class CustomerDoesNotExist: Exception("Customer does not exist")

/**
 * Exception thrown if balance exceeds maximum that can be handled by the sysytem
 */
class BalanceException: Exception("Balance exceeds maximum balance")

/**
 * Exception thrown when not enough funds exist to complete transaction
 */
class NotEnoughFundsException: Exception("Cannot complete transaction, not enough funds")

