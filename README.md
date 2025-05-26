Account Balance API (In-Memory, Spring Boot, Maven, Java 17)

This is a simple Spring Boot REST API to manage account balances.
You can:
- Create accounts
- Add deposit or withdraw transactions
- View account balances
- View all transactions
- Update a transaction

(!!)Currently all data is stored in memory and is lost when the app restarts.

---

API Endpoints:

1. Create Balance

POST /balances/{name}

Example:
`curl -X POST http://localhost:8080/balances/main-acc`

---

2. Get Balance Info

GET /balances/{name}/info

Example:
`curl http://localhost:8080/balances/main-acc/info`

---

3. Add a Transaction

POST /balances/{name}/transaction/add

Example:
```
curl -X POST http://localhost:8080/balances/main-acc/transaction/add \
  -H "Content-Type: application/json" \
  -d '{
        "type": "DEPOSIT",
        "amount": 100.0,
        "currency": "EUR"
      }'
```
---

4. Get All Transactions

GET /balances/{name}/transactions

Example:
`curl http://localhost:8080/balances/main-acc/transactions`

---

5. Update a Transaction

PUT /balances/transaction/{id}/update

Example:
```
curl -X PUT http://localhost:8080/balances/transaction/1/update \
  -H "Content-Type: application/json" \
  -d '{
        "type": "WITHDRAW",
        "amount": 50.0,
        "currency": "USD"
      }'
```
Note: Replace 1 with the actual transaction ID returned earlier.

---

Supported Currencies:
- USD
- EUR
- BYN
- RUB
