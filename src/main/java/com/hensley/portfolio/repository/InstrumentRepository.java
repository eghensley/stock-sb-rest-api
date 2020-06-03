package com.hensley.portfolio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hensley.portfolio.domain.InstrumentData;

@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentData, String> {
	Optional<InstrumentData> findByRobinhoodId(String robinhoodId);
	Optional<InstrumentData> findBySymbol(String symbol);

	@Query(value = "select f.symbol from stocks.instrument f", nativeQuery = true)
	List<String> findAllInstrumentSymbols();
}