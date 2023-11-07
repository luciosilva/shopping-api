package com.luciofdasilva.shoppingapi.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luciofdasilva.shoppingapi.models.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>, ReportRepository {

    public List<Shop> findAllByUserIdentifier(String userIdentifiers);

    public List<Shop> findAllByTotalGreaterThan(Float total);

    List<Shop> findAllByDateGreaterThanEqual(Date date);
}
