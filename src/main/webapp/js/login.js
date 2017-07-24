/**
 * Created by Dana on 2017/7/13.
 */
$('.toggle').on('click', function () {
    $('.container').stop().addClass('active');
});

$('.close').on('click', function () {
    $('.container').stop().removeClass('active');
});

function login() {
    var userName = $('#userName').val();
    var password = $('#password').val();
    if (userName == '') {
        Toast('请输入用户名', 3000)
        return;
    }
    if (password == '') {
        Toast('请输入密码', 3000)
        return;
    }
    document.getElementById("loading").style.display = "inline";
    $.ajax({
        url: "./app/user/login",
        type: "post",
        data: {
            userName: userName,
            password: password
        },
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200 && data.result != null) {
                localStorage.setItem('user', JSON.stringify(data.result));
                self.location = 'index.html'
            }else{
                Toast('请稍后重试', 3000)
            }
            document.getElementById("loading").style.display = "none";
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
            document.getElementById("loading").style.display = "none";
        }
    });
}
function reg() {
    var userMail = $('#userMail').val();
    var userName_r = $('#userName_r').val();
    var password_r = $('#password_r').val();
    if (userMail == '') {
        Toast('请输入邮箱', 3000)
        return;
    }
    if (userName_r == '') {
        Toast('请输入用户名', 3000)
        return;
    }
    if (password_r == '') {
        Toast('请输入密码', 3000)
        return;
    }
    document.getElementById("loading").style.display = "inline";
    $.ajax({
        url: "./app/user/register",
        type: "post",
        data: {
            email: userMail,
            userName: userName_r,
            password: password_r
        },
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200 && data.result != null) {
                localStorage.setItem('user', JSON.stringify(data.result));
                self.location = 'index.html'
            }else{
                Toast('请稍后重试', 3000)
            }
            document.getElementById("loading").style.display = "none";
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
            document.getElementById("loading").style.display = "none";
        }
    });
}
function changePasswd() {
    var userMail = $('#userMail_c').val();
    var userName_r = $('#userName_c').val();
    var password_r = $('#newPassword').val();
    if (userMail == '') {
        Toast('请输入邮箱', 3000)
        return;
    }
    if (userName_r == '') {
        Toast('请输入用户名', 3000)
        return;
    }
    if (password_r == '') {
        Toast('请输入密码', 3000)
        return;
    }
    document.getElementById("loading").style.display = "inline";
    $.ajax({
        url: "./app/user/changePasswd",
        type: "post",
        data: {
            email: userMail,
            userName: userName_r,
            password: password_r
        },
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                Toast(data.message, 3000)
                self.location = 'login.html'
            }else{
                Toast('请稍后重试', 3000)
            }
            document.getElementById("loading").style.display = "none";
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
            document.getElementById("loading").style.display = "none";
        }
    });
}