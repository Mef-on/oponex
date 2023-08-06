package com.oponex.oponexservice.service;

import com.oponex.oponexservice.config.exception.TireAlreadyExistException;
import com.oponex.oponexservice.config.exception.TireHasEmptyFieldException;
import com.oponex.oponexservice.config.exception.TireNotFoundException;
import com.oponex.oponexservice.model.TireDTO;
import com.oponex.oponexservice.model.TireSearchDTO;
import com.oponex.oponexservice.model.dao.Tire;
import com.oponex.oponexservice.repo.TireRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TireService {

    private final TireRepository repo;
    private final ModelMapper mapper;
    private final Validator validator;

    public Tire saveTire(TireDTO tireDTO) throws TireAlreadyExistException, TireHasEmptyFieldException {
        if (repo.existsByProperties(tireDTO))
            throw new TireAlreadyExistException(String.format("This tire %s is already in the list.", tireDTO.name()));

        Tire tire = mapper.map(tireDTO, Tire.class);
        Set<ConstraintViolation<Tire>> violations = validator.validate(tire);

        if (!violations.isEmpty()) {
            throw new TireHasEmptyFieldException(violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
        return repo.save(tire);
    }

    public Tire deleteTire(Long id) throws TireNotFoundException {
        Tire tire = getTire(id);
        repo.delete(tire);
        return tire;
    }

    public Tire getTire(Long id) throws TireNotFoundException {
        return repo.findById(id).orElseThrow(() -> new TireNotFoundException(String.format("Not found Tire with ID %s", id)));
    }

    public Page<Tire> getTires(TireSearchDTO tireSearchDTO, Pageable pageable) {
        return repo.findAll(new TireSpecification(tireSearchDTO), pageable);
    }
}
