package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.dto.reqdto.ReqDto;
import com.example.sourcebase.domain.dto.resdto.ListResDto;
import com.example.sourcebase.domain.dto.resdto.ResDto;
import com.example.sourcebase.service.IService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {

    @Override
    public ResDto findById(Long id) {
        return null;
    }

    @Override
    public ResDto add(ResDto resDto) {
        return null;
    }

    @Override
    public boolean update(Long id, ReqDto reqDto) {
        return false;
    }

    @Override
    public boolean deletedById(Long id) {
        return false;
    }

    @Override
    public Page<ListResDto> findAllWithSearch(String search, Pageable pageable) {
        return null;
    }
}
