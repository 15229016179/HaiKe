
function initMenu() {
    $.ajax({
        url: "./app/menu/getAll",
        type: "get",
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                if (data.result != null && data.result.length > 0) {
                    setMenu(data.result);
                }
            }
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
        }
    });
}

function setMenu(menus) {
    var htmlStr = '<li></li><li class="dropdown1"><a href="index.html">首页<span>综合推荐</span></a></li>'
    for (i = 0; i < menus.length; i++) {
        var menu = menus[i];
        if (menu != null) {
            var htmlChildrenStr = '';
            var menuChildren = menu.menus;
            if (menuChildren != null) {
                for (j = 0; j < menuChildren.length; j++) {
                    var menuChild = menuChildren[j];
                    if(menuChild != null){
                        htmlChildrenStr = htmlChildrenStr +
                            '<li>' +
                            '<a href="share_list.html" ' +
                            'onclick="localStorage.setItem(\'menuId\', \'' + menuChild.id + '\')">' + menuChild.title + '</a>' +
                            '</li>'
                    }
                }
            }
            htmlStr = htmlStr +
                '<li class="dropdown1">' +
                '<a>' + menu.title + '<span>' + menu.describe + '</span></a>' +
                '<ul class="dropdown2">' + htmlChildrenStr + '</ul>' +
                '</li>'
        }
    }
    var nav_menu = document.getElementById('nav-menu');
    nav_menu.innerHTML = htmlStr;
}
