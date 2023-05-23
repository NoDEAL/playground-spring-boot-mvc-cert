<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>로그인</title>
    <link type="text/css" rel="stylesheet" href="../../CC_WSTD_home/unisignweb/rsrc/css/certcommon.css?v=1" />
    <script type="text/javascript" src="../../CC_WSTD_home/unisignweb/js/unisignwebclient.js?v=1"></script>
    <script type="text/javascript" src="../../ccc-sample-wstd/UniSignWeb_Multi_Init_Nim.js?v=1"></script>
    <script type="text/javascript" defer="defer">
        function signIn() {
            const form = document.detailForm;
            if (!form) {
                alert('form is missing');
                return;
            }

            unisign.SetMultiUsingOpt(true);
            unisign.SignData(form.email.value, null, signedData => {
                form.signedData.value = signedData;
                form.action = "<c:url value="/user/signIn.do" />";
                form.submit();
                console.log('form', form);
                console.log('form json', JSON.stringify(form));
            });
            unisign.GetRValueFromKey
        }
    </script>
</head>
<body>
<form:form modelAttribute="userVO" id="detailForm" name="detailForm">
    <div>
        <h2>로그인</h2>
    </div>
    <table>
        <tr>
            <td><label for="email">이메일</label></td>
            <td><form:input path="email" cssClass="txt" /></td>
        </tr>
    </table>
    <div>
        <a href="javascript:signIn();">로그인</a>
        <a href="/user/signUp.do">회원가입</a>
        <a href="/user/registerCertificate.do">인증서 등록</a>
    </div>
    <form:hidden path="signedData" />
</form:form>
</body>
</html>
