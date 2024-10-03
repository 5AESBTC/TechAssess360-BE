package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Criteria;
import com.example.sourcebase.domain.dto.reqdto.CriteriaReqDTO;
import com.example.sourcebase.domain.dto.resdto.CriteriaResDTO;
import com.example.sourcebase.mapper.CriteriaMapper;
import com.example.sourcebase.repository.ICriteriaRepository;
import com.example.sourcebase.service.ICriteriaService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CriteriaServiceImpl implements ICriteriaService {

    @Autowired
    ICriteriaRepository criteriaRepository;
    CriteriaMapper criteriaMapper = CriteriaMapper.INSTANCE;

    @Override
    public List<CriteriaResDTO> getAllCriterias() {
        List<Criteria> criteriaResDTOS = criteriaRepository.findAll();
        return criteriaResDTOS.stream()
                .map(criteriaMapper::toCriteriaResDTO)
                .collect(Collectors.toList());
    }

}
