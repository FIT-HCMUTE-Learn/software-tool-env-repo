package com.landingis.api.util;

import com.landingis.api.dto.ApiMessageDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiMessageUtils {

    public <T> ApiMessageDto<T> success(T data, String message) {
        ApiMessageDto<T> response = new ApiMessageDto<>();
        response.setResult(true);
        response.setData(data);
        response.setMessage(message);
        response.setCode(null);
        return response;
    }
}
