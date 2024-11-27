package com.group21.tour_reservation.controller.client;

import com.group21.tour_reservation.dto.request.ReserveRequest;
import com.group21.tour_reservation.dto.response.PaymentResponse;
import com.group21.tour_reservation.entity.Reserve;
import com.group21.tour_reservation.payment.Config;
import com.group21.tour_reservation.repository.ReserveRepository;
import com.group21.tour_reservation.utils.PayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private ReserveRepository reserveRepository;

    public int scheduleId = -1;
    public int reserveId = -1;

    @PostMapping("/create_payment")
    public ResponseEntity<?> createPayment(HttpServletRequest request , @RequestBody ReserveRequest reserveRequest) throws UnsupportedEncodingException {

//        String orderType = "other";
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
//        String bankCode = req.getParameter("bankCode");

        System.out.println("secretKey >>> "+Config.secretKey);

        long amount = 10000*100 ;

        String vnp_TxnRef = Config.getRandomNumber(8);
//        String vnp_IpAddr = Config.getIpAddress(req);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);

        vnp_Params.put("vnp_OrderType", "other");

        vnp_Params.put("vnp_Locale", "vn");
        scheduleId = reserveRequest.getTourScheduleId();
        reserveId = reserveRequest.getReserveId();


        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        vnp_Params.put("vnp_IpAddr",  PayUtil.getIpAddress(request));
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("OK");
        paymentResponse.setMessage("Successfully");
        paymentResponse.setURL(paymentUrl);

        return ResponseEntity.status(HttpStatus.OK).body(paymentResponse);
    }


    @GetMapping("/payment_info")
    public String transaction (
    @RequestParam(value = "vnp_Amount") String amount,
    @RequestParam(value = "vnp_BankCode") String bankCode,
    @RequestParam(value = "vnp_OrderInfo") String orderInfo,
    @RequestParam(value = "vnp_ResponseCode") String responseCode

    ) {
        if (responseCode.equals("00")) {
            Reserve reserve = reserveRepository.findById(reserveId).orElseThrow(null);
            if (reserve != null) {
                reserve.setStatus(2);
                reserveRepository.save(reserve);
            }
            return "success" + "schedule id >>> " + scheduleId + "reserve id >>> " + reserveId;

        } else {
            return "error";
        }
    }
}
