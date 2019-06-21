package pos.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.dto.ItemDTO;
import pos.entity.Item;
import pos.repository.ItemRepository;
import pos.service.custom.ItemService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDTO> getAllItems(){

        List<ItemDTO> items = itemRepository.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
        return items;

    }

    public String saveItem(ItemDTO item)  {

        Item save = itemRepository.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        return save.getCode();

    }

    public void updateItem(ItemDTO item){

        itemRepository.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

    }

    public void deleteItem(String code){

        itemRepository.deleteById(code);

    }

    public ItemDTO getItemById(String code){

        Item item = itemRepository.getOne(code);
        ItemDTO itemDTO = new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
        return itemDTO;

    }

    @Override
    public boolean isItemExists(String code) {

        return itemRepository.existsById(code);
    }

}
