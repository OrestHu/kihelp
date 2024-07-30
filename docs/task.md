#### /api/v1/tasks/task [POST] - Створити завдання/тест

***Вхідні параметри***

**Параметр type - true (Завдання), false (Тест)**
**Параметр argumentsId - це масив id aргументів**

```json
{
  "title": "",
  "path": "",
  "price": 1,
  "teacherId": 1,
  "type": true,
  "argumentsId": [
    1,
    2,
    3
  ]
}
```

#### /api/v1/tasks/task/teacher/{teacher_id} [GET] - Получити список завдань/тестів по id викладача

***Вихідні параметри***

```json
[
  {
    "id": 1,
    "subjectTitle": "",
    "title": "",
    "path": "",
    "price": 1,
    "discount": 1.0,
    "type": true,
    "teacherId": 1,
    "args": [
      "Введіть ФАЛ0",
      "Введіть ФАЛ1",
      "Введіть ФАЛ2"
    ]
  }
]
```

#### /api/v1/tasks/task/{task_id} [GET] - Получити інформація про завдання по id

***Вихідні параметри***

```json
{
  "id": 1,
  "subjectTitle": "",
  "title": "",
  "path": "",
  "price": 1,
  "discount": 1.0,
  "type": true,
  "teacherId": 1,
  "args": [
    "Введіть ФАЛ0",
    "Введіть ФАЛ1",
    "Введіть ФАЛ2"
  ]
}
```

#### /api/v1/tasks/test/{test_id}/{repeat_count} [GET] - Получити інформацію про тест по id (repeat_count це кількість запитань)

***Вихідні параметри***

```json
{
  "id": 1,
  "subjectTitle": "",
  "title": "",
  "path": "",
  "price": 1,
  "discount": 1.0,
  "type": true,
  "teacherId": 1,
  "args": [
    "Введіть ФАЛ0",
    "Введіть ФАЛ1",
    "Введіть ФАЛ2"
  ]
}
```

#### /api/v1/tasks/task/program/{task_id} [POST] - Запустити програму вирішення завдання/тесту

***Вхідні параметри***

```json
{
  "args": [
    "ab v /cd",
    "ab v /cd",
    "ab v /cd"
  ]
}
```

***Вихідні параметри***

```json
{
  "fileName": "",
  "fileType": "",
  "fileLink": ""
}
```

#### /api/v1/tasks/task/{task_id} [DELETE] - Видалити завдання/тест

#### /api/v1/tasks/task/{task_id} [PATCH] - Обновити завдання/тест (Не обовязково заповняти всі параметри)

***Вхідні параметри***

```json
{
  "title": "",
  "path": "",
  "price": 1,
  "discount": 1.0
}
```

