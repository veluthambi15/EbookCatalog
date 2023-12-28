package com.example.ebookcatalog.testcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ebookcatalog.controller.EbookController;
import com.example.ebookcatalog.model.Ebook;
import com.example.ebookcatalog.service.EbookService;

class EbookControllerTest {

    @Mock
    private EbookService ebookService;

    @InjectMocks
    private EbookController ebookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Display All Ebooks Details")
    void testGetAllEbooks() {
        Ebook sampleEbook = createSampleEbook();
        List<Ebook> ebookList = Collections.singletonList(sampleEbook);

        Mockito.when(ebookService.getAllEbooks()).thenReturn(ebookList);

        ResponseEntity<List<Ebook>> responseEntity = ebookController.getAllEbooks();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ebookList, responseEntity.getBody());
    }

    @Test
    @DisplayName("Fetch Ebook Details by Id")
    void testGetEbookById() {
        UUID ebookId = UUID.randomUUID();
        Ebook sampleEbook = createSampleEbook();
        sampleEbook.setId(ebookId);

        Mockito.when(ebookService.getEbookById(ebookId)).thenReturn(sampleEbook);

        ResponseEntity<Ebook> responseEntity = ebookController.getEbookById(ebookId);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sampleEbook, responseEntity.getBody());
    }

    @Test
    @DisplayName("Add new Ebook Details")
    void testAddEbook() {
        Ebook requestEbook = createSampleEbook();
        Ebook savedEbook = createSampleEbook();
        savedEbook.setId(UUID.randomUUID());

        Mockito.when(ebookService.addEbook(requestEbook)).thenReturn(savedEbook);

        ResponseEntity<Ebook> responseEntity = ebookController.addEbook(requestEbook);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedEbook, responseEntity.getBody());
    }

    @Test
    @DisplayName("Update Ebook Details Using Id")
    void testUpdateEbook() {
        UUID ebookId = UUID.randomUUID();
        Ebook requestEbook = createSampleEbook();
        Ebook updatedEbook = createSampleEbook();
        updatedEbook.setId(ebookId);

        Mockito.when(ebookService.updateEbook(ebookId, requestEbook)).thenReturn(updatedEbook);

        ResponseEntity<Ebook> responseEntity = ebookController.updateEbook(ebookId, requestEbook);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedEbook, responseEntity.getBody());
    }

    @Test
    @DisplayName("Delete an Ebook Detail")
    void testDeleteEbook() {
        UUID ebookId = UUID.randomUUID();

        ResponseEntity<Void> responseEntity = ebookController.deleteEbook(ebookId);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
    
    
    private Ebook createSampleEbook() {
        Ebook ebook = new Ebook();
        ebook.setAuthor("Veluthambi");
        ebook.setTitle("Finnish Language");
        ebook.setFormat("Pdf");
        return ebook;
    }
}
