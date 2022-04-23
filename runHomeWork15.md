# Запуск автотестов для проекта QA.GURU

### Запуск тестов local:
```
 gradle clean testHw15 -DenvTest=local
```

### Запуск тестов в Selenoid:
>```
> gradle clean testHw15 -DenvTest=remote -Dlogin=**** -Dpassword=****
>```
> обязательные параметры:
>> * login - для доступа к Selenoid
>> * password - для доступа к Selenoid
