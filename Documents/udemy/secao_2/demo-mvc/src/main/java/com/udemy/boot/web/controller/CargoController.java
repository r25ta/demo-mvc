package com.udemy.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udemy.boot.web.domain.Cargo;
import com.udemy.boot.web.domain.Departamento;
import com.udemy.boot.web.service.CargoService;
import com.udemy.boot.web.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	CargoService cargoService;
	
	@Autowired
	DepartamentoService departamentoService;

	@RequestMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@RequestMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos",cargoService.buscarTodos());
		return "/cargo/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	/*
	 * Metodo retorna para página uma lista de objeto DEPARTAMENTO
	 * retorna para formulario um modelattribute conforme anotado
	 * */
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.buscarTodos();
				
	}

	/*
	 * Metodo responsavel para carregar a página de cadastro, para que posteriormente seja editada
	 * */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Cargo cargo = cargoService.buscarPorId(id);
		model.addAttribute("cargo", cargo);
		return "/cargo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Cargo cargo, RedirectAttributes attr) {
		cargoService.editar(cargo);
		attr.addFlashAttribute("success","Cargo editado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
	
		if(cargoService.cargoTemFuncionarios(id)) {
			model.addAttribute("fail", "Cargo não removido. Possui funcionario(s) vinculado(s).");
		}else {
			cargoService.excluir(id);
			model.addAttribute("success","Cargo excluído com sucesso.");
		}
		
		/*
		 * Também poderia ser um redirect:/departamentos/listar
		 * */
		return listar(model);
	}
	
}
