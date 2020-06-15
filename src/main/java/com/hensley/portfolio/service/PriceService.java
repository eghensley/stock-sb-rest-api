package com.hensley.portfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hensley.portfolio.domain.InDayPriceData;
import com.hensley.portfolio.domain.InstrumentData;
import com.hensley.portfolio.domain.PriceData;
import com.hensley.portfolio.enums.PriceTypeEnum;
import com.hensley.portfolio.pojo.dto.price.BasicPriceDto;
import com.hensley.portfolio.pojo.response.GeneralApiResponse;
import com.hensley.portfolio.pojo.response.GetResponse;
import com.hensley.portfolio.repository.InDayPriceRepository;
import com.hensley.portfolio.repository.InstrumentRepository;
import com.hensley.portfolio.repository.PriceRepository;
import com.hensley.portfolio.util.MappingUtils;

@Service
public class PriceService {
	private static final Logger LOG = Logger.getLogger(PriceService.class.toString());
	private static final String NO_PRICE_FOUND = "No price available for id %s";
	private static final String PRICE_EXISTS = "Price already exists for %s";
	
	private final PriceRepository priceRepo;
	private final InstrumentRepository instrumentRepo;
	private final ErrorService errorService;
	private final MappingUtils mappingUtils;
	private final InDayPriceRepository inDayPriceRepo;

	@Autowired
	public PriceService(InDayPriceRepository inDayPriceRepo, InstrumentRepository instrumentRepo, PriceRepository priceRepo, ErrorService errorService, MappingUtils mappingUtils) {
		this.priceRepo = priceRepo;
		this.inDayPriceRepo = inDayPriceRepo;
		this.errorService = errorService;
		this.mappingUtils = mappingUtils;
		this.instrumentRepo = instrumentRepo;
	}
	
	@Transactional
	public GeneralApiResponse addNewPrice(String instrumentOid, BasicPriceDto payload) {
		
		PriceData price;
		String errorString = null;

		try {
			List<String> existPrice = priceRepo.findIfPriceExists(instrumentOid, payload.getTimestamp(), payload.getTypeInt());
			if (CollectionUtils.isEmpty(existPrice)) {
				InstrumentData instrument = instrumentRepo.findById(instrumentOid).get();
				price = (PriceData) mappingUtils.mapToModel(payload, PriceData.class);
				instrument.addPrice(price);
				priceRepo.save(price);
				return new GeneralApiResponse(HttpStatus.ACCEPTED, errorString);
			} else {
				errorString = String.format(PRICE_EXISTS, payload.getTimestamp());
				LOG.log(Level.WARNING, errorString);
				return new GeneralApiResponse(HttpStatus.ACCEPTED, errorString);
			}
			
		} catch (Exception e) {
			errorString = e.getLocalizedMessage();
			LOG.log(Level.SEVERE, errorString, e);
			return new GeneralApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorString);
		}
	}
	
	@Transactional
	public GetResponse getLatestPriceTs(String instrumentOid, PriceTypeEnum type) {
		Integer typeInt;
		if (PriceTypeEnum.INTRA_DAY.equals(type)) {
			typeInt = 0;
		} else {
			typeInt = 1;
		}
		String errorString = null;
		List<String> symbolList = priceRepo.findLatestPrice(instrumentOid, typeInt);
		return new GetResponse(HttpStatus.ACCEPTED, errorString, symbolList);
	}
	
	@Transactional
	public GeneralApiResponse addNewInDayPrice(String instrumentOid, BasicPriceDto payload) {
		
		InDayPriceData price;
		String errorString = null;

		try {
			List<String> existPrice = inDayPriceRepo.findIfPriceExists(instrumentOid, payload.getTimestamp(), payload.getTypeInt());
			if (CollectionUtils.isEmpty(existPrice)) {
				InstrumentData instrument = instrumentRepo.findById(instrumentOid).get();
				price = (InDayPriceData) mappingUtils.mapToModel(payload, InDayPriceData.class);
				instrument.addInDayPrice(price);
				inDayPriceRepo.save(price);
				return new GeneralApiResponse(HttpStatus.ACCEPTED, errorString);
			} else {
				errorString = String.format(PRICE_EXISTS, payload.getTimestamp());
				LOG.log(Level.WARNING, errorString);
				return new GeneralApiResponse(HttpStatus.ACCEPTED, errorString);
			}
			
		} catch (Exception e) {
			errorString = e.getLocalizedMessage();
			LOG.log(Level.SEVERE, errorString, e);
			return new GeneralApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorString);
		}
	}
	
	@Transactional
	public GetResponse getLatestInDayPriceTs(String instrumentOid, PriceTypeEnum type) {
		Integer typeInt;
		if (PriceTypeEnum.INTRA_DAY.equals(type)) {
			typeInt = 0;
		} else {
			typeInt = 1;
		}
		String errorString = null;
		List<String> symbolList = inDayPriceRepo.findLatestPrice(instrumentOid, typeInt);
		return new GetResponse(HttpStatus.ACCEPTED, errorString, symbolList);
	}
}
