package com.luciofdasilva.shoppingapi.repositories;

import java.util.Date;
import java.util.List;


import com.luciofdasilva.shoppingapi.models.Shop;
import com.luciofdasilva.shoppingclient.dto.ShopReportDTO;

public interface ReportRepository {
    public List<Shop> getShopByFilters(
        Date dataInicio,
        Date dataFim,
        Float valorMinimo);

    public ShopReportDTO getReportByDate(
        Date dataInicio,
        Date dataFim);
}
