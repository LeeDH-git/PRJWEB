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
    <%@ include file="/resources/plugin/plugin.jsp" %>
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
                                <form action="${pageContext.request.contextPath}/user/loginCheck" method="post">
                                    <div class="form-group">
                                        <label class="small mb-1" for="empEmail">이메일</label>
                                        <input class="form-control py-4" id="empEmail" name="empEmail" type="email"
                                               placeholder="이메일을 입력하세요."/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="empPw">비밀번호</label>
                                        <input class="form-control py-4" id="empPw" name="empPw" type="password"
                                               placeholder="비밀번호를 입력하세요."/>
                                    </div>
                                    <div class="row">
                                        <div class="col-8">
                                            <div class="checkbox icheck">
                                                <label>
                                                    <input type="checkbox" name="cookie"> 로그인유지
                                                </label>
                                            </div>
                                        </div>
                                        <!-- /.col -->
                                        <div class="col-8">
                                            <button type="submit" class="btn btn-primary btn-block btn-flat">
                                                <i class="fa fa-sign-in"></i> 로그인
                                            </button>
                                        </div>
                                        <!-- /.col -->
                                    </div>

                                    <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="small" href="password.jsp">비밀번호를 잊으셨나요?</a>
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
   <!-- 로그인 실패 시 출력할 메세지 -->
    ${requestScope.loginFailMsg}
</script>


</body>
</html>
