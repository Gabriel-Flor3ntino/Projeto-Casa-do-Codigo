<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Produto</title>
</head>
<body>

	<form action="${pageContext.request.contextPath}/produtos"
		method="post">

		<div>
			<label for="title">Título</label> <input type="text" name="title"
				id="title" />
		</div>

		<div>
			<label for="description">Descrição</label>
			<textarea rows="10" cols="20" name="description" id="description"></textarea>
		</div>

		<div>
			<label for="value">Valor</label> <input type="text" name="value"
				id="value" />
		</div>

		<div>
			<label for="numPages">Número de páginas</label> <input type="text"
				name="numPages" id="numPages" />
		</div>

		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label> <input
					type="text" name="prices[${status.index}].value"
					id="price_${bookType}" /> <input type="hidden"
					name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>

		<div>
			<input type="submit" value="Enviar">
		</div>

	</form>

</body>
</html>