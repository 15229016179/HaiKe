var menus = [{
    id: '',
    title: '',
    describe: '',
    level: 1,
    pid: '',
    createTime: 0,
    removed: false,
    menus: []
}];

window.onload = function () {

    initMenu();

    $.ajax({
        url: "./app/menu/getAll",
        type: "get",
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                if (data.result != null && data.result.length > 0) {
                    menus = data.result;
                    initMenuSelect();
                }
            }
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
        }
    });

};

function initMenuSelect() {
    if (menus != null) {
        setMenuChildren(menus, document.getElementById("category_one"));
        menuChange(0);
    }
}

function setMenuChildren(menus, select) {
    select.options.length = 0;
    for (var i = 0; i < menus.length; i++) {
        if (menus[i] != null) {
            var title = menus[i].title;
            var value = menus[i].id;
            var option = document.createElement("OPTION");
            option.value = value;
            option.text = title;
            select.options.add(option);
        }
    }
}

function menuChange(index){
    var menuChildren = menus[index].menus;
    if (menuChildren != null) {
        setMenuChildren(menuChildren, document.getElementById("category_two"));
    }
}

function getSelected() {
    var selectedIndex = $('#category_one').get(0).selectedIndex;//索引
    return selectedIndex;
}

function submitShare(){
    var menuId = $('#category_two option:selected').val();//选中的值
    var share_url = $('#share_url').val();
    var share_title = $('#share_title').val();
    var share_content = $('#share_content').val();
    if (share_url == '') {
        Toast('请输入链接地址', 3000)
        return;
    }
    if (share_title == '') {
        Toast('请输入标题', 3000)
        return;
    }
    if (share_content == '') {
        Toast('请输入推荐理由', 3000)
        return;
    }
    var user = localStorage.getItem('user');
    var userId = '';
    if (user != '') {
        user = JSON.parse(user);
        userId = user.id;
    }
    $.ajax({
        url: "./app/share/add",
        type: "post",
        data: {
        	userId: userId,
            menuId: menuId,
            link: share_url,
            title: share_title,
            content: share_content
        },
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                Toast(data.message + ",感谢您的分享！", 3000)
            }else{
                Toast('请稍后重试', 3000)
            }
        },
        error: function (error) {
            var errorJson = error.responseJSON;
            Toast(errorJson.message, 3000);
        }
    });
}
