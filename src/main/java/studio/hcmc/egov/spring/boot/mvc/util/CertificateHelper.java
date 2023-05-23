package studio.hcmc.egov.spring.boot.mvc.util;

import crosscert.Base64;
import crosscert.Certificate;
import crosscert.Verifier;
import studio.hcmc.egov.spring.boot.mvc.ErrorResponse;

public class CertificateHelper {
    /**
     * JSP에서 전달된 공동인증서 서명 결과애서 공동인증서 정보 추출
     * @param encodedSignedData Base64 encoded signedData
     * @return 공동인증서 정보
     * @throws studio.hcmc.egov.spring.boot.mvc.ErrorResponse.Cert.Base64.DecodeError 올바르지 않은 Base64 인코딩 문자열
     * @throws studio.hcmc.egov.spring.boot.mvc.ErrorResponse.Cert.Verifier.VerifyError 올바르지 않은 서명 결과
     * @throws studio.hcmc.egov.spring.boot.mvc.ErrorResponse.Cert.Certificate.ExtractError 서명 결과로부터 공동인증서 정보 추출 실패
     */
    public static Certificate decodeCertificate(String encodedSignedData) {
        final var base64 = new Base64();
        final var encodedSignedDataBytes = encodedSignedData.getBytes();
        final var decodeResultCode =  base64.Decode(encodedSignedDataBytes, encodedSignedDataBytes.length);
        if (decodeResultCode != 0) {
            throw new ErrorResponse.Cert.Base64.DecodeError(base64.errmessage, base64.errdetailmessage);
        }

        final var verifier = new Verifier();
        final var verifyResultCode = verifier.VerSignedData(base64.contentbuf, base64.contentlen);
        if (verifyResultCode != 0) {
            throw new ErrorResponse.Cert.Verifier.VerifyError(verifier.errmessage, verifier.errdetailmessage);
        }

        final var certificate = new Certificate();
        final var extractResultCode = certificate.ExtractCertInfo(verifier.certbuf, verifier.certlen);
        if (extractResultCode != 0) {
            throw new ErrorResponse.Cert.Certificate.ExtractError(certificate.errmessage, certificate.errdetailmessage);
        }

        return certificate;
    }
}
