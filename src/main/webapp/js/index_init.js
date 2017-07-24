/**
 * Created by Dana on 2017/7/11.
 */
window.onload = function () {

    // 日期赋值
    var today = new Date();//定义日期对象
    var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年
    var MM = today.getMonth() + 1;//通过日期对象的getMonth()方法返回年
    var dd = today.getDate();//通过日期对象的getDate()方法返回年
    for (var i = 0; i < 2; i++) {
        document.getElementById('new_update_date_' + i).innerHTML = yyyy + "年" + MM + "月" + dd + "日";
    }
    for (var i = 0; i < 2; i++) {
        document.getElementById('hot_update_date_' + i).innerHTML = yyyy + "年" + MM + "月" + dd + "日";
    }
    for (var i = 0; i < 4; i++) {
        document.getElementById('like_update_date_' + i).innerHTML = yyyy + "年" + MM + "月" + dd + "日";
    }

    initUser();

};

function initUser() {
    var user = localStorage.getItem('user');
    if (user != '') {
        user = JSON.parse(user);
        var userName = user.userName;
        document.getElementById("login").style.display = "none";
        document.getElementById("user").style.display = "inline";
        document.getElementById("user").innerHTML = userName + ' | <a href="#" onclick="quit()">注销</a>';
    } else {
    	document.getElementById("user").style.display = "none";
        document.getElementById("login").style.display = "inline";
    }
}

function quit() {
    localStorage.removeItem('user')
    location.reload(true)
}

