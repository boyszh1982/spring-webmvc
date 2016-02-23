<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<body>

	<form:form action="/boyz/StaffController/toLoginCheck.do" method="post" commandName="staff">
		<table>
			<tr>
				<td>Username:</td>
				<td><form:input path="staffId" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<input type="hidden" name="login_token" value="${LOGIN_TOKEN}" />
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>

	<%-- <form action="${LOGIN_ACTION }">
		USERNAME:<input type="text" name="username" value="${username}" />
		PASSWORD:<input type="text" name="password" value="${password}" /> <input
			type="hidden" name="login_token" value="${LOGIN_TOKEN}" /> <input
			type="submit" value="submit" />
	</form> --%>
</body>
</html>
