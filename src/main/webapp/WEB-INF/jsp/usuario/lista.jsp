<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp" />

<a href="${linkTo[UsuarioController].form()}">Novo Usuario</a>
<table class="table table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>E-mail</th>
			<th>Login</th>
			<th>A��o</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${usuarios}" var="u">
			<tr>
				<td>${u.id}</td>
				<td>${u.nome}</td>
				<td>${u.email}</td>
				<td>${u.login}</td>
				<td>
					<button type="button" class="close" aria-label="Close">
						<a href="${linkTo[UsuarioController].remove(u)}"><span aria-hidden="true">&times;</span></a>
					</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/WEB-INF/jsp/footer.jsp" />
