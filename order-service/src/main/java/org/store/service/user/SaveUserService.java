package org.store.service.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.store.models.dtos.request.client.ClientCreate;
import org.store.models.entities.UserEntity;

@ApplicationScoped
public class SaveUserService {

    @Transactional
    public void saveUser(ClientCreate clientCreate) throws RuntimeException {
        if (UserEntity.findByEmail(clientCreate.getEmail()).isPresent()) {
            throw new RuntimeException("Данный пользователь уже зарегистрирован");
        }

        UserEntity user = new UserEntity();
        user.email = clientCreate.getEmail();
        user.phoneNumber = clientCreate.getPhoneNumber();
        user.fullName = clientCreate.getFullName();
        user.BirthDay = clientCreate.getBirtDay();

        user.persist();
    }
}
