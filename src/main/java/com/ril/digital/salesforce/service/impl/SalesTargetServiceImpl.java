package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.SalesTarget;
import com.ril.digital.salesforce.repository.SalesTargetRepository;
import com.ril.digital.salesforce.service.SalesTargetService;
import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
import com.ril.digital.salesforce.service.mapper.SalesTargetMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SalesTarget}.
 */
@Service
@Transactional
public class SalesTargetServiceImpl implements SalesTargetService {

    private final Logger log = LoggerFactory.getLogger(SalesTargetServiceImpl.class);

    private final SalesTargetRepository salesTargetRepository;

    private final SalesTargetMapper salesTargetMapper;

    public SalesTargetServiceImpl(SalesTargetRepository salesTargetRepository, SalesTargetMapper salesTargetMapper) {
        this.salesTargetRepository = salesTargetRepository;
        this.salesTargetMapper = salesTargetMapper;
    }

    @Override
    public SalesTargetDTO save(SalesTargetDTO salesTargetDTO) {
        log.debug("Request to save SalesTarget : {}", salesTargetDTO);
        SalesTarget salesTarget = salesTargetMapper.toEntity(salesTargetDTO);
        salesTarget = salesTargetRepository.save(salesTarget);
        return salesTargetMapper.toDto(salesTarget);
    }

    @Override
    public SalesTargetDTO update(SalesTargetDTO salesTargetDTO) {
        log.debug("Request to update SalesTarget : {}", salesTargetDTO);
        SalesTarget salesTarget = salesTargetMapper.toEntity(salesTargetDTO);
        salesTarget = salesTargetRepository.save(salesTarget);
        return salesTargetMapper.toDto(salesTarget);
    }

    @Override
    public Optional<SalesTargetDTO> partialUpdate(SalesTargetDTO salesTargetDTO) {
        log.debug("Request to partially update SalesTarget : {}", salesTargetDTO);

        return salesTargetRepository
            .findById(salesTargetDTO.getId())
            .map(existingSalesTarget -> {
                salesTargetMapper.partialUpdate(existingSalesTarget, salesTargetDTO);

                return existingSalesTarget;
            })
            .map(salesTargetRepository::save)
            .map(salesTargetMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesTargetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesTargets");
        return salesTargetRepository.findAll(pageable).map(salesTargetMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesTargetDTO> findOne(Long id) {
        log.debug("Request to get SalesTarget : {}", id);
        return salesTargetRepository.findById(id).map(salesTargetMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesTarget : {}", id);
        salesTargetRepository.deleteById(id);
    }
}
