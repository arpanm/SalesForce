package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.RetailerMapping;
import com.ril.digital.salesforce.repository.RetailerMappingRepository;
import com.ril.digital.salesforce.service.RetailerMappingService;
import com.ril.digital.salesforce.service.dto.RetailerMappingDTO;
import com.ril.digital.salesforce.service.mapper.RetailerMappingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RetailerMapping}.
 */
@Service
@Transactional
public class RetailerMappingServiceImpl implements RetailerMappingService {

    private final Logger log = LoggerFactory.getLogger(RetailerMappingServiceImpl.class);

    private final RetailerMappingRepository retailerMappingRepository;

    private final RetailerMappingMapper retailerMappingMapper;

    public RetailerMappingServiceImpl(RetailerMappingRepository retailerMappingRepository, RetailerMappingMapper retailerMappingMapper) {
        this.retailerMappingRepository = retailerMappingRepository;
        this.retailerMappingMapper = retailerMappingMapper;
    }

    @Override
    public RetailerMappingDTO save(RetailerMappingDTO retailerMappingDTO) {
        log.debug("Request to save RetailerMapping : {}", retailerMappingDTO);
        RetailerMapping retailerMapping = retailerMappingMapper.toEntity(retailerMappingDTO);
        retailerMapping = retailerMappingRepository.save(retailerMapping);
        return retailerMappingMapper.toDto(retailerMapping);
    }

    @Override
    public RetailerMappingDTO update(RetailerMappingDTO retailerMappingDTO) {
        log.debug("Request to update RetailerMapping : {}", retailerMappingDTO);
        RetailerMapping retailerMapping = retailerMappingMapper.toEntity(retailerMappingDTO);
        retailerMapping = retailerMappingRepository.save(retailerMapping);
        return retailerMappingMapper.toDto(retailerMapping);
    }

    @Override
    public Optional<RetailerMappingDTO> partialUpdate(RetailerMappingDTO retailerMappingDTO) {
        log.debug("Request to partially update RetailerMapping : {}", retailerMappingDTO);

        return retailerMappingRepository
            .findById(retailerMappingDTO.getId())
            .map(existingRetailerMapping -> {
                retailerMappingMapper.partialUpdate(existingRetailerMapping, retailerMappingDTO);

                return existingRetailerMapping;
            })
            .map(retailerMappingRepository::save)
            .map(retailerMappingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RetailerMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RetailerMappings");
        return retailerMappingRepository.findAll(pageable).map(retailerMappingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RetailerMappingDTO> findOne(Long id) {
        log.debug("Request to get RetailerMapping : {}", id);
        return retailerMappingRepository.findById(id).map(retailerMappingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RetailerMapping : {}", id);
        retailerMappingRepository.deleteById(id);
    }
}
