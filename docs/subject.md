#### /api/v1/subjects/subject [POST] - Створити предмет (Для Адмінів)

***Вхідні параметри***

```json
{
  "title": ""  
}
```

#### /api/v1/subjects/subject [GET] - Получити всіх Subject

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "title": ""
  }
]
```

#### /api/v1/subjects/subject/{subject_id} [GET] - Получити Subject по id

***Вихідні параметри***

```json
{
  "id": 1,
  "title": ""
}
```

#### /api/v1/subjects/subject/{subject_id} [DELETE] - Удалити Subject по id (Для Адмінів)

#### /api/v1/subjects/subject/{subject_id} [PUT] - Редагувати Subject по id (Для Адмінів)

***Вхідні параметри***

```json
{
  "title": ""  
}
```