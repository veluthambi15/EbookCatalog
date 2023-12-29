package com.example.ebookcatalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ebookcatalog.model.Ebook;
import com.example.ebookcatalog.service.EbookService;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/ebooks")
public class EbookController {

    private static final Logger logger = LogManager.getLogger(EbookController.class);
	
    private final EbookService ebookService;

    public EbookController(EbookService ebookService) {
        this.ebookService = ebookService;
    }

    /*
     * Method: getAllEbooks
     * Description: Retrive all the ebooks details.
     * HTTP Method: GET
     */
    @GetMapping
    public ResponseEntity<List<Ebook>> getAllEbooks() {
    	logger.info("Get All Ebook Endpoint triggered");
    	logger.info("Get All Ebook Endpoint Completed");
        return ResponseEntity.ok(ebookService.getAllEbooks());
    }

    /*
     * Method: getEbookById
     * Description: Retrive a ebook detail using Id.
     * HTTP Method: GET
     */
    @GetMapping("/{ebookId}")
    public ResponseEntity<Ebook> getEbookById(@PathVariable UUID ebookId) {
    	logger.info("Get an Ebook by Id Endpoint triggered");
        Ebook ebook = ebookService.getEbookById(ebookId);
     	logger.info("Get an Ebook by Id Endpoint completed");
        return ebook != null ? ResponseEntity.ok(ebook) : ResponseEntity.notFound().build();
    }

    /*
     * Method: addEbook
     * Description: Adds a new ebook to the catalog.
     * HTTP Method: POST
     */
    @PostMapping
    public ResponseEntity<Ebook> addEbook(@RequestBody Ebook ebook) {
    	logger.info("Add an Ebook Endpoint triggered");
        Ebook addedEbook = ebookService.addEbook(ebook);
        logger.info("Add an Ebook Endpoint completed");
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEbook);
    }

    /*
     * Method: updateEbook
     * Description: Update an existing ebook.
     * HTTP Method: PUT
     */
    @PutMapping("/{ebookId}")
    public ResponseEntity<Ebook> updateEbook(@PathVariable UUID ebookId, @RequestBody Ebook ebook) {
    	logger.info("Update an Ebook using Id Endpoint triggered");
        Ebook updatedEbook = ebookService.updateEbook(ebookId, ebook);
        logger.info("Update an Ebook using Id Endpoint completed");
        return updatedEbook != null ? ResponseEntity.ok(updatedEbook) : ResponseEntity.notFound().build();
    }

    /*
     * Method: deleteEbook
     * Description: Delete an existing ebook.
     * HTTP Method: DELETE
     */
    @DeleteMapping("/{ebookId}")
    public ResponseEntity<Void> deleteEbook(@PathVariable UUID ebookId) {
    	logger.info("Delete an Ebook Endpoint triggered");
        ebookService.deleteEbook(ebookId);
        logger.info("Delete an Ebook Endpoint completed");
        return ResponseEntity.noContent().build();
    }
}
