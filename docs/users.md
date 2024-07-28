#### /api/v1/users/user [POST] - Логін/Реєстрація

***Вхідні параметри***

```json
{
    "telegramId": "",
    "username": ""
}
```

***Вихідні параметри***

```json
{
  "jwt": ""
}
```

#### /api/v1/user [GET] - Получити всіх User

***Вихідні параметри***

```json
  [
    {
      "id": 1,
      "telegramId": "",
      "username": ""
    }
  ]
```

#### /api/v1/user/{user_id} [GET] - Получити User по id

***Вихідні параметри***

```json
{
  "id": 1,
  "telegramId": "",
  "username": ""
}
```
