package com.jboyCorp.course.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String str) {
		
		try {
			return URLDecoder.decode(str, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
	
	public static List<Long> decodeIntList(String str){
		
		String [] vet = str.split(",");
		List<Long> list = new ArrayList<>();
		for(int i=0; i< vet.length; i++) {
			list.add(Long.parseLong(vet[i]));

		}
		return list;
		
		//Outro possibilidade de method auxiliar;
		//return Arrays.asList(str.split(",")).stream().map(x -> Long.parseLong(x)).collect(Collectors.toList());
	}

}
