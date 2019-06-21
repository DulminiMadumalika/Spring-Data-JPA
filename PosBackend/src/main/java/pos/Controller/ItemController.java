package pos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.dto.ItemDTO;
import pos.service.custom.ItemService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<ItemDTO> getAllItems(){
        return itemService.getAllItems();
    }

    @GetMapping(value = "/{id:Item\\d{3}}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDTO> getItem(@PathVariable("id") String id){

        ItemDTO itemDTO = itemService.getItemById(id);
        return new ResponseEntity<ItemDTO>(itemDTO, (itemDTO != null)? HttpStatus.OK: HttpStatus.NOT_FOUND );

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveItem(@RequestBody ItemDTO itemDTO){

        if(itemDTO.getCode().isEmpty() || itemDTO.getDescription().isEmpty()){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }else{

            if(itemService.isItemExists(itemDTO.getCode())){
                throw new RuntimeException("This item is already exists");
            }else{
                String code = itemService.saveItem(itemDTO);
                return new ResponseEntity<String>("\""+code+"\"",HttpStatus.CREATED);
            }
        }
    }

    @PutMapping(path = "/{code:Item\\d{3}}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateItem(@PathVariable("code") String code,@RequestBody ItemDTO itemDTO){
        if(!itemService.isItemExists(code)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        if(code.isEmpty() || itemDTO.getDescription().isEmpty()){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }else{
            itemDTO.setCode(code);
            itemService.updateItem(itemDTO);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping(path = "/{code:Item\\d{3}}")
    public ResponseEntity<Void> deleteItem(@PathVariable("code") String code){

        if(!itemService.isItemExists(code)){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        itemService.deleteItem(code);
        return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
