package kr.co.fastcampus.eatgo.interfaces;

import lombok.Builder;
import lombok.Data;

@Data    // 단순 Data를 리턴하는 lombokAnnotation
@Builder
public class SessionResponseDto {

    private String accessToken;

}
