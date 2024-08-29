package com.example.sourcebase.mapper;

import com.example.sourcebase.domain.Item;
import com.example.sourcebase.domain.dto.reqdto.ItemReqDTO;
import com.example.sourcebase.domain.dto.resdto.ItemResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
<<<<<<< HEAD

=======
>>>>>>> e96da83 (update source base)
    Item reqDtoToItem(ItemReqDTO reqDTO);
    ItemResDTO toResDTO(Item item);
}
