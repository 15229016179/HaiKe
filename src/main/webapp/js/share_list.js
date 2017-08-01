window.onload = function () {

    var menuId = localStorage.getItem('menuId');
    $.ajax({
        url: "./app/share/getByMenuId",
        type: "post",
        data: {
            menuId: menuId
        },
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                if (data.result != null && data.result.length > 0) {
                    setData(data.result);
                }
            }
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
        }
    });

};

function setData(shares) {
    var html = '<h3>' + shares[0].menu.title + '</h3>';
    for (i = 0; i < shares.length; i++) {
        var share = shares[i];
        if(share != null){
            html = html + activeItemHtml(share);
            document.getElementById('list').innerHTML = html;
        }
    }
}

function activeItemHtml(share){
    var title = share.title;
    var content = share.content;
    var link = share.link;
    var imgUrl = share.imgUrl;
    if(imgUrl == undefined || imgUrl == ''){
        imgUrl = 'http://7xn2r6.com1.z0.glb.clouddn.com/2016-03-29_2016-05-02.png';
    }
    var updateTime = share.updateTime;
    var userName = share.user.userName;
    var html =
        '<div class="post-grids">' +
            '<div class="col-md-4 post-left">' +
                '<a href="' + link+ '"><img src="' + imgUrl + '" alt=""/></a>' +
            '</div>' +
            '<div class="col-md-8 post-right share_list-left">' +
                '<h4><a href="' + link + '">'+ title + '</a></h4>' +
                '<p class="comments">更新于 ' + DateFormat(new Date(updateTime), 'yyyy年MM月dd日') + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + userName + '</p>' +
                '<p class="text">' + content + '</p>' +
            '</div>' +
            '<div class="clearfix"></div>' +
        '</div>'
     return html;
}