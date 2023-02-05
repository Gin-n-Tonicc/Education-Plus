package com.hackaton.project.dtos.common;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorDTO {
    public String title;
    public String detail;
    public int status;
    public String errorType;
    public String errorCode;
    public String timeStamp;

    public ErrorDTO(String title, String detail, int status, String errorType, String errorCode) {
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.errorType = errorType;
        this.errorCode = errorCode;
        this.timeStamp = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static ErrorDTOBuilder builder() {
        return new ErrorDTOBuilder();
    }

    public static class ErrorDTOBuilder {
        public String title;
        public String detail;
        public int status;
        public String errorType;
        public String errorCode;

        public ErrorDTOBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ErrorDTOBuilder withDetails(String detail) {
            this.detail = detail;
            return this;
        }

        public ErrorDTOBuilder withStatus(int status) {
            this.status = status;
            return this;
        }

        public ErrorDTOBuilder withErrorType(String errorType) {
            this.errorType = errorType;
            return this;
        }

        public ErrorDTOBuilder withErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorDTO build() {
            return new ErrorDTO(title, detail, status, errorType, errorCode);
        }
    }

}
