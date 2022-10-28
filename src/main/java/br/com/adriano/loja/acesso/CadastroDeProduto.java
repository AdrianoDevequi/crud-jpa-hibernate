package br.com.adriano.loja.acesso;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.adriano.loja.dao.CategoriaDao;
import br.com.adriano.loja.dao.ProdutoDao;
import br.com.adriano.loja.modelo.Categoria;
import br.com.adriano.loja.modelo.Produto;
import br.com.adriano.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
			
		
		Produto produto = new Produto();
		Categoria categoria = new Categoria();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Scanner leitura = new Scanner(System.in);
		
		System.out.println("Selecione o que deseja fazer: ");
		System.out.println("1 - Cadastrar produto");
		System.out.println("2 - Listar produtos");
		System.out.println("3 - Alterar produto e categoria");
		System.out.println("4 - Excluir produto");
		System.out.println("5 - Alterar produto");
		
		
		int opcao = leitura.nextInt();
		leitura.nextLine();
		
		
		switch (opcao) {

		case 1:
			
			System.out.println("Digite o nome do produto: ");
			String nomeProduto = leitura.nextLine();
			produto.setNome(nomeProduto);
			
			System.out.println("Digite a descrição do produto: ");
			String descricaoProduto = leitura.nextLine();
			produto.setDescricao(descricaoProduto);
			
			System.out.println("Digite o preço do produto: ");
			BigDecimal precoProduto = leitura.nextBigDecimal();
			produto.setPreco(precoProduto);
			leitura.nextLine();
			
			System.out.println("Digite a categoria do produto: ");
			String categoriaProduto = leitura.nextLine();	
			categoria.setNome(categoriaProduto);
			produto.setCategoria(categoria);
			
			ProdutoDao produtoDao = new ProdutoDao(em);
			CategoriaDao categoriaDao = new CategoriaDao(em);
			
			em.getTransaction().begin();
					
			produtoDao.cadastrar(produto);
			categoriaDao.cadastrar(categoria);
			
			em.getTransaction().commit();
			em.close();
			

		break;

		case 2:
			
			ProdutoDao produtoDaoLista = new ProdutoDao(em);
		    List<Produto> todos = produtoDaoLista.buscarTodos();
		        todos.forEach(p2 -> System.out.println("Id: " +p2.getId() + "| Nome: " +p2.getNome() +" | Descrição: " + p2.getDescricao() +" | Valor: " + p2.getPreco()));

		break;

		case 3:
			
			System.out.println("Digite o id do produto que você atualizar: ");
			Integer idUpdateComCategoria = leitura.nextInt();
			
			Categoria categorias = new Categoria();
			Produto p = new Produto();
			
			
			

			Produto produtoEncontrado = em.find(Produto.class, idUpdateComCategoria);
			
			if(idUpdateComCategoria != null) {
				
				em.getTransaction().begin();
				
			
				
				Categoria categoriaEncontrada = produtoEncontrado.getCategoria();
				System.out.println("Encontrada: " + categoriaEncontrada.getNome());
	
					System.out.println("Nome da categoria ");
					String teste = leitura.next();
					categoriaEncontrada.setNome(teste);
					produtoEncontrado.setCategoria(categoriaEncontrada);
					leitura.nextLine();
					
					System.out.println("Digite o nome do produto: ");
					String nomeProdutoUpdate = leitura.nextLine();
					produtoEncontrado.setNome(nomeProdutoUpdate);
					
					System.out.println("Digite a descrição do produto: ");
					String descricaoProdutoUpdate = leitura.nextLine();
					produtoEncontrado.setDescricao(descricaoProdutoUpdate);
					
					System.out.println("Digite o preço do produto: ");
					BigDecimal precoProdutoUpdate = leitura.nextBigDecimal();
					produtoEncontrado.setPreco(precoProdutoUpdate);
					leitura.nextLine();
					
		
					em.flush();


					em.getTransaction().commit();
					em.close();
					
				
			}
			

		break;
		
		case 4:
			ProdutoDao produtoRemoverDao = new ProdutoDao(em);
			
			
			System.out.println("Digite o id do produto que você quer deletar: ");
			Integer idProduto = leitura.nextInt();
			produto.setId(idProduto);
			System.out.println(produto.getId());
			
			em.getTransaction().begin();
			
			produtoRemoverDao.remover(produto);
			
			em.getTransaction().commit();
			em.close();

		break;
		
		case 5:
			
			
			System.out.println("Digite o id do produto que você atualizar: ");
			Integer idUpdate = leitura.nextInt();
			leitura.nextLine();
			
			if(idUpdate != null) {
				
			Produto produtoSemCategoria = em.find(Produto.class, idUpdate);
			
			
			em.getTransaction().begin();
			
			
			System.out.println("Digite o nome do produto: ");
			String nomeProdutoUpdate = leitura.nextLine();
			produtoSemCategoria.setNome(nomeProdutoUpdate);
			
			
			System.out.println("Digite a descrição do produto: ");
			String descricaoProdutoUpdate = leitura.nextLine();
			produtoSemCategoria.setDescricao(descricaoProdutoUpdate);
			
			System.out.println("Digite o preço do produto: ");
			BigDecimal precoProdutoUpdate = leitura.nextBigDecimal();
			produtoSemCategoria.setPreco(precoProdutoUpdate);
			leitura.nextLine();
			
			em.flush();

			em.getTransaction().commit();
			em.close();
			}

		break;

		default:

		
		}
		
		
	}

	
}
