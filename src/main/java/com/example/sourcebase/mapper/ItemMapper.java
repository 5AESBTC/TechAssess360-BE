package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Item;
import com.example.sourcebase.domain.dto.reqdto.ItemReqDto;
import com.example.sourcebase.domain.dto.resdto.ItemResDto;
import com.example.sourcebase.domain.dto.resdto.ListItemResDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item reqDtoToItem(ItemReqDto reqDto);
    ItemResDto toResDto(Item item);
    ListItemResDto toListItemResDto(Item item);
}
