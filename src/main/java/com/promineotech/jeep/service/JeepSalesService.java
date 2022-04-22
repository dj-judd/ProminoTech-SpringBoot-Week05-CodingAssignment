package com.promineotech.jeep.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

public interface JeepSalesService {

  List<Jeep> fetchJeeps(JeepModel model, String trim);

  /**
   * @param image
   * @param jeepPK
   * @return
   */
  String uploadImage(MultipartFile image, Long jeepPK);

}
