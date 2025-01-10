<!DOCTYPE html>
<html>
<head>
    <title>My Marketplace</title>
</head>
<body>
<h1>My Marketplace</h1>
<h4>Авторизация</h4>
<form action="/login" method="post">
    Эл. почта <input type="email" name="username"><br><br>
    Пароль <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход">
</form>
<br><br>
<a href="/registration">Зарегистрироваться</a><br><br>
<a href="/">Главная</a>
</body>
</html>