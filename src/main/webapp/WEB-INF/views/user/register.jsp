<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Focusleader Project Manager 회원가입</title>
    <%-- <link href="${pageContext.request.contextPath}/resources/dist/css/styles.css" rel="stylesheet" /> --%>
    <link href="${pageContext.request.contextPath}/resources/dist/css/styles.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js"
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
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">회원가입</h3>
                            </div>
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/user/register" method="post">
                                    <div class=form-group">
                                        <label class="small mb-1" for="empNo">사원 번호</label>
                                        <input class="form-control py-4" id="empNo" name="empNo" type="text"
                                               placeholder="사원번호 입력 (YYMM000)"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empNm">이름</label>
                                        <input class="form-control py-4" id="empNm" name="empNm" type="text"
                                               placeholder="이름 입력"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empEmail">이메일</label>
                                        <input type="email" id="empEmail" name="empEmail" class="form-control py-4"
                                               placeholder="이메일을 입력하세요">
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empPw">비밀번호</label>
                                        <input class="form-control py-4" id="empPw" name="empPw" type="password" placeholder="비밀번호"/>
                                    </div>
                               <%--     <div class="form-group">
                                        <label class="small mb-1">비밀번호 확인</label>
                                        <input class="form-control py-4" type="password" placeholder="비밀번호 확인"/>
                                    </div>--%>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empPhoneNo">휴대전화 번호</label>
                                        <input class="form-control py-4" id="empPhoneNo" name="empPhoneNo" type="text"
                                               placeholder="휴대전화 번호 입력"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empEmerNo">비상연락처</label>
                                        <input class="form-control py-4" id="empEmerNo" name="empEmerNo" type="text"
                                               placeholder="비상연락처 입력"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empJoinDate">입사일</label>
                                        <input class="form-control py-4" id="empJoinDate" name="empJoinDate" type="date"
                                               placeholder="입사일 입력"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="pjtPosC">직위</label>
                                        <select class="form-control py" id="pjtPosC" name="pjtPosC"
                                                style="width:130px;text-align-last:center">
                                            <option value="none">=== 선택 ===</option>
                                            <option value="A001">사원</option>
                                            <option value="A002">주임</option>
                                            <option value="A003">대리</option>
                                            <option value="A004">과장</option>
                                            <option value="A005">차장</option>
                                            <option value="A006">부장</option>
                                            <option value="A007">이사</option>
                                            <option value="A008">상무</option>
                                            <option value="A009">전무</option>
                                            <option value="A010">대표이사</option>
                                        </select>

                                        <div class="form-group">
                                            <label class="small mb-1" for="pjtLvC">기술등급</label>
                                            <br/>
                                            <select class="form-control py" id="pjtLvC" name="pjtLvC"
                                                    style="width:130px;text-align-last:center">
                                                <option value="none">=== 선택 ===</option>
                                                <option value="A01">초급</option>
                                                <option value="A02">중급</option>
                                                <option value="A03">고급</option>
                                                <option value="A04">특급</option>
                                            </select>
                                        </div>
                                        <div style="text-align:center;">
                                            <button type="submit" class="btn btn-primary">회원가입</button>
                                            <br>
                                            <div class="card-footer text-center">
                                                <div class="small"><a href="login.jsp">계정이 있으신가요? 로그인으로</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2020</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/scripts.js"></script>
</body>
</html>
