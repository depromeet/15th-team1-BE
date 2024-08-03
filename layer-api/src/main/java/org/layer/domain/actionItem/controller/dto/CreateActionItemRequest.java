package org.layer.domain.actionItem.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "액션 아이템 생성 요정")
public record CreateActionItemRequest(@NotNull
                                      @Schema(description = "액션 아이템과 매핑되는 스페이스 ID")
                                      Long spaceId,
                                      @NotNull
                                      @Schema(description = "액션 아이템 내용")
                                      @NotNull String content) {
}
