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
		<title>注册</title>
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
						<h3>新用户注册</h3>
						<p style="color:red" id="msg"></p>
						<p>请输入您的信息:</p>
					</div>
					<div class="form-top-right">
						<i class="fa fa-key"></i>
					</div>
				</div>
				<div class="form-bottom">
					<form role="form" action="/user/register" method="post" class="login-form" id="mainForm">

						<!--上面的输入框尽可能不需要外边距，使用row解决-->
						<div class="row">

							<div class="form-group">
								<label class="sr-only" for="username">Username</label>
								<input type="text" name="username" placeholder="用户名" class="form-username form-control" id="username">
							</div>
							<div class="form-group">
								<label class="sr-only" for="password">Password</label>
								<input type="password" name="password" placeholder="密码" class="form-password form-control" id="password">
							</div>
							<div class="form-group">
								<label class="sr-only" for="email">Email</label>
								<input type="text" name="email" placeholder="邮箱" class="form-username form-control" id="email">
							</div>
							<div class="form-group">
								<label class="sr-only" for="rePassword">Password</label>
								<input type="password" name="rePassword" placeholder="确认密码" class="form-password form-control" id="rePassword">
							</div>
						</div>
						<!--上面的输入框尽可能不需要外边距，使用row包裹起来解决-->


						<button type="submit" class="btn">注册</button>

						<div class="row">
							<div style="padding: 10px 25px">
								<div style="display: inline-block;float: left" class="text-left"><a href="index.jsp">返回登录</a>
								</div>
								<!--<div style="display: inline-block;float: right" class="text-right"><a href="#">没有账号?</a></div>-->
							</div>
						</div>

						<%-- 标识表单是否可以提交 0:不可以 1:可以 --%>
						<input type="hidden" id="flag_username" value="0">
						<input type="hidden" id="flag_email" value="0">
						<input type="hidden" id="flag_password" value="0">
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
			$(function() {
				$("#mainForm").submit(function() {

					if ($("#flag_username").val() != '1') {

						layer.msg('该用户名已经被注册过！')
						return false;
					}
					if ($("#flag_password").val() != '1') {

						layer.msg('两次密码不一致！')
						return false;
					}
					if ($("#flag_email").val() != '1') {

						layer.msg('该邮箱已经被绑定过！')
						return false;
					}
					return true;
				})

				/**
				 * 校验邮箱是否注册过
				 */
				$("#email").blur(function() {

					if ($(this).val() != '') {
						$.post("/user/checkEmail", {
							email: $(this).val()
						}, function(res) {
							if (res == '1') {

								layer.msg('邮箱已经被绑定');

								// 标识为不可提交
								$("#flag_email").val(0);
							} else {

								//标识为可提交
								$("#flag_email").val(1);

							}
						})
					}


				});

				/**
				 * 校验用户名是否注册过
				 */
				$("#username").blur(function() {

					if ($(this).val() != '') {
						$.post("/user/checkUsername", {
							username: $(this).val()
						}, function(res) {
							if (res == '1') {

								layer.msg('用户名已经被注册');

								// 标识为不可提交
								$("#flag_username").val(0);
							} else {

								// 标识为可提交
								$("#flag_username").val(1);
							}
						})
					}
				})

				/**
				 * 校验两次密码是否一致
				 */
				$("#rePassword").blur(function() {
					if ($(this).val() == $("#password").val()) {
						$("#flag_password").val(1);


					} else {

						layer.msg("两次密码不一致")
						// 标识为不可提交
						$("#flag_password").val(0);
					}
				})
			})
		</script>
	</body>
</html>
