/**
 * Form에 포함된 주민등록번호 앞, 뒷자리가 각각 6, 7자리면서 정수로 표현이 가능한지 확인
 * @param form POST로 제출할 대상 객체
 * @returns {*|null} 조건을 만족할 때 주민등록번호 13자리, 그렇지 않을 때 null
 */
function validateSerialNumber(form) {
    const leading = form.serialNumberLeading.value;
    const trailing = form.serialNumberTrailing.value;
    if (
        !leading || leading.length !== 6 || isNaN(parseInt(leading)) ||
        !trailing || trailing.length !== 7 || isNaN(parseInt(trailing))
    ) {
        return null;
    }

    return leading + trailing;
}