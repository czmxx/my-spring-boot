
//登录接口
function login() {
    $.ajax({
        url: url + "login/login",
        data: {keyword:$("#keyword").val(),password:$("#password").val()},
        type: 'post',
        success: function (result) {
            console.log(result);
            if (result) {
                alert('登录成功');
                // window.location.href = url + "index.html"
            } else {
                alert('登录失败');
            }
        }
    });
}

$("#login").on("click",function () {
    login();
})