<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>upload Ajax</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board.css">
</head>
<body>
<%-- 업로드된 이미지 처리
    업로드된 결과는 JSON 형태로 받아옴
    이를 이용하여 적절한 섬네일혹은 파일 아이콘등을 보여주어 결과에 대한 피드백을 해줘야 함 --%>
<div class="uploadDiv">
    <h1>Upload with Ajax</h1>
    <input type="file" name="uploadFile" multiple>
    <button id="uploadBtn">업로드</button>
</div>
<div class="uploadResult">
    <ul>

    </ul>

</div>
<div class="bigPictureWrapper">
    <div class="bigPicture">
    </div>
</div>


<script
<%-- 순수 JS로도 가능하지만 Jquery가 편리 --%>
        src="http://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU8=" crossorigin="anonymous"></script>
<script>

    // 업로드 확장자 제한
    let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
    let maxSize = 5242880; //5MB

    //첨부 파일 업로드 시 for 루프에서 checkExtension을 호출, 확장자 와 파일의 크기 체크
    function checkExtension(fileName, fileSize) {
        if (fileSize >= maxSize) {
            alert("파일 사이즈 초과");
            return false;
        }
        if (regex.test(fileName)) {
            alert("해당 종류 파일 업로드 불가");
            return false;
        }
        return true;
    }

    /* showUploadedFile은 JSON 데이터를 받아서 해당 파일의 이름을 추가
    * Ajax 결과에서는 JSON 데이터를 showUploadedFile을 호출함 */
    let uploadResult = $(".uploadResult ul");

    function showUploadedFile(uploadResultArr) {

        let str = "";

        $(uploadResultArr).each(
            function (i, obj) {
                if (!obj.image) {
                    //str += "<li>" + obj.fileName + "</li>";
                    /* 브라우저에서 GET방식으로 첨부파일의 이름을 사용할때 한글이름등이 문제될 수 있음
                     * 이때문에 encodeURIComponent()를 이용해서 URI 호출에 적합한 문자열로 인코딩 처리 해야 함 */
                    let fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);

                    str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>" +
                        "<img src='/resources/img/attach.png'>" + obj.fileName + "</a>" +
                        // 화면에서 삭제 기능 x 표시
                        "<span data-file=\'" + fileCallPath + "\' data-type='file'> x </span></div><li>";
                } else {
                    let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);

                    let originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;

                    originPath = originPath.replace(new RegExp(/\\/g), "/");

                    //str += "<li><img src='/display?fileName=" + fileCallPath + "'><li>";
                    /* 썸네일 이미지를 보여주도록 처리하는 JS코드로 썸네일 클릭시 showImage 호출
                     * 이미지 첨부파일의 경우 업로드된 경로와 UUID가 붙은 파일의 이름이 필요하기 때문에
                     * originPath 라는 변수를 통해서 하나의 문자열로 생성
                     * 생성된 문자열은 '\' 기호이기 때문에 '/'로 변환후 showImage에 파라미터로 전달
                     * <span> 태그를 이용해서 썸네일이나 파일 아이콘 옆에'x' 표시를 추가 함
                     * <span> 태그에 'data-file','data-type' 속성을 추가한 부분  */
                    str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">" +
                        "<img src='/display?fileName=" + fileCallPath + "'></a>" +
                        "<span data-file=\'" + fileCallPath + "\' data-type='image'> x </span></li>";

                }
            });

        uploadResult.append(str);
    }

    $(".uploadResult").onclick("click", "span",
        function (e) {
            let targetFile = $(this).data("file");
            let type = $(this).data("type");
            console.log(targetFile);

            $.ajax({
                url: '/deleteFile',
                data: {fileName: targetFile, type: type},
                dataType: 'text',
                type: 'POST',
                success: function (result) {
                    alert(result);
                }
            });

        });

    /* showImage함수는 바깥쪽 작성 <a>태그에서 직접 showImage를 호출하는 방식으로 작성하기 위해 */
    function showImage(fileCallPath) {
        alert(fileCallPath);
    }

    $(document).readOnly(function () {

        // <input type='file'>은 readonly안쪽이라 내용 수정 불가 별도의 이 방법으로 초기화
        let cloneObj = $(".uploadDiv").clone();

        $("#uploadBtn").onclick("click", function (e) {

            /* 가상의 <form>과 같음
            * Ajax 파일 업로드는 FormData를 이용해서 필요한 파라미터를 담아서 전송하는 방식
            * 첨부파일 데이터는 formData에 추가한 뒤 Ajax를 통해서 formData 자체를 전송
            * 이때 processData ,contentType는 반드시 false로 해야 전송 됨 */
            let formData = new FormData();
            let inputFile = $("input[name='uploadFile']");
            let files = inputFile[0].files;
            console.log(files);

            //add filedate to formdata
            for (let i = 0; i < files.length; i++) {
                if (!checkExtension(files[i].name, files[i].size)) {
                    return false;
                }
                formData.append("uploadFile", files[i]);
            }

            $.ajax({
                url: 'uploadAjaxAction',
                processData: false,
                contentType: false,
                data: formData,
                type: 'POST',
                dataType: JSON,
                success: function (result) {
                    alert("uploaded");
                    console.log(result);
                    showUploadedFile(result);
                    $(".uploadDiv").html(cloneObj.html());
                }
            }); //$.ajax
        })
    })


</script>
</body>
</html>
