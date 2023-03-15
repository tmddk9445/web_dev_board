package com.jihoon.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jihoon.board.common.constant.ApiPattern;
import com.jihoon.board.service.FileService;

@RestController
@RequestMapping(ApiPattern.FILE)
public class FileController {

  @Autowired FileService fileService;

  private final String UPLOAD = "/upload";
  private final String GET_FILE = "/{fileName}";

  @PostMapping(UPLOAD)
  public String upload(@RequestParam("file") MultipartFile file) {
    String response = fileService.upload(file);
    return response;
  }
  
  @GetMapping(value=GET_FILE, produces={MediaType.ALL_VALUE})
  public Resource getFile(@PathVariable("fileName") String fileName) {
    Resource response = fileService.getFile(fileName);
    return response;
  }
}
