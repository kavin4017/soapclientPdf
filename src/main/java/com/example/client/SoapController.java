package com.example.client;

import com.example.client.service.PdfClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoapController {

    @Autowired
    private PdfClientService pdfClientService;

    @GetMapping("/pdf")
    public ResponseEntity generatePdf(@RequestParam String val) throws Exception {
        byte[] pdf = pdfClientService.getPdf("Hello");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "requests.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

}
