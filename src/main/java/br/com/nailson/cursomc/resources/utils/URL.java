package br.com.nailson.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static  List<Integer> decodeIntList(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<Integer>();
		for(int i =0; i<vet.length; i++) {
			try {
				list.add(Integer.parseInt(vet[i]));
			} catch (NumberFormatException e) {
				System.out.println("Falha ao converter lista :"+ vet[i]);
				continue;
			}
		}
//		return Arrays.asList(s.split(",")).stream().map(x-> Integer.parseInt(x)).collect(Collectors.toList());
		return list;
	}
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Falha ao decodificar string :"+ s);
			return "";
		}
	}
}
