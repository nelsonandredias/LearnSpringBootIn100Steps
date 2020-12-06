<%@ include file="common/header.jspf" %> <%@ include
file="common/navigation.jspf" %>

<div class="container">
	<font color="red">${mErrorMessage}</font>
	<form method="POST">
		Name: <input type="text" name="fName" /> Password: <input
			type="password" name="fPassword" /> <input type="submit" />
	</form>
</div>
<%@ include file="common/footer.jspf" %>
