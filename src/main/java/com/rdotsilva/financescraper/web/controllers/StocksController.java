package com.rdotsilva.financescraper.web.controllers;

import com.rdotsilva.financescraper.web.models.Stock;
import com.rdotsilva.financescraper.web.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/api/v1/stocks")
@Controller
public class StocksController {
    @Autowired
    private StockRepository stockRepository;

    @RequestMapping(value = "/stocks", method = RequestMethod.GET)
    public String getAllStocks(Model model)
    {
        List<Stock> list = stockRepository.findAll();

        model.addAttribute("stocks", list);
        return "stocks";

    }

//    @GetMapping
//    public List<Stock> list() {
//        return stockRepository.findAll();
//    }
//
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public void create(@RequestBody Stock stock) {
//        stockRepository.save(stock);
//    }
//
//    @GetMapping("/{id}")
//    public Stock get(@PathVariable("id") long id) {
//        return stockRepository.getOne(id);
//    }

}
