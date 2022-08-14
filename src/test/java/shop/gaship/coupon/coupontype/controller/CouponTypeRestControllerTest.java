package shop.gaship.coupon.coupontype.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import shop.gaship.coupon.coupontype.dto.CouponTypeCreationRequestDto;
import shop.gaship.coupon.coupontype.service.CouponTypeService;

/**
 * @author : 최겸준
 * @since 1.0
 */
@WebMvcTest(CouponTypeRestController.class)
class CouponTypeRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CouponTypeService couponTypeService;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoAmount;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoRate;

    @BeforeEach
    void setUp() {
        couponTypeCreationRequestDtoAmount = new CouponTypeCreationRequestDto();
        ReflectionTestUtils.setField(couponTypeCreationRequestDtoAmount, "name", "1000원 할인쿠폰");
        couponTypeCreationRequestDtoAmount.setDiscountRate(0.1); // 검증용값 null로 변할것임
        couponTypeCreationRequestDtoAmount.setDiscountAmount(1000L);

        couponTypeCreationRequestDtoRate = new CouponTypeCreationRequestDto();
        ReflectionTestUtils.setField(couponTypeCreationRequestDtoRate, "name", "10% 할인쿠폰");
        couponTypeCreationRequestDtoRate.setDiscountRate(0.9);
        couponTypeCreationRequestDtoRate.setDiscountAmount(100L); // 검증용값 null로 변할것임
    }

    @DisplayName("정액쿠폰종류 생성요청시에 201을 반환하고 서비스 메서드가 잘 동작한다.")
    @Test
    void couponTypeAddFixedAmount() throws Exception {
        doNothing().when(couponTypeService).addCouponType(any(CouponTypeCreationRequestDto.class));

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(post("/api/coupon-types/fixed-amount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(couponTypeCreationRequestDtoAmount)))
            .andExpect(status().isCreated());

        verify(couponTypeService).addCouponType(any(CouponTypeCreationRequestDto.class));
    }

    @DisplayName("정률쿠폰종류 생성요청시에 201을 반환하고 서비스 메서드가 잘 동작한다.")
    @Test
    void couponTypeAddFixedRate() throws Exception {
        doNothing().when(couponTypeService).addCouponType(any(CouponTypeCreationRequestDto.class));

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(post("/api/coupon-types/fixed-rate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(couponTypeCreationRequestDtoRate)))
            .andExpect(status().isCreated());

        verify(couponTypeService).addCouponType(any(CouponTypeCreationRequestDto.class));
    }

    @DisplayName("추천인쿠폰변경 요청시에 200을 반환하고 서비스 메서드가 잘 동작한다.")
    @Test
    void couponTypeModifyRecommendMemberCoupon() throws Exception {
        doNothing().when(couponTypeService).modifyRecommendMemberCoupon(any(CouponTypeCreationRequestDto.class));

        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(put("/api/coupon-types/recommend-member-coupon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(couponTypeCreationRequestDtoAmount)))
            .andExpect(status().isOk());

        verify(couponTypeService).modifyRecommendMemberCoupon(any(CouponTypeCreationRequestDto.class));
    }
}