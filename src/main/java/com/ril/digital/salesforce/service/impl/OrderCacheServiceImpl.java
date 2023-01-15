package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.OrderCache;
import com.ril.digital.salesforce.repository.OrderCacheRepository;
import com.ril.digital.salesforce.service.OrderCacheService;
import com.ril.digital.salesforce.service.dto.OrderCacheDTO;
import com.ril.digital.salesforce.service.mapper.OrderCacheMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OrderCache}.
 */
@Service
@Transactional
public class OrderCacheServiceImpl implements OrderCacheService {

    private final Logger log = LoggerFactory.getLogger(OrderCacheServiceImpl.class);

    private final OrderCacheRepository orderCacheRepository;

    private final OrderCacheMapper orderCacheMapper;

    public OrderCacheServiceImpl(OrderCacheRepository orderCacheRepository, OrderCacheMapper orderCacheMapper) {
        this.orderCacheRepository = orderCacheRepository;
        this.orderCacheMapper = orderCacheMapper;
    }

    @Override
    public OrderCacheDTO save(OrderCacheDTO orderCacheDTO) {
        log.debug("Request to save OrderCache : {}", orderCacheDTO);
        OrderCache orderCache = orderCacheMapper.toEntity(orderCacheDTO);
        orderCache = orderCacheRepository.save(orderCache);
        return orderCacheMapper.toDto(orderCache);
    }

    @Override
    public OrderCacheDTO update(OrderCacheDTO orderCacheDTO) {
        log.debug("Request to update OrderCache : {}", orderCacheDTO);
        OrderCache orderCache = orderCacheMapper.toEntity(orderCacheDTO);
        orderCache = orderCacheRepository.save(orderCache);
        return orderCacheMapper.toDto(orderCache);
    }

    @Override
    public Optional<OrderCacheDTO> partialUpdate(OrderCacheDTO orderCacheDTO) {
        log.debug("Request to partially update OrderCache : {}", orderCacheDTO);

        return orderCacheRepository
            .findById(orderCacheDTO.getId())
            .map(existingOrderCache -> {
                orderCacheMapper.partialUpdate(existingOrderCache, orderCacheDTO);

                return existingOrderCache;
            })
            .map(orderCacheRepository::save)
            .map(orderCacheMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderCacheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderCaches");
        return orderCacheRepository.findAll(pageable).map(orderCacheMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderCacheDTO> findOne(Long id) {
        log.debug("Request to get OrderCache : {}", id);
        return orderCacheRepository.findById(id).map(orderCacheMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderCache : {}", id);
        orderCacheRepository.deleteById(id);
    }
}
