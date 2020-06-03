package com.hensley.portfolio.repository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hensley.portfolio.domain.PriceData;

@Repository
public interface PriceRepository extends JpaRepository<PriceData, String>{
	@Query(value = "select p.oid from stocks.price p where p.instrument_oid=:instrumentIdx and p.timestamp =:ts and p.type=:type", nativeQuery = true)
	List<String> findIfPriceExists(@Param("instrumentIdx") String instrumentIdx, @Param("ts") Calendar ts, @Param("type") Integer type);

	@Query(value = "select p.timestamp from stocks.price p where p.instrument_oid=:instrumentIdx and p.type=:type order by p.timestamp desc limit 1", nativeQuery = true)
	List<String> findLatestPrice(@Param("instrumentIdx") String instrumentIdx, @Param("type") Integer type);

}
