<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/form-elements.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/layer.css">
    <title>重置密码</title>
    <style>
        body {
            background: url("/img/bg.jpg") no-repeat fixed;
            background-size: cover;
            overflow: hidden;
        }

    </style>
</head>
<body>

<div class="container myBox">
    <div class="col-xs-8 col-xs-offset-4 col-sm-6 col-sm-offset-3 form-box">
        <div class="form-top">
            <div class="form-top-left">
                <h3>重置密码</h3>
                <p style="color:red" id="msg"></p>
                <p>请输入您的邮箱:</p>
            </div>
            <div class="form-top-right">
                <i class="fa fa-key"></i>
            </div>
        </div>
        <div class="form-bottom">
            <form role="form" action="/user/forget" class="login-form" id="mainForm" method="post">

                <!--上面的输入框尽可能不需要外边距，使用row解决-->
                <div class="row">
                    <div style="margin-bottom: 15px" class="form-inline">
                        <label class="sr-only" for="email">Email</label>
                        <input type="text" name="email" placeholder="邮箱" class="form-username"
                               id="email">
                        <input type="button" class="btn btn-primary" value="发送验证码" id="sendEmailBtn">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="form-password">验证码</label>
                        <input type="text" id="checkCode" placeholder="验证码" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="form-password">Password</label>
                        <input type="password" name="password" placeholder="新密码" class="form-password form-control"
                               id="form-password">
                    </div>
                </div>
                <!--上面的输入框尽可能不需要外边距，使用row包裹起来解决-->

                <!--<div class="checkbox">-->
                <!--<label>-->
                <!--<input type="checkbox"> 记住我-->
                <!--</label>-->
                <!--</div>-->
                <button type="submit" class="btn">重置</button>

                <div class="row">
                    <div style="padding: 10px 25px">
                        <div style="display: inline-block;float: left" class="text-left"><a href="/index.jsp">返回登录</a>
                        </div>
                        <!--<div style="display: inline-block;float: right" class="text-right"><a href="#">没有账号?</a></div>-->
                    </div>
                </div>

                <%-- 标识表单是否可以提交 0:不可以 1:可以 --%>
                <input type="hidden" id="flag_check" value="1">
                <input type="hidden" id="flag_email" value="1">
            </form>
        </div>
    </div>
</div>


<!-- Javascript -->
<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/js/jquery.backstretch.min.js"></script>
<script src="/js/scripts.js"></script>
<script src="/js/layer.js"></script>


<script>

    $(function () {

        $("#mainForm").submit(function () {

            if (!$("#flag_email").val() != '1') {

                layer.msg('该邮箱不存在');
                return false;
            }

            if (!$("#flag_check").val() != '1') {
                layer.msg('验证码错误');

                return false;

            }
            return true;
        })

        /**
         * 校验邮箱是否存在
         */
        $("#email").blur(function () {

            if ($(this).val() != '') {
                $.get("/user/checkEmail?email=" + $(this).val(), function (res) {
                    if (res == 0) {

                        layer.msg('该邮箱不存在');

                        // 将表单变为不可提交
                        $("#flag_email").val(0);
                        $("#sendEmailBtn").prop("disabled", true);

                    } else {
                        // 将表单变为可提交
                        $("#flag_email").val(1);

                        // 清空提示
                        $("#msg").text('')
                        $("#sendEmailBtn").prop("disabled", false);
                    }
                })

            }


        });

        /**
         * 发送验证码
         */
        $("#sendEmailBtn").click(function () {
            if ($("#email").val() != '') {


                $.post("/user/sendEmail", {
                    email: $("#email").val()
                }, function (res) {

                    if (res == '1') {

                        layer.msg("验证码发送成功,请注意查收!");
                        $("#msg").text('');

                    } else {

                        layer.msg("服务器忙")
                    }
                })

            } else {
                layer.msg('请先输入邮箱');
            }

        })

        /**
         * 校验验证码是否正确
         */
        $("#checkCode").blur(function () {
            if ($(this).val().length > 1) {
                $.post("/user/checkForgetCode", {
                    checkCode: $(this).val()
                }, function (res) {

                    if (res != '1') {
                        layer.msg('验证码错误');
                        // 将表单变为不可提交
                        $('#flag_check').val(0)
                    } else {

                        // 将表单变为可提交
                        $('#flag_check').val(1)
                    }
                })
            }

        });


    })

</script>

</body>
</html>