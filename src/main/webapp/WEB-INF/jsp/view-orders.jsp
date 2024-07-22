<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Books</title>
    <link rel="shortcut icon" href="<c:url value='/favicon.ico' />">
    <link href="<c:url value='/css/common.css' />" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:useBean id="date" class="java.util.Date"/>
<p> <%= date %></p>
<form method="post">
    <h1>Нове замовлення</h1>
    <br>
    <div class="input_wrapper">
        <div class="inputs_div">
            <label for="lastName">Прізвище клієнта</label>
            <input type="text" id="lastName" name="lastName" placeholder="Прізвище"><br><br>
        </div>
        <div class="inputs_div">
            <label for="productName">Назва товару</label>
            <input type="text" id="productName" name="productName" placeholder="назва товару"><br><br>
        </div>
        <div class="inputs_div">
            <label for="amount">Кількість одиниць</label>
            <input type="number" id="amount" name="amount" placeholder="0"><br><br>
        </div>
    </div>
    <input type="submit" value="Додати">
</form>
</body>
<script>
    document.querySelector("form").addEventListener("submit", async function (event) {
        event.preventDefault();
        const formData = new FormData(this);
        const formObject = Object.fromEntries(formData.entries());

        try {
            const response = await fetch(this.action, {
                method: 'POST',
                body: JSON.stringify(formObject),
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            let data = await response.json();
            if (response.ok) {
                alert('OK');
            } else {
                let errors = '';
                errors += "timestamp: " + data.timestamp + "\n";
                errors += "status: " + data.status + "\n";
                errors += "errors: ";

                if (typeof data.errors === 'string') {
                    errors += data.errors;
                } else {
                    for (const [field, message] of Object.entries(data.errors)) {
                        errors += field + ": " + message + "\n";
                    }
                }
                alert('Error:\n' + errors);
            }
        } catch (error) {
            alert('Error: ' + error.message);
        }
    });
</script>
</html>
