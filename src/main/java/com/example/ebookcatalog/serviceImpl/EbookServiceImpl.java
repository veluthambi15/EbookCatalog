package com.example.ebookcatalog.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.ebookcatalog.model.Ebook;
import com.example.ebookcatalog.service.EbookService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EbookServiceImpl implements EbookService {
	
	private static final Logger logger = LogManager.getLogger(EbookService.class);

    private final List<Ebook> ebooks = new ArrayList<>();

    /*
     * Method to get all the Ebooks details
     * return all the Ebooks
     */
    @Override
    public List<Ebook> getAllEbooks() {
    	logger.info("Getting all the Ebook details");
        return ebooks;
    }

    /*
     * Method to get the particular Ebook detail by using Id
     * ebookId - EBookId
     * return Ebook
     */
    @Override
    public Ebook getEbookById(UUID ebookId) {
    	logger.info("Getting an Ebook detail using the Id : " + ebookId);
        return ebooks.stream()
                .filter(e -> e.getId().equals(ebookId))
                .findFirst()
                .orElse(null);
    }

    /*
     * Method to Add a new Ebook
     * ebook - Ebook Details
     * return Ebook
     */
    @Override
    public Ebook addEbook(Ebook ebook) {
        ebook.setId(UUID.randomUUID());
        ebooks.add(ebook);
        logger.info("Added the given Ebook details");
        return ebook;
    }

    /*
     * Method to Update a Ebook using Id
     * ebookId - Existing EbookId 
     * updatedEbook - New Ebook details
     * return updated Ebook
     */
    @Override
    public Ebook updateEbook(UUID ebookId, Ebook updatedEbook) {
    	logger.info("Update an Existing Ebook detail using the Id : " + ebookId);
        Ebook existingEbook = getEbookById(ebookId);
        if (existingEbook != null) {
            existingEbook.setAuthor(updatedEbook.getAuthor());
            existingEbook.setTitle(updatedEbook.getTitle());
            existingEbook.setFormat(updatedEbook.getFormat());
        }
        return existingEbook;
    }

    /*
     * Method to remove an Ebook
     * ebookId - EbookId to be deleted
     */
	@Override
	public void deleteEbook(UUID ebookId) {
		logger.info("Delete an Existing Ebook details using Id : " + ebookId);
		ebooks.removeIf(e -> e.getId().equals(ebookId));
	}
    
}