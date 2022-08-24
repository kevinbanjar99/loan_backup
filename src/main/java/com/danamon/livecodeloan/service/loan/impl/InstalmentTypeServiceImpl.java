package com.danamon.livecodeloan.service.loan.impl;

import com.danamon.livecodeloan.entity.loan_entity.InstalmentType;
import com.danamon.livecodeloan.repo.loan_repo.InstalmentTypeRepository;
import com.danamon.livecodeloan.service.loan.InstalmentTypeService;
import com.danamon.livecodeloan.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstalmentTypeServiceImpl implements InstalmentTypeService {
    InstalmentTypeRepository instalmentTypeRepository;

    @Autowired
    public InstalmentTypeServiceImpl(InstalmentTypeRepository instalmentTypeRepository) {
        this.instalmentTypeRepository = instalmentTypeRepository;
    }

    @Override
    public InstalmentType saveInstalmentType(InstalmentType instalmentType) {
        return instalmentTypeRepository.save(instalmentType);
    }

    @Override
    public List<InstalmentType> getAllInstalmentType() {
        return instalmentTypeRepository.findAll();
    }

    @Override
    public InstalmentType getInstalmentTypeById(String id) {
        if(instalmentTypeRepository.findById(id).isPresent()){
            return instalmentTypeRepository.findById(id).get();
        }
        else throw new DataNotFoundException("Data Not Found");
    }

    @Override
    public InstalmentType updateInstalmentType(InstalmentType instalmentType) {
        if(instalmentTypeRepository.findById(instalmentType.getId()).isPresent()){
            return instalmentTypeRepository.save(instalmentType);
        }
        else throw new DataNotFoundException("DAta not found");
    }

    @Override
    public void deleteInstalmentType(String id) {
        if (instalmentTypeRepository.findById(id).isPresent()){
            instalmentTypeRepository.deleteById(id);
        }
        else throw new DataNotFoundException("Data Not Found");
    }
}
