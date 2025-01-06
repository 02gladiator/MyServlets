<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница</title>
    <link rel="stylesheet" href="../css/MainPageStyle.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"/>
</head>
<body>
<header>
    <button onclick="location.href='user'">Перейти в профиль</button>
</header>
<main>
    <div class="main-content">
        <div class="new-post">
            <button onclick="openModal()">Добавить новый пост</button>
        </div>
        <button id="refreshBtn" class="refreshBtn">
            <span class="material-symbols-outlined">refresh</span>
        </button>
        <div class="posts" id="posts">
        </div>
    </div>
    <div class="sidebar">
        <div>
            <button onclick="location.href='characters'">
                <span class="material-symbols-outlined">groups</span>
                Персонажи
            </button>
            <button onclick="location.href='weapons'">
                <span class="material-symbols-outlined">sports_esports</span>
                Оружие
            </button>
            <button onclick="location.href='enemies'">
                <span class="material-symbols-outlined">sentiment_very_dissatisfied</span>
                Враги
            </button>
            <button onclick="location.href='artefacts'">
                <span class="material-symbols-outlined">star</span>
                Артефакты
            </button>
        </div>
        <div>
            <img src="../css/photos/6552317430.jpg" alt="Genshin Impact" style="width:100%; border-radius:10px;">
            <p>Погрузитесь в удивительный мир Teyvat с его захватывающими приключениями, персонажами и загадками!</p>
        </div>
    </div>
</main>

<div class="modal" id="modal">
    <form class="modal-content" method="post" action="/main">
        <h2>Добавить новый пост</h2>
        <input type="text" id="post-title" name="title" placeholder="Введите заголовок поста" required>
        <textarea id="post-content" name="content" placeholder="Введите текст поста..." required></textarea>
        <div>
            <button class="close" onclick="closeModal()">Отмена</button>
            <button type="submit">Добавить</button>
        </div>
    </form>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="../javaScript/MainPageScript.js"></script>
</body>
</html>


