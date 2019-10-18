<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>消息通知</title>
</head>

<style type="text/css">
    table {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        width: 100%;
        border-collapse: collapse;
    }

    td, th {
        font-size: 1em;
        border: 1px solid #5B4A42;
        padding: 3px 7px 2px 7px;
    }

    th {
        font-size: 1.1em;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 4px;
        background-color: #24A9E1;
        color: #ffffff;
    }
</style>
<body>
<div>
    <h2>您的邮件(${verifyMail.email})</h2>
    <p>您好，感谢您的注册，这是一封验证邮件，请点击下面的链接完成注册！</p>
    <a href="http://lagou.stave.tech/zhuce.html?verify=${verifyMail.verifyCode}" >点我完成注册</a>
</div>
</body>

</html>