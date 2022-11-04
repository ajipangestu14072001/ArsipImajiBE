package com.example.arsipimajibe.controllers;


import com.example.arsipimajibe.models.Keranjang;
import com.example.arsipimajibe.payload.ResponseHandler;
import com.example.arsipimajibe.repository.MasterKeranjangRepository;
import com.example.arsipimajibe.repository.MasterKeranjangService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/cart")
public class KeranjangController {

    private final ResponseHandler responseHandler = new ResponseHandler();
    final
    MasterKeranjangService masterKeranjangService;

    final
    MasterKeranjangRepository masterKeranjangRepository;

    public KeranjangController(MasterKeranjangService masterKeranjangService, MasterKeranjangRepository masterKeranjangRepository) {
        this.masterKeranjangService = masterKeranjangService;
        this.masterKeranjangRepository = masterKeranjangRepository;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> Post(@RequestBody Keranjang params) {
        try {
            Keranjang result = masterKeranjangService.Post(params);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");

        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> Delete(@PathVariable Integer id) {
        try {
            String result = masterKeranjangService.Delete(id);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }

    @GetMapping(value = "/barang/{id}")
    public ResponseEntity<Object> Get(@PathVariable Integer id) {
        try {
            Keranjang result = masterKeranjangService.Get(id);
            return responseHandler.generateResponse("OK", HttpStatus.OK, result);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }
    @GetMapping(value = "/barang")
    public ResponseEntity<Object> findKategori(@RequestParam(name = "idUsers",
            required = false,
            defaultValue = "") String idUsers) {
        try {
            List<Keranjang> data;
            data = masterKeranjangRepository.findByIdUsersContaining(idUsers);
            return responseHandler.generateResponse("OK", HttpStatus.OK, data);
        } catch (Exception e) {
            return responseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "[]");
        }
    }
}
