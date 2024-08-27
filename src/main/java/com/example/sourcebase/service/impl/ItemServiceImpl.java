package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Item;
import com.example.sourcebase.domain.dto.reqdto.ItemReqDto;
import com.example.sourcebase.domain.dto.resdto.ListItemResDto;
import com.example.sourcebase.domain.dto.resdto.ItemResDto;
import com.example.sourcebase.mapper.ItemMapper;
import com.example.sourcebase.repository.IItemRepository;
import com.example.sourcebase.service.IItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements IItemService {

    private IItemRepository itemRepository;

    private ItemMapper itemMapper = ItemMapper.INSTANCE;

    @Override
    public ItemResDto findById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        return itemMapper.toResDto(item);
    }

    @Override
    public ItemResDto add(ItemReqDto itemReqDto) {
        return null;
    }

    @Override
    public boolean update(Long id, ItemReqDto reqDto) {
        return false;
    }

    @Override
    public boolean deletedById(Long id) {
        return false;
    }

    @Override
    public Page<ListItemResDto> findAllWithSearch(String search, Pageable pageable) {
        return null;
    }
}
