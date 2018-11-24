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
import com.udemy.boot.web.domain.Funcionario;
import com.udemy.boot.web.domain.UF;
import com.udemy.boot.web.service.CargoService;
import com.udemy.boot.web.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	CargoService cargoService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@RequestMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@RequestMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionario inserido como sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	
	/*METODO PARA PREENCHIMENTO DO COMBOBOX DE CARGOS*/
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoService.buscarTodos();
	}
	
	/*METODO PARA PREENCHIMENTO DO COMBOBOX DE UFS -- AS UFS ESTÃO EM FORMATO ENUN*/
	@ModelAttribute("ufs")
	public UF[] getUFs() {
		return UF.values();
	}
	
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionário editado com sucesso.");
		return "redirect:/funcionarios/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario",funcionarioService.buscarPorId(id));
		return "funcionario/cadastro";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		funcionarioService.excluir(id);
		model.addAttribute("success", "Funcionário removido com sucesso.");
		return listar(model);
	}
}
