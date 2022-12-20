<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD>
    <title>上传文件</title>
</HEAD>
<body MS_POSITIONING="GridLayout">
<form action="/uploadFile" method="post" enctype="multipart/form-data">
    <!--承接整个file文件框的HTML容器-->
    <div id="uploadContent">
        <div id=div1><input id=file1 type=file size=50 name=upload></div>
    </div>
    <br/>
    <button type="button" onclick="addUpload()">添加附件</button>
    <button type="submit">提交</button>
    <br/>
</form>
<script>
    var count = 0;
    var maxfile = 5;

    function addUpload() {
        if (count >= maxfile) return;//限制最多maxfile个文件框
        count++;
        //自增id不同的HTML对象，并附加到容器最后
        var newDiv = "<div id=divUpload" + count + ">"
            + " <input id=file" + count + " type=file name=upload>"
            + " <button type='button' onclick=delUpload('divUpload" + count + "');>删除</button>"
            + " </div>";
        document.getElementById("uploadContent").insertAdjacentHTML("beforeEnd", newDiv);
    }

    //删除指定元素
    function delUpload(diva) {
        count--;
        document.getElementById(diva).parentNode.removeChild(document.getElementById(diva));
    }
</script>
</body>
</HTML>