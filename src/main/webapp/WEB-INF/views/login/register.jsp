<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<title>회원가입</title>
	</head>
	<body>
		
		<form action="/member/register">
			<div>
				<label for="userId">아이디</label>
				<input type="text" id="userId" name="userId" />
			</div>
			<div>
				<label for="userPass">패스워드</label>
				<input type="password" id="userPass" name="userPass" />
			</div>
			<div>
				<label for="userName">성명</label>
				<input type="text" id="userName" name="userName" />
			</div>
			<div>
				<button type="submit" id="submit">회원가입</button>
				<input type="button">
			</div>
		</form>
		
		
	</body>
	
</html>