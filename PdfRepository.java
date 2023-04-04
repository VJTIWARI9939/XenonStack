package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.model.PdfFile;

public interface PdfRepository extends JpaRepository<PdfFile, Integer>{

}
