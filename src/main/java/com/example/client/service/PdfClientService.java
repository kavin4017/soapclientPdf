package com.example.client.service;

import com.example.client.wsdl.TextToPdfRequest;
import com.example.client.wsdl.TextToPdfResponse;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class PdfClientService {
    private final WebServiceTemplate webServiceTemplate;

    public PdfClientService(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public byte[] getPdf(String text) {
        TextToPdfRequest request = new TextToPdfRequest();
        request.setText(text);

        TextToPdfResponse response = (TextToPdfResponse) webServiceTemplate
                .marshalSendAndReceive(request);

        return response.getPdfContent();
    }
}
