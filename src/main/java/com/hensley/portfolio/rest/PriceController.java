package com.hensley.portfolio.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hensley.portfolio.enums.PriceTypeEnum;
import com.hensley.portfolio.pojo.dto.price.BasicPriceDto;
import com.hensley.portfolio.pojo.response.GeneralApiResponse;
import com.hensley.portfolio.pojo.response.GetResponse;
import com.hensley.portfolio.service.PriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("rest/price")
@Api(value = "Price System")
public class PriceController {

	@Autowired
	PriceService priceService;

	@ApiOperation(value = "Add new intra day price")
	@PostMapping("instrument/{instrumentOid}/add")
	public ResponseEntity<GeneralApiResponse> addNewIntraDayPrice(@PathVariable("instrumentOid") String instrumentOid,
			@RequestBody BasicPriceDto request) {
		GeneralApiResponse response = priceService.addNewPrice(instrumentOid, request);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@ApiOperation(value = "Fetch latest saved intra day price for instrument")
	@GetMapping("instrument/{instrumentOid}/type/{type}/latest")
	public ResponseEntity<GetResponse> getLatestIntraDayPriceBySymbol(@PathVariable("instrumentOid") String instrumentOid, @PathVariable("type") PriceTypeEnum type) {
		GetResponse response = priceService.getLatestPriceTs(instrumentOid, type);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@ApiOperation(value = "Add new inner day price")
	@PostMapping("instrument/{instrumentOid}/add/inday")
	public ResponseEntity<GeneralApiResponse> addNewInnerDayPrice(@PathVariable("instrumentOid") String instrumentOid,
			@RequestBody BasicPriceDto request) {
		GeneralApiResponse response = priceService.addNewPrice(instrumentOid, request);
		return new ResponseEntity<>(response, response.getStatus());
	}
	
	@ApiOperation(value = "Fetch latest saved inner day price for instrument")
	@GetMapping("instrument/{instrumentOid}/type/{type}/latest/inday")
	public ResponseEntity<GetResponse> getLatestInnerDayPriceBySymbol(@PathVariable("instrumentOid") String instrumentOid, @PathVariable("type") PriceTypeEnum type) {
		GetResponse response = priceService.getLatestPriceTs(instrumentOid, type);
		return new ResponseEntity<>(response, response.getStatus());
	}

}
