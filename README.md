Фреймворк автоматизации тестирования UI и API
1. Запуск тестов:
Требования - Java 20, Maven 4, IDE
2. Клонируем репозиторий -> cmd -> git clone https://github.com/VladimirPS/TestTask.git
3. Устанавливаем зависимости -> execute maven goal -> команда mvn clean install 
4. Для запуска тестов через maven -> execute maven goal -> команда mvn clean test -Dtest=testClassName 

Для api тестов нужно наследоваться от класса BaseApiTest и создавать Actions класс относительно сервиса, общие конфиги брать из .properties файла, и общий настройки клиента добавлять в класс ApiClient.
Если тест только API использовать наследоваться от  [BaseTestApi.java] и вызывать Actions класс сервиса для которого пишется тест.
Для UI наследоваться от [BaseTestUI.java] и создать PageObject классы. 

Сделаны варианты A и B из задания 4. добавлена логика для скришотов при фейле и записи логов при фейле API реквеста.
logConfig(LogConfig.logConfig().defaultStream(printStream).enableLoggingOfRequestAndResponseIfValidationFails()); [ApiClient.java]
seleniumJupiter.getConfig().enableScreenshotWhenFailure(); [BaseTestUI.java]

