# Запуск автотестов для проекта QA.GURU

### Запуск всех тестов:
```
    gradle clean test
```

### Запуск тестов в Selenoid без параметров :
```
    gradle clean selenoidFirst
```

### Запуск тестов в Selenoid с параметрами :
>```
>    gradle clean testOwner -Dlogin=**** -Dpassword=****
>```
> обязательные параметры:
>> * login - для доступа к Selenoid
>> * password - для доступа к Selenoid
>
> дополнительные параметры:
>> * browser - тип браузера
>> * browserVersion - версия браузера
>> * browserSize - размер окна
>> * remoteDriverUrl - адрес Selenoid
> 
> По умолчанию, необязательные параметры прописаны в `resources/config/local.properties`