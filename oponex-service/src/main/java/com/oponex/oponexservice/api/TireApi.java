package com.oponex.oponexservice.api;

import com.oponex.oponexservice.config.exception.TireAlreadyExistException;
import com.oponex.oponexservice.config.exception.TireHasEmptyFieldException;
import com.oponex.oponexservice.config.exception.TireNotFoundException;
import com.oponex.oponexservice.model.TireDTO;
import com.oponex.oponexservice.model.TireSearchDTO;
import com.oponex.oponexservice.model.dao.Tire;
import com.oponex.oponexservice.service.TireService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tire")
@RequiredArgsConstructor
public class TireApi {

    private final TireService tireService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<Tire> getTire(@PathVariable("id") Long id) throws TireNotFoundException {
        return ResponseEntity.ok(tireService.getTire(id));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Page<Tire>> getTires(@RequestBody TireSearchDTO tireSearchDTO,
                                               @RequestBody Pageable pageable) {
        return ResponseEntity.ok(tireService.getTires(tireSearchDTO, pageable));
    }

    @PostMapping
    public ResponseEntity<Tire> createTire(@RequestBody TireDTO tireDTO) throws TireAlreadyExistException, TireHasEmptyFieldException {
        return new ResponseEntity<>(tireService.saveTire(tireDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Tire> deleteTire(@PathVariable("id") Long id) throws TireNotFoundException {
        return ResponseEntity.ok(tireService.deleteTire(id));
    }
}
