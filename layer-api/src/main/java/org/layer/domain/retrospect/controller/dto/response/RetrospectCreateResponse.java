package org.layer.domain.retrospect.controller.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RetrospectCreateResponse", description = "회고 생성 Dto")
public record RetrospectCreateResponse(
        @Schema(description = "생성된 회고 아이디", example = "1")
        Long retrospectId
) {
    public static RetrospectCreateResponse of(Long id) {
        return new RetrospectCreateResponse(id);
    }
}
