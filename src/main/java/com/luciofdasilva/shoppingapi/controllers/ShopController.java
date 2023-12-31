package com.luciofdasilva.shoppingapi.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.luciofdasilva.shoppingapi.services.ShopService;
import com.luciofdasilva.shoppingclient.dto.ShopDTO;
import com.luciofdasilva.shoppingclient.dto.ShopReportDTO;

import jakarta.validation.Valid;

@RestController
public class ShopController {
    @Autowired
    private ShopService shopService;

/*
    @GetMapping("/")
    public ResponseEntity<String> index() {
        return new ResponseEntity<String>("It's working...!", HttpStatus.OK);
    }
*/

    @GetMapping("/shopping")
    public List<ShopDTO> getShop() {
        List<ShopDTO> produtos = shopService.getAll();
        return produtos;
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        List<ShopDTO> produtos = shopService.getByUser(userIdentifier);
        return produtos;
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        List<ShopDTO> produtos = shopService.getByDate(shopDTO);
        return produtos;
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(
    @RequestHeader(name = "key", required = true) String key,
    @RequestBody ShopDTO shopDTO){
        return shopService.save(shopDTO,key);
    }

    @GetMapping("/shopping/search")
	public List<ShopDTO> getShopsByFilter(
		@RequestParam(name = "dataInicio", required=true)
		@DateTimeFormat(pattern = "dd/MM/yyyy")
        Date dataInicio,

		@RequestParam(name = "dataFim", required=false)
		@DateTimeFormat(pattern = "dd/MM/yyyy")
        Date dataFim,

		@RequestParam(name = "valorMinimo", required=false)
        Float valorMinimo)
    {
    	return shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
	}

	@GetMapping("/shopping/report")
	public ShopReportDTO getReportByDate(
			@RequestParam(name = "dataInicio", required=true)
			@DateTimeFormat(pattern = "dd/MM/yyyy")
            Date dataInicio,

			@RequestParam(name = "dataFim", required=true)
			@DateTimeFormat(pattern = "dd/MM/yyyy")
            Date dataFim)
    {
	    return shopService.getReportByDate(dataInicio, dataFim);
	}
}
