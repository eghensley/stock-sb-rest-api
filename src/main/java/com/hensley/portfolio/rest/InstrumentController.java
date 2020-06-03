package com.hensley.portfolio.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hensley.portfolio.pojo.dto.instrument.BasicInstrumentDto;
import com.hensley.portfolio.pojo.response.GeneralApiResponse;
import com.hensley.portfolio.pojo.response.GetResponse;
import com.hensley.portfolio.service.InstrumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/instrument")
@Api(value="Instrument System")
public class InstrumentController {

	@Autowired
	InstrumentService instrumentService;
	
	@ApiOperation(value = "Fetch instrument details by Id")
	@GetMapping("robinhoodId/{rhId}")
	public ResponseEntity<GetResponse> getInstrumentById(@PathVariable("rhId") String rhId) {
		GetResponse response = instrumentService.getInstrumentDto(rhId, null); 
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@ApiOperation(value = "Fetch instrument details by Symbol")
	@GetMapping("symbol/{symbol}")
	public ResponseEntity<GetResponse> getInstrumentBySymbol(@PathVariable("symbol") String symbol) {
		GetResponse response = instrumentService.getInstrumentDto(null, symbol); 
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@ApiOperation(value = "Add new instrument")
	@PostMapping("add")
	public ResponseEntity<GeneralApiResponse> addNewInstrument(@RequestBody BasicInstrumentDto request) {
		GeneralApiResponse response = instrumentService.addNewInstrument(request);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@ApiOperation(value = "Fetch all instrument symbols")
	@GetMapping("symbol/all")
	public ResponseEntity<GetResponse> getAllSymbols() {
		GetResponse response = instrumentService.getSymbols(); 
		return new ResponseEntity<>(response, response.getStatus());
	}
}
