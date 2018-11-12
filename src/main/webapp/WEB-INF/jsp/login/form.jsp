<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="alura"%>
<c:import url="/WEB-INF/jsp/header.jsp" />

<form action="${linkTo[LoginController].login(null,null)}" method="post">
	<alura:validationMessage name="login_invalido" />

	<label for="login">Login: </label> <input id="login" name="login"
		type="text" class="form-control" /> <label for="senha">Senha:
	</label> <input id="senha" name="senha" type="password" class="form-control" />
	<div class="btn-group">
		<button type="submit" class="btn btn-outline-secondary">Autenticar</button>
	</div>
</form>

<c:import url="/WEB-INF/jsp/footer.jsp" />