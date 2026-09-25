# API Autotests

## 1. Назначение проекта и тестируемое приложение

Проект содержит API-автотесты для учебного приложения TestRail.

Тестируемое приложение:

`https://workspaceforapiandui.testrail.io`

В проекте автоматизировано 10 API тест-кейсов для проверки работы с:

- проектами;
- Test Cases;
- Test Runs.

Автотесты выполняют HTTP-запросы к TestRail API и проверяют статус-коды, поля ответов, обработку ошибок, создание, изменение, получение и удаление тестовых данных.

## 2. Используемые инструменты

- Java 17
- Gradle
- JUnit 5
- Rest Assured
- Jackson Databind
- DataFaker

## 3. Требования для запуска

Для запуска проекта необходимо:

- установить Java 17;
- иметь доступ к тестируемому приложению TestRail;
- иметь действующую учётную запись TestRail;
- иметь доступ к TestRail API;
- иметь API key;
- иметь права на получение, создание, изменение и удаление используемых сущностей TestRail.

Для запуска используется Gradle Wrapper, поэтому отдельно устанавливать Gradle не требуется.

Проверить установленную Java можно командой:

```powershell
java -version
```

Проект рассчитан на Java 17.

## 4. Настройка адреса и авторизации

### Адрес приложения

Базовый адрес TestRail хранится в `ApiConfig.java`.

Адрес по умолчанию:

```text
https://workspaceforapiandui.testrail.io
```

Базовый адрес можно переопределить без изменения исходного кода с помощью системного свойства `baseUrl`.

Windows:

```powershell
.\gradlew.bat clean test -DbaseUrl=https://example.testrail.io
```

Linux/macOS:

```bash
./gradlew clean test -DbaseUrl=https://example.testrail.io
```

### Авторизация

Данные авторизации не хранятся в исходном коде.

Для запуска необходимо настроить две переменные окружения:

```text
TEST_EMAIL
TEST_API_KEY
```

`TEST_EMAIL` — email пользователя TestRail.

`TEST_API_KEY` — API key пользователя TestRail.

Пример настройки в Windows PowerShell для текущей сессии:

```powershell
$env:TEST_EMAIL="your_email"
$env:TEST_API_KEY="your_api_key"
```

После этого тесты можно запускать из этой же PowerShell-сессии.

При использовании системных переменных окружения необходимо перезапустить IntelliJ IDEA или терминал после их создания.

Авторизация API выполняется с помощью Basic Authentication.

## 5. Запуск тестов

### Запуск всех тестов

Windows:

```powershell
.\gradlew.bat clean test
```

Linux/macOS:

```bash
./gradlew clean test
```

### Запуск одного теста

Например, запуск API-001:

```powershell
.\gradlew.bat test --tests "tests.ProjectApiTest.getProjectsTest"
```

Пример запуска API-007:

```powershell
.\gradlew.bat test --tests "tests.CaseApiTest.addTestCaseTest"
```

Пример запуска API-010:

```powershell
.\gradlew.bat test --tests "tests.RunsApiTest.createTestRunTest"
```

Тесты независимы и могут запускаться как вместе, так и по отдельности.

## 6. Расположение отчёта

После выполнения тестов Gradle формирует HTML-отчёт.

Отчёт находится по пути:

```text
build/reports/tests/test/index.html
```

Папка `build` создаётся автоматически при запуске тестов и не добавляется в Git-репозиторий.

При падении проверки Rest Assured выводит диагностическую информацию о HTTP-запросе и HTTP-ответе.

Заголовок `Authorization` исключён из логирования, чтобы данные авторизации не выводились в лог.

## 7. Соответствие ручных тест-кейсов и автотестов

| ID ручного кейса | Название | Класс и метод автотеста |
|---|---|---|
| API-001 | Получение всех проектов | `ProjectApiTest.getProjectsTest()` |
| API-002 | Получение конкретного проекта по ID | `ProjectApiTest.getProjectTest()` |
| API-003 | Получение несуществующего проекта | `ProjectApiTest.getNonExistentProjectTest()` |
| API-004 | Получение списка Test Cases проекта | `ProjectApiTest.getListTestCasesProjectTest()` |
| API-005 | Получение конкретного Test Case | `CaseApiTest.getTestCaseTest()` |
| API-006 | Получение несуществующего Test Case | `CaseApiTest.getNonExistentTestCaseTest()` |
| API-007 | Создание Test Case | `CaseApiTest.addTestCaseTest()` |
| API-008 | Обновление Test Case | `CaseApiTest.updateTestCaseTest()` |
| API-009 | Получение списка Test Runs | `RunsApiTest.getListTestRunsTest()` |
| API-010 | Создание Test Run | `RunsApiTest.createTestRunTest()` |

## 8. Известные ограничения

- Автотесты выполняются на учебном экземпляре TestRail и зависят от его доступности.
- Для выполнения тестов необходим действующий пользователь TestRail с доступом к API.
- Для тестов создания, изменения и удаления сущностей необходимы соответствующие права пользователя.
- Тесты используют заранее существующие Project, Suite и Section, поэтому их ID должны существовать в тестируемом приложении.
- В текущей конфигурации тесты используют учебные сущности с фиксированными ID проекта, suite и section.
- API-009 ожидает наличие хотя бы одного Test Run в используемом проекте.
- Тесты, создающие временные Test Case и Test Run, удаляют созданные данные после выполнения сценария, когда API позволяет выполнить удаление.
- Результаты тестов зависят от текущего состояния и доступности учебного TestRail API.