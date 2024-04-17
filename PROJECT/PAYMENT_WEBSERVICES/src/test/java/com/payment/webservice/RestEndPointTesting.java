package com.payment.webservice;

import com.payment.webservice.restservices.PayeeController;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RestEndPointTesting {
    @Mock
    private PaymentTransferRepository paymentTransferImplementation;

    @InjectMocks
    private PayeeController payeeController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(payeeController).build();
    }

   // @Test
    void testDeletePayee_Success() throws Exception {
        // Prepare Payee object for deletion
        String requestBody = "{\"payeeId\":1,\"senderAccountNumber\":987456789,\"payeeAccountNumber\":123456789,\"payeeName\":\"Arundhathi\"}";

        // Mock the successful deletion of payee
        doNothing().when(paymentTransferImplementation).deletePayee(1, 987456789L, 123456789L, "Arundhathi");

        // Perform the DELETE request
        mockMvc.perform(delete("/payees/delete/payee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                //  .andExpect(content().string("Payee has been successfully deleted."));
                .andExpect(content().string("Payee Arundhathi deleted successfully"));
    }

    //   @Test
    void testDeletePayee_NotFound() throws Exception {
        // Prepare Payee object for deletion
        String requestBody = "{\"payeeId\":1,\"senderAccountNumber\":987456789,\"payeeAccountNumber\":123456789,\"payeeName\":\"Arundhathi\"}";

        // Mock the deletion of payee that throws PayeeException (not found)
        doThrow(new PayeeException("Payee not found")).when(paymentTransferImplementation).deletePayee(anyInt(), anyLong(), anyLong(), anyString());

        // Perform the DELETE request
        mockMvc.perform(delete("/payees/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Payee not found"));
    }
}
