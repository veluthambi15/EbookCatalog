package com.example.ebookcatalog.testservice;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.ebookcatalog.model.Ebook;
import com.example.ebookcatalog.service.EbookService;
import com.example.ebookcatalog.serviceImpl.EbookServiceImpl;

/*
 *Class to perform Unit Tests
 */
class EbookServiceTest {

    private EbookService ebookService;

    @BeforeEach
    void setUp() {
        ebookService = new EbookServiceImpl();
    }

    @Test
    @DisplayName("Display All Ebooks Details")
    void testGetAllEbooks() {
        List<Ebook> ebooks = ebookService.getAllEbooks();
        Assertions.assertNotNull(ebooks);
        Assertions.assertEquals(0, ebooks.size());
    }

    @Test
    @DisplayName("Add new Ebook Details")
    void testAddEbook() {
        Ebook ebookToAdd = new Ebook();
        ebookToAdd.setAuthor("Veluthambi");
        ebookToAdd.setTitle("Finnish Language");
        ebookToAdd.setFormat("Pdf");

        Ebook addedEbook = ebookService.addEbook(ebookToAdd);

        Assertions.assertNotNull(addedEbook);
        Assertions.assertNotNull(addedEbook.getId());
        Assertions.assertEquals(ebookToAdd.getAuthor(), addedEbook.getAuthor());
        Assertions.assertEquals(ebookToAdd.getTitle(), addedEbook.getTitle());
        Assertions.assertEquals(ebookToAdd.getFormat(), addedEbook.getFormat());

        List<Ebook> ebooks = ebookService.getAllEbooks();
        Assertions.assertEquals(1, ebooks.size());
    }

    @Test
    @DisplayName("Fetch Ebook Details by Id")
    void testGetEbookById() {
        Ebook ebookToAdd = new Ebook();
        ebookToAdd.setAuthor("Veluthambi");
        ebookToAdd.setTitle("Finnish Language");
        ebookToAdd.setFormat("Online");

        Ebook addedEbook = ebookService.addEbook(ebookToAdd);
        UUID ebookId = addedEbook.getId();

        Ebook retrievedEbook = ebookService.getEbookById(ebookId);

        Assertions.assertNotNull(retrievedEbook);
        Assertions.assertEquals(addedEbook, retrievedEbook);
    }

    @Test
    @DisplayName("Update Ebook Details Using Id")
    void testUpdateEbook() {
        Ebook ebookToAdd = new Ebook();
        ebookToAdd.setAuthor("Praveen");
        ebookToAdd.setTitle("English Grammer");
        ebookToAdd.setFormat("Pdf");

        Ebook addedEbook = ebookService.addEbook(ebookToAdd);
        UUID ebookId = addedEbook.getId();

        Ebook updatedEbookInfo = new Ebook();
        updatedEbookInfo.setAuthor("Veluthambi");
        updatedEbookInfo.setTitle("Finnish Language");
        updatedEbookInfo.setFormat("Online");

        Ebook updatedEbook = ebookService.updateEbook(ebookId, updatedEbookInfo);

        Assertions.assertNotNull(updatedEbook);
        Assertions.assertEquals(ebookId, updatedEbook.getId());
        Assertions.assertEquals(updatedEbookInfo.getAuthor(), updatedEbook.getAuthor());
        Assertions.assertEquals(updatedEbookInfo.getTitle(), updatedEbook.getTitle());
        Assertions.assertEquals(updatedEbookInfo.getFormat(), updatedEbook.getFormat());
    }

    @Test
    @DisplayName("Delete an Ebook Detail")
    void testDeleteEbook() {
        Ebook ebookToAdd = new Ebook();
        ebookToAdd.setAuthor("Veluthambi");
        ebookToAdd.setTitle("Finnish Language");
        ebookToAdd.setFormat("Online");

        Ebook addedEbook = ebookService.addEbook(ebookToAdd);
        UUID ebookId = addedEbook.getId();

        ebookService.deleteEbook(ebookId);

        List<Ebook> ebooks = ebookService.getAllEbooks();
        Assertions.assertEquals(0, ebooks.size());
    }
}
