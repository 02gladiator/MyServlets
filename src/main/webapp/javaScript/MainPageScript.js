const modal = document.getElementById('modal');
const button = document.getElementById('refreshBtn');

function openModal() {
    modal.style.display = 'flex';
}

function closeModal() {
    modal.style.display = 'none';
}

let rotationDegree = 0;

button.addEventListener('click', function () {
    rotationDegree += 360;
    button.style.transform = 'rotate(' + rotationDegree + 'deg)';
});

$('#refreshBtn').click(function () {
    loadPosts();
});

function loadPosts() {
    $.ajax({
        url: 'main',
        type: 'post',
        success: function (response) {
            renderPosts(response, $('#posts')[0]);
        },
        dataType: 'json',
        contentType: 'application/json',
    });
}

loadPosts();

function renderPosts(post, posts) {
    let innerHTML = '';
    for (let i = 0; i < post.length; i++) {
        innerHTML += '<div class="post">';
        innerHTML += '  <h3>' + post[i]['title'] + '</h3>';
        innerHTML += '  <p>' + post[i]['content'] + '</p>';
        innerHTML += '</div>';
    }
    posts.innerHTML = innerHTML;
}




