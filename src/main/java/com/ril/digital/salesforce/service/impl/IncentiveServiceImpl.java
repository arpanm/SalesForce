package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.Incentive;
import com.ril.digital.salesforce.repository.IncentiveRepository;
import com.ril.digital.salesforce.service.IncentiveService;
import com.ril.digital.salesforce.service.dto.IncentiveDTO;
import com.ril.digital.salesforce.service.mapper.IncentiveMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Incentive}.
 */
@Service
@Transactional
public class IncentiveServiceImpl implements IncentiveService {

    private final Logger log = LoggerFactory.getLogger(IncentiveServiceImpl.class);

    private final IncentiveRepository incentiveRepository;

    private final IncentiveMapper incentiveMapper;

    public IncentiveServiceImpl(IncentiveRepository incentiveRepository, IncentiveMapper incentiveMapper) {
        this.incentiveRepository = incentiveRepository;
        this.incentiveMapper = incentiveMapper;
    }

    @Override
    public IncentiveDTO save(IncentiveDTO incentiveDTO) {
        log.debug("Request to save Incentive : {}", incentiveDTO);
        Incentive incentive = incentiveMapper.toEntity(incentiveDTO);
        incentive = incentiveRepository.save(incentive);
        return incentiveMapper.toDto(incentive);
    }

    @Override
    public IncentiveDTO update(IncentiveDTO incentiveDTO) {
        log.debug("Request to update Incentive : {}", incentiveDTO);
        Incentive incentive = incentiveMapper.toEntity(incentiveDTO);
        incentive = incentiveRepository.save(incentive);
        return incentiveMapper.toDto(incentive);
    }

    @Override
    public Optional<IncentiveDTO> partialUpdate(IncentiveDTO incentiveDTO) {
        log.debug("Request to partially update Incentive : {}", incentiveDTO);

        return incentiveRepository
            .findById(incentiveDTO.getId())
            .map(existingIncentive -> {
                incentiveMapper.partialUpdate(existingIncentive, incentiveDTO);

                return existingIncentive;
            })
            .map(incentiveRepository::save)
            .map(incentiveMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncentiveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Incentives");
        return incentiveRepository.findAll(pageable).map(incentiveMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncentiveDTO> findOne(Long id) {
        log.debug("Request to get Incentive : {}", id);
        return incentiveRepository.findById(id).map(incentiveMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Incentive : {}", id);
        incentiveRepository.deleteById(id);
    }
}
