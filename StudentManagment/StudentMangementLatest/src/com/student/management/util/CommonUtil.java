package com.student.management.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {

	public static  String readJsonResponse(HttpServletRequest request) throws IOException {
		  StringBuffer buffer=null;
		  String line;
		  BufferedReader reader ;
		  
		  buffer =new StringBuffer();
		  reader=request.getReader();
		  while((line=reader.readLine())!=null) {
			  buffer.append(line);
		  }
		return buffer.toString();
	}
}
