package net.malvanav.dto;

import lombok.Data;

@Data
public class FilterRequest {
  private int page = 0;
  private int pageSize = 10;
  private String sortBy;
  private String sortDir;
  private String name;
  private String department;
}
