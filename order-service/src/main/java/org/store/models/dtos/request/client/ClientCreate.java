package org.store.models.dtos.request.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClientCreate {
    private String email;
    private String phoneNumber;
    private String fullName;
    private LocalDate birtDay;
}
