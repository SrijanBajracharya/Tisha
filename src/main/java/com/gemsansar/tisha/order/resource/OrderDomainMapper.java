package com.gemsansar.tisha.order.resource;

import com.gemsansar.tisha.cost.domain.Cost;
import com.gemsansar.tisha.cost.domain.request.CostCreateRequest;
import com.gemsansar.tisha.cost.domain.request.CostUpdateRequest;
import com.gemsansar.tisha.cost.domain.response.CostResponse;
import com.gemsansar.tisha.items.domain.Item;
import com.gemsansar.tisha.items.domain.ItemStatus;
import com.gemsansar.tisha.items.domain.dto.request.ItemCreateRequest;
import com.gemsansar.tisha.items.domain.dto.request.ItemUpdateRequest;
import com.gemsansar.tisha.items.domain.dto.response.ItemResponse;
import com.gemsansar.tisha.order.domain.Order;
import com.gemsansar.tisha.order.domain.OrderStatus;
import com.gemsansar.tisha.order.domain.dto.request.OrderRequest;
import com.gemsansar.tisha.order.domain.dto.request.OrderUpdateRequest;
import com.gemsansar.tisha.order.domain.dto.response.OrderResponse;
import com.gemsansar.tisha.platform.utils.PriceCalculation;
import com.gemsansar.tisha.stone.domain.Stone;
import com.gemsansar.tisha.stone.domain.StoneType;
import com.gemsansar.tisha.stone.domain.dto.request.StoneCreateRequest;
import com.gemsansar.tisha.stone.domain.dto.response.StoneResponse;
import com.gemsansar.tisha.stone.service.GetStoneTypeByIdUseCaseService;
import com.gemsansar.tisha.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class OrderDomainMapper {

    private final GetStoneTypeByIdUseCaseService getStoneTypeByIdUseCaseService;

    private static final BigDecimal RATE_PER_GRAM = BigDecimal.valueOf(10);

    public Order mapToDomain(OrderRequest request, User user, OrderStatus status, ItemStatus itemStatus){
        return Order.builder()
                .comment(request.getComment())
                .dueDate(request.getDueDate())
                .status(status)
                .lastModifiedBy(user.getId())
                .lastModifiedDate(Instant.now())
                .items(mapToItems(request.getItems(), itemStatus, user.getId()))
                .customerId(request.getCustomerId())
                .createBy(user.getId())
                .build();
    }

    public Order mapToDomain(Order order, OrderUpdateRequest request){
        return Order.builder()
                .id(order.getId())
                .dueDate(request.getDueDate())
                .status(request.getStatus())
                .customerId(request.getCustomerId())
                .items(mapToItemUpdateRequests(request.getItems(), order))
                .comment(request.getComment())
                .build();
    }

    private List<Item> mapToItemUpdateRequests(List<ItemUpdateRequest> requests, Order order){
        return requests.stream().map(itemUpdateRequest -> mapToItemUpdateRequest(itemUpdateRequest, order)).toList();
    }

    private Item mapToItemUpdateRequest(ItemUpdateRequest itemUpdateRequest, Order order){
        Map<Long, Item> itemRateMap = order.getItems().stream().collect(Collectors.toMap(Item::getId, Function.identity()));
        return Item.builder()
                .id(itemUpdateRequest.getId())
                .comment(itemUpdateRequest.getComment())
                .name(itemUpdateRequest.getName())
                .purity(itemUpdateRequest.getPurity())
                .weight(itemUpdateRequest.getWeight())
                .status(itemUpdateRequest.getStatus())
                .rate(itemRateMap.get(itemUpdateRequest.getId()) != null ? itemRateMap.get(itemUpdateRequest.getId()).getRate() : null)
                .cost(mapToCost(itemUpdateRequest.getCost(), itemUpdateRequest.getId()))
                .build();
    }

    private Cost mapToCost(CostUpdateRequest costUpdateRequest, Long itemId){
        return Cost.builder()
                .id(costUpdateRequest.getId())
                .jyala(costUpdateRequest.getJyala())
                .jartiQuantity(costUpdateRequest.getJartiQuantity())
                .itemId(itemId)
                .build();
    }

    public List<OrderResponse> mapToResponses(List<Order> orders){
        return orders.stream().map(this::mapToResponse).toList();
    }

    public OrderResponse mapToResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .dueDate(order.getDueDate())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .items(mapToItemResponses(order.getItems()))
                .totalPrice(PriceCalculation.orderTotal(order))
                .build();
    }

    private List<ItemResponse> mapToItemResponses(List<Item> items){
        return items.stream().map(this::mapToItemResponse).toList();
    }

    private ItemResponse mapToItemResponse(Item item){
        return ItemResponse.builder()
                .id(item.getId())
                .rate(item.getRate())
                .status(item.getStatus())
                .purity(item.getPurity())
                .weight(item.getWeight())
                .comment(item.getComment())
                .status(item.getStatus())
                .name(item.getName())
                .stones(mapToStoneResponses(item.getStones()))
                .total(PriceCalculation.calculateItemTotal(item))
                .itemType(item.getItemType())
                .costResponse(mapToCostResponse(item.getCost()))
                .build();
    }

    private CostResponse mapToCostResponse(Cost cost){
        return CostResponse.builder()
                .id(cost.getId())
                .itemId(cost.getItemId())
                .jartiQuantity(cost.getJartiQuantity())
                .jyala(cost.getJyala())
                .build();
    }

    private List<StoneResponse> mapToStoneResponses(List<Stone> stones){
        if(CollectionUtils.isEmpty(stones)){
            return null;
        }
        return stones.stream().map(this::mapToStoneResponse).toList();
    }

    private StoneResponse mapToStoneResponse(Stone stone){
        StoneType stoneType = getStoneTypeByIdUseCaseService.execute(stone.getStoneTypeId());
        return StoneResponse.builder()
                .id(stone.getId())
                .quantity(stone.getQuantity())
                .price(stone.getPrice())
                .stoneType(stoneType)
                .itemId(stone.getItemId())
                .build();
    }

    private List<Item> mapToItems(List<ItemCreateRequest> items, ItemStatus itemStatus, Long createdBy){
        return items.stream().map(item -> mapToItemDomain(item, itemStatus, createdBy)).toList();
    }

    private Item mapToItemDomain(ItemCreateRequest item, ItemStatus itemStatus, Long createdBy){
        return Item.builder()
                .comment(item.getComment())
                .name(item.getName())
                .lastModifiedDate(Instant.now())
                .status(itemStatus)
                .purity(item.getPurity())
                .weight(item.getWeight())
                .rate(item.getRate().divide(RATE_PER_GRAM,2, RoundingMode.HALF_UP))
                .itemType(item.getItemType())
                .cost(mapToCost(item.getCost()))
                .createdBy(createdBy)
                .lastModifiedBy(createdBy)
                .stones(mapToStones(item.getStones()))
                .build();
    }

    private List<Stone> mapToStones(List<StoneCreateRequest> stoneRequests){
        if(CollectionUtils.isEmpty(stoneRequests)){
            return null;
        }
        return stoneRequests.stream().map(this::mapToStone).toList();
    }

    private Stone mapToStone(StoneCreateRequest payload){
        return Stone.builder()
                .price(payload.getPrice())
                .quantity(payload.getQuantity())
                .stoneTypeId(payload.getStoneTypeId())
                .build();
    }

    private Cost mapToCost(CostCreateRequest request){
        return Cost.builder()
                .jyala(request.getJyala())
                .jartiQuantity(request.getJartiQuantity())
                .build();
    }

}
