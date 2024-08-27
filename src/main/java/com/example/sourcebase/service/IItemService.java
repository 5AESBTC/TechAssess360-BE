package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.ItemReqDto;
import com.example.sourcebase.domain.dto.resdto.ListItemResDto;
import com.example.sourcebase.domain.dto.resdto.ItemResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItemService {
    ItemResDto findById(Long id);
    ItemResDto add(ItemReqDto resDto);
    boolean update(Long id, ItemReqDto reqDto);
    boolean deletedById(Long id);
    Page<ListItemResDto> findAllWithSearch(String search, Pageable pageable);
//    List<ListResDto> getAllByCategoryId(Long id);
}
