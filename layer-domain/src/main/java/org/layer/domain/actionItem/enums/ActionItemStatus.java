package org.layer.domain.actionItem.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActionItemStatus {
    BEFORE_START("시작 전"),
    PROCEEDING("진행 중"),
    NOT_ACHIEVED("미달성"),
    DONE("완료"); // MVP 단계에선 생성, 삭제 밖에 없는 것 같지만, 확장성 고려

    private final String value;

}
