<!DOCTYPE html>
<html>
<head>
    <title>My Marketplace</title>
</head>
<body>
<h1>My Marketplace</h1>
<h4>Подробная информация о товаре</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Название товара: </b>${goods.name}<br>
<b>Цена: </b>${goods.price}<br>
<b>Автор: </b>${goods.author}<br>
<hr>
<form action="/goods/delete/${goods.id}" method="post">
    <input type="submit" value="Удалить товар">
</form>
</body>
</html>