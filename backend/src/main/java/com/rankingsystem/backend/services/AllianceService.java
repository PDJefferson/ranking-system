package com.rankingsystem.backend.services;

import com.rankingsystem.backend.web.model.AllianceBasicData;
import com.rankingsystem.backend.web.model.AllianceBasicDataList;
import com.rankingsystem.backend.web.model.pagination.ObjectPagedList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AllianceService {

  ObjectPagedList<AllianceBasicData> alliancesPaginated(Pageable pageable);

  ObjectPagedList<AllianceBasicData> listTopAlliances(Pageable pageable);
}
