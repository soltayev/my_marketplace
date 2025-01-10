<!DOCTYPE html>
<html>
<head>My Marketplace</head>
<body>
<h1>My Marketplace</h1>
<h3>Подробная информация о продавце ${user.name}</h3>
<b>Номер телефона: </b> ${user.phoneNumber}
<h4>Товары пользователя ${user.name}</h4>
<#list goods as good>
    <div>
        <p><b>${good.name}</b> ${good.price} тг | <a href="/goods/${good.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>

<a href="/">Главная</a>
<a href="/login">Войти</a>
</body>
</html>