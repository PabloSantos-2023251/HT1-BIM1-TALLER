package com.PabloSantos.Protesis.service;

import com.PabloSantos.Protesis.entity.Protesis;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProtesisService {

    List<Protesis> getAllProtesis();
    Protesis getProtesisById(Integer id);
    Protesis saveProtesis(Protesis protesis) throws RuntimeException;
    Protesis updateProtesis(Integer id, Protesis protesis);
    void deleteProtesis(Integer id);

}
