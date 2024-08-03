package org.layer.domain.actionItem.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreatePrivateActionItemRequest(@NotNull
                                             @Schema(description = "액션 아이템 내용")
                                             @NotNull String content) {
}
