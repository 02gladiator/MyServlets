<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <link rel="stylesheet" href="../css/AdminPageStyle.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0"/>

</head>
<body>
<main>
    <aside class="sidebar">
        <header class="sidebar-header">
            <a href="#" class="header-logo">
                <img src="../css/photos/logo-genshin-impact-42376.png" alt=#>
            </a>
            <button class="toggler sidebar-toggler">
                <span class="material-symbols-outlined">chevron_left</span>
            </button>
        </header>
        <nav class="sidebar-nav">
            <ul class="nav-list primary-nav">
                <li class="nav-item">
                    <button type="button" class="nav-link" id="user-btn">
                        <span class="material-symbols-outlined">account_circle</span>
                        <span class="nav-label">Users</span>
                    </button>
                </li>
                <li class="nav-item">
                    <button type="button" class="nav-link" id="characters-btn">
                        <span class="material-symbols-outlined">account_box</span>
                        <span class="nav-label">Characters</span>
                    </button>
                </li>
                <li class="nav-item">
                    <button type="button" class="nav-link" id="weapon-btn">
                        <span class="material-symbols-outlined">sports_esports</span>
                        <span class="nav-label">Weapons</span>
                    </button>
                </li>
                <li class="nav-item">
                    <button type="button" class="nav-link" id="artefacts-btn">
                        <span class="material-symbols-outlined">star</span>
                        <span class="nav-label">Artefacts</span>
                    </button>
                </li>
                <li class="nav-item">
                    <button type="button" class="nav-link" id="enemies-btn">
                        <span class="material-symbols-outlined">sentiment_very_dissatisfied</span>
                        <span class="nav-label">Enemies</span>
                    </button>
                </li>
            </ul>
        </nav>
    </aside>
    <section class="tables">
        <div class="user-table">
            <button id="refreshBtn" class="refreshBtn">
                <span class="material-symbols-outlined">refresh</span>
            </button>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>EMAIL</th>
                    <th>NICKNAME</th>
                    <th>ROLE</th>
                </tr>
                </thead>
                <tbody id="users"></tbody>
            </table>
        </div>
        <div class="character-table">
            <div class="new-char">
                <button id="add-character-btn" class="add-btn">Add char</button>
            </div>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>WEAPON</th>
                    <th>ELEMENT</th>
                    <th>REGION</th>
                    <th>DESCRIPTION</th>
                </tr>
                </thead>
                <tbody id="characters">

                </tbody>
            </table>
            <div id="character-modal" class="modal">
                <div class="modal-content genshin-style">
                    <span class="close-btn" id="close-character-modal">&times;</span>
                    <h2>Add new char</h2>
                    <form action="/addcharacters" method="post" enctype="multipart/form-data" id="character-form">
                        <div class="form-group">
                            <label for="character-name">Char name:</label>
                            <input type="text" id="character-name" name="character-name" required>
                        </div>
                        <div class="form-group">
                            <label for="character-element">Element:</label>
                            <select id="character-element" name="character-element" required>
                                <option value="">Choose element</option>
                                <option value="pyro">Pyro</option>
                                <option value="hydro">Gydro</option>
                                <option value="anemo">Anemo</option>
                                <option value="electro">Electro</option>
                                <option value="dendro">Dendro</option>
                                <option value="cryo">Cryo</option>
                                <option value="geo">Geo</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="character-weapon">Weapon:</label>
                            <select id="character-weapon" name="character-weapon" required>
                                <option value="">Choose weapon</option>
                                <option value="sword">sword</option>
                                <option value="claymore">claymore</option>
                                <option value="polearm">polearm</option>
                                <option value="bow">bow</option>
                                <option value="catalyst">catalyst</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="character-region">Region:</label>
                            <select id="character-region" name="character-region" required>
                                <option value="">Choose region</option>
                                <option value="Mondstadt">Mondstadt</option>
                                <option value="Liyue">Liyue</option>
                                <option value="Inazuma">Inazuma</option>
                                <option value="Sumeru">Sumeru</option>
                                <option value="Fontaine">Fontaine</option>
                                <option value="Natlan">Natlan</option>
                                <option value="Snezhnaya">Snezhnaya</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="character-description">Description:</label>
                            <textarea id="character-description" name="character-description" rows="4"
                                      required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="character-icon">Char icon:</label>
                            <input type="file" id="character-icon" name="character-icon" accept="image/*" required>
                        </div>
                        <button type="submit" class="submit-btn">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="weapon-table">
            <button id="add-weapon-btn" class="add-btn">Add weapon</button>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>TYPE</th>
                </tr>
                </thead>
                <tbody id="weapon"></tbody>
            </table>
            <div id="weapon-modal" class="modal">
                <div class="modal-content genshin-style">
                    <span class="close-btn" id="close-weapon-modal">&times;</span>
                    <h2>Add new weapon</h2>
                    <form id="weapon-form">
                        <div class="form-group">
                            <label for="weapon-name">Weapon name:</label>
                            <input type="text" id="weapon-name" name="weapon-name" required>
                        </div>

                        <div class="form-group">
                            <label for="weapon-type">Type:</label>
                            <select id="weapon-type" name="weapon-type" required>
                                <option value="">Choose type</option>
                                <option value="sword">sword</option>
                                <option value="claymore">claymore</option>
                                <option value="polearm">polearm</option>
                                <option value="bow">bow</option>
                                <option value="catalyst">catalyst</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="weapon-icon">Weapon icon:</label>
                            <input type="file" id="weapon-icon" name="weapon-icon" accept="image/*" required>
                        </div>

                        <button type="submit" class="submit-btn">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="artefacts-table">
            <button id="add-artefact-btn" class="add-btn">Add artefact</button>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>1 BONUS</th>
                    <th>2 BONUS</th>
                </tr>
                </thead>
                <tbody id="artefacts"></tbody>
            </table>
            <div id="artefact-modal" class="modal">
                <div class="modal-content genshin-style">
                    <span class="close-btn" id="close-artefact-modal">&times;</span>
                    <h2>Add new artefact</h2>
                    <form id="artefact-form">
                        <div class="form-group">
                            <label for="artefact-name">Artefact name:</label>
                            <input type="text" id="artefact-name" name="artefact-name" required>
                        </div>

                        <div class="form-group">
                            <label for="artefact-bonus1">1-st bonus:</label>
                            <input type="text" id="artefact-bonus1" name="artefact-bonus1" required>
                        </div>

                        <div class="form-group">
                            <label for="artefact-bonus2">2-nd bonus:</label>
                            <input type="text" id="artefact-bonus2" name="artefact-bonus2" required>
                        </div>

                        <div class="form-group">
                            <label for="artefact-icon">Artefact icon:</label>
                            <input type="file" id="artefact-icon" name="artefact-icon" accept="image/*" required>
                        </div>

                        <button type="submit" class="submit-btn">Save</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="enemies-table">
            <button id="add-enemy-btn" class="add-btn">Add enemy</button>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>DESCRIPTION</th>
                </tr>
                </thead>
                <tbody id="enemies"></tbody>
            </table>
            <div id="enemy-modal" class="modal">
                <div class="modal-content genshin-style">
                    <span class="close-btn" id="close-enemy-modal">&times;</span>
                    <h2>Add new enemy</h2>
                    <form id="enemy-form">
                        <div class="form-group">
                            <label for="enemy-name">Enemy name:</label>
                            <input type="text" id="enemy-name" name="enemy-name" required>
                        </div>
                        <div class="form-group">
                            <label for="enemy-description">Description:</label>
                            <textarea id="enemy-description" name="enemy-description" rows="4" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="enemy-icon">Enemy icon:</label>
                            <input type="file" id="enemy-icon" name="enemy-icon" accept="image/*" required>
                        </div>
                        <button type="submit" class="submit-btn">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>
<script
        src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="../javaScript/AdminPageScript.js" defer></script>
<script src="../javaScript/AdminTablesScript.js"></script>
</body>
</html>
