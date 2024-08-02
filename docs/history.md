#### /api/v1/histories/history [GET] - Получити список проходжень завдань (Для Адмінів)

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "name": "",
    "createdTimeStamp": "",
    "userId": 1,
    "taskId": 1
  }
]
```

#### /api/v1/histories/history/user/{user_id} [GET] - Получити список проходжень завдань по user id (Для Адмінів)

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "name": "",
    "createdTimeStamp": "",
    "userId": 1,
    "taskId": 1
  }
]
```

#### /api/v1/histories/history/amount/{user_id} [GET] - Получити суму витрат по user id

```json
{
  "amount": 1
}
```