package com.rankingsystem.backend.web.controllers.api;

import com.rankingsystem.backend.services.AllianceService;
import com.rankingsystem.backend.services.PlayerService;
import com.rankingsystem.backend.web.model.AllianceBasicData;
import com.rankingsystem.backend.web.model.AllianceBasicDataList;
import com.rankingsystem.backend.web.model.PlayerBasicData;
import com.rankingsystem.backend.web.model.PlayerBasicDataList;
import com.rankingsystem.backend.web.model.pagination.ObjectPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("/api/v1/public/")
@RestController
public class PublicController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 2;
    private final PlayerService playerService;
    private final AllianceService  allianceService;

    @GetMapping(path = "allianceList", produces = "application/json")
    public ResponseEntity<ObjectPagedList<AllianceBasicData>> listTopAlliances(@RequestParam(value="page", required = false) Integer page,
                                                                               @RequestParam(value = "size", required = false) Integer size,
                                                                               @RequestParam(value="sortBy", required = false) String sortBy) {
        if(page == null || page < 0) {
            page = DEFAULT_PAGE_NUMBER;
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(size == null || size < 1) {
            size = DEFAULT_PAGE_SIZE;
        }
        if(sortBy != null){
            return new ResponseEntity<>(allianceService.listTopAlliances(PageRequest.of(page, size, Sort.by(sortBy).descending())),
                    HttpStatus.OK);
            //handle error if sortBy does not exist! Might not be an issue as developers should know what they want.
        }
        return new ResponseEntity<>(allianceService.alliancesPaginated(PageRequest.of(page, size)), HttpStatus.OK);

    }

    @GetMapping(path = "playerList", produces = "application/json")
    public ResponseEntity<ObjectPagedList<PlayerBasicData>> playerPagination(@RequestParam(value="page", required = false) Integer page,
                                                             @RequestParam(value = "size", required = false) Integer size,
                                                             @RequestParam(value="sortBy", required = false) String sortBy) {

        if(page == null || page < 0) {
            page = DEFAULT_PAGE_NUMBER;
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(size == null || size < 1) {
            size = DEFAULT_PAGE_SIZE;
        }
        if(sortBy != null){
            return new ResponseEntity<>(playerService.playersPaginated(PageRequest.of(page, size, Sort.by(sortBy).descending())),
                    HttpStatus.OK);
            //handle error if sortBy does not exist! Might not be an issue as developers should know what they want.
        }
        return new ResponseEntity<>(playerService.playersPaginated(PageRequest.of(page, size)), HttpStatus.OK);
    }
}
