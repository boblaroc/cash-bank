package ciolli.rob.bank


/**
 * Helper function to update a customer in a Lists
 *
 * @param customer new version customer object to add to list.
 * if customer with same id exists, it is replaced by new object
 */
fun List<Customer>.replace(customer: Customer): List<Customer> {
    return this.map { if (it.id == customer.id) customer else it }
}