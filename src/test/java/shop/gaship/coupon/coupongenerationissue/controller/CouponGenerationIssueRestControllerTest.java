package shop.gaship.coupon.coupongenerationissue.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import shop.gaship.coupon.coupongenerationissue.dto.response.CouponGenerationIssueResponseDto;
import shop.gaship.coupon.coupongenerationissue.service.CouponGenerationIssueService;

/**
 * CouponGenerationIssue 관련 controller 테스트 클래스 입니다.
 *
 * @author : 조재철
 * @since 1.0
 */
@WebMvcTest(CouponGenerationIssueRestController.class)
class CouponGenerationIssueRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CouponGenerationIssueService couponGenerationIssueService;

    private CouponGenerationIssueResponseDto couponGenerationIssueUsedResponseDto;
    private CouponGenerationIssueResponseDto couponGenerationIssueUnusedAndExpiredResponseDto;
    private CouponGenerationIssueResponseDto couponGenerationIssueUnusedAndUnexpiredResponseDto;

    private PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoPage;
    private PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredResponseDtoPage;
    private PageImpl<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredResponseDtoPage;

    @BeforeEach
    void setUp() {
        setCouponGenerationIssueUsedResponseDto();
        setCouponGenerationIssueUnusedAndExpiredResponseDto();
        setCouponGenerationIssueUnusedAndUnexpiredResponseDto();

        setCouponGenerationIssueUsedResponseDtoList();
        setCouponGenerationIssueUnusedAndExpiredResponseDtoList();
        setCouponGenerationIssueUnusedAndUnexpiredResponseDtoList();
    }

    private void setCouponGenerationIssueUsedResponseDto() {
        couponGenerationIssueUsedResponseDto = new CouponGenerationIssueResponseDto("최겸준", 1, null);
    }

    private void setCouponGenerationIssueUsedResponseDtoList() {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUsedResponseDtoList =
            List.of(couponGenerationIssueUsedResponseDto);

        couponGenerationIssueUsedResponseDtoPage =
            new PageImpl(couponGenerationIssueUsedResponseDtoList, PageRequest.of(0, 5), 10);
    }

    private void setCouponGenerationIssueUnusedAndExpiredResponseDto() {
        couponGenerationIssueUnusedAndExpiredResponseDto = new CouponGenerationIssueResponseDto("최겸준", 1,
            LocalDateTime.now().minusDays(1L));
    }

    private void setCouponGenerationIssueUnusedAndExpiredResponseDtoList() {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndExpiredResponseDtoList =
            List.of(couponGenerationIssueUnusedAndExpiredResponseDto);

        couponGenerationIssueUnusedAndExpiredResponseDtoPage =
            new PageImpl(couponGenerationIssueUnusedAndExpiredResponseDtoList, PageRequest.of(0, 5), 10);
    }

    private void setCouponGenerationIssueUnusedAndUnexpiredResponseDto() {
        couponGenerationIssueUnusedAndUnexpiredResponseDto = new CouponGenerationIssueResponseDto("최겸준", 1,
            LocalDateTime.now().plusDays(1L));
    }

    private void setCouponGenerationIssueUnusedAndUnexpiredResponseDtoList() {
        List<CouponGenerationIssueResponseDto> couponGenerationIssueUnusedAndUnexpiredResponseDtoList =
            List.of(couponGenerationIssueUnusedAndUnexpiredResponseDto);

        couponGenerationIssueUnusedAndUnexpiredResponseDtoPage =
            new PageImpl(couponGenerationIssueUnusedAndUnexpiredResponseDtoList, PageRequest.of(0, 5), 10);
    }

    @Test
    void couponGenerationIssueList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssues(any())).thenReturn(
            couponGenerationIssueUsedResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponGenerationIssueUsedResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(couponGenerationIssueUsedResponseDto.getMemberNo()))
               .andExpect(
                   jsonPath("$.content[0].expirationDatetime").value(
                       couponGenerationIssueUsedResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssues(any());

    }

    @Test
    void couponGenerationIssueUsedList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssuesUsed(any())).thenReturn(
            couponGenerationIssueUsedResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/used-coupons"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponGenerationIssueUsedResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(couponGenerationIssueUsedResponseDto.getMemberNo()))
               .andExpect(
                   jsonPath("$.content[0].expirationDatetime").value(
                       couponGenerationIssueUsedResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesUsed(any());

    }

    @Test
    void couponGenerationIssueUnusedList() throws Exception {
        when(couponGenerationIssueService.findCouponGenerationIssuesUnused(any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/unused-coupons"))
               .andExpect(status().isOk())
               .andExpect(
                   jsonPath("$.content[0].name").value(couponGenerationIssueUnusedAndExpiredResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(
                   couponGenerationIssueUnusedAndExpiredResponseDto.getMemberNo()));
//               .andExpect(
//                   jsonPath("$.content[0].expirationDatetime").value(
//                       couponGenerationIssueUnusedAndExpiredResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesUnused(any());
    }

    @Test
    void couponGenerationIssueByMemberNoList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssuesByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUsedResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/member/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponGenerationIssueUsedResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(couponGenerationIssueUsedResponseDto.getMemberNo()))
               .andExpect(
                   jsonPath("$.content[0].expirationDatetime").value(
                       couponGenerationIssueUsedResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesByMemberNo(any(), any());

    }

    @Test
    void couponGenerationIssueUsedByMemberNoList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssuesUsedByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUsedResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/member/1/used-coupons"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.content[0].name").value(couponGenerationIssueUsedResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(couponGenerationIssueUsedResponseDto.getMemberNo()))
               .andExpect(
                   jsonPath("$.content[0].expirationDatetime").value(
                       couponGenerationIssueUsedResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesUsedByMemberNo(any(), any());

    }

    @Test
    void couponGenerationIssueUnusedByMemberNoList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssuesUnusedByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUnusedAndUnexpiredResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/member/1/unused-coupons"))
               .andExpect(status().isOk())
               .andExpect(
                   jsonPath("$.content[0].name").value(couponGenerationIssueUnusedAndUnexpiredResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(
                   couponGenerationIssueUnusedAndUnexpiredResponseDto.getMemberNo()));
//               .andExpect(
//                   jsonPath("$.content[0].expirationDatetime").value(
//                       couponGenerationIssueUnusedAndUnexpiredResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesUnusedByMemberNo(any(), any());
    }

    @Test
    void couponGenerationIssueUnusedAndExpiredByMemberNoList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssuesUnusedAndExpiredByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUnusedAndExpiredResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/member/1/unused-coupons/expired-coupons"))
               .andExpect(status().isOk())
               .andExpect(
                   jsonPath("$.content[0].name").value(couponGenerationIssueUnusedAndExpiredResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(
                   couponGenerationIssueUnusedAndExpiredResponseDto.getMemberNo()));
//               .andExpect(
//                   jsonPath("$.content[0].expirationDatetime").value(
//                       couponGenerationIssueUnusedAndUnexpiredResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesUnusedAndExpiredByMemberNo(any(), any());
    }

    @Test
    void couponGenerationIssueUnusedAndUnexpiredByMemberNoList() throws Exception {
        // given
        when(couponGenerationIssueService.findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(any(),
            any())).thenReturn(
            couponGenerationIssueUnusedAndUnexpiredResponseDtoPage);

        // when, then
        mockMvc.perform(
                   get("/api/coupon-generations-issues/member/1/unused-coupons/unexpired-coupons"))
               .andExpect(status().isOk())
               .andExpect(
                   jsonPath("$.content[0].name").value(couponGenerationIssueUnusedAndUnexpiredResponseDto.getName()))
               .andExpect(jsonPath("$.content[0].memberNo").value(
                   couponGenerationIssueUnusedAndUnexpiredResponseDto.getMemberNo()));
//               .andExpect(
//                   jsonPath("$.content[0].expirationDatetime").value(
//                       couponGenerationIssueUnusedAndUnexpiredResponseDto.getExpirationDatetime()));

        verify(couponGenerationIssueService).findCouponGenerationIssuesUnusedAndUnexpiredByMemberNo(any(), any());
    }

    @Test
    void useCoupons() throws Exception {
        // given
        List<Integer> couponGenerationIssueNumbers = List.of(1, 2, 3);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(couponGenerationIssueNumbers);

        // when, then
        mockMvc.perform(
                   patch("/api/coupon-generations-issues/used")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(content))
               .andExpect(status().isOk());
    }

    @Test
    void cancelUsedCoupons() throws Exception {
        // given
        List<Integer> couponGenerationIssueNumbers = List.of(1, 2, 3);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(couponGenerationIssueNumbers);

        // when, then
        mockMvc.perform(
                   patch("/api/coupon-generations-issues/used-to-cancel")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(content))
               .andExpect(status().isOk());
    }
}