package ciolli.rob.bank

/**
 * Customer Model Object.
 *
 * <P>Basic attributes of a customer
 *
 * <P>Note that {@link Int} is used to model the balance - not double or float.
 * balance represents lowest denomination (cents in AUD)
 *
 * @author Rob Ciolli
 * @version 1.0
 */
data class Customer(
    val id: Int,
    val name: String,
    val balance: Int = 0
)