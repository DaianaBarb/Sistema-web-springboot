package com.mballen.curso.boot.web.controller;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballen.curso.boot.domain.Cargo;
import com.mballen.curso.boot.domain.Funcionario;
import com.mballen.curso.boot.domain.UF;
import com.mballen.curso.boot.service.CargoService;
import com.mballen.curso.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioService service;
	@Autowired
private	CargoService cargoservice;
	
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
	
		model.addAttribute("funcionario", service.buscarPorId(id));
		return"funcionario/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar( @Valid Funcionario funcionario,BindingResult result,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return"funcionario/cadastro";
		}
		service.editar(funcionario);
		attr.addFlashAttribute("success","Funcionario editado com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("success", "Excluido com Sucesso!");
		return "redirect:/funcionarios/listar";
		
	}
	
	
	@GetMapping("/cadastrar")
public String cadastrar(Funcionario funcionario) {
	return"funcionario/cadastro";
}
	@GetMapping("/listar")
public String listar(ModelMap model) {
		model.addAttribute("funcionarios", service.buscarTodos());
	return "funcionario/lista";
}
	
	@PostMapping("/salvar")
	public String Salvar(@Valid Funcionario funcionario,BindingResult result, RedirectAttributes att){
		if(result.hasErrors()) {
			return"funcionario/cadastro";
		}
		service.salvar(funcionario);
		att.addFlashAttribute("success","salvo com sucesso");
		return "redirect:/funcionarios/cadastrar";
	}
	//ATENÇAO NESTA ANOTAÇÃO E DIFERNETE DAS DEMAIS
	@ModelAttribute("cargos")
	public List<Cargo> getCargos(){
		return cargoservice.buscarTodos();
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome,ModelMap model) {
		model.addAttribute("funcionarios",service.buscarPorNome(nome));
		
		
		return "funcionario/lista";
	}
	@GetMapping("/buscar/cargo")
		public String getCargo(@RequestParam("id")Long id, ModelMap model) {
model.addAttribute("funcionarios",service.buscarPorCargo(id));
		
		return"funcionario/lista";
		
	}
	@GetMapping("/buscar/data")
	public String getData(@RequestParam("entrada") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate entrada,
			@RequestParam("saida") @DateTimeFormat( iso=DateTimeFormat.ISO.DATE) LocalDate saida, ModelMap model) {
		model.addAttribute("funcionarios", service.buscarPorDatas(entrada, saida));
		return "funcionario/lista";
	}
	
	@ModelAttribute("ufs")
	public UF[]getUFs(){
		return UF.values();
	}
}
