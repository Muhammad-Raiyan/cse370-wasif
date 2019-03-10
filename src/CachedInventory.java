import java.util.HashMap;
import java.util.Map;

public class CachedInventory implements Inventory{

    private Map<String, ItemModel> inventoryMap;

    public CachedInventory() {
        inventoryMap = new HashMap<>();
    }

    @Override
    public int insert(ItemModel item) {
        String name = item.getName();
        if(inventoryMap.containsKey(name))
            return update(item);

        // Set id; human readable to starts from 1
        int id = inventoryMap.size()+1;
        item.setId(id);

        // Set timestamp
        item.setTimestamp();

        // Insert
        inventoryMap.put(name, item);
        return id;
    }

    @Override
    public int update(ItemModel item) {
        if ( !inventoryMap.containsKey(item.getName()))
            return insert(item);

        // Update
        ItemModel targetItem = inventoryMap.get(item.getName());
        targetItem.setQuantity(targetItem.getQuantity() + item.getQuantity());
        targetItem.setPrice(item.getPrice());
        targetItem.setPurchaseDate(item.getPurchaseDate());
        targetItem.setTimestamp();

        return targetItem.getId();
    }

    @Override
    public ItemModel deleteById(int id) throws IllegalArgumentException{
        ItemModel targetItem = null;

        // Find item
        for(ItemModel item : inventoryMap.values()){
            if(item.getId() == id){
                targetItem = item;
                break;
            }
        }

        // Delete
        if(targetItem == null)
            throw new IllegalArgumentException("Item with id " + id + " not found in inventory");
        inventoryMap.remove(targetItem.getName());

        return targetItem;
    }

    @Override
    public ItemModel deleteByName(String name) throws IllegalArgumentException{
        if(!inventoryMap.containsKey(name))
            throw new IllegalArgumentException(name + " not in inventory");
        ItemModel item = inventoryMap.get(name);
        inventoryMap.remove(name);
        return item;
    }
}
