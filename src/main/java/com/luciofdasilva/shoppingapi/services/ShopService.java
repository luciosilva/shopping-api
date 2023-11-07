package com.luciofdasilva.shoppingapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciofdasilva.shoppingapi.converter.DTOConverter;
import com.luciofdasilva.shoppingapi.models.Shop;
import com.luciofdasilva.shoppingapi.repositories.ShopRepository;
import com.luciofdasilva.shoppingclient.dto.ItemDTO;
import com.luciofdasilva.shoppingclient.dto.ProductDTO;
import com.luciofdasilva.shoppingclient.dto.ShopDTO;
import com.luciofdasilva.shoppingclient.dto.ShopReportDTO;
import com.luciofdasilva.shoppingclient.dto.UserDTO;

@Service
public class ShopService {
    @Autowired(required=true)
    private ShopRepository shopRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public List<ShopDTO>getAll() {
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDto) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThanEqual(shopDto.getDate());
        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if(shop.isPresent()) {
            return DTOConverter.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key){

        UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);

        if(userService
        .getUserByCpf(shopDTO.getUserIdentifier(), key) == null) {
            return null;
        }

        if(!validateProducts(shopDTO.getItems())) {
            return null;
        }

        shopDTO.setTotal(shopDTO.getItems()
        .stream()
        .map(x -> x.getPrice())
        .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(new Date());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items){
        for(ItemDTO item : items){
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if (productDTO == null) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public List<ShopDTO> getShopsByFilter(
        Date dataIncio,
        Date dataFim,
        Float valorMinimo
    ){
        List<Shop> shops = shopRepository.getShopByFilters(dataIncio, dataFim, valorMinimo);
        return shops
        .stream()
        .map(DTOConverter::convert)
        .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
        Date dataIncio,
        Date dataFim
    ){
        return shopRepository.getReportByDate(dataIncio, dataFim);
    }
}
