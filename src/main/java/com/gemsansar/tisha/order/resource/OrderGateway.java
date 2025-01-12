package com.gemsansar.tisha.order.resource;

import com.gemsansar.tisha.authentication.service.SessionService;
import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.domain.OrderStatus;
import com.gemsansar.tisha.order.domain.dto.request.OrderRequest;
import com.gemsansar.tisha.order.domain.dto.request.OrderUpdateRequest;
import com.gemsansar.tisha.order.domain.dto.response.OrderResponse;
import com.gemsansar.tisha.order.service.CreateOrderUseCaseService;
import com.gemsansar.tisha.order.service.GetOrderUseCaseService;
import com.gemsansar.tisha.order.service.GetOrdersService;
import com.gemsansar.tisha.order.service.UpdateOrderUseCaseService;
import com.gemsansar.tisha.platform.enums.AppRole;
import com.gemsansar.tisha.platform.exception.UseCaseException;
import com.gemsansar.tisha.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderGateway {

    private final OrderDomainMapper orderDomainMapper;
    private final GetOrderUseCaseService getOrderUseCaseService;
    private final GetOrdersService getOrdersService;
    private final CreateOrderUseCaseService createOrderUseCaseService;
    private final UpdateOrderUseCaseService updateOrderUseCaseService;
    private final SessionService sessionService;

    public OrderResponse create(OrderRequest request){
        User user = sessionService.getCurrentUser();
        Order order = orderDomainMapper.mapToDomain(request, user, OrderStatus.PLACED, ItemStatus.IN_PROGRESS);
        Order savedOrder = createOrderUseCaseService.handle(order);
        return orderDomainMapper.mapToResponse(savedOrder);
    }

    public OrderResponse update(Long id, OrderUpdateRequest request){
        User user = sessionService.getCurrentUser();
        Order orderInDb = getOrderUseCaseService.getByOrderId(id);
        if(!user.getId().equals(orderInDb.getCreateBy()) || user.getRole().equals(AppRole.SUPPLIER)){
            throw new UseCaseException("Update operation not allowed for user:" + user.getId());
        }

        Order order = orderDomainMapper.mapToDomain(orderInDb, request);
        Order savedOrder = updateOrderUseCaseService.handle(order);
        return orderDomainMapper.mapToResponse(savedOrder);

    }

    public OrderResponse getOrderById(Long id){
        User user = sessionService.getCurrentUser();
        Order order = getOrderUseCaseService.getByOrderId(id);
        if(!order.getCreateBy().equals(user.getId()) && !AppRole.getNonUserRole().contains(user.getRole())){
            throw new UseCaseException("Get operation not allowed for user:" + user.getId());
        }
        return orderDomainMapper.mapToResponse(order);
    }

    public Page<OrderResponse> getOrders(Pageable pageable){
        User user = sessionService.getCurrentUser();
        if(AppRole.getNonUserRole().contains(user.getRole())){
            return getOrdersService.getOrders(pageable).map(orderDomainMapper::mapToResponse);
        }
        return getOrdersService.getOrdersByUserId(user.getId(), pageable).map(orderDomainMapper::mapToResponse);
    }
}
