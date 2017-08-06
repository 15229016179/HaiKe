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
    initMenu();
    initBanner();

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

function initBanner() {
    $.ajax({
        url: "./app/banner/getAll",
        type: "get",
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                if (data.result != null && data.result.length > 0) {
                    setBanners(data.result);
                }
            }
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
        }
    });
}

function setBanners(banners) {
    var ul = document.createElement("ul");
    ul.className = 'slider';
    ul.id = 'slider';
    var ul_small = document.createElement("ul");
    ul_small.className = 'slider3-pager';
    ul_small.id = 'slider_small';
    document.getElementById('banner').appendChild(ul);
    document.getElementById('banner').appendChild(ul_small);
    var htmlStr = '';
    var htmlSmallStr = '';
    for (i = 0; i < banners.length; i++) {
        var banner = banners[i];
        htmlStr = htmlStr +
            '<li>' +
            '<a target="_blank" href="' + banner.link + '"><img src="' + banner.imgUrl + '"></a>' +
            '</li>';
        htmlSmallStr = htmlSmallStr +
            '<li>' +
            '<a target="_blank" href="#"><img src="' + banner.smallImgUrl + '"></a>' +
            '</li>';
    }
    document.getElementById('slider').innerHTML = htmlStr;
    document.getElementById('slider_small').innerHTML = htmlSmallStr;
}

function quit() {
    localStorage.removeItem('user')
    location.reload(true)
}
