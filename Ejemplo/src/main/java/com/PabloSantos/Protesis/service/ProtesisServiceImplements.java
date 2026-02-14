package com.PabloSantos.Protesis.service;

import com.PabloSantos.Protesis.entity.Protesis;
import com.PabloSantos.Protesis.repository.ProtesisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProtesisServiceImplements implements ProtesisService {

    private final ProtesisRepository protesisRepository;

    public ProtesisServiceImplements(ProtesisRepository protesisRepository){
        this.protesisRepository =protesisRepository;
    }

    @Override
    public List<Protesis> getAllProtesis() {
        return  protesisRepository.findAll();
    }

    @Override
    public Protesis getProtesisById(Integer id) {
        return protesisRepository.findById(id).orElse(null);
    }

    @Override
    public Protesis saveProtesis(Protesis protesis) throws RuntimeException {
        return protesisRepository.save(protesis);
    }

    @Override
    public Protesis updateProtesis(Integer id, Protesis protesis) {
        protesis.setId_protesis(id);
        return protesisRepository.save(protesis);
    }

    @Override
    public void deleteProtesis(Integer id) {
        protesisRepository.deleteById(id);
    }

}
