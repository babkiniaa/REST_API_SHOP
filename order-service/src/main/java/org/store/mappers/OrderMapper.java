package org.store.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.store.models.dtos.request.order.OrderStatusDto;
import org.store.models.dtos.response.OrderResponseDto;
import org.store.models.entities.OrderEntity;
import org.store.models.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderMapper {

    public OrderStatusDto mapOrder(OrderEntity orderEntity) {
        OrderStatusDto orderStatusDto = new OrderStatusDto();

        orderStatusDto.setOrderId(orderEntity.orderId != null ? orderEntity.orderId.toString() : null);
        orderStatusDto.setStatus(OrderStatus.valueOf(orderEntity.orderStatus));
        orderStatusDto.setCreatedAt(orderEntity.dateCreate);
        orderStatusDto.setEstimatedDelivery(orderEntity.awaitDateDeliver);
        orderStatusDto.setTrackingNumber(orderEntity.orderNumber);

        return orderStatusDto;
    }

    public List<OrderResponseDto> mapListDto(List<OrderEntity> orderEntities) {
        if (orderEntities == null) {
            return new ArrayList<>();
        }

        return orderEntities.stream()
                .map(this::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto toOrderResponseDto(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        OrderResponseDto dto = new OrderResponseDto();

        dto.setOrderId(entity.orderId != null ? entity.orderId.toString() : null);
        dto.setCreatedAt(entity.dateCreate);

        if (entity.orderStatus != null) {
            try {
                dto.setStatus(OrderStatus.valueOf(entity.orderStatus.toUpperCase()));
            } catch (IllegalArgumentException e) {
                dto.setStatus(OrderStatus.CANCELLED);
            }
        }

        if (entity.customer != null) {
            dto.setCustomerEmail(entity.customer.email);
            dto.setCustomerName(entity.customer.fullName);
        }

        boolean documentsAvailable = entity.dateCreate != null &&
                entity.dateCreate.isBefore(LocalDateTime.now().minusDays(1));
        dto.setDocumentsAvailable(documentsAvailable);

        return dto;
    }
}