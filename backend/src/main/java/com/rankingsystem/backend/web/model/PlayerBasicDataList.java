package com.rankingsystem.backend.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerBasicDataList {

    private List<PlayerBasicData> players = new ArrayList<>();
}
