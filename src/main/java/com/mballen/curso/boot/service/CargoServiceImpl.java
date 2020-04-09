package com.mballen.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mballen.curso.boot.dao.CargoDao;
import com.mballen.curso.boot.domain.Cargo;
//abre transação padrao
@Service @Transactional(readOnly = false)//vai informar ao Spring que a transação não é apenas de leitura, ou seja, ela deve ser usada tanto em métodos de consulta como de escrita.
public class CargoServiceImpl implements CargoService {
	
	@Autowired
	private CargoDao dao;

	@Override
	public void salvar(Cargo cargo) {
		dao.save(cargo);		
	}

	@Override
	public void editar(Cargo cargo) {
		dao.update(cargo);		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);		
	}
// n abre transação
	@Override @Transactional(readOnly = true)//Esse comportamento vai fazer com que não seja aberta uma transação quando o método de consulta for executado, liberando assim, o acesso a tabela em questão para outras operações. Essa prática melhora a performance do banco de dados.
	public Cargo buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override @Transactional(readOnly = true)
	public List<Cargo> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	public boolean cargoTemFuncionarios(Long id) {
		if(buscarPorId(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}

}
