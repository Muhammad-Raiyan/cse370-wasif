public interface Inventory {

    int insert(ItemModel item);
    int update(ItemModel item);
    ItemModel deleteById(int id);
    ItemModel deleteByName(String name);
}
