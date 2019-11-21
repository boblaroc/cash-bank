package ciolli.rob.bank

/**
 * Bank Model Object.

 * @author Rob Ciolli
 * @version 2.0
 */
data class Bank(

     /**
      * List of Bank customers
      */
     val customers: List<Customer> = emptyList()
) {

     /**
      * Calculated field representing all funds held by bank
      */
     val balance: Int = customers.map { it.balance }.sum()

     /**
      * Create a customer
      *
      * @param name A name to identify customer - this does not need to be unique
      */
     fun createCustomer(name: String): Bank  {
          val maxCustomerId = customers.map { it.id }.max() ?: 0

          if (maxCustomerId == Int.MAX_VALUE)
               throw CreateCustomerException()

          val newCustomer = Customer(id = maxCustomerId.inc(), name = name)

          return Bank(customers = this.customers.replace(newCustomer))
     }

     /**
      * Adjust customer balance
      *
      * @param customerId id of customer whose balance to adjust
      * @param amount positive amounts are deposits, negative amounts are withdrawals
      */
     fun adjustCustomerBalance(customerId: Int, amount: Int): Bank {

          val customer = customers.firstOrNull { it.id == customerId }
               ?: throw CustomerDoesNotExist()

          if(amount > 0 && Int.MAX_VALUE - customer.balance < amount)
               throw BalanceException()

          val newBalance = customer.balance + amount

          if(newBalance < 0)
               throw NotEnoughFundsException()

          val updatedCustomer = customer.copy(balance = newBalance)

          return Bank(customers = this.customers.replace(updatedCustomer))
     }
}
