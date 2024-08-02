#### /api/v1/teachers/teacher [POST] - Створити викладача (Для Адмінів)

***Вхідні параметри***

```json
{
  "name": "",
  "subjectId": 1
}
```

#### /api/v1/teachers/teacher/{teacher_id} [GET] - Получити викладача по id

***Вихідні параметри***

```json
{
  "id": 1,
  "name": ""
}
```

#### /api/v1/teachers/teacher/subject/{subject_id} [GET] - Получити список викладачів по subject id

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "name": ""
  }
]
```

#### /api/v1/teachers/teacher [GET] - Получити викладачів (Длч Адмінів)

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "name": ""
  }
]
```

#### /api/v1/teachers/teacher/{teacher_id} [DELETE] - Удалити викладача по id (Для Адмінів)

#### /api/vq/teachers/teacher/{teacher_id} [PUT] - Обновити викладача (Для Адмінів)

***Вхідні параметри***

```json
{
  "name": ""
}
```

