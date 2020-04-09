package com.mballen.curso.boot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballen.curso.boot.domain.Departamento;
import com.mballen.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	@Autowired
	private DepartamentoService service;
	
  @GetMapping("/cadastrar")
	public String cadastrar( Departamento departamento) {
		return "departamento/cadastro";
	
}
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		//A Model é uma interface baseada em métodos com chave e valor. Serve para renderizar views com atribuição de parâmetros que serão enviados para a página. Essa interface possui várias implementações e o Spring é quem vai definir qual será usada no processo.

       //A ModelMap é uma classe do tipo java.util.Map para atribuição de valores via chave e valor e renderização de views. A diferença entre a Model é que ModelMap é que a última já tem seus próprios métodos implementados.

		model.addAttribute("departamentos", service.buscarTodos());
		return "departamento/lista";
	}
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento,BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return"departamento/cadastro";
		}
		service.salvar(departamento);
		attr.addFlashAttribute("success","Departamento salvo com sucesso");
		return "redirect:/departamentos/cadastrar";
		
	}
	// recupera o id e manda pra outra pagina ao clicar em editar
	@GetMapping("/editar/{id}") //path
	public  String preEditar(@PathVariable("id") Long id,ModelMap model)
	{ // recuperando o path id
		model.addAttribute("departamento",service.buscarPorId(id)); //cria uma variavel e coloca a busca dentro ddela para ser enviado para a outra pagina
	 return "departamento/cadastro";
	}
	// edita e volta pra pagina de cadastro
	@PostMapping("/editar")
	public String editar(@Valid  Departamento departamento,BindingResult result, RedirectAttributes attr)
	{ 
		if(result.hasErrors()) {
			return"departamento/cadastro";
		}
		service.editar(departamento);
	attr.addFlashAttribute("success", "Departamento editado com sucesso");
	return "redirect:/departamentos/cadastrar";
		}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable ("id") Long id, ModelMap model ) {
		if(service.departamentoTemCargos(id)) {
		   model.addAttribute("fail", "Departamento nao removido.Possui Cargo(s)");
		}else {
			service.excluir(id);
			model.addAttribute("success", "Departamento removido com sucesso");
		}
	return listar(model);
}
	
}
