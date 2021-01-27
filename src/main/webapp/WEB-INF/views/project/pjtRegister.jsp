<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Focusleader Project Manager 프로젝트 등록</title>
    <link href="${pageContext.request.contextPath}/resources/dist/css/styles.css" rel="stylesheet"/>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
            crossorigin="anonymous"></script>

</head>
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header">
                                <h3 class="text-center font-weight-light my-4">프로젝트 등록</h3>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class=form-group">
                                        <label class="small mb-1" for="pjtNm">프로젝트 명</label> <input
                                            class="form-control py-4" id="pjtNm" type="text"
                                            placeholder="프로젝트 명 입력"/>
                                    </div>

                                    <!--    <div class="col-md-6"> -->
                                    <div class="form-group">
                                        <label class="small mb-1" for="pjtClient">고객명</label> <input
                                            class="form-control py-4" id="pjtClient" type="text"
                                            placeholder="고객명 입력"/>
                                    </div>

                                    <div class="form-group">
                                        <lable class="small mb-1" for="orgKind">기관종류</lable>
                                        <input class="form-control py-4" id=orgKind
                                        " type="text"
                                        placeholder="기관 종류 입력" />

                                    </div>

                                    <div class="form-group">
                                        <label class="small mb-1" for="pjtPm">PM</label> <input
                                            class="form-control py-4" id="pjtPm" type="text"
                                            placeholder="PM"/>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="small mb-1" for="pjtStartDate">프로젝트 시작일</label>
                                                <input class="form-control py" type='date' id='pjtStartDate'>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <lable class="small mb-1" for="pjtEndDate">프로젝트 종료일</lable>
                                                <input class="form-control py" type='date' id='pjtEndDate'>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- <input class="form-control py-4" id="inputSkillLevel" type="text" placeholder="ê¸°ì ë±ê¸ ìë ¥"/> -->
                                    <!--  </div> -->
                                    <div class="form-group">
                                        <label class="small mb-1" for="pjtEmpList">프로젝트 투입인원</label>
                                        <input class="form-control py-4" id="pjtEmpList" type="text"
                                               placeholder="프로젝트 투입인력 입력 ex)홍길동, 김철수"/>
                                    </div>
                                    <div style="text-align: center;">
                                        <button type="button" class="btn btn-primary" id="pjtRegister">등록</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <div id="layoutAuthentication_footer">
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid">
                <div
                        class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2020</div>
                    <div>
                        <a href="#">Privacy Policy</a> &middot; <a href="#">Terms
                        &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script>
    document.getElementById('inputStartDate').value = new Date().toISOString().substring(0, 10);
    ;
    document.getElementById('inputStartDate').min = new Date().toISOString().substring(0, 10);
    ;
    document.getElementById('inputEndDate').value = new Date().toISOString().substring(0, 10);
    ;
    document.getElementById('inputEndDate').min = new Date().toISOString().substring(0, 10);
    ;
</script>
</body>
</html>
