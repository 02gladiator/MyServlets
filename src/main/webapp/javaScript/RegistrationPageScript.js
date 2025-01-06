const answer = document.getElementById('modal');

const closeBtn = document.getElementById("closeMe");

closeBtn.addEventListener('click',()=>{
    answer.classList.remove('open')
})

$(document).ready(function() {
    $('form').on('submit', function(event) {
        event.preventDefault();

        const nickname = $('#nickname').val();
        const email = $('#email').val();
        const password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: 'registration',
            data: {
                nickname:nickname,
                email: email,
                password: password
            },
            success: function(response) {
                if (response.success) {
                    answer.classList.add('open')
                }else {
                    window.location.href = '/login';
                }
            }
        });
    });
});