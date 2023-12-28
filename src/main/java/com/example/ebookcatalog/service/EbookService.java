package com.example.ebookcatalog.service;

import java.util.List;
import java.util.UUID;

import com.example.ebookcatalog.model.Ebook;

public interface EbookService {
    List<Ebook> getAllEbooks();

    Ebook getEbookById(UUID ebookId);

    Ebook addEbook(Ebook ebook);

    Ebook updateEbook(UUID ebookId, Ebook ebook);

    void deleteEbook(UUID ebookId);
}
