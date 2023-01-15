package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.IncentiveScheme;
import com.ril.digital.salesforce.repository.IncentiveSchemeRepository;
import com.ril.digital.salesforce.service.IncentiveSchemeService;
import com.ril.digital.salesforce.service.dto.IncentiveSchemeDTO;
import com.ril.digital.salesforce.service.mapper.IncentiveSchemeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link IncentiveScheme}.
 */
@Service
@Transactional
public class IncentiveSchemeServiceImpl implements IncentiveSchemeService {

    private final Logger log = LoggerFactory.getLogger(IncentiveSchemeServiceImpl.class);

    private final IncentiveSchemeRepository incentiveSchemeRepository;

    private final IncentiveSchemeMapper incentiveSchemeMapper;

    public IncentiveSchemeServiceImpl(IncentiveSchemeRepository incentiveSchemeRepository, IncentiveSchemeMapper incentiveSchemeMapper) {
        this.incentiveSchemeRepository = incentiveSchemeRepository;
        this.incentiveSchemeMapper = incentiveSchemeMapper;
    }

    @Override
    public IncentiveSchemeDTO save(IncentiveSchemeDTO incentiveSchemeDTO) {
        log.debug("Request to save IncentiveScheme : {}", incentiveSchemeDTO);
        IncentiveScheme incentiveScheme = incentiveSchemeMapper.toEntity(incentiveSchemeDTO);
        incentiveScheme = incentiveSchemeRepository.save(incentiveScheme);
        return incentiveSchemeMapper.toDto(incentiveScheme);
    }

    @Override
    public IncentiveSchemeDTO update(IncentiveSchemeDTO incentiveSchemeDTO) {
        log.debug("Request to update IncentiveScheme : {}", incentiveSchemeDTO);
        IncentiveScheme incentiveScheme = incentiveSchemeMapper.toEntity(incentiveSchemeDTO);
        incentiveScheme = incentiveSchemeRepository.save(incentiveScheme);
        return incentiveSchemeMapper.toDto(incentiveScheme);
    }

    @Override
    public Optional<IncentiveSchemeDTO> partialUpdate(IncentiveSchemeDTO incentiveSchemeDTO) {
        log.debug("Request to partially update IncentiveScheme : {}", incentiveSchemeDTO);

        return incentiveSchemeRepository
            .findById(incentiveSchemeDTO.getId())
            .map(existingIncentiveScheme -> {
                incentiveSchemeMapper.partialUpdate(existingIncentiveScheme, incentiveSchemeDTO);

                return existingIncentiveScheme;
            })
            .map(incentiveSchemeRepository::save)
            .map(incentiveSchemeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncentiveSchemeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IncentiveSchemes");
        return incentiveSchemeRepository.findAll(pageable).map(incentiveSchemeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncentiveSchemeDTO> findOne(Long id) {
        log.debug("Request to get IncentiveScheme : {}", id);
        return incentiveSchemeRepository.findById(id).map(incentiveSchemeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete IncentiveScheme : {}", id);
        incentiveSchemeRepository.deleteById(id);
    }
}
