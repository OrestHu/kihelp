#### /api/v1/arguments/argument [POST] - Створити аргумент (Для Адмінів)

***Вхідні параметри***

```json
{
  "name": ""
}
```

#### /api/v1/arguments/argument [GET] - Получити список аргументів (Для Адмінів)

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "name": ""
  }
]
```

#### /api/v1/arguments/argument/{argument_id} [GET] - Получити аргумент по id (Для Адмінів)

***Вихідні параметри***

```json
{
  "id": 1,
  "name": ""
}
```

#### /api/v1/arguments/argument/{argument_id} [DELETE] - Удалити аргумент (Для Адмінів)

#### /api/v1/arguments/argument/{argument_id} [PUT] - Обновити аргумент (Для Адмінів)

***Вхідні параметри***

```json
{
  "name": ""
}
```