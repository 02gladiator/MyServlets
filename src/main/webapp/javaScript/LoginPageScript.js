const answer = document.getElementById('modal');

const closeBtn = document.getElementById("closeMe");

closeBtn.addEventListener('click',()=>{
    answer.classList.remove('open')
})

$(document).ready(function() {
    $('form').on('submit', function(event) {
        event.preventDefault();

        const email = $('#email').val();
        const password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: 'login',
            data: {
                email: email,
                password: password
            },
            success: function(response) {
                if (response.success) {
                    window.location.href = '/main';
                } else {
                    answer.classList.add('open')
                }
            }
        });
    });
});