package org.store.models.dtos.request;

import org.store.models.enums.DocumentType;

public class OrderPrdInfo {
    private String orderId;
    private DocumentType documentType;
    private Boolean includePrices;
    private String language;
}