package com.udemy.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udemy.boot.web.domain.Departamento;
import com.udemy.boot.web.service.DepartamentoService;



@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	DepartamentoService service;
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	/*
	 * ModelMap retorna para página uma lista de departamentos
	 * é importante que o identificador da MAP seja mantido na pagina, para que BIND fique perfeito.
	 * */
	@RequestMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos",service.buscarTodos());
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		service.salvar(departamento);
		attr.addFlashAttribute("success","Departamento inserido com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}

	/*
	 * Metodo responsavel para carregar a página de cadastro, para que posteriormente seja editada
	 * */
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Departamento depto = service.buscarPorId(id);
		model.addAttribute("departamento", depto);
		return "/departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Departamento departamento, RedirectAttributes attr) {
		service.editar(departamento);
		attr.addFlashAttribute("success","Departamento editado com sucesso.");
		return "redirect:/departamentos/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
	
		if(service.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
		}else {
			service.excluir(id);
			model.addAttribute("success","Departamento excluído com sucesso.");
		}
		
		/*
		 * Também poderia ser um redirect:/departamentos/listar
		 * */
		return listar(model);
	}
}
