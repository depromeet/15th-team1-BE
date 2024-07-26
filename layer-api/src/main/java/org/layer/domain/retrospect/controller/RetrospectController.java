package org.layer.domain.retrospect.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.layer.common.annotation.MemberId;
import org.layer.domain.retrospect.controller.dto.request.RetrospectCreateRequest;
import org.layer.domain.retrospect.controller.dto.response.RetrospectCreateResponse;
import org.layer.domain.retrospect.controller.dto.response.RetrospectGetResponse;
import org.layer.domain.retrospect.controller.dto.response.RetrospectListGetResponse;
import org.layer.domain.retrospect.service.RetrospectService;
import org.layer.domain.retrospect.service.dto.request.RetrospectCreateServiceRequest;
import org.layer.domain.retrospect.service.dto.response.RetrospectListGetServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/space/{spaceId}/retrospect")
public class RetrospectController implements RetrospectApi {

    private final RetrospectService retrospectService;

    @Override
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RetrospectCreateResponse> createRetrospect(
            @PathVariable("spaceId") Long spaceId,
            @RequestBody @Valid RetrospectCreateRequest request,
            @MemberId Long memberId) {

        var retrospectId = retrospectService.createRetrospect(
                RetrospectCreateServiceRequest.of(request.title(), request.introduction(), spaceId, request.questions()), memberId);

        return ResponseEntity.ok().body(RetrospectCreateResponse.of(retrospectId));
    }

    @Override
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RetrospectListGetResponse> getRetrospects(@PathVariable("spaceId") Long spaceId,
                                                                    @MemberId Long memberId) {

        RetrospectListGetServiceResponse serviceResponse = retrospectService.getRetrospects(spaceId, memberId);

        List<RetrospectGetResponse> retrospectGetResponses = serviceResponse.retrospects().stream()
                .map(r -> RetrospectGetResponse.of(r.title(), r.introduction(), r.isWrite(), r.retrospectStatus(),
                        r.writeCount()))
                .toList();

        return ResponseEntity.ok()
                .body(RetrospectListGetResponse.of(serviceResponse.layerCount(), retrospectGetResponses));
    }

}
