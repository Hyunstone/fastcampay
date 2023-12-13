package com.fastcampus.work_taste.fastcampay.config;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    SUCCESS(true, 200, "s200", "요청에 성공하였습니다"),
    REQUEST_ERROR(false, 404, "e400", "입력값을 확인해주세요"),
    NOT_EXIST_USER(false, 404, "e401", "해당 유저를 찾을 수 없습니다"),
    NOT_EXIST_PAY_REQUEST(false, 404, "e402", "해당 결제 요청을 찾을 수 없습니다"),
    PAYMENT_INSUFFICIENT(false, 404, "e403","잔액이 부족합니다"),
    IS_ALREADY_PROCESS(false, 404, "e404", "이미 처리된 결제 요청입니다"),
    WRONG_STATUS_CODE(false, 500, "e500","존재하지 않은 상태코드입니다");

    private final boolean isSuccess;
    private final int status;
    private final String code;
    private final String message;

    BaseResponseStatus(boolean isSuccess, int status, String code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public static BaseResponseStatus getBaseStatusByCode(String code) throws BaseException {
        for (BaseResponseStatus baseStatus: BaseResponseStatus.values()) {
            if (baseStatus.code.equals(code)) {
                return baseStatus;
            }
        }
        throw new BaseException(BaseResponseStatus.WRONG_STATUS_CODE);
    }
}
