package com.example.jejuddai.dto;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
public class RequestDTO {
    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    private double radius; // 1미터 단위 - 이후 확인 필요
}
