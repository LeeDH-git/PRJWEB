<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>upload Form</title>
</head>
<body>
    <%-- 파일 업로드에서 제일 중요 > enctype="multipart/form-data
       multiple : 하나의 <input>로 여러개 업로그 할 수 있음 --%>
    <form action="uploadFormAction"  method="post" enctype="multipart/form-data">
        <input type="file" name="uploadFile" multiple>
        <button>Submit</button>
    </form>
</body>
</html>
