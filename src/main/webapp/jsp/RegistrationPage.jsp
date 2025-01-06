<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale = 1.0">
    <title>Registration</title>
    <link rel="stylesheet" href="../css/LoginAndRegistrationPagesStyle.css">
</head>
<body>
<section>
    <div class="register-box">
        <form class="registration">
            <h1>Registration</h1>
            <div class="input-box">
                    <span class="icon">
                        <ion-icon name="person"></ion-icon>
                    </span>
                <input type="text" name="nickname" id="nickname" required>
                <label>Username</label>
            </div>
            <div class="input-box">
                    <span class="icon">
                        <ion-icon name="mail"></ion-icon>
                    </span>
                <input type="email" name="email" id="email" required>
                <label>Email</label>
            </div>
            <div class="input-box">
                    <span class="icon">
                <ion-icon name="lock-closed"></ion-icon>
                    </span>
                <input type="password" name="password" id="password" required>
                <label>Password</label>
            </div>
            <button type="submit" id="register-btn">Register</button>
            <div class="register-link">
                <p>Already have an account? <a href="/login">LogIn</a></p>
            </div>
        </form>
    </div>
    <div class="ifEmailExist" id="modal">
        <button type="button" id="closeMe">
            X
        </button>
        <h2 class="message">This email already exists</h2>
    </div>
</section>
<script
        src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
        crossorigin="anonymous"></script>
<script src="../javaScript/RegistrationPageScript.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

</body>
</html>