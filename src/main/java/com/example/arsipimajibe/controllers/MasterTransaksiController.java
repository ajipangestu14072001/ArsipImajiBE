package com.example.arsipimajibe.controllers;

import com.example.arsipimajibe.models.MasterTransaksi;
import com.example.arsipimajibe.payload.ResponseHandler;
import com.example.arsipimajibe.pdf.TransaksiExport;
import com.example.arsipimajibe.repository.MasterTransaksiRepository;
import com.example.arsipimajibe.repository.MasterTransaksiService;
import com.example.arsipimajibe.services.MasterTransaksiImpl;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/transactions")
public class MasterTransaksiController {

    final MasterTransaksiRepository masterTransaksiRepository;

    private final ResponseHandler responseHandler = new ResponseHandler();
    final
    MasterTransaksiService masterTransaksiService;

    @Autowired
    MasterTransaksiImpl masterTransaksiImpl;


    public MasterTransaksiController(MasterTransaksiService masterTransaksiService, MasterTransaksiRepository masterTransaksiRepository) {
        this.masterTransaksiService = masterTransaksiService;
        this.masterTransaksiRepository = masterTransaksiRepository;
    }

    @PostMapping(value = "/buy")
    public ResponseEntity<Object> buyProduct(@RequestBody MasterTransaksi params) {
        try {
            MasterTransaksi result = masterTransaksiService.Post(params);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @GetMapping(value = "/buy/idUser")
    public ResponseEntity<Object> findKategori(@RequestParam(name = "idUser",
            required = false,
            defaultValue = "") Long idUser) {
        try {
            List<MasterTransaksi> data;
            data = masterTransaksiRepository.findByIdUser(idUser);
            return responseHandler.generateResponse("OK", HttpStatus.OK, data);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @GetMapping("/transaksi/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transaksi_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<MasterTransaksi> listUsers = masterTransaksiImpl.listAll();

        TransaksiExport exporter = new TransaksiExport(listUsers);
        exporter.export(response);

    }

}
