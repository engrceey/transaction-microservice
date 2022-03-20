# transaction-microservice


## Introduction 
Transaction Microservice: The Transaction microservice enables users to make transactions with their account through 
Rest calls with the Account microservice

## The Transactions
* Keep transaction records
* Get transaction records
* Make transactions
* Authenticate Transacting Account


## How to Run
Before running the Transaction Service please make sure to first Run the Account Service, because it is needed to 
validate and make transactions with users account.

### Endpoints

Transaction Account Login : http://localhost:9092/api/v1/transaction/auth/login    [POST]

Transfer funds : http://localhost:9092/api/v1/transaction/transfer-funds     [POST]

Deposit funds : http://localhost:9092/api/v1/transaction/deposit-funds       [POST]

Withdraw funds : http://localhost:9092/api/v1/transaction/withdraw-funds     [POST]

Get Transactions : http://localhost:9092/api/v1/transaction/transactions  [GET]



### H2 Database
H2 database url : http://localhost:9092/api/v1/transaction/h2-console/

username: sa

password: 


the full postman collection will be shared
