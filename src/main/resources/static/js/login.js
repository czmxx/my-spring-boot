$(function () {
});
//登录接口
function login() {
    const email = $("#email").val();
    const password = $("#password_login").val();
    $.ajax({
        type: 'post',
        url: url + "login/getLogin",
        data: {mobile: email, password: password},
        success: function (data) {
            console.log(data);
            if (data.code === 200) {
                window.location.href = 'index.html';
            } else {
                $("#info1").html(data.message);
                $("#myModal1").modal("show");
            }
        }
    });
}

$("#denglu").on("click", function () {
    login();
});
$("#register").on('click', function () {
    register();
});
function register() {
    const userName = $("#userName").val();
    const mobil = $("#mobil").val();
    const password = $("#password").val();
    const password2 = $("#password2").val();
    if (!userName) {
        errorInf("userName");
        return;
    }
    if (!mobil) {
        errorInf("mobil");
        return;
    }
    if (!password) {
        errorInf("password");
        return;
    }
    if (!password2) {
        errorInf("password2");
        return;
    }
    if (password !== password2) {
        errorInf("password");
        errorInf("password2");
        return;
    }
    $.ajax({
        url: url + "login/getRegister",
        data: {mobile: mobil, nickName: userName, password: password2},
        type: 'post',
        success: function (result) {
            if (result.code === 200) {
                window.location.href = 'login.html';
            } else {
                $("#info").html(result.message);
                $("#myModal").modal("show");
            }
        }
    });
}
/**
 * 错误提示框
 * @param obj
 */
function errorInf(obj) {
    $("#" + obj).attr('class', 'form-control btn-danger');
    setTimeout(function () {
        $(".form-control ").attr('class', 'form-control');
    }, 1000);
}