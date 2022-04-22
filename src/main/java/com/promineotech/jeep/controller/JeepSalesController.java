package com.promineotech.jeep.controller;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.jeep.Constants;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/jeeps")
@OpenAPIDefinition(info = @Info(title = "Jeep Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local Server.")})
public interface JeepSalesController {
  
  /**
   * 
   */


  // @formatter:off
  @Operation(
      summary = "Returns a list of Jeeps",
      description = "Returns a list of Jeeps given an optional model and/or trim",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "A list of Jeeps is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Jeep.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No Jeeps were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occured",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "model",
              allowEmptyValue = false,
              required = false,
              description = "The model name (ex: 'WRANGLER')"),
          @Parameter(
              name = "trim",
              allowEmptyValue = false,
              required = false,
              description = "The trim level (ex: 'SPORT')")
      }
  )
// HOMEWORK WAY OF DOING IT
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Jeep> fetchJeeps(
      @RequestParam JeepModel model,
      // Video Add Start
      @Length(max = Constants.TRIM_MAX_LENGTH)
      @Pattern(regexp = "[\\w\\s]*")
      // Video Add End
      @RequestParam String trim);
  // @formatter:on  
  
  // You should write the OpenAPI documentation.
  
  @PostMapping("/{jeepPK}/image")
  @ResponseStatus(code = HttpStatus.CREATED)
  String uploadImage(@RequestParam("image") MultipartFile image,
      @PathVariable Long jeepPK);
}

//  VIDEO WAY OF DOING IT
//  @GetMapping
//  @ResponseStatus(code = HttpStatus.OK)
//  List<Jeep> fetchJeeps(
//      @RequestParam(required = false) JeepModel model,
//      @RequestParam(required = false) String trim);