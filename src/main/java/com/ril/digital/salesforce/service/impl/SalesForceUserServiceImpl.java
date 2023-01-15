package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.repository.SalesForceUserRepository;
import com.ril.digital.salesforce.service.SalesForceUserService;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import com.ril.digital.salesforce.service.mapper.SalesForceUserMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SalesForceUser}.
 */
@Service
@Transactional
public class SalesForceUserServiceImpl implements SalesForceUserService {

    private final Logger log = LoggerFactory.getLogger(SalesForceUserServiceImpl.class);

    private final SalesForceUserRepository salesForceUserRepository;

    private final SalesForceUserMapper salesForceUserMapper;

    public SalesForceUserServiceImpl(SalesForceUserRepository salesForceUserRepository, SalesForceUserMapper salesForceUserMapper) {
        this.salesForceUserRepository = salesForceUserRepository;
        this.salesForceUserMapper = salesForceUserMapper;
    }

    @Override
    public SalesForceUserDTO save(SalesForceUserDTO salesForceUserDTO) {
        log.debug("Request to save SalesForceUser : {}", salesForceUserDTO);
        SalesForceUser salesForceUser = salesForceUserMapper.toEntity(salesForceUserDTO);
        salesForceUser = salesForceUserRepository.save(salesForceUser);
        return salesForceUserMapper.toDto(salesForceUser);
    }

    @Override
    public SalesForceUserDTO update(SalesForceUserDTO salesForceUserDTO) {
        log.debug("Request to update SalesForceUser : {}", salesForceUserDTO);
        SalesForceUser salesForceUser = salesForceUserMapper.toEntity(salesForceUserDTO);
        salesForceUser = salesForceUserRepository.save(salesForceUser);
        return salesForceUserMapper.toDto(salesForceUser);
    }

    @Override
    public Optional<SalesForceUserDTO> partialUpdate(SalesForceUserDTO salesForceUserDTO) {
        log.debug("Request to partially update SalesForceUser : {}", salesForceUserDTO);

        return salesForceUserRepository
            .findById(salesForceUserDTO.getId())
            .map(existingSalesForceUser -> {
                salesForceUserMapper.partialUpdate(existingSalesForceUser, salesForceUserDTO);

                return existingSalesForceUser;
            })
            .map(salesForceUserRepository::save)
            .map(salesForceUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalesForceUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SalesForceUsers");
        return salesForceUserRepository.findAll(pageable).map(salesForceUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SalesForceUserDTO> findOne(Long id) {
        log.debug("Request to get SalesForceUser : {}", id);
        return salesForceUserRepository.findById(id).map(salesForceUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SalesForceUser : {}", id);
        salesForceUserRepository.deleteById(id);
    }
}
