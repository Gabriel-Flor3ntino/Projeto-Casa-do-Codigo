package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfigurationTest.class, ProductDAO.class, JPAConfiguration.class })
@ActiveProfiles("test")
public class ProductDAOTest {
	
	@Autowired
	ProductDAO productDAO = new ProductDAO();
	
	@Transactional
	@Test
	public void shouldSumAllPricesOfEachBookPerType() {
		
		//salva uma lista de livros impressos
		List<Product> printedBooks = ProductBuilder.newProduct(BookType.PRINTED, BigDecimal.TEN).more(4).buildAll();
		//foreach do Java8, fique à vontade para usar um for normal
	
		printedBooks.stream().forEach(productDAO::save);
		
		//salva uma lista de ebooks
		List<Product> ebooks = ProductBuilder.newProduct(BookType.EBOOK, BigDecimal.TEN).more(4).buildAll();

		//foreach do Java8, fique à vontade para usar um for normal
		ebooks.stream().forEach(productDAO::save);
		
		BigDecimal value =productDAO.sumPricesPerType(BookType.PRINTED);
		Assert.assertEquals(new BigDecimal(50).setScale(2),value);
	}
}