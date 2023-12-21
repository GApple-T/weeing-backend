package com.gapple.weeingback.domain.consulting.entity.dto.response;

import com.gapple.weeingback.domain.consulting.entity.ToConsultingResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultingShowResponse {

    private List<ToConsultingResponse> consults;

    private String success;
}
