package com.simple.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.simple.model.Coche;
import com.simple.repository.*;

@RestController
public class HDosController {

	@Autowired
	CochesRepository cochesServicio;

	@RequestMapping(value = "/coches/{id}", method = RequestMethod.GET)
	Coche getCoches(@PathVariable Integer id) {

		return cochesServicio.findById(id).get();
	}

	@RequestMapping(value = "/coches/all", method = RequestMethod.GET)
	List<Coche> getAllCoches() {

		return cochesServicio.findAll();
	}

}
