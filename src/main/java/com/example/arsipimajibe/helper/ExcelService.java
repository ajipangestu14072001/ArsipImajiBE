package com.example.arsipimajibe.helper;

import com.example.arsipimajibe.models.MasterData;
import com.example.arsipimajibe.repository.MasterProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ExcelService {
    @Autowired
    MasterProductRepository repository;

    public void save(MultipartFile file) {
        try {
            List<MasterData> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<MasterData> tutorials = repository.findAll();

        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
        return in;
    }

    public List<MasterData> getAllTutorials() {
        return repository.findAll();
    }
}
