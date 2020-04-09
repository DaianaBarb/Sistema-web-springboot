package com.mballen.curso.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballen.curso.boot.domain.Cargo;
import com.mballen.curso.boot.domain.Departamento;
import com.mballen.curso.boot.service.CargoService;
import com.mballen.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	@Autowired
	private DepartamentoService departamentoService;
	@Autowired
	 private CargoService service;
	

	@GetMapping("/cadastrar")
public String cadastrar(Cargo cargo) {
	return"cargo/cadastro";
}
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", service.buscarTodos());
		return "cargo/lista";}
	
	@PostMapping("/salvar")
	public String salvar( @Valid Cargo cargo,BindingResult result, RedirectAttributes att) {
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		service.salvar(cargo);
		att.addFlashAttribute("success","Cargo inserido com sucesso");
		return "redirect:/cargos/cadastrar";
	}
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.buscarTodos();
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id")
	Long id, ModelMap model) {
		model.addAttribute("cargo",service.buscarPorId(id));
		return "cargo/cadastro";
	}
	//@valid esta avisando que esta fazendo uma validaçao
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult result,RedirectAttributes att) {
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		service.editar(cargo);
		att.addFlashAttribute("success","Registro atualizado com sucesso");
	return "redirect:/cargos/cadastrar";
	}
	@GetMapping ("excluir/{id}")
 public String excluir(@PathVariable("id") Long id, RedirectAttributes att) {	
	if(service.cargoTemFuncionarios(id)) {
		att.addFlashAttribute("fail","Cargo nao Excluido");	
	}else
		service.excluir(id);
	att.addFlashAttribute("success","Cargo Excluido com sucesso");
		return "redirect:/cargos/listar";	
	}
	
}
