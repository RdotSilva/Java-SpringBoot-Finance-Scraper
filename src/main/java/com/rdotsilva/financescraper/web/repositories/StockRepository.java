package com.rdotsilva.financescraper.web.repositories;

import com.rdotsilva.financescraper.web.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
