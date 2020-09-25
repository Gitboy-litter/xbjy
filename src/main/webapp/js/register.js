$(function () {
    $(".username").on("blur",function () {
        var username = $(".username").val();
        if (username.length>0){
            $.ajax({
                url:"/login/selectUsername",
                data:{username:username},
                dataType:"text",
                type:"post",
                success:function (data) {
                    if (data=="exit"){
                        $("#msg").html("用户已经存在")
                        $(".username").val("");
                    }else {
                        $("#msg").html("")
                    }
                },
                error:function () {
                    alert(1)
                }
            })
        }
    })

    $(".email").on("blur",function () {
        var email = $(".email").val();
        if (email.length>0){
            $.ajax({
                url:"/login/selectEmail",
                data:{email:email},
                dataType:"text",
                type:"post",
                success:function (data) {
                    if (data=="exit"){
                        $("#msg").html("邮箱已经存在")
                        $(".username").val("");
                    }else {
                        $("#msg").html("")
                    }
                },
                error:function () {
                    alert(1)
                }
            })
        }
    })

    $(".button").on("click",function () {
        var username = $(".username").val();
        var password = $(".password").val();
        var email = $(".email").val();
        var comfirpassword = $(".comfirpassword").val();
        if (password!=comfirpassword){
            alert("两次输入密码不准确")
        }else if(email==null||username==null||password==null){
            alert("信息不可以为空")
        }
    })
})