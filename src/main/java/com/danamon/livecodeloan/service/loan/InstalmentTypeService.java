package com.danamon.livecodeloan.service.loan;

import com.danamon.livecodeloan.entity.loan_entity.Customer;
import com.danamon.livecodeloan.entity.loan_entity.InstalmentType;

import java.util.List;

public interface InstalmentTypeService {
    InstalmentType saveInstalmentType(InstalmentType instalmentType);
    List<InstalmentType> getAllInstalmentType();
    InstalmentType getInstalmentTypeById(String id);
    InstalmentType updateInstalmentType(InstalmentType instalmentType);
    void deleteInstalmentType(String id);
}
