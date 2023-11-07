package com.luciofdasilva.shoppingapi.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.luciofdasilva.shoppingclient.dto.ProductDTO;
import com.luciofdasilva.shoppingclient.exception.UserNotFoundException;

@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081/product/}")
    private String productApiURL;

    public ProductDTO getProductByIdentifier(String productIdentifier){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = productApiURL + productIdentifier;

            ResponseEntity<ProductDTO> responseEntity = 
            restTemplate.getForEntity(url, ProductDTO.class);

            return responseEntity.getBody();
        }
        catch (HttpClientErrorException.NotFound e)
        {
            throw new UserNotFoundException();
        }
    }
}
