package com.jihoon.board.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface FileService {
  public String upload(MultipartFile file);

  public Resource getFile(String fileName);
}
