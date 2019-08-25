package com.bolbas.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Runner {

	private static HashMap<String, Integer> hashMap = new HashMap<>();
	private static final int FIRST = 1;

	public static void main(String[] args) {
		File file = new File("C:\\Windows\\System32");
		File[] files = file.listFiles();

		printFiles(files);

		Map<String, Integer> hm1 = sortByValue(hashMap);

		hm1.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() + " " + entry.getValue());
		});
		System.out.println("//************************************************************************//");

		List<Entry<String, Integer>> indexedList = new ArrayList<Map.Entry<String, Integer>>(hm1.entrySet());

		int length = indexedList.size();
		length -= 5;
		for (int i = length; i < indexedList.size(); i++) {
			Map.Entry<String, Integer> entry = indexedList.get(i);
			System.out.println(entry.getKey() + " " + entry.getValue());
		}

	}

	// function to sort hashmap by values
	public static LinkedHashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();

		/*
		 * int count = 5;
		 * 
		 * for (int i = 0; i < count; i++) { Map.Entry<String, Integer> aa =
		 * list.get(i); temp.put(aa.getKey(), aa.getValue()); }
		 */

		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());

		}
		return temp;
	}

	private static void printFiles(File[] files) {
		if (files != null) {
			for (File file : files) {
				if (file != null) {
					if (file.isDirectory()) { // проверка является ли файл дерикторией
						File[] filesInner = file.listFiles();
						printFiles(filesInner);
					}

					int y = file.getName().lastIndexOf(".");
					if (y > 0) {
						String temp = null;
						try {
							temp = file.getName().substring(y);
						} catch (StringIndexOutOfBoundsException e) {

						}
						if (hashMap.containsKey(temp)) {
							hashMap.put(temp, hashMap.get(temp) + 1);
						} else {
							hashMap.put(temp, FIRST);
						}
					}
				}
			}
		}
	}

}
