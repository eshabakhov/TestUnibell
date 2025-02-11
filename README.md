# Тестовое задание.

Спроектировать простую БД, обеспечивающую хранение информации о клиентах и их контактой информации.

Каждый клиент характеризуется именем.

Каждому клиенту в соответствие может быть поставлена информация о его контактах: 0 и более телефонных номеров, 0 и более адресов электронной почты.

Разработать в Spring Framework API, обеспечивающее работу с данной БД.

API должно обеспечивать следующие функции:
1. Добавление нового клиента
2. Добавление нового контакта клиента (телефон или email)
3. Получение списка клиентов
4. Получение информации по заданному клиенту (по id)
5. Получение списка контактов заданного клиента
6. Получение списка контактов заданного типа заданного клиента

## Требования к окружению

1. jdk-17
2. maven2
3. docker-compose

## Запуск приложения

1. Для сборки приложения использовать команду:
```
mvn clean package -P build
```
Для сборки необходим запущенный докер, так как в нём поднимается тестовая БД, в которой применяются миграции и производятся тесты

2. Для запуска приложения и БД в контейнерах:
```
docker-compose up -d
```

## Спецификация OpenAPI
http://localhost:9500/api/swagger-ui/index.html после запуска приложения