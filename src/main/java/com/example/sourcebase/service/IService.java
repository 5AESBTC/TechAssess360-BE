package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.ReqDto;
import com.example.sourcebase.domain.dto.resdto.ListResDto;
import com.example.sourcebase.domain.dto.resdto.ResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService {
    ResDto findById(Long id);
    ResDto add(ResDto resDto);
    boolean update(Long id, ReqDto reqDto);
    boolean deletedById(Long id);
    Page<ListResDto> findAllWithSearch(String search, Pageable pageable);
//    List<ListResDto> getAllByCategoryId(Long id);
}
