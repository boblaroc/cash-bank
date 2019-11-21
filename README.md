# Cash Bank

Implementation of a simple banking application that allow user to:
	* create multiple customers
	* deposit and withdraw funds for a specific user
	* retrieve total funds held by bank
	
## Architecture Notes

This solution requires Java 1.8 or above to be installed on host system.

This solution uses a fluent, functional design with immutable data structures. Rather than mutating the state (represented by the Bank object), a new state is created on every transaction.

This design allows for simpler testing and more robust code as the results of all actions are deterministic.

Balance is stored as an `Int`, this was selected as a convienience, however at scale a data type with a bigger capacity could be considered.

Balance is stored in lowest denomination. It can be assumed that for the purpose of this app that number represents one cent.

Customers are assigned a unique id but there is no restriction on names being unique.


## Run Tests

From the root directory execute:

```./gradlew clean test```

This command will clean solution and output the name of each test and indicate if they passed or failed
A summary of test results will also be output


## Future extension

There is currently a restriction of one balance per customer, the concept of accounts could be added to the system.

Currently the system does not inform the consumer of the api of account id when it is created, unique names are required to identify accounts for testing purposes. This could be solved by passing a callback function to createCustomer(). This pattern could be repeated for other functions.

This solution could be adjusted to allow for negative balances

This solution could be updated to kepp a record of transactions or update ledgers.

Library could be exposed via an interface (either cli or RESTful api)




