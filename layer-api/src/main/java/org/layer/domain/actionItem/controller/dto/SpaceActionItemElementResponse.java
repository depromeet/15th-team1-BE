package org.layer.domain.actionItem.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.layer.domain.actionItem.entity.ActionItem;

@Builder
public record SpaceActionItemElementResponse(
                                    @NotNull
                                    @Schema(description = "액션 아이템 ID")
                                    Long actionItemId,
                                    @NotNull
                                    @Schema(description = "액션 아이템과 내용")
                                    String actionItemContent,
                                    @NotNull
                                    @Schema(description = "액션 아이템과 매핑되는 회고 ID")
                                    Long retrospectId,
                                    @NotNull
                                    @Schema(description = "액션 아이템과 매핑되는 회고 이름")
                                    String retrospectName,
                                    @NotNull
                                    @Schema(description = "액션 아이템과 매핑되는 스페이스 ID")
                                    Long spaceId,
                                    @NotNull
                                    @Schema(description = "액션 아이템과 매핑되는 스페이스 이름")
                                    String spaceName,
                                    @NotNull
                                    @Schema(description = "핀의 여부")
                                    String isPinned,
                                    @NotNull
                                    @Schema(description = "회고의 진행 상태")
                                    String status) {

    public static SpaceActionItemElementResponse toResponse(ActionItem actionItem, String retrospectName) {
        return SpaceActionItemElementResponse.builder()
                .actionItemId(actionItem.getId())
                .actionItemContent(actionItem.getContent())
                .retrospectId(actionItem.getRetrospectId())
                .retrospectName(retrospectName)
                .build();
    }
}