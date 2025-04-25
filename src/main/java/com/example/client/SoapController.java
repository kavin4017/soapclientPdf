package com.example.client;

import com.example.client.service.PdfClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class SoapController {

    private static final String UPLOAD_DIR = "uploads/";

    @Autowired
    private PdfClientService pdfClientService;

    @GetMapping("/pdf")
    public ResponseEntity generatePdf(@RequestParam String val) throws Exception {
        byte[] pdf = pdfClientService.getPdf(val);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "requests.pdf");
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

    @GetMapping("/text")
    public ResponseEntity processPdf(@RequestBody MultipartFile file) throws Exception {

        byte[] val = file.getBytes();

        String pdf = pdfClientService.getText(val);
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "requests.pdf");
//        return ResponseEntity.ok().headers(headers).body(pdf);
        return ResponseEntity.ok().headers(headers).body(pdf);
    }

}
