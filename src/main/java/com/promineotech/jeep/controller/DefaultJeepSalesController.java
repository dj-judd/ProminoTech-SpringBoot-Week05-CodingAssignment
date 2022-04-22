package com.promineotech.jeep.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {

  @Autowired
  private JeepSalesService jeepSalesService;
  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.info("model={}, trim={}", model, trim);
    return jeepSalesService.fetchJeeps(model, trim);
  }

  
  @Override
  public String uploadImage(MultipartFile image, Long jeepPK) {
    log.debug("image={},jeepPK={}", image, jeepPK);
    String imageId = jeepSalesService.uploadImage(image, jeepPK);
    String json = "{\"imageId\":\"" + imageId + "\"}";
    
    return json;
  }

}
