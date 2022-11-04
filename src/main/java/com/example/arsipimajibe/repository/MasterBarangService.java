package com.example.arsipimajibe.repository;

import com.example.arsipimajibe.models.MasterData;
import org.springframework.stereotype.Component;


@Component
public interface MasterBarangService {
    MasterData Post(MasterData params);

    MasterData Get(String id);

    MasterData Update(MasterData params, String id);

    String Delete(String id);
}
