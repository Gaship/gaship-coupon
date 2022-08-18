package shop.gaship.coupon.coupontype.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;

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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import shop.gaship.coupon.coupontype.dto.CouponTypeDto;
import shop.gaship.coupon.coupontype.service.impl.CouponTypeServiceImpl;

/**
 * CouponType controller 테스트 클래스 입니다.
 *
 * @author 최겸준, 조재철
 * @since 1.0
 */
@WebMvcTest(CouponTypeRestController.class)
class CouponTypeRestControllerTest {

    private MockMvc mockMvc;

    @MockBean
    CouponTypeServiceImpl couponTypeService;

    private CouponTypeDto couponTypeDtoFixRateCanDelete;
    private CouponTypeDto couponTypeDtoFixAmountCannotDelete;

    private PageImpl<CouponTypeDto> couponTypeDtoFixRateCanPage;
    private PageImpl<CouponTypeDto> couponTypeDtoFixAmountCannotDeletePage;

    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoAmount;
    private CouponTypeCreationRequestDto couponTypeCreationRequestDtoRate;

    @BeforeEach
    void setUp() {
        setCouponTypeDtoFixRateCanDelete();
        setCouponTypeDtoFixAmountCannotDelete();
        setCouponTypeDtoFixRateCanDeleteList();
        setCouponTypeDtoFixAmountCannotDeleteList();
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

    @Order(1)
    private void setCouponTypeDtoFixRateCanDelete() {
        couponTypeDtoFixRateCanDelete = new CouponTypeDto();

        ReflectionTestUtils.setField(couponTypeDtoFixRateCanDelete, "name", "최겸준");
        ReflectionTestUtils.setField(couponTypeDtoFixRateCanDelete, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponTypeDtoFixRateCanDelete, "discountRate", 10.0);
    }

    @Order(2)
    private void setCouponTypeDtoFixRateCanDeleteList() {
        List<CouponTypeDto> couponTypeDtoFixRateCanList = List.of(couponTypeDtoFixRateCanDelete);

        couponTypeDtoFixRateCanPage = new PageImpl(couponTypeDtoFixRateCanList, PageRequest.of(0, 5), 10);
    }

    @Order(1)
    private void setCouponTypeDtoFixAmountCannotDelete() {
        couponTypeDtoFixAmountCannotDelete = new CouponTypeDto();

        ReflectionTestUtils.setField(couponTypeDtoFixAmountCannotDelete, "name", "최겸준");
        ReflectionTestUtils.setField(couponTypeDtoFixAmountCannotDelete, "isStopGenerationIssue", false);
        ReflectionTestUtils.setField(couponTypeDtoFixAmountCannotDelete, "discountAmount", 10000L);
    }

    @Order(2)
    private void setCouponTypeDtoFixAmountCannotDeleteList() {
        List<CouponTypeDto> couponTypeDtoFixAmountCannotDeleteList = List.of(couponTypeDtoFixAmountCannotDelete);

        couponTypeDtoFixAmountCannotDeletePage = new PageImpl(
            couponTypeDtoFixAmountCannotDeleteList, PageRequest.of(0, 5), 10);
    }

    @Test
    void couponTypeModify() throws Exception {
        // given
        Integer couponTypeNo = 1;

        doNothing().when(couponTypeService).modifyCouponTypeStopGenerationIssue(couponTypeNo);

        // when, then

        mockMvc.perform(
                   patch("/api/coupon-types/" + couponTypeNo + "/stop-generation-issue"))
               .andExpect(status().isOk());

        verify(couponTypeService).modifyCouponTypeStopGenerationIssue(couponTypeNo);

    }

    @Test
    void couponTypeDelete() throws Exception {
        // given
        Integer couponTypeNo = 1;

        doNothing().when(couponTypeService).deleteCouponType(couponTypeNo);

        // when, then
        mockMvc.perform(
                   delete("/api/coupon-types/" + couponTypeNo))
               .andExpect(status().isOk());

        verify(couponTypeService).deleteCouponType(couponTypeNo);
    }

    @Test
    void couponTypeList() throws Exception {
        // given
        given(couponTypeService.findCouponTypes(any())).willReturn(couponTypeDtoFixRateCanPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-types"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponTypeDtoFixRateCanDelete.getName()))
               .andExpect(
                   jsonPath("$.content[0].isStopGenerationIssue").value(
                       couponTypeDtoFixRateCanDelete.getIsStopGenerationIssue()))
               .andExpect(jsonPath("$.content[0].discountRate").value(couponTypeDtoFixRateCanDelete.getDiscountRate()))
               .andExpect(
                   jsonPath("$.content[0].discountAmount").value(couponTypeDtoFixRateCanDelete.getDiscountAmount()));

        verify(couponTypeService).findCouponTypes(any());
    }

    @Test
    void couponTypeCanDeleteList() throws Exception {
        // given
        given(couponTypeService.findCouponTypesCanDelete(any())).willReturn(couponTypeDtoFixRateCanPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-types/delete-can"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponTypeDtoFixRateCanDelete.getName()))
               .andExpect(
                   jsonPath("$.content[0].isStopGenerationIssue").value(
                       couponTypeDtoFixRateCanDelete.getIsStopGenerationIssue()))
               .andExpect(jsonPath("$.content[0].discountRate").value(couponTypeDtoFixRateCanDelete.getDiscountRate()))
               .andExpect(
                   jsonPath("$.content[0].discountAmount").value(couponTypeDtoFixRateCanDelete.getDiscountAmount()));

        verify(couponTypeService).findCouponTypesCanDelete(any());

    }

    @Test
    void couponTypeCannotDeleteList() throws Exception {
        // given
        given(couponTypeService.findCouponTypesCannotDelete(any())).willReturn(couponTypeDtoFixAmountCannotDeletePage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-types/delete-cannot"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponTypeDtoFixAmountCannotDelete.getName()))
               .andExpect(
                   jsonPath("$.content[0].isStopGenerationIssue").value(
                       couponTypeDtoFixAmountCannotDelete.getIsStopGenerationIssue()))
               .andExpect(
                   jsonPath("$.content[0].discountRate").value(couponTypeDtoFixAmountCannotDelete.getDiscountRate()))
               .andExpect(jsonPath("$.content[0].discountAmount").value(
                   couponTypeDtoFixAmountCannotDelete.getDiscountAmount()));

        verify(couponTypeService).findCouponTypesCannotDelete(any());
    }

    @Test
    void couponTypeFixedAmountList() throws Exception {
        // given
        given(couponTypeService.findCouponTypesFixedAmount(any())).willReturn(couponTypeDtoFixAmountCannotDeletePage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-types/fixed-amount"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponTypeDtoFixAmountCannotDelete.getName()))
               .andExpect(
                   jsonPath("$.content[0].isStopGenerationIssue").value(
                       couponTypeDtoFixAmountCannotDelete.getIsStopGenerationIssue()))
               .andExpect(
                   jsonPath("$.content[0].discountRate").value(couponTypeDtoFixAmountCannotDelete.getDiscountRate()))
               .andExpect(jsonPath("$.content[0].discountAmount").value(
                   couponTypeDtoFixAmountCannotDelete.getDiscountAmount()));

        verify(couponTypeService).findCouponTypesFixedAmount(any());
    }

    @Test
    void couponTypeFixedRateList() throws Exception {
        // given
        List<CouponTypeDto> couponTypeDtoList = List.of(couponTypeDtoFixRateCanDelete);

        PageImpl<CouponTypeDto> couponTypesPage = new PageImpl(couponTypeDtoList, PageRequest.of(0, 5), 10);

        given(couponTypeService.findCouponTypesFixedRate(any())).willReturn(couponTypesPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-types/fixed-rate"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponTypeDtoFixRateCanDelete.getName()))
               .andExpect(
                   jsonPath("$.content[0].isStopGenerationIssue").value(
                       couponTypeDtoFixRateCanDelete.getIsStopGenerationIssue()))
               .andExpect(jsonPath("$.content[0].discountRate").value(couponTypeDtoFixRateCanDelete.getDiscountRate()))
               .andExpect(
                   jsonPath("$.content[0].discountAmount").value(couponTypeDtoFixRateCanDelete.getDiscountAmount()));

        verify(couponTypeService).findCouponTypesFixedRate(any());
    }
}