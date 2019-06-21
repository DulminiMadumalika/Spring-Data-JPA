package pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.entity.Item;

public interface ItemRepository extends JpaRepository<Item, String> {

}
