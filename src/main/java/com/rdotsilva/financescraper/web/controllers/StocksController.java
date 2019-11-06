package com.rdotsilva.financescraper.web.controllers;

import com.rdotsilva.financescraper.web.models.Stock;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Stock stock) {
    }

    @GetMapping("/{id}")
    public Stock get(@PathVariable("id") long id) {
        return new Stock();
    }

}
