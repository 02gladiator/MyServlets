function loadUserPosts() {
    $.ajax({
        url: 'user',
        type: 'post',
        success: function (response) {
            renderUserPosts(response, $('#right-section')[0]);
        },
        dataType: 'json',
        contentType: 'application/json',
    });
}

function renderUserPosts(post, posts) {
    let innerHTML = '';
    for (let i = 0; i < post.length; i++) {
        innerHTML += '<div class="post">';
        innerHTML += '  <h3>' + post[i]['title'] + '</h3>';
        innerHTML += '  <p>' + post[i]['content'] + '</p>';
        innerHTML += '</div>';
    }
    posts.innerHTML = innerHTML;
}

loadUserPosts();