package com.schoolmanager.schoolhub.exceptions;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
  private Date timestamp;
  private int status;
  private String path;
  private String error;
  private String message;
}
