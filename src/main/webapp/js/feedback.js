
function submitFeedback(){
    var feedback_name = $('#feedback_name').val();
    var feedback_email = $('#feedback_email').val();
    var feedback_title = $('#feedback_title').val();
    var feedback_content = $('#feedback_content').val();
    if (feedback_title == '') {
        Toast('请输入主题', 3000)
        return;
    }
    if (feedback_content == '') {
        Toast('请输入描述', 3000)
        return;
    }
    var user = localStorage.getItem('user');
    var userId = '';
    if (user != '') {
        user = JSON.parse(user);
        userId = user.id;
    }
    $.ajax({
        url: "./app/feedback/add",
        type: "post",
        data: {
        	userId: userId,
            name: feedback_name,
            email: feedback_email,
            title: feedback_title,
            content: feedback_content
        },
        dataType: "json",
        success: function (data, status, res) {
            if (data.code == 200) {
                Toast(data.message + ",感谢您的反馈！", 3000)
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
