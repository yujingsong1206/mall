<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>
springmvc上传文件测试
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="上传文件" />
</form>
富文本图片上传测试
<form name="form2" action="/manage/product/rechtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file" />
    <input type="submit" value="上传文件" />
</form>
</body>
</html>
