package pos.service.custom;

import pos.dto.ItemDTO;
import pos.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {

    public List<ItemDTO> getAllItems();

    public String saveItem(ItemDTO item);

    public void updateItem(ItemDTO item);

    public void deleteItem(String code);

    public ItemDTO getItemById(String code);

    public boolean isItemExists(String code);

}
