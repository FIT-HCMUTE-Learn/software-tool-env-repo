package com.landingis.api.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_FORM("INVALID_FORM", "Invalid form"),
    NOT_FOUND("NOT_FOUND", "Resource not found"),
    UNKNOWN_ERROR("UNKNOWN_ERROR", "Unknown error"),
    BUSINESS_ERROR("BUSINESS_ERROR", "Business logic error"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Method not allowed"),
    AUTHENTICATION_ERROR("AUTHENTICATION_ERROR", "Authentication error"),
    ACCESS_DENIED("ACCESS_DENIED", "Access denied");
    
    private final String code;
    private final String message;
}
