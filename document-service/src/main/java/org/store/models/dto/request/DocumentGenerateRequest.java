package org.store.models.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DocumentGenerateRequest {
    private String orderId;
    private String documentType;
    private Map<String, Object> templateData;
}
