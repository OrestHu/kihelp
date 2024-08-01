#### /api/v1/wallets/wallet [GET] - Получити список гаманців (Потрібна роль ADMIN)

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "amount": 10.0
  }
]
```

#### /api/v1/wallets/wallet/{wallet_id} [GET] - Получити гаманець по wallet id (Потрібна роль ADMIN)

***Вихідні параметри***

```json
{
  "id": 1,
  "amount": 10.0
}
```

#### /api/v1/wallets/wallet/user [GET] - Получити гаманець по user id

***Вихідні параметри***

```json
{
  "id": 1,
  "amount": 10.0
}
```

#### /api/v1/wallets/wallet/{wallet_id} [PUT] - Обновити гаманець по wallet id (Потрібна роль ADMIN)

***Вхідні параметри***

```json
{
  "amount": 10.0
}
```

#### /api/v1/wallets/wallet/user/{telegram_id} [PUT] - Обновити гаманець по user telegram_id (Потрібна роль ADMIN)

***Вхідні параметри***

```json
{
  "amount": 10.0
}
```

