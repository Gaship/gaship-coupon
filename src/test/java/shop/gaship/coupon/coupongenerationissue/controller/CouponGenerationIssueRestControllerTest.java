package shop.gaship.coupon.coupongenerationissue.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueDetailsResponseDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@WebMvcTest(CouponGenerationIssueRestController.class)
class CouponGenerationIssueRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponGenerationIssueService couponGenerationIssueService;

    @Test
    void couponGenerationIssueAdd() {
    }

    @DisplayName("쿠폰 상세조회 요청이 오면 CouponGenerationIssueDetailsResponseDto으로 잘 변환하여 body에 넣어 보낸다. status값 200")
    @Test
    void couponGenerationIssueDetails() throws Exception {

        CouponGenerationIssueDetailsResponseDto dto =
            new CouponGenerationIssueDetailsResponseDto("천원쿠폰", 1000L, null, LocalDateTime.now(),
                LocalDateTime.now(), LocalDateTime.now(), null, 1);
        given(couponGenerationIssueService.findCouponGenerationIssue(1))
            .willReturn(dto);

        mvc.perform(get("/api/coupon-generations-issues/{couponGenerationIssueNo}", 1).accept(
                MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.couponName").value(dto.getCouponName()))
            .andExpect(jsonPath("$.discountAmount").value(dto.getDiscountAmount()))
            .andExpect(jsonPath("$.discountRate").value(dto.getDiscountRate()))
            .andExpect(jsonPath("$.memberNo").value(dto.getMemberNo()))
            .andReturn();

        verify(couponGenerationIssueService).findCouponGenerationIssue(1);
    }

    @DisplayName("추천인에게 쿠폰생성발급 요청이 들어오면 예외없이 addCouponGenerationIssueToRecommendMember() 메서드가 잘 동작한다.")
    @Test
    void couponGenerationIssueAddToRecommendMember() throws Exception {
        doNothing().when(couponGenerationIssueService).addCouponGenerationIssueToRecommendMember(1);
        mvc.perform(post("/api/coupon-generations-issues/{recommendMemberNo}", 1).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
        verify(couponGenerationIssueService).addCouponGenerationIssueToRecommendMember(1);
    }

}