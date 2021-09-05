1. Создаем БД (script/ddl/ddl.sql)
2. Собираем - gradle build
3. Запускаем  (terminal idea)
   - cd build/libs
   - java -jar cinema-0.0.1-SNAPSHOT.jar

4. Open-api docs http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
5. Коллекция в постман (postman/CinemaTask.postman_collection.json)
   - Получение списка мест в зале со статусами свободно/забронировано.
     URL - http://localhost:8080/api/sessions/1/places
   - Бронирование одного или нескольких свободных мест в зале кинотеатра.
     URL - http://localhost:8080/api/tickets/reserve
6. Для демонстрации можно пройтись по curl в файле (postman/Curl)

 



    

