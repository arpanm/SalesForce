package com.ril.digital.salesforce.service.impl;

import com.ril.digital.salesforce.domain.AchivementCache;
import com.ril.digital.salesforce.repository.AchivementCacheRepository;
import com.ril.digital.salesforce.service.AchivementCacheService;
import com.ril.digital.salesforce.service.dto.AchivementCacheDTO;
import com.ril.digital.salesforce.service.mapper.AchivementCacheMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AchivementCache}.
 */
@Service
@Transactional
public class AchivementCacheServiceImpl implements AchivementCacheService {

    private final Logger log = LoggerFactory.getLogger(AchivementCacheServiceImpl.class);

    private final AchivementCacheRepository achivementCacheRepository;

    private final AchivementCacheMapper achivementCacheMapper;

    public AchivementCacheServiceImpl(AchivementCacheRepository achivementCacheRepository, AchivementCacheMapper achivementCacheMapper) {
        this.achivementCacheRepository = achivementCacheRepository;
        this.achivementCacheMapper = achivementCacheMapper;
    }

    @Override
    public AchivementCacheDTO save(AchivementCacheDTO achivementCacheDTO) {
        log.debug("Request to save AchivementCache : {}", achivementCacheDTO);
        AchivementCache achivementCache = achivementCacheMapper.toEntity(achivementCacheDTO);
        achivementCache = achivementCacheRepository.save(achivementCache);
        return achivementCacheMapper.toDto(achivementCache);
    }

    @Override
    public AchivementCacheDTO update(AchivementCacheDTO achivementCacheDTO) {
        log.debug("Request to update AchivementCache : {}", achivementCacheDTO);
        AchivementCache achivementCache = achivementCacheMapper.toEntity(achivementCacheDTO);
        achivementCache = achivementCacheRepository.save(achivementCache);
        return achivementCacheMapper.toDto(achivementCache);
    }

    @Override
    public Optional<AchivementCacheDTO> partialUpdate(AchivementCacheDTO achivementCacheDTO) {
        log.debug("Request to partially update AchivementCache : {}", achivementCacheDTO);

        return achivementCacheRepository
            .findById(achivementCacheDTO.getId())
            .map(existingAchivementCache -> {
                achivementCacheMapper.partialUpdate(existingAchivementCache, achivementCacheDTO);

                return existingAchivementCache;
            })
            .map(achivementCacheRepository::save)
            .map(achivementCacheMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AchivementCacheDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AchivementCaches");
        return achivementCacheRepository.findAll(pageable).map(achivementCacheMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AchivementCacheDTO> findOne(Long id) {
        log.debug("Request to get AchivementCache : {}", id);
        return achivementCacheRepository.findById(id).map(achivementCacheMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AchivementCache : {}", id);
        achivementCacheRepository.deleteById(id);
    }
}
