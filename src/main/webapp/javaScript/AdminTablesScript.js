function renderUsersTable(users, table) {
    let innerHTML = '';
    for (let i = 0; i < users.length; i++) {
        innerHTML += '<tr>';
        innerHTML += '  <td>' + users[i]['userId'] + '</td>';
        innerHTML += '  <td>' + users[i]['email'] + '</td>';
        innerHTML += '  <td>' + users[i]['nickname'] + '</td>';
        innerHTML += '  <td>' + users[i]['role'] + '</td>';
        innerHTML += '</tr>';
    }
    table.innerHTML = innerHTML;
}

function renderCharsTable(chars, table) {
    let innerHTML = '';
    for (let i = 0; i < chars.length; i++) {
        innerHTML += '<tr>';
        innerHTML += '  <td>' + chars[i]['characterId'] + '</td>';
        innerHTML += '  <td>' + chars[i]['characterName'] + '</td>';
        innerHTML += '  <td>' + chars[i]['weapon'] + '</td>';
        innerHTML += '  <td>' + chars[i]['element'] + '</td>';
        innerHTML += '  <td>' + chars[i]['region'] + '</td>';
        innerHTML += '  <td>' + chars[i]['characterDescription'] + '</td>';
        innerHTML += '</tr>';
    }
    table.innerHTML = innerHTML;
}


function loadUsers() {
    $.ajax({
        url: '/admin',
        type: 'post',
        success: function (response) {
            renderUsersTable(response, $('#users')[0]);
        },
        dataType: 'json',
        contentType: 'application/json',
    });
}

function loadChars() {
    $.ajax({
        url: '/characters',
        type: 'post',
        success: function (response) {
            renderCharsTable(response, $('#characters')[0]);
        },
        dataType: 'json',
        contentType: 'application/json',
    });
}

$('#refreshBtn').click(function () {
    loadUsers();
    loadChars();
});

loadChars();
loadUsers();
