package com.luciofdasilva.shoppingapi.converter;

import java.util.ArrayList;

import com.luciofdasilva.shoppingapi.models.Item;
import com.luciofdasilva.shoppingapi.models.Shop;
import com.luciofdasilva.shoppingclient.dto.ItemDTO;
import com.luciofdasilva.shoppingclient.dto.ShopDTO;

public class DTOConverter {
    
    public static ItemDTO convert(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        if(shopDTO.getItems() == null)
            shopDTO.setItems(new ArrayList<ItemDTO>());
        for (Item item : shop.getItems()) {
            shopDTO.getItems().add(DTOConverter.convert(item));
        }
        return shopDTO;
    }

}
