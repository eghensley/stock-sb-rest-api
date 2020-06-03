package com.hensley.portfolio.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hensley.portfolio.domain.InstrumentData;
import com.hensley.portfolio.pojo.dto.instrument.BasicInstrumentDto;
import com.hensley.portfolio.pojo.response.GeneralApiResponse;
import com.hensley.portfolio.pojo.response.GetResponse;
import com.hensley.portfolio.repository.InstrumentRepository;
import com.hensley.portfolio.util.MappingUtils;

@Service
public class InstrumentService {
	private static final Logger LOG = Logger.getLogger(InstrumentService.class.toString());
	private static final String NO_INSTRUMENT_FOUND = "No instrument available for id %s";
	private static final String INSTRUMENT_EXISTS = "Instrument id %s already exists";

	private final InstrumentRepository instrumentRepo;
	private final ErrorService errorService;
	private final MappingUtils mappingUtils;

	@Autowired
	public InstrumentService(InstrumentRepository instrumentRepo, ErrorService errorService, MappingUtils mappingUtils) {
		this.instrumentRepo = instrumentRepo;
		this.errorService = errorService;
		this.mappingUtils = mappingUtils;
	}
	
	@Transactional
	public GetResponse getSymbols() {
		String errorString = null;
		List<String> symbolList = instrumentRepo.findAllInstrumentSymbols();
		return new GetResponse(HttpStatus.ACCEPTED, errorString, symbolList);
	}
	
	@Transactional
	public GeneralApiResponse addNewInstrument(BasicInstrumentDto payload) {
		Optional<InstrumentData> instDataOpt;
		InstrumentData instrument;
		String errorString = null;

		try {
			instDataOpt = instrumentRepo.findByRobinhoodId(payload.getRobinhoodId());
			if (instDataOpt.isPresent()) {
				errorString = String.format(INSTRUMENT_EXISTS, payload.getRobinhoodId());
				LOG.log(Level.WARNING, errorString);
				return new GeneralApiResponse(HttpStatus.ACCEPTED, errorString);
			} else {				
				instrument = (InstrumentData) mappingUtils.mapToModel(payload, InstrumentData.class);
				instrumentRepo.save(instrument);
				return new GeneralApiResponse(HttpStatus.ACCEPTED, errorString);
			}
		} catch (Exception e) {
			errorString = e.getLocalizedMessage();
			LOG.log(Level.SEVERE, errorString, e);
			return new GeneralApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorString);
		}
	}
	
	@Transactional
	public GetResponse getInstrumentDto(String robinhoodId, String symbol) {
		Optional<InstrumentData> instDataOpt;
		InstrumentData instData;
		BasicInstrumentDto instDto;
		String errorString = null;

		try {
			if (robinhoodId != null) {
				instDataOpt = instrumentRepo.findByRobinhoodId(robinhoodId);
			} else {
				instDataOpt = instrumentRepo.findBySymbol(symbol);
				
			}
			if (instDataOpt.isPresent()) {
				instData = instDataOpt.get();
				instDto = (BasicInstrumentDto) mappingUtils.mapToDto(instData, BasicInstrumentDto.class);
				return new GetResponse(HttpStatus.ACCEPTED, errorString, instDto);
			} else {
				errorString = String.format(NO_INSTRUMENT_FOUND, robinhoodId);
				LOG.log(Level.WARNING, errorString);
				return new GetResponse(HttpStatus.ACCEPTED, errorString, null);
			}
		} catch (Exception e) {
			errorString = e.getLocalizedMessage();
			LOG.log(Level.SEVERE, errorString, e);
			return new GetResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorString, null);
		}
	}
}
