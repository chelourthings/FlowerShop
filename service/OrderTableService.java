package com.flowerShop.ccsdProject.service;

import com.flowerShop.ccsdProject.model.OrderTable;
import com.flowerShop.ccsdProject.repository.OrderTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderTableService {

    @Autowired
    private OrderTableRepository orderTableRepository;

    public OrderTable save(OrderTable orderTable) {
        return orderTableRepository.save(orderTable);
    }

    public OrderTable findById(Long id) {
        return orderTableRepository.findById(id).orElse(null);
    }
}
