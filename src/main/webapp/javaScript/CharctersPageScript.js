document.getElementById('character-grid').addEventListener('click', (event) => {
    const character = event.target.closest('.character');
    if (!character) return;

    const modal = document.getElementById('character-modal');
    const characterIcon = document.getElementById('character-icon');
    const characterName = document.getElementById('character-name');
    const characterElement = document.getElementById('character-element');
    const characterWeapon = document.getElementById('character-weapon');
    const characterInfo = document.getElementById('character-info');

    characterIcon.src = character.getAttribute('data-icon');
    characterName.textContent = character.getAttribute('data-name');
    characterElement.textContent = character.getAttribute('data-element');
    characterWeapon.textContent = character.getAttribute('data-weapon');
    characterInfo.textContent = character.getAttribute('data-info');

    modal.style.display = 'flex';
});

document.querySelector('.close-btn').addEventListener('click', () => {
    document.getElementById('character-modal').style.display = 'none';
});

window.addEventListener('click', (event) => {
    const modal = document.getElementById('character-modal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});
