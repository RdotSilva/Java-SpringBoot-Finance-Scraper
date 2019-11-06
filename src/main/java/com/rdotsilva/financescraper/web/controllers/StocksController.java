package com.rdotsilva.financescraper.web.controllers;

import com.rdotsilva.financescraper.web.models.Stock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {
    @GetMapping
    public List<Stock> list() {
        List<Stock> stocks = new ArrayList<>();
        return stocks;
    }

    @GetMapping("/{id}")
    public Stock get(@PathVariable("id") long id) {
        return new Stock();
    }

}
