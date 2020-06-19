<%@ page contentType="text/html" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE>
<html>
<head>
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/include/header.jsp"/>

<div class="infa">
    <h2>Contacts</h2>
    <table>
        <tr>
            <td>email:</td>
            <td>contact@examle.heh</td>
        </tr>
        <tr>
            <td>phone:</td>
            <td>8-800-555-35-35</td>
        </tr>
        <tr>
            <td>address:</td>
            <td>st. Hmmmmm, 45, Minsk, Belarus</td>
        </tr>
        <tr></tr>
        <tr></tr>
    </table>
</div>

<div id="googleMap" class="hm"></div>
<script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY&callback=myMap"></script>
<script>
    window.onload = function () {
        function myMap() {
            var mapProp = {
                center: new google.maps.LatLng(51.508742, -0.120850),
                zoom: 5,
            };
            var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
        }
    }
</script>

<jsp:include page="${pageContext.request.contextPath}/include/footer.jsp"/>
</body>
</html>
