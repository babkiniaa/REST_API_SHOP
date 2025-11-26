package org.store.models.dtos.request.order;

import lombok.Getter;
import lombok.Setter;
import org.store.models.enums.DocumentType;

@Setter
@Getter
public class OrderPrdInfo {
    private Long orderId;
    private DocumentType documentType;
    private String customEmail;
    private Boolean includePrices;
    private String language;
}