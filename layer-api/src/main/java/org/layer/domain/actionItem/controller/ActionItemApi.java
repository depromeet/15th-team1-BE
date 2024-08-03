package org.layer.domain.actionItem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.layer.common.annotation.MemberId;
import org.layer.domain.actionItem.controller.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "액션아이템 API")
public interface ActionItemApi {
    @Operation(summary = "특정 스페이스 & 회고와 매핑되는 액션 아이템 생성", method = "POST", description = """
            특정 회고, 스페이스와 매핑되는 액션 아이템을 생성합니다.
            """)
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CreateActionItemResponse.class)
                            )
                    }
            )
    }
    )
    ResponseEntity<CreateActionItemResponse> createActionItem(@MemberId Long memberId,
                                                              @Validated @RequestBody CreateActionItemRequest createActionItemRequest);

    @Operation(summary = "개인의 액션 아이템 생성", method = "POST", description = """
            개인의 실행 목표를 생성합니다.
            이 실행 목표들은 개인의 실행 목표 탭에서만 볼 수 있으며, 이 경우 spaceId, retrospectId가 null입니다.
            """)
    @ApiResponses({
            @ApiResponse(responseCode = "201")
    }
    )
    ResponseEntity<Void> createPrivateActionItem(@MemberId Long memberId,
                                                              @Validated @RequestBody CreatePrivateActionItemRequest createActionItemRequest);


    @Operation(summary = "개인의 액션 아이템 조회", method = "GET", description = """
            회원 아이디로 개인이 작성한 모든 액션아이템을 조회합니다.
            정렬 기준: 핀 > 진행 중 > 시작 전 > 완료. 기본적으로 최신순
            """)
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MemberActionItemResponse.class)
                            )
                    }
            )
    }
    )
    ResponseEntity<List<MemberActionItemResponse>> memberActionItem(@MemberId Long currentMemberId);

    @Operation(summary = "팀의 액션 아이템 조회", method = "GET", description = """
            팀 아이디로 팀의 모든 액션아이템을 조회합니다.
            정렬 기준: 진행 중 > 시작 전 > 완료. 기본적으로 최신순
            """)
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SpaceActionItemElementResponse.class)
                            )
                    }
            )
    }
    )
    ResponseEntity<SpaceActionItemResponse> teamActionItem(@MemberId Long memberId,
                                                           @PathVariable Long spaceId);

    @Operation(summary = "액션 아이템 삭제", method = "DELETE", description = """
            액션 아이템을 삭제합니다.
            """)
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    }
    )
    ResponseEntity<DeleteActionItemResponse> deleteActionItem(@MemberId Long memberId, @PathVariable("actionItemId") Long actionItemId);
}