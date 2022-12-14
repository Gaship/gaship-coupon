package shop.gaship.coupon.coupongenerationissue.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 쿠폰을 생성발급하기 위해 client가 request할때 해당 정보를 binding하기위한 클래스입니다.
 *
 * @author 최겸준
 * @since 1.0
 */
@Getter
public class CouponGenerationIssueCreationRequestDto {

    @NotNull
    @Min(1)
    private Integer couponTypeNo;

    @NotNull
    @Min(1)
    private Integer memberGradeNo;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime generationDatetime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime issueDatetime;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime expirationDatetime;
}
