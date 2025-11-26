package org.store.service.validate;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dtos.request.client.ClientCreate;

@ApplicationScoped
public class UserValidate {

    public void validUser(ClientCreate clientCreate) {
        if (clientCreate.getFullName() == null) {
            throw new RuntimeException("Не указано имя");
        }
        if (clientCreate.getEmail() == null) {
            throw new RuntimeException("Не указан emaill");
        }
        if (clientCreate.getPhoneNumber() == null) {
            throw new RuntimeException("Не указан номер телефона");
        }
        if (clientCreate.getBirtDay() == null) {
            throw new RuntimeException("Не указана дата рождения");
        }

    }
}
