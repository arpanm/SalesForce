package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.SalesTargetFilter;
import com.ril.digital.salesforce.repository.SalesTargetFilterRepository;
import com.ril.digital.salesforce.service.SalesTargetFilterService;
import com.ril.digital.salesforce.service.dto.SalesTargetFilterDTO;
import com.ril.digital.salesforce.service.mapper.SalesTargetFilterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SalesTargetFilter}.
 */
@Service
@Transactional
public class SalesTargetFilterServiceImpl implements SalesTargetFilterService {

    private final Logger log = LoggerFactory.getLogger(SalesTargetFilterServiceImpl.class);

    private final SalesTargetFilterRepository salesTargetFilterRepository;

    private final SalesTargetFilterMapper salesTargetFilterMapper;

    public SalesTargetFilterServiceImpl(
        SalesTargetFilterRepository salesTargetFilterRepository,
        SalesTargetFilterMapper salesTargetFilterMapper
    ) {
        this.salesTargetFilterRepository = salesTargetFilterRepository;
        this.salesTargetFilterMapper = salesTargetFilterMapper;
    }

    @Override
    public SalesTargetFilterDTO save(SalesTargetFilterDTO salesTargetFilterDTO) {
        log.debug("Request to save SalesTargetFilter : {}", salesTargetFilterDTO);
        SalesTargetFilter salesTargetFilter = salesTargetFilterMapper.toEntity(salesTargetFilterDTO);
        salesTargetFilter = salesTargetFilterRepository.save(salesTargetFilter);
        return salesTargetFilterMapper.toDto(salesTargetFilter);
    }

    @Override
    public SalesTargetFilterDTO update(SalesTargetFilterDTO salesTargetFilterDTO) {
        log.debug("Request to update SalesTargetFilter : {}", salesTargetFilterDTO);
        SalesTargetFilter salesTargetFilter = salesTargetFilterMapper.toEntity(salesTargetFilterDTO);
        salesTargetFilter = salesTargetFilterRepository.save(salesTargetFilter);
        return salesTargetFilterMapper.toDto(salesTargetFilter);
    }

    @Override
    public Optional<SalesTargetFilterDTO> partialUpdate(SalesTargetFilterDTO salesTargetFilterDTO) {
        log.debug("Request to partially update SalesTargetFilter : {}", salesTargetFilterDTO);

        return salesTargetFilterRepository
            .findById(salesTargetFilterDTO.getId())
            .map(existingSalesTargetFilter -> {
                salesTargetFilterMapper.partialUpdate(existingSalesTargetFilter, salesTargetFilterDTO);

                return existingSalesTargetFilter;
            })
            .map(salesTargetFilterRepository::save)
            .map(salesTargetFilterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesTargetFilterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesTargetFilters");
        return salesTargetFilterRepository.findAll(pageable).map(salesTargetFilterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesTargetFilterDTO> findOne(Long id) {
        log.debug("Request to get SalesTargetFilter : {}", id);
        return salesTargetFilterRepository.findById(id).map(salesTargetFilterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesTargetFilter : {}", id);
        salesTargetFilterRepository.deleteById(id);
    }
}
