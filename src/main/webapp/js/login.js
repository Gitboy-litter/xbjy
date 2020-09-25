$(function () {
    $(".button").on("click",function () {
        var username = $(".username").val();
        var password = $(".password").val();
        var remeberme = $(".remeberme").val();
        var code = $(".code").val();
        console.log(username,password)
        if (username==""||password==""){
            alert("账号密码不可以为空")
        }else {
        $.ajax({
            url:"/login/login",
            data:{username:username,password:password,remeberme:remeberme,code:code},
            dataType:"text",
            type:"post",
            success:function (data) {
                if (data=="success"){
                    window.location.href='/html/user/home.jsp';
                }else {
                $("#msg").html(data)
                }
            },
            error:function () {
                alert(1)
            }
        })
        }
    })
})
function clickCode() {
    console.log(1)
   $("#vcode").attr("src","/img/verifyCode?no-cache"+new Date().getTime())
}