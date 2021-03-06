$(function () {
    $("#slider").responsiveSlides({
        auto: true,
        manualControls: '#slider3-pager',
    });

    addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    }

    $(document).ready(function () {
        $('#horizontalTab').easyResponsiveTabs({
            type: 'default', // Types: default, vertical, accordion
            width: 'auto', // auto or any width like 600px
            fit: true
            // 100% fit in a container
        });
    });

    // start-smoth-scrolling
    jQuery(document).ready(function ($) {
        $(".scroll").click(function (event) {
            event.preventDefault();
            $('html,body').animate({
                scrollTop: $(this.hash).offset().top
            }, 1000);
        });
    });

})

function Toast(msg, duration) {
    duration = isNaN(duration) ? 3000 : duration;
    var m = document.createElement('div');
    m.innerHTML = msg;
    m.style.cssText = "background: rgb(0, 0, 0);\
                     opacity: 0.6;\
                     color: rgb(255, 255, 255);\
                     text-align: center;\
                     border-radius: 5px;\
                     position: fixed;\
                     bottom: 20%;\
                     z-index: 999999;\
                     font-weight: 500;\
                     display: inline;\
                     padding-left: 10px;\
                   	 padding-right: 10px;\
					 padding-top: 6px;\
					 padding-bottom: 6px;\
                     font-family: 'Lucida Grande', 'Helvetica', sans-serif;  \
                     font-size: 15px;";
    document.body.appendChild(m);
    m.style.left = ((document.body.clientWidth - m.offsetWidth) / 2) + 'px';
    setTimeout(function () {
        var d = 0.5;
        m.style.webkitTransition = '-webkit-transform ' + d
            + 's ease-in, opacity ' + d + 's ease-in';
        m.style.opacity = '0';
        setTimeout(function () {
            document.body.removeChild(m)
        }, d * 1000);
    }, duration);
}

DateFormat = function (date, format) {
    var o = {
        "M+": date.getMonth() + 1,                 //月份
        "d+": date.getDate(),                    //日
        "h+": date.getHours(),                   //小时
        "m+": date.getMinutes(),                 //分
        "s+": date.getSeconds(),                 //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return format;
}