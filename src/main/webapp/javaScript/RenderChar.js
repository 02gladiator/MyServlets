function renderChar(chars, grid) {
    let innerHTML = '';
    for (let i = 0; i < chars.length; i++) {
        innerHTML += `
            <div class="character" 
                 data-name="${chars[i]['characterName']}" 
                 data-element="${chars[i]['element']}" 
                 data-weapon="${chars[i]['weapon']}" 
                 data-info="${chars[i]['characterDescription']}" 
                 data-icon="http://localhost:8080/charicon?char_id=${chars[i]['characterId']}">
                <img src="http://localhost:8080/charicon?char_id=${chars[i]['characterId']}" 
                     alt="${chars[i]['characterName']}">
                <p>${chars[i]['characterName']}</p>
            </div>
        `;
    }
    grid.innerHTML = innerHTML;
}

function loadChar() {
    $.ajax({
        url: '/characters',
        type: 'post',
        success: function (response) {
            renderChar(response, document.getElementById('character-grid'));
        },
        dataType: 'json',
        contentType: 'application/json',
    });
}

loadChar();
