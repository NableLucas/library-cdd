package com.nable.library.newbook;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewBookController {

	@PostMapping(value = "/book")
	public void execute(@RequestBody @Valid NewBookRequest request) {
	}
}
