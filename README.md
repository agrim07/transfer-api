# Transfer Service

This is a service that is responsible for reading a file having details of bank account transaction and it will 
return balance amount of all bank accounts, frequently used account in transaction and the account number have highest amount. 

We can view this api documentation at http://localhost:8080/swagger-ui/index.html location.

###This application having one end-points:

1) GET: /api/transfer/{fileName} - This end-point takes file name as parameter and return transaction information in given below format.
   Example: /api/transfer/transfers.txt
   Response Format: 

{
"balances": [
      {
         "accountNumber": 112233,
         "amount": 36.77
      },
      {
         "accountNumber": 223344,
         "amount": 30.122
      },
      {
         "accountNumber": 334455,
         "amount": 85.808
      }
   ],
   "highestBalanceBankAccount": 334455,
   "frequentlyUsedBankAccount": 112233
   }