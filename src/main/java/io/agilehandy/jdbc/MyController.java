/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.agilehandy.jdbc;

import io.agilehandy.jdbc.firstDB.repository.FirstRepository;
import io.agilehandy.jdbc.secondDB.repository.SecondRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haytham Mohamed
 **/

@RestController
public class MyController {

	private final FirstRepository firstRepository;
	private final SecondRepository secondRepository;

	public MyController(FirstRepository firstRepository, SecondRepository secondRepository) {
		this.firstRepository = firstRepository;
		this.secondRepository = secondRepository;
	}


	@GetMapping("/firstdb")
	public String firstdb() {
		return allRecords(firstRepository.findAll().iterator());
	}

	@GetMapping("/seconddb")
	public String seconddb() {
		return allRecords(secondRepository.findAll().iterator());
	}

	private String allRecords(Iterator iterator) {
		List<String> records = new ArrayList<>();
		while(iterator.hasNext()) {
			records.add(iterator.next().toString());
		}
		return records.stream().collect(Collectors.joining(","));
	}
}
