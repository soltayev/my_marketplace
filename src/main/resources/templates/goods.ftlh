<!DOCTYPE html>
<html>
<head>
    <title>My Marketplace</title>
</head>
<body>
<h1>My Marketplace</h1>
<h4>Электроника</h4>
<form action="/" method="get">
    Поиск по названию товаров: <input type="text" name="name"><br>
    <input type="submit" value="Поиск"/>
</form>
<#list goods as good>
    <div>
        <p><b>${good.name}</b> ${good.price} тг | <a href="/goods/${good.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<hr>
<h3>Создать новый товар</h3>
<form action="/goods/create" method="post" enctype="multipart/form-data">
    Название товара: <input type="text" name="name"/><br><br>
    Описание товара: <input type="text" name="info"/><br><br>
    Категория: <input type="text" name="category"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Автор: <input type="text" name="author"/><br><br>
    Первая фотография: <input type="file" name="file1"/><br><br>
    Вторая фотография: <input type="file" name="file2"/><br><br>
    Третья фотография: <input type="file" name="file3"/><br><br>
    <input type="submit" value="Добавить товар"/>
</form>
</body>
</html>