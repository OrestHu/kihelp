#### /api/v1/wallets/wallet [GET] - Получити список гаманців

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "amount": 10.0
  }
]
```

#### /api/v1/wallets/wallet/{wallet_id} [GET] - Получити гаманець по wallet id

***Вихідні параметри***

```json
{
  "id": 1,
  "amount": 10.0
}
```

#### /api/v1/wallets/wallet/user/{user_id} [GET] - Получити гаманець по user id

***Вихідні параметри***

```json
{
  "id": 1,
  "amount": 10.0
}
```

#### /api/v1/wallets/wallet/{wallet_id} [PUT] - Обновити гаманець по wallet id

***Вхідні параметри***

```json
{
  "amount": 10.0
}
```

#### /api/v1/wallets/wallet/user/{user_tg_id} [PUT] - Обновити гаманець по user telegram_id

***Вхідні параметри***

```json
{
  "amount": 10.0
}
```

