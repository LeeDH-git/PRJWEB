<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Focusleader Project Manager 회원가입</title>
        <link href="${pageContext.request.contextPath}/resources/dist/css/styles.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                                    <div class="card-body">
                                        <form>
                                                                          	<div class = form-group">
                                        		<label class="small mb-1" for="inputEMPNO">사원 번호 </label>
                                        		<input class = "form-control py-4" id = "inputEMPNO" type="text" placeholder="사원번호 입력" />
                                        	</div>
                                        		

                                                <!--    <div class="col-md-6"> -->
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputName">이름</label>
                                                        <input class="form-control py-4" id="inputName" type="text" placeholder="이름 입력" />
                                                    </div>

     
                                               		<div class="form-group">
                                               			<lable class = "small mb-1" for ="inputEmailAddress">아이디(이메일)</lable>
                                               			<input class="form-control py-4" id=inputEmailAddress" type = "email" aria-describedby ="emailHelp" placeholder = "아이디(이메일) 입력"/>
                                               		</div>

                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputPassword">비밀번호</label>
                                                        <input class="form-control py-4" id="inputPassword" type="password" placeholder="비밀번호" />
                                                    </div>
                                                    
                                                      <div class="form-group">
                                                        <label class="small mb-1" for="inputConfirmPassword">비밀번호 확인</label>
                                                        <input class="form-control py-4" id="inputConfirmPassword" type="password" placeholder="비밀번호 확인"/>
                                                    </div>
                                                    
                                                     <div class="form-group">
                                                        <label class="small mb-1" for="inputPhoneNumber">휴대전화 번호</label>
                                                        <input class="form-control py-4" id="inputPhoneNumber" type="text" placeholder="휴대전화 번호 입력"/>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputEmergencyContactNumber">비상연락처</label>
                                                        <input class="form-control py-4" id="inputEmergencyContactNumber" type="text" placeholder="비상연락처 입력"/>
                                                    </div>
                                                    
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputJoinDate">입사일</label>
                                                        <input class="form-control py-4" id="inputJoinDate" type="text" placeholder="입사일 입력"/>
                                                    </div>
                                                    
                                                     <div class="form-group">
                                                        <label class="small mb-1" for="inputPosition">직위</label>
                                                        <input class="form-control py-4" id="inputPosition" type="text" placeholder="직위 입력"/>
                                                    
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputSkillLevel">기술등급</label>
                                                                                                               <label class="small mb-1" for="inputSkillLevel">기술등급</label>
                                                        <select class = "from-control py" id ="inputSkillLevel" style="width:480px;text-align-last:center">                                    
                                                        	  <option>초급</option>
															  <option>중하</option>
	  														  <option>중</option>
	  														  <option>중상</option>
															  <option>고급</option>
															  <option>특급</option>
                                                        </select>
                                                      <!-- <input class="form-control py-4" id="inputSkillLevel" type="text" placeholder="기술등급 입력"/> -->
                                                    </div>
                                                    <div style="text-align:center;">
                                                  	  <button type= "button" class="btn btn-primary">회원가입 </button>
                                                    <div class="card-footer text-center">
                                     				   <div class="small"><a href="login.jsp">Have an account? Go to login</a></div>
                                 				   </div>
                                 <!--            <div class="form-row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputFirstName">First Name</label>
                                                        <input class="form-control py-4" id="inputFirstName" type="text" placeholder="Enter first name" />
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputLastName">Last Name</label>
                                                        <input class="form-control py-4" id="inputLastName" type="text" placeholder="Enter last name" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">Email</label>
                                                <input class="form-control py-4" id="inputEmailAddress" type="email" aria-describedby="emailHelp" placeholder="Enter email address" />
                                            </div>
                                            <div class="form-row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputPassword">Password</label>
                                                        <input class="form-control py-4" id="inputPassword" type="password" placeholder="Enter password" />
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="small mb-1" for="inputConfirmPassword">Confirm Password</label>
                                                        <input class="form-control py-4" id="inputConfirmPassword" type="password" placeholder="Confirm password" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group mt-4 mb-0"><a class="btn btn-primary btn-block" href="login.jsp">Create Account</a></div>
                                        </form>
                                    </div> -->
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
