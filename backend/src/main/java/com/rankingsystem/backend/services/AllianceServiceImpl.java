package com.rankingsystem.backend.services;

import com.rankingsystem.backend.domain.Alliance;
import com.rankingsystem.backend.repositories.AllianceRepository;
import com.rankingsystem.backend.web.mappers.AllianceBasicDataMapper;
import com.rankingsystem.backend.web.model.AllianceBasicData;
import com.rankingsystem.backend.web.model.AllianceBasicDataList;
import com.rankingsystem.backend.web.model.PlayerBasicDataList;
import com.rankingsystem.backend.web.model.pagination.ObjectPagedList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AllianceServiceImpl implements AllianceService {

    private final AllianceRepository allianceRepository;
    private final AllianceBasicDataMapper allianceBasicDataMapper;

    @Override
    public ObjectPagedList<AllianceBasicData> alliancesPaginated(Pageable pageable) {
        Page<Alliance> alliancePage = allianceRepository.findAll(pageable);
        return new ObjectPagedList<>(alliancePage
                .stream()
                .map(allianceBasicDataMapper::allianceToAllianceBasicData)
                .collect(Collectors.toList()),
                PageRequest.of(
                        alliancePage.getPageable().getPageNumber(),
                        alliancePage.getPageable().getPageSize()),
                alliancePage.getTotalElements());
    }

    @Override
    public ObjectPagedList<AllianceBasicData> listTopAlliances(Pageable pageable) {
        Page<Alliance> alliancePage = allianceRepository.findAll(pageable);
        return new ObjectPagedList<>(alliancePage
                .stream()
                .map(allianceBasicDataMapper::allianceToAllianceBasicData)
                .collect(Collectors.toList()),
                PageRequest.of(
                        alliancePage.getPageable().getPageNumber(),
                        alliancePage.getPageable().getPageSize(),
                        alliancePage.getSort()),
                alliancePage.getTotalElements());
    }

}
