<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>공동인증서 등록</title>
    <link type="text/css" rel="stylesheet" href="../../CC_WSTD_home/unisignweb/rsrc/css/certcommon.css?v=1" />
    <script type="text/javascript" src="../../CC_WSTD_home/unisignweb/js/unisignwebclient.js?v=1"></script>
    <script type="text/javascript" src="../../ccc-sample-wstd/UniSignWeb_Multi_Init_Nim.js?v=1"></script>
    <script type="text/javascript" src="../../serial_number_validator.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/js-sha512/0.8.0/sha512.min.js"></script>
    <script type="text/javascript" defer="defer">
        function register() {
            const form = document.detailForm;
            if (!form) {
                alert('form is missing');
                return;
            }

            const serialNumber = validateSerialNumber(form);
            if (!serialNumber) {
                alert('주민등록번호를 확인해주세요.');
                return;
            }

            unisign.SignDataNVerifyVID(serialNumber, null, serialNumber, resultObject => {
                if (!resultObject || resultObject.resultCode !== 0) {
                    alert('오류가 발생했습니다.');
                    return;
                }

                form.serialNumberLeading.value = '';
                form.serialNumberTrailing.value = '';
                form.password.value = sha512(serialNumber);
                form.signedData.value = resultObject.signedData;
                form.action = "<c:url value="/user/registerCertificate.do" />";
                form.submit();
            });
        }
    </script>
</head>
<body>
<form:form modelAttribute="userVO" id="detailForm" name="detailForm">
    <div>
        <h2>공동인증서 등록</h2>
    </div>
    <table>
        <tr>
            <td><label for="email">이메일</label></td>
            <td><form:input path="email" cssClass="txt" /></td>
        </tr>
        <tr>
            <td><label>주민등록번호</label></td>
            <td><form:input path="serialNumberLeading" /></td>
            <td><form:password path="serialNumberTrailing" /></td>
        </tr>
    </table>
    <div>
        <a href="javascript:register();">등록</a>
    </div>
    <form:hidden path="password" />
    <form:hidden path="signedData" />
</form:form>
</body>
</html>
