package com.example.arsipimajibe.controllers;


import com.example.arsipimajibe.models.MasterData;
import com.example.arsipimajibe.payload.ResponseHandler;
import com.example.arsipimajibe.pdf.BarangMasukReport;
import com.example.arsipimajibe.repository.MasterBarangService;
import com.example.arsipimajibe.repository.MasterProductRepository;
import com.example.arsipimajibe.services.MasterBarangImpl;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class MasterProductController {

    private final MasterProductRepository masterProductRepository;

    final MasterBarangService masterBarangService;
    private final ResponseHandler responseHandler = new ResponseHandler();
    @Autowired
    MasterBarangImpl masterBarang;

    public MasterProductController(MasterBarangService masterBarangService, MasterProductRepository masterProductRepository) {
        this.masterBarangService = masterBarangService;
        this.masterProductRepository = masterProductRepository;
    }

    @GetMapping("/barang")
    public ResponseEntity<Object> findAll(
            @RequestParam(name = "namaBarang",
                    required = false,
                    defaultValue = "") String namaBarang) {
        try {
            List<MasterData> data;
            if (StringUtils.hasText(namaBarang)) {
                data = masterProductRepository.findByNamaBarangContaining(namaBarang);
            } else {
                data = masterProductRepository.findAll();
            }
            return responseHandler.generateResponse("OK", HttpStatus.OK, data);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }

    }

    @GetMapping(value = "/barang/kategori")
    public ResponseEntity<Object> findKategori(@RequestParam(name = "kategori",
            required = false,
            defaultValue = "") String kategori) {
        try {
            List<MasterData> data;
            data = masterProductRepository.findByKategoriContaining(kategori);
            return responseHandler.generateResponse("OK", HttpStatus.OK, data);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @PostMapping(value = "/barang")
    public ResponseEntity<Object> Post(@RequestBody MasterData params) {
        try {
            MasterData result = masterBarangService.Post(params);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");

        }
    }

    @PutMapping(value = "/barang/{id}")
    public ResponseEntity<Object> Update(@PathVariable String id, @RequestBody MasterData params) {
        try {
            MasterData result = masterBarangService.Update(params, id);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @GetMapping(value = "/barang/{id}")
    public ResponseEntity<Object> Get(@PathVariable String id) {
        try {
            MasterData result = masterBarangService.Get(id);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @DeleteMapping(value = "/barang/{id}")
    public ResponseEntity<Object> Delete(@PathVariable String id) {
        try {
            String result = masterBarangService.Delete(id);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @GetMapping("/barang/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transaksi_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<MasterData> listUsers = masterBarang.listAll();

        BarangMasukReport exporter = new BarangMasukReport(listUsers);
        exporter.export(response);

    }
}
