<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Focusleader Project Manager Login</title>
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
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">FocusLeader Project
                                Manager</h3></div>
                            <div class="card-body">
                                <form action="${pageContext.request.contextPath}/user/login" method="post">
                                    <div class="form-group">
                                        <label class="small mb-1" for="empNm">이메일</label>
                                        <input class="form-control py-4" id="empNm" type="email"
                                               placeholder="이메일을 입력하세요."/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empPw">비밀번호</label>
                                        <input class="form-control py-4" id="empPw" type="password"
                                               placeholder="비밀번호를 입력하세요."/>
                                    </div>
                                    <div class="row">
                                        <div class="custom-control custom-checkbox iCheck">
                                            <input class="custom-control-input" id="cookie" type="checkbox"/>
                                            <label class="custom-control-label" for="cookie">로그인 유지</label>
                                        </div>
                                    </div>
                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="small" href="password.jsp">비밀번호를 잊으셨나요?</a>
                                        <a class="btn btn-primary" type="submit">로그인</a>
                                    </div>
                                </form>
                            </div>
                            <div class="card-footer text-center">
                                <div class="small"><a href="${pageContext.request.contextPath}/user/register">계정이 없으신가요?
                                    회원가입!</a></div>
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

<script>
    var msg = "${Message}";
    if (msg === "register success") {
        alert("회원가입이 완료되었습니다.")
    } else {
        alert("이메일과 비밀번호를 확인해주세요.")
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resources/dist/js/scripts.js"></script>
</body>
</html>
