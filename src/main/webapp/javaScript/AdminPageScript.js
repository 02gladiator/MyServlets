const sidebar = document.querySelector(".sidebar")
const sidebarToggler = document.querySelector(".sidebar-toggler");
const userBtn = document.getElementById("user-btn");
const charBtn = document.getElementById("characters-btn");
const weaponBtn = document.getElementById("weapon-btn");
const artBtn = document.getElementById("artefacts-btn");
const enemyBtn = document.getElementById("enemies-btn");
const userTable = document.querySelector(".user-table");
const charTable = document.querySelector(".character-table");
const weaponTable = document.querySelector(".weapon-table");
const artTable = document.querySelector(".artefacts-table");
const enemyTable = document.querySelector(".enemies-table")

const button = document.getElementById('refreshBtn');

let rotationDegree = 0;

button.addEventListener('click', function() {
    rotationDegree += 360;
    button.style.transform = 'rotate(' + rotationDegree + 'deg)';
});

sidebarToggler.addEventListener("click", () => {
    sidebar.classList.toggle("collapsed");
})

userBtn.addEventListener('click', () => {
    userTable.classList.add("active");
    charTable.classList.remove("active");
    weaponTable.classList.remove("active");
    artTable.classList.remove("active");
    enemyTable.classList.remove("active");
});

charBtn.addEventListener('click', () => {
    userTable.classList.remove("active");
    charTable.classList.add("active");
    weaponTable.classList.remove("active");
    artTable.classList.remove("active");
    enemyTable.classList.remove("active");
});

weaponBtn.addEventListener('click', () => {
    userTable.classList.remove("active");
    charTable.classList.remove("active");
    weaponTable.classList.add("active");
    artTable.classList.remove("active");
    enemyTable.classList.remove("active");
});

artBtn.addEventListener('click', () => {
    userTable.classList.remove("active");
    charTable.classList.remove("active");
    weaponTable.classList.remove("active");
    artTable.classList.add("active");
    enemyTable.classList.remove("active");
});

enemyBtn.addEventListener('click', () => {
    userTable.classList.remove("active");
    charTable.classList.remove("active");
    weaponTable.classList.remove("active");
    artTable.classList.remove("active");
    enemyTable.classList.add("active");
});

document.getElementById('add-character-btn').addEventListener('click', function() {
    document.getElementById('character-modal').style.display = 'block';
});
document.getElementById('close-character-modal').addEventListener('click', function() {
    document.getElementById('character-modal').style.display = 'none';
});

document.getElementById('add-weapon-btn').addEventListener('click', function() {
    document.getElementById('weapon-modal').style.display = 'block';
});
document.getElementById('close-weapon-modal').addEventListener('click', function() {
    document.getElementById('weapon-modal').style.display = 'none';
});

document.getElementById('add-artefact-btn').addEventListener('click', function() {
    document.getElementById('artefact-modal').style.display = 'block';
});
document.getElementById('close-artefact-modal').addEventListener('click', function() {
    document.getElementById('artefact-modal').style.display = 'none';
});

document.getElementById('add-enemy-btn').addEventListener('click', function() {
    document.getElementById('enemy-modal').style.display = 'block';
});
document.getElementById('close-enemy-modal').addEventListener('click', function() {
    document.getElementById('enemy-modal').style.display = 'none';
});

window.addEventListener('click', function(event) {
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });
});