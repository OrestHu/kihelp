#### /api/v1/transactions/transaction [POST] - Створити транзакцію (Для Адмінів)

***Вхідні параметри***

```json
{
  "telegramId": "",
  "paymentId": "",
  "initial": "",
  "amount": 0.02
}
```

#### /api/v1/transactions/transaction/{wallet_id}?page=0&limit=5 [GET] - Получити список транзакцій по wallet id

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "initial": "",
    "createdTimeStamp": ""
  }
]
```

#### /api/v1/transactions/transaction/all [GET] - Получити список транзакцій (Для Адмінів)

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "initial": "",
    "createdTimeStamp": ""
  }
]
```

#### /api/v1/transactions/earnings [GET] - Получити прибуток (Для Адмінів)

***Вихідні параметри***

```json
{
  "amount": 1.0
}
```

#### /api/v1/transactions/transaction/{transaction_id} [DELETE] - Удалити транзакцію (Для Адмінів)
